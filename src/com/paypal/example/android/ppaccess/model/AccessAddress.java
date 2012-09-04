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

package com.paypal.example.android.ppaccess.model;

import java.io.Serializable;

/**
 * This class is used to represent an adress object which may be received with
 * PayPal Access. Please notice that not each field must contain data as this
 * depends on your application's settings.
 * 
 * @author tmesserschmidt@paypal.com
 * 
 */
public class AccessAddress implements Serializable {
	public static final String	STATE				= "state";
	public static final String	COUNTRY				= "country";
	public static final String	STREET_1			= "street1";
	public static final String	STREET_2			= "street2";
	public static final String	CITY				= "city";
	public static final String	ZIP					= "zip";

	private static final long	serialVersionUID	= -7935662138761118089L;

	private String				state;
	private String				country;
	private String				street1;
	private String				street2;
	private String				city;
	private String				zip;

	/**
	 * Creates a new empty instance of an address.
	 */
	public AccessAddress() {
	}

	/**
	 * Creates a new full initialized instance of an address.
	 * 
	 * @param state
	 * @param country
	 * @param street1
	 * @param street2
	 * @param city
	 * @param zip
	 */
	public AccessAddress(String state, String country, String street1,
			String street2, String city, String zip) {
		this.state = state;
		this.country = country;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.zip = zip;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the street1
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * @param street1
	 *            the street1 to set
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * @param street2
	 *            the street2 to set
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccessAddress ["
				+ (state != null ? "state=" + state + ", " : "")
				+ (country != null ? "country=" + country + ", " : "")
				+ (street1 != null ? "street1=" + street1 + ", " : "")
				+ (street2 != null ? "street2=" + street2 + ", " : "")
				+ (city != null ? "city=" + city + ", " : "")
				+ (zip != null ? "zip=" + zip : "") + "]";
	}
}
