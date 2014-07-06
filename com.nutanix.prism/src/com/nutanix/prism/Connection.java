/**
 * com.nutanix.prism;
 */
package com.nutanix.prism;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 
 * @author andreleibovici
 * 
 * @version $Revision: 1.0 $
 */
public class Connection implements Cloneable {
	/**
	 * Field defaults.
	 */
	private static final ResourceBundle URIDEFAULTS = ResourceBundle
			.getBundle("DefaultValuesBundle");
	/**
	 * Base hostname or IPAddress
	 */
	protected static String SERVICE_ADDRESS;
	/**
	 * Default HttpClient
	 */
	protected static CloseableHttpClient HttpClient;
	/**
	 * Base URI for API validation
	 */
	private static final String SERVICE_URL = URIDEFAULTS
			.getString("SERVICE_URL");
	/**
	 * Default API port
	 */
	protected static final String SERVICE_PORT = URIDEFAULTS
			.getString("SERVICE_PORT");
	/**
	 * Default vm URI
	 */
	protected static final String SERVICE_VM_GET = URIDEFAULTS
			.getString("SERVICE_VM_GET");
	/**
	 * Default virtual disks URI
	 */
	protected static final String SERVICE_VIRTUAL_DISKS_GET = URIDEFAULTS
			.getString("SERVICE_VIRTUAL_DISKS_GET");
	/**
	 * Default host URI
	 */
	protected static final String SERVICE_HOST_GET = URIDEFAULTS
			.getString("SERVICE_HOST_GET");

	/**
	 * Connect to PRISM API
	 * 
	 * @param hostname
	 * @param credential
	 */
	public void connect(String hostname, String credential) {

		String header = null;
		String uri = null;

		SERVICE_ADDRESS = hostname;

		uri = MessageFormat.format("https://{0}:{1}{2}", SERVICE_ADDRESS,
				SERVICE_PORT, SERVICE_URL);
		header = MessageFormat.format("Basic {0}",
				com.nutanix.prism.Functions.encodeBase64(credential));
		HttpClient = HttpClients
				.custom()
				.setSSLSocketFactory(
						com.nutanix.prism.Functions.verifyHostname()).build();
		com.nutanix.prism.Functions.httpClientExecute(uri, header);

	}

	/**
	 * Disconnect from PRISM API
	 */
	public void disconnect() {

		try {
			HttpClient.close();
		} catch (final IOException e) {
			return;
		}

	}

	/**
	 * Method clone.
	 * @return Connection
	 * @throws CloneNotSupportedException
	 */
	@Override
	public Connection clone() throws CloneNotSupportedException {
		return (Connection) super.clone();
	}

}
