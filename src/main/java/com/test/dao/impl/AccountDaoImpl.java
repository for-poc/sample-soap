/**
 * 
 */
package com.test.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.test.dao.AccountDao;
import com.test.entity.Account;

/**
 *  
 *
 */
@Component
public class AccountDaoImpl implements AccountDao{
	
	private static final String QUERYADD = "insert into Account (ACCT_ID, ACCT_TYPE, AMOUNT,BAL_TYPE,BANK_ACCT_STATUS_CODE,Cur_Code,CUST_ID,NICKNAME,OWNERSHIP) values (?,?,?,?,?,?,?,?,?)";
	private static final String QUERYGET = "select ACCT_ID, ACCT_TYPE, AMOUNT,BAL_TYPE,BANK_ACCT_STATUS_CODE,Cur_Code,CUST_ID,NICKNAME,OWNERSHIP,ACCOUNT_ID, OPEN_DT FROM Account where CUST_ID=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void addAccount(Account account){
		
		Object[] insertValues = {account.getAcctId(),account.getAcctTypeValue(),account.getAmt(),account.getBalTypeValues(),account.getBankAcctStatusCode(),account.getCurCodeValue(),account.getCustId(),account.getNickname(),account.getOwnership()};
		int status = jdbcTemplate.update(QUERYADD, insertValues);
		if(status != 0){
			System.out.println("Insert successfull.");
		}else{
			System.out.println("Insert Failure with status code: "+status);
		}
	}
	
	public List<Account> getAccount(String custId){
		List<Account> accounts = new ArrayList<>();
		List<Map<String, Object>> accountsFromDB = jdbcTemplate.queryForList(QUERYGET, custId);
		for(Map<String, Object> accountFromDB: accountsFromDB){
			Account account = new Account();
			account.setAcctId(String.valueOf(accountFromDB.get("ACCT_ID")));
			account.setAcctTypeValue(String.valueOf(accountFromDB.get("ACCT_TYPE")));
			account.setAmt(Double.valueOf(String.valueOf(accountFromDB.get("AMOUNT"))));
			account.setBalTypeValues(String.valueOf(accountFromDB.get("BAL_TYPE")));
			account.setBankAcctStatusCode(String.valueOf(accountFromDB.get("BANK_ACCT_STATUS_CODE")));
			account.setCurCodeValue(String.valueOf(accountFromDB.get("CUR_CODE")));
			account.setCustId(String.valueOf(accountFromDB.get("CUST_ID")));
			account.setNickname(String.valueOf(accountFromDB.get("NICKNAME")));
			account.setOwnership(String.valueOf(accountFromDB.get("OWNERSHIP")));
			account.setOpenDt((Date)accountFromDB.get("OPEN_DT"));
			accounts.add(account);
		}
		return accounts;
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
