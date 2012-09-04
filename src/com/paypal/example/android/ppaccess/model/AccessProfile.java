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
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to represent the user's PayPal profile which can be
 * acquired by using PayPal Access. Please notice that not each field must
 * contain data as this depends on your application's settings.
 * 
 * @author tmesserschmidt@paypal.com
 * 
 */
public class AccessProfile implements Serializable {
	public static final String	FIRST_NAME			= "firstName";
	public static final String	LAST_NAME			= "lastName";
	public static final String	FULL_NAME			= "fullName";
	public static final String	STATUS				= "status";
	public static final String	USER_ID				= "userId";
	public static final String	TELEPHONE_NUMBER	= "telephoneNumber";
	public static final String	EMAILS				= "emails";
	public static final String	ADDRESSES			= "addresses";
	public static final String	TIMEZONE			= "timezone";
	public static final String	DOB					= "dob";
	public static final String	GENDER				= "gender";
	public static final String	LANGUAGE			= "language";

	private static final long	serialVersionUID	= -1341013285242669632L;

	private String				firstName;
	private String				lastName;
	private String				fullName;
	private String				status;
	private String				userId;
	private String				telephoneNumber;
	private String				gender;
	private String				dob;
	private String				timezone;
	private String				language;
	private List<String>		emails;
	private List<AccessAddress>	addresses;

	/**
	 * Creates a new empty instance of a profile.
	 */
	public AccessProfile() {
		emails = new ArrayList<String>();
		addresses = new ArrayList<AccessAddress>();
	}

	/**
	 * Creates a new full initialized instance of a profile.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param fullName
	 * @param status
	 * @param userId
	 * @param telephoneNumber
	 * @param gender
	 * @param dob
	 * @param timezone
	 * @param language
	 * @param emails
	 */
	public AccessProfile(String firstName, String lastName, String fullName,
			String status, String userId, String telephoneNumber,
			String gender, String dob, String timezone, String language,
			List<String> emails, List<AccessAddress> addresses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.status = status;
		this.userId = userId;
		this.telephoneNumber = telephoneNumber;
		this.gender = gender;
		this.dob = dob;
		this.timezone = timezone;
		this.language = language;
		this.emails = emails;
		this.addresses = addresses;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the telephoneNumber
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param telephoneNumber
	 *            the telephoneNumber to set
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the timezone
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * @param timezone
	 *            the timezone to set
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the emails
	 */
	public List<String> getEmails() {
		return emails;
	}

	/**
	 * @param emails
	 *            the emails to set
	 */
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	/**
	 * @return the addresses
	 */
	public List<AccessAddress> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(List<AccessAddress> addresses) {
		this.addresses = addresses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccessProfile ["
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (fullName != null ? "fullName=" + fullName + ", " : "")
				+ (status != null ? "status=" + status + ", " : "")
				+ (userId != null ? "userId=" + userId + ", " : "")
				+ (telephoneNumber != null ? "telephoneNumber="
						+ telephoneNumber + ", " : "")
				+ (gender != null ? "gender=" + gender + ", " : "")
				+ (dob != null ? "dob=" + dob + ", " : "")
				+ (timezone != null ? "timezone=" + timezone + ", " : "")
				+ (language != null ? "language=" + language + ", " : "")
				+ (emails != null ? "emails=" + emails + ", " : "")
				+ (addresses != null ? "addresses=" + addresses : "") + "]";
	}
}
