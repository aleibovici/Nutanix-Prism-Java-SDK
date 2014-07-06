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
public class VirtualDisks implements Cloneable {
	/**
	 * getVirtualDisks()
	 * 
	
	 * @return Map<?,?> */
	public Map<?, ?> getVirtualDisks() {

		final String uri = MessageFormat.format("https://{0}:{1}{2}",
				com.nutanix.prism.Connection.SERVICE_ADDRESS,
				com.nutanix.prism.Connection.SERVICE_PORT,
				com.nutanix.prism.Connection.SERVICE_VIRTUAL_DISKS_GET);

		return com.nutanix.prism.Functions
				.stringToJSON(com.nutanix.prism.Functions.httpClientExecute(
						uri, null));
	}

	/**
	 * Method clone.
	 * @return VirtualDisks
	 * @throws CloneNotSupportedException
	 */
	@Override
	public VirtualDisks clone() throws CloneNotSupportedException {
		return (VirtualDisks) super.clone();
	}
}
