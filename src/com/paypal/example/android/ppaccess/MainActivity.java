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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.example.android.ppaccess.helper.AccessHelper;
import com.paypal.example.android.ppaccess.helper.AccessHelperOAuth;

/**
 * The application's main {@link Activity}.<br/>
 * It is used to login and present the received data.
 * 
 * @author tmesserschmidt@paypal.com
 * 
 */
public class MainActivity extends Activity {
	private TextView	profileText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		profileText = (TextView) findViewById(R.id.main_text_profile);
	}

	public void onOAuthClick(View view) {
		login(AccessHelper.TYPE.OAUTH);
	}

	public void onOpenIDClick(View view) {
		login(AccessHelper.TYPE.OPENID);
	}

	private void login(AccessHelper.TYPE type) {
		final Intent loginIntent = new Intent(this, LoginActivity.class);
		loginIntent.putExtra(LoginActivity.TYPE, type.toString());
		startActivityForResult(loginIntent, R.id.LOGIN_REQUEST);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == R.id.LOGIN_REQUEST && resultCode == RESULT_OK) {
			Toast.makeText(getApplicationContext(), R.string.toast_login_ok,
					Toast.LENGTH_LONG).show();

			// Set the raw json representation as content of the TextView
			profileText.setText(data
					.getStringExtra(AccessHelperOAuth.DATA_PROFILE));
		} else {
			Toast.makeText(getApplicationContext(),
					R.string.toast_login_failed, Toast.LENGTH_LONG).show();
		}
	}
}
