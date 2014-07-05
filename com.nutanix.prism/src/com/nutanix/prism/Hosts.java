package com.nutanix.prism;

import org.json.simple.JSONObject;

public class Hosts {
	/**
	 * GetHosts()
	 * 
	 * @return
	 */
	public JSONObject GetHosts() {

		String URI = "https://" + com.nutanix.prism.Connection.SERVICE_ADDRESS
				+ ":" + com.nutanix.prism.Connection.SERVICE_PORT
				+ com.nutanix.prism.Connection.SERVICE_HOST_GET;

		return com.nutanix.prism.Functions
				.StringToJSON(com.nutanix.prism.Functions.HttpClientExecute(
						URI, null));
	}
}
