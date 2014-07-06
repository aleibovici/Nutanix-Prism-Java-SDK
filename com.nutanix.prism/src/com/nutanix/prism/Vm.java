/**
 * com.nutanix.prism;
 */
package com.nutanix.prism;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author andreleibovici
 * @version $Revision: 1.0 $
 */
public class Vm implements Cloneable {
	/**
	 * getVms()
	 * 
	
	 * @return Map<?,?> */
	public Map<?, ?> getVms() {

		final String uri = MessageFormat.format("https://{0}:{1}{2}",
				com.nutanix.prism.Connection.SERVICE_ADDRESS,
				com.nutanix.prism.Connection.SERVICE_PORT,
				com.nutanix.prism.Connection.SERVICE_VM_GET);

		return com.nutanix.prism.Functions
				.stringToJSON(com.nutanix.prism.Functions.httpClientExecute(
						uri, null));
	}

	/**
	 * Method getVm.
	 * 
	 * @param vmName
	 *            String
	
	 * @return JSONObject */
	@SuppressWarnings("unchecked")
	public Map<?, ?> getVm(String vmName) {

		final Map<?, ?> vmsJSON = getVms();
		String uri = null;

		final List<JSONObject> msg = (JSONArray) vmsJSON.get("entities");
		final Iterator<JSONObject> iterator = msg.iterator();
		Map<?, ?> factObj = null;
		String vmId = null;
		while (iterator.hasNext()) {
			factObj = iterator.next();
			if (factObj.get("vmName").toString().equalsIgnoreCase(vmName)) {
				vmId = factObj.get("vmId").toString();
			}
		}

		uri = MessageFormat.format("https://{0}:{1}{2}{3}",
				com.nutanix.prism.Connection.SERVICE_ADDRESS,
				com.nutanix.prism.Connection.SERVICE_PORT,
				com.nutanix.prism.Connection.SERVICE_VM_GET, vmId);

		return com.nutanix.prism.Functions
				.stringToJSON(com.nutanix.prism.Functions.httpClientExecute(
						uri, null));
	}

	/**
	 * Method clone.
	 * @return Vm
	 * @throws CloneNotSupportedException
	 */
	@Override
	public Vm clone() throws CloneNotSupportedException {
		return (Vm) super.clone();
	}

}
