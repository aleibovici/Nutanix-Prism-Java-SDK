package com.nutanix.prism;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

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

public class Functions {
	/**
	 * Encode String to Base64
	 * 
	 * @param string
	 * @return
	 */
	protected static final String EncodeBase64(String string) {

		byte[] encodedBytes = Base64.encodeBase64(string.getBytes());

		return new String(encodedBytes);
	}

	/**
	 * String To JSON
	 * 
	 * @param string
	 * @return
	 */
	protected final static JSONObject StringToJSON(String string) {

		JSONParser parser = new JSONParser();
		Object obj = null;

		try {
			obj = parser.parse(string);
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}

		return (JSONObject) obj;
	}

	/**
	 * Execute Http Call
	 * 
	 * @param URI
	 * @return
	 */
	protected static final String HttpClientExecute(final String URI,
			final String Header) {

		HttpGet HttpRequest = new HttpGet(URI);
		HttpRequest.addHeader("Authorization", Header);
		CloseableHttpResponse response = null;

		try {
			response = com.nutanix.prism.Connection.httpclient
					.execute(HttpRequest);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		HttpEntity e = response.getEntity();
		String entity = null;

		try {
			entity = EntityUtils.toString(e);
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return entity;
	}

	/**
	 * Ignore SelfSigned Certificate
	 * 
	 * @return
	 */
	protected static final SSLConnectionSocketFactory verifyHostname() {

		SSLContextBuilder builder = new SSLContextBuilder();

		try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}

		SSLConnectionSocketFactory sslsf = null;

		try {
			sslsf = new SSLConnectionSocketFactory(builder.build(),
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sslsf;
	}
}
