package com.nutanix.prism;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Vm {
	/**
	 * GetVms()
	 * 
	 * @return
	 */
	public JSONObject GetVms() {

		String URI = "https://" + com.nutanix.prism.Connection.SERVICE_ADDRESS
				+ ":" + com.nutanix.prism.Connection.SERVICE_PORT
				+ com.nutanix.prism.Connection.SERVICE_VM_GET;

		return com.nutanix.prism.Functions
				.StringToJSON(com.nutanix.prism.Functions.HttpClientExecute(
						URI, null));
	}

	@SuppressWarnings("unchecked")
	public JSONObject GetVm(String vmName) {

		JSONObject VmsJSON = this.GetVms();

		JSONArray msg = (JSONArray) VmsJSON.get("entities");
		Iterator<JSONObject> iterator = msg.iterator();
		String vmId = null;
		while (iterator.hasNext()) {
			JSONObject factObj = iterator.next();
			if (factObj.get("vmName").toString().equalsIgnoreCase(vmName)) {
				vmId = factObj.get("vmId").toString();
			}
		}

		String URI = "https://" + com.nutanix.prism.Connection.SERVICE_ADDRESS
				+ ":" + com.nutanix.prism.Connection.SERVICE_PORT
				+ com.nutanix.prism.Connection.SERVICE_VM_GET + vmId;

		return com.nutanix.prism.Functions
				.StringToJSON(com.nutanix.prism.Functions.HttpClientExecute(
						URI, null));
	}
}
