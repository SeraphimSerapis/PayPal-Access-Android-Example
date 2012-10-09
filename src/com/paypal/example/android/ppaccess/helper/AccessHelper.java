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

package com.paypal.example.android.ppaccess.helper;

/**
 * This class is being used as an abstraction for AccessHelpers. It is being
 * implemented by the OAuth-Helper & the OpenID Connect-Helper.
 * 
 * @author tmesserschmidt@paypal.com
 * 
 */
public abstract class AccessHelper {
	public static final String		DATA_PROFILE		= "profile";

	protected static final String	URL_REDIRECT		= "http://access.com/index.php";

	protected static final String	PARAM_CLIENT_ID		= "client_id=";
	protected static final String	PARAM_CLIENT_SECRET	= "client_secret=";
	protected static final String	PARAM_REDIRECT_URI	= "redirect_uri=";
	protected static final String	PARAM_SCOPE			= "scope=";
	protected static final String	PARAM_SCHEMA		= "schema=";
	protected static final String	PARAM_RESPONSE_TYPE	= "response_type=";
	protected static final String	PARAM_CODE			= "code=";
	protected static final String	PARAM_ACCESS_TOKEN	= "access_token=";
	protected static final String	PARAM_OAUTH_TOKEN	= "oauth_token=";
	protected static final String	PARAM_GRANT_TYPE	= "grant_type=authorization_code";
	protected static final String	VALUE_RESPONSE_TYPE	= "code";

	protected static String			valueClientId		= null;
	protected static String			valueClientSecret	= null;

	public enum TYPE {
		OAUTH, OPENID
	};

	/**
	 * Not going to be exposed.
	 * 
	 * @param clientId
	 * @param clientSecret
	 */
	public AccessHelper(final String clientId, final String clientSecret) {
		valueClientId = clientId;
		valueClientSecret = clientSecret;
	}

	/**
	 * Returns the application's authorization URL for PayPal Access.
	 * 
	 * @return the authorization URL as {@link String}
	 */
	public abstract String getAuthUrl();

	/**
	 * Returns the Access Token url.
	 * 
	 * @return the Access Token url
	 */
	public abstract String getTokenServiceUrl();

	/**
	 * Creates the needed parameters to get the Authorization Token.
	 * 
	 * @param code
	 *            the code from the Token Service
	 * @return the needed parameters
	 */
	public abstract String getTokenServiceParameters(final String code);

	/**
	 * Returns the URL for requesting profile information.
	 * 
	 * @param accessToken
	 * @return the profile url including the Access Token
	 */
	public abstract String getProfileUrl(final String accessToken);

	/**
	 * Returns the URL which can be converted to an URI to extract the access
	 * code
	 * 
	 * @return the callback URL
	 */
	public abstract String getAccessCodeUrl();
}
