/**
 * com.nutanix.prism.validation;
 * This package and class are only used to test implementation and should be removed for compile.
 */
package com.nutanix.prism.validation;

import com.nutanix.prism.Connection;

/**
 * 
 * @author andreleibovici
 * 
 * @version $Revision: 1.0 $
 */
public class Main implements Cloneable {

	/**
	 * Method main.
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {

		final com.nutanix.prism.Connection prismConnect = new Connection();
		final com.nutanix.prism.Hosts prismHost = new com.nutanix.prism.Hosts();
		final com.nutanix.prism.Vm prismVm = new com.nutanix.prism.Vm();
		final com.nutanix.prism.VirtualDisks prismVirtualDisk = new com.nutanix.prism.VirtualDisks();
		prismConnect.connect("10.20.18.10", "admin:admin");
		System.out.println(prismVm.getVms());
		System.out.println(prismHost.getHosts());
		System.out.println(prismVirtualDisk.getVirtualDisks());
		System.out.println(prismVm.getVm("bizman-02"));
		prismConnect.disconnect();

	}

	/**
	 * Method clone.
	 * 
	 * @return Main
	 * @throws CloneNotSupportedException
	 */
	@Override
	public Main clone() throws CloneNotSupportedException {
		return (Main) super.clone();
	}

}
