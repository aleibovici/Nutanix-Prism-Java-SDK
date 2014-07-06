/**
 * com.nutanix.prism;
 */
package com.nutanix.prism;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 
 * @author andreleibovici
 * @version 1.0
 */
public class Functions implements Cloneable {
	/**
	 * Encode String to Base64
	 * 
	 * @param string
	
	 * @return String */
	protected static final String encodeBase64(String string) {

		final byte[] encodedBytes = Base64.encodeBase64(string.getBytes());

		return new String(encodedBytes);
	}

	/**
	 * String To JSON
	 * 
	 * @param string
	
	 * @return JSONObject */
	protected static final Map<?, ?> stringToJSON(String string) {

		final JSONParser parser = new JSONParser();
		Object obj = null;

		try {
			obj = parser.parse(string);
		} catch (final org.json.simple.parser.ParseException e) {
			return null;
		}

		return (JSONObject) obj;
	}

	/**
	 * Execute HTTP Call
	 * 
	 * @param uri
	 * @param authHeader
	 *            String
	
	 * @return String */
	protected static final String httpClientExecute(final String uri,
			final String authHeader) {

		final HttpGet httpRequest = new HttpGet(uri);
		CloseableHttpResponse response = null;

		httpRequest.addHeader("Authorization", authHeader);

		try {
			response = com.nutanix.prism.Connection.HttpClient
					.execute(httpRequest);
		} catch (final ClientProtocolException e1) {
			return null;
		} catch (final IOException e1) {
			return null;
		}

		final HttpEntity e = response.getEntity();
		String entity = null;

		try {
			entity = EntityUtils.toString(e);
		} catch (final ParseException e1) {
			return null;
		} catch (final IOException e1) {
			return null;
		}

		return entity;
	}

	/**
	 * Ignore SelfSigned Certificate
	 * 
	
	 * @return SSLConnectionSocketFactory */
	protected static final SSLConnectionSocketFactory verifyHostname() {

		final SSLContextBuilder builder = new SSLContextBuilder();
		SSLConnectionSocketFactory sslsf = null;

		try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		} catch (final NoSuchAlgorithmException e) {
			return null;
		} catch (final KeyStoreException e) {
			return null;
		}

		try {
			sslsf = new SSLConnectionSocketFactory(builder.build(),
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		} catch (final KeyManagementException e) {
			return null;
		} catch (final NoSuchAlgorithmException e) {
			return null;
		}

		return sslsf;
	}

	/**
	 * Method clone.
	 * @return Functions
	 * @throws CloneNotSupportedException
	 */
	@Override
	public Functions clone() throws CloneNotSupportedException {
		return (Functions) super.clone();
	}
}
