/*
 * Copyright 2012 PayPal.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paypal.example.android.ppaccess;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.paypal.example.android.ppaccess.http.AsyncConnection;
import com.paypal.example.android.ppaccess.http.AsyncConnection.AsyncConnectionListener;
import com.paypal.example.android.ppaccess.model.AccessAddress;
import com.paypal.example.android.ppaccess.model.AccessProfile;

/**
 * This {@link Activity} is used to enable the user to login at PayPal and
 * accept the usage of his data in the embedding application.<br/>
 * Therefore the LoginActivity uses a {@link WebView} that loads the
 * {@link AccessHelper}'s urls.
 * 
 * @author tmesserschmidt@paypal.com
 * 
 */
public class LoginActivity extends Activity {
	/*
	 * The id and secret are used to identify your application. Those
	 * credentials can be obtained at https://devportal.x.com/
	 */
	private static final String	CLIENT_ID		= "";
	private static final String	CLIENT_SECRET	= "";

	private static final String	IDENTITY		= "identity";

	private WebView				webView;
	private ProgressDialog		progress;
	private AccessHelper		helper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		webView = new WebView(this);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new PPWebViewClient());

		setContentView(webView);

		helper = AccessHelper.init(CLIENT_ID, CLIENT_SECRET);

		progress = ProgressDialog.show(LoginActivity.this,
				getString(R.string.progress_loading_title),
				getString(R.string.progress_loading_msg));

		webView.loadUrl(helper.getAuthUrl());
	}

	private class PPWebViewClient extends WebViewClient {

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			if (progress != null && progress.isShowing()) {
				progress.dismiss();
				progress = null;
			}
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (url.startsWith("http://access.com/index.php?code=")) {
				getAccessToken(url);
				return true;
			}
			return super.shouldOverrideUrlLoading(view, url);
		}

		private void getAccessToken(String url) {
			final Uri uri = Uri.parse(url);
			final String code = uri.getQueryParameter("code");
			final String urlParams = helper.getTokenServiceParameters(code);
			final String urlString = helper.getTokenServiceUrl();

			new AsyncConnection(new AsyncConnectionListener() {
				public void connectionDone(String result) {
					try {
						final JSONObject object = new JSONObject(result);
						final String accessToken = object
								.getString("access_token");

						if (accessToken != null && !accessToken.equals("")) {
							getProfile(accessToken);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}).execute(AsyncConnection.METHOD_POST, urlString, urlParams);
		}

		private void getProfile(String accessToken) {
			final String urlString = helper.getProfileUrl(accessToken);

			new AsyncConnection(new AsyncConnectionListener() {
				public void connectionDone(String result) {
					try {
						final JSONObject profile = new JSONObject(result);
						final JSONObject identity = profile
								.getJSONObject(IDENTITY);

						final String firstName = getField(identity,
								AccessProfile.FIRST_NAME);
						final String lastName = getField(identity,
								AccessProfile.LAST_NAME);
						final String fullName = getField(identity,
								AccessProfile.FULL_NAME);
						final String status = getField(identity,
								AccessProfile.STATUS);
						final String userId = getField(identity,
								AccessProfile.USER_ID);
						final String telephoneNumber = getField(identity,
								AccessProfile.TELEPHONE_NUMBER);
						final String gender = getField(identity,
								AccessProfile.GENDER);
						final String dob = getField(identity, AccessProfile.DOB);
						final String language = getField(identity,
								AccessProfile.LANGUAGE);
						final String timezone = getField(identity,
								AccessProfile.TIMEZONE);

						final List<String> emailList = new ArrayList<String>();
						JSONArray emails = null;
						if (identity.has(AccessProfile.EMAILS)) {
							emails = identity
									.getJSONArray(AccessProfile.EMAILS);
							for (int i = 0; i < emails.length(); i++) {
								emailList.add(emails.getString(i));
							}
						}

						final List<AccessAddress> addressList = new ArrayList<AccessAddress>();
						JSONArray addresses = null;
						if (identity.has(AccessProfile.ADDRESSES)) {
							addresses = identity
									.getJSONArray(AccessProfile.ADDRESSES);
							for (int i = 0; i < addresses.length(); i++) {
								addressList.add(createAddress(addresses
										.getJSONObject(i)));
							}
						}

						final AccessProfile accessProfile = new AccessProfile(
								firstName, lastName, fullName, status, userId,
								telephoneNumber, gender, dob, timezone,
								language, emailList, addressList);

						setResult(RESULT_OK, new Intent().putExtra(
								AccessHelper.DATA_PROFILE, accessProfile));
						finish();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}).execute(AsyncConnection.METHOD_GET, urlString);
		}
	}

	/**
	 * Creates a new {@link AccessAddress}.
	 * 
	 * @param addressField
	 *            the returned {@link JSONObject}
	 * @return the address
	 */
	private static AccessAddress createAddress(JSONObject addressField) {
		final String state = getField(addressField, AccessAddress.STATE);
		final String street1 = getField(addressField, AccessAddress.STREET_1);
		final String street2 = getField(addressField, AccessAddress.STREET_2);
		final String zip = getField(addressField, AccessAddress.ZIP);
		final String city = getField(addressField, AccessAddress.CITY);
		final String country = getField(addressField, AccessAddress.COUNTRY);
		return new AccessAddress(state, country, street1, street2, city, zip);
	}

	/**
	 * Returns the defined field as a {@link String}
	 * 
	 * @param object
	 *            the {@link JSONObject} to get the value from
	 * @param name
	 *            the field's name
	 * @return the field's value if existant, <code>null</code> otherwise
	 */
	private static String getField(JSONObject object, String name) {
		if (object.has(name)) {
			try {
				return object.getString(name);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
