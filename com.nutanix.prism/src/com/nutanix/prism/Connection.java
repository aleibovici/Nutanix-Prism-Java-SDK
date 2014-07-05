package com.nutanix.prism;

import java.io.IOException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Connection {
	protected static String SERVICE_ADDRESS;
	private static final String SERVICE_URL = "/PrismGateway/services";
	protected static final String SERVICE_PORT = "9440";
	protected static final String SERVICE_VM_GET = "/PrismGateway/services/rest/v1/vms/";
	protected static final String SERVICE_VIRTUAL_DISKS_GET = "/PrismGateway/services/rest/v1/virtual_disks/";
	protected static final String SERVICE_HOST_GET = "/PrismGateway/services/rest/v1/hosts/";
	protected static CloseableHttpClient httpclient;

	/**
	 * Connect to PRISM API
	 * 
	 * @param hostname
	 * @param Credential
	 */
	public void Connect(String hostname, String Credential) {

		SERVICE_ADDRESS = hostname;
		String URI = "https://" + SERVICE_ADDRESS + ":" + SERVICE_PORT
				+ SERVICE_URL;
		String Header = "Basic "
				+ com.nutanix.prism.Functions.EncodeBase64(Credential);
		httpclient = HttpClients
				.custom()
				.setSSLSocketFactory(
						com.nutanix.prism.Functions.verifyHostname()).build();
		com.nutanix.prism.Functions.HttpClientExecute(URI, Header);

	}

	/**
	 * Disconnect from PRISM API
	 */
	public void Disconnect() {

		try {
			httpclient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
