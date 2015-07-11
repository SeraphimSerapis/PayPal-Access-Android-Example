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

package com.paypal.example.android.ppaccess.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.protocol.HTTP;

import android.os.AsyncTask;

/**
 * This {@link AsyncTask} was designed to handle POST and GET operations and
 * return the results to a defined listener.
 * 
 * @author tmesserschmidt@paypal.com
 * 
 */
public class AsyncConnection extends AsyncTask<String, Void, String> {
	public static final String		METHOD_GET	= "GET";
	public static final String		METHOD_POST	= "POST";

	private AsyncConnectionListener	listener;

	public AsyncConnection(AsyncConnectionListener listener) {
		this.listener = listener;
	}

	@Override
	public String doInBackground(String... params) {
		final String method = params[0];
		final String urlString = params[1];

		final StringBuilder builder = new StringBuilder();

		try {
			final URL url = new URL(urlString);

			final HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
			conn.setRequestMethod(method);

			if (method.equals(METHOD_POST)) {
				final String urlParams = params[2];
				conn.setRequestProperty(HTTP.CONTENT_LEN,
						"" + Integer.toString(urlParams.getBytes().length));
				System.out.println(urlParams);
				// Send request
				final DataOutputStream wr = new DataOutputStream(
						conn.getOutputStream());
				wr.writeBytes(urlParams);
				wr.flush();
				wr.close();
			}

			// Get Response
			final InputStream is = conn.getInputStream();
			final BufferedReader rd = new BufferedReader(new InputStreamReader(
					is));

			String line;
			while ((line = rd.readLine()) != null) {
				builder.append(line);
			}
			rd.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	@Override
	public void onPostExecute(String result) {
		listener.connectionDone(result);
	}

	public interface AsyncConnectionListener {
		public void connectionDone(String result);
	}
}
