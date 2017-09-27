/**
 * 
 */
package com.test.dao;

import java.util.List;

import com.test.entity.Account;

/**
 *  
 *
 */
public interface AccountDao {
	
	
	public void addAccount(Account account);
	
	public List<Account> getAccount(String custId);

}
