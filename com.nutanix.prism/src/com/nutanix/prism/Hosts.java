/**
 * com.nutanix.prism;
 */
package com.nutanix.prism;

import java.text.MessageFormat;
import java.util.Map;

/**
 * @author andreleibovici
 * @version $Revision: 1.0 $
 */
public class Hosts implements Cloneable {
	/**
	 * getHosts()
	 * 
	
	 * @return JSONObject */
	public Map<?, ?> getHosts() {

		final String uri = MessageFormat.format("https://{0}:{1}{2}",
				com.nutanix.prism.Connection.SERVICE_ADDRESS,
				com.nutanix.prism.Connection.SERVICE_PORT,
				com.nutanix.prism.Connection.SERVICE_HOST_GET);

		return com.nutanix.prism.Functions
				.stringToJSON(com.nutanix.prism.Functions.httpClientExecute(
						uri, null));
	}

	/**
	 * Method clone.
	 * @return Hosts
	 * @throws CloneNotSupportedException
	 */
	@Override
	public Hosts clone() throws CloneNotSupportedException {
		return (Hosts) super.clone();
	}
}
