package com.nutanix.prism;

import org.json.simple.JSONObject;

public class VirtualDisks {
	/**
	 * GetVirtualDisks()
	 * 
	 * @return
	 */
	public JSONObject GetVirtualDisks() {

		String URI = "https://" + com.nutanix.prism.Connection.SERVICE_ADDRESS
				+ ":" + com.nutanix.prism.Connection.SERVICE_PORT
				+ com.nutanix.prism.Connection.SERVICE_VIRTUAL_DISKS_GET;

		return com.nutanix.prism.Functions
				.StringToJSON(com.nutanix.prism.Functions.HttpClientExecute(
						URI, null));
	}
}
