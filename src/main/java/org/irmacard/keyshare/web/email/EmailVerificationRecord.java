package org.irmacard.keyshare.web.email;

import org.irmacard.keyshare.web.users.Users;
import org.javalite.activejdbc.Model;

public class EmailVerificationRecord extends Model {
	private static final String EMAIL_FIELD = "email";
	private static final String TOKEN_FIELD = "token";
	private static final String TIMEOUT_FIELD = "timeout";
	private static final String VALIDITY_FIELD = "validity";
	private static final String TIME_CREATED_FIELD = "time_created";
	private static final String TIME_VERIFIED_FIELD = "time_verified";

	public static final int DEFAULT_TIMEOUT = 60 * 60 * 24 * 3;
	public static final int DEFAULT_VALIDITY = 60 * 60 * 24 * 3;

	public EmailVerificationRecord() {}

	public EmailVerificationRecord(String email) {
		this(email, DEFAULT_TIMEOUT, DEFAULT_VALIDITY);
	}

	public EmailVerificationRecord(String email, int timeout) {
		this(email, timeout, DEFAULT_VALIDITY);
	}

	public EmailVerificationRecord(String email, int timeout, int validity) {
		setString(EMAIL_FIELD, email);
		setString(TOKEN_FIELD, Users.randomSessionToken());
		setInteger(TIMEOUT_FIELD, timeout);
		setInteger(VALIDITY_FIELD, validity);
		setLong(TIME_CREATED_FIELD, System.currentTimeMillis()/1000);
		saveIt();
	}

	public String getEmail() {
		return getString(EMAIL_FIELD);
	}

	public String getToken() {
		return getString(TOKEN_FIELD);
	}

	public int getValidity() {
		return getInteger(VALIDITY_FIELD);
	}

	public void setVerified() {
		setLong(TIME_VERIFIED_FIELD, System.currentTimeMillis()/1000);
		saveIt();
	}
}
