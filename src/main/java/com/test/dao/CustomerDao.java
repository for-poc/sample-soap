/**
 * 
 */
package com.test.dao;

import com.test.entity.Customer;

/**
 *  
 *
 */
public interface CustomerDao {
	/**
	 * 
	 * @param custPermId
	 * @return
	 */
	public Customer getCustomer(String custPermId);
	/**
	 * 
	 * @param customer
	 */
	public void addCustomer(Customer customer);
	/**
	 * 
	 * @param custPermId
	 */
	public void deleteCustomer(String custPermId);

}
