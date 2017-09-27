/**
 * 
 */
package com.test.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.test.dao.CustomerDao;
import com.test.entity.Customer;

/**
 *  
 *
 */
@Component
public class CustomerDaoImpl implements CustomerDao {
	private static final String DELETE = "DELETE FROM CUSTOMER WHERE CUST_PERM_ID=?";
	private static final String GET = "SELECT CUSTOMER_ID,CUST_PERM_ID,FULL_NAME,GIVEN_NAME,MIDDLE_NAME,PHONE,EMAIL_ADDR,ADDR1,ADDR2,CITY,STATE_PROV,POSTAL_CODE,ADDR_TYPE,TIN_TYPE,TAX_ID,BIRTH_DT,GENDER,MARITAL_STATUS FROM CUSTOMER WHERE CUST_PERM_ID=?";
	private static final String ADD = "INSERT INTO CUSTOMER (CUST_PERM_ID,FULL_NAME,GIVEN_NAME,MIDDLE_NAME,PHONE,EMAIL_ADDR,ADDR1,ADDR2,CITY,STATE_PROV,POSTAL_CODE,ADDR_TYPE,TIN_TYPE,TAX_ID,BIRTH_DT,GENDER,MARITAL_STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_ACCOUNT = "DELETE FROM ACCOUNT WHERE CUST_ID=?";
	private Random random = new Random();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 
	 */
	public CustomerDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ally.bits.dao.CustomerDao#getCustomer(java.lang.String)
	 */
	@Override
	public Customer getCustomer(String custPermId) {
		Customer customer = null;
		List<Map<String, Object>> customerFromDB = jdbcTemplate.queryForList(GET, custPermId);
		for (Map<String, Object> customerMa : customerFromDB) {
			customer = new Customer();
			customer.setCustomerId(Integer.valueOf(String.valueOf(customerMa.get("CUSTOMER_ID"))));
			customer.setCustPermId(Integer.valueOf(String.valueOf(customerMa.get("CUST_PERM_ID"))));
			customer.setFullName(String.valueOf(customerMa.get("FULL_NAME")));
			customer.setGivenName(String.valueOf(customerMa.get("GIVEN_NAME")));
			customer.setMiddleName(String.valueOf(customerMa.get("MIDDLE_NAME")));
			customer.setPhone(String.valueOf(customerMa.get("PHONE")));
			customer.setEmailAddr(String.valueOf(customerMa.get("EMAIL_ADDR")));
			customer.setAddr1(String.valueOf(customerMa.get("ADDR1")));
			customer.setAddr2(String.valueOf(customerMa.get("ADDR2")));
			customer.setCity(String.valueOf(customerMa.get("CITY")));
			customer.setStateProv(String.valueOf(customerMa.get("STATEPROV")));
			customer.setPostalCode(String.valueOf(customerMa.get("POSTAL_CODE")));
			customer.setAddrType(String.valueOf(customerMa.get("ADDR_TYPE")));
			customer.setTinType(String.valueOf(customerMa.get("TIN_TYPE")));
			customer.setTaxId(String.valueOf(customerMa.get("TAX_ID")));
			customer.setBirthDt(new Date());
			customer.setGender(String.valueOf(customerMa.get("GENDER")));
			customer.setMaritalStatus(String.valueOf(customerMa.get("MARITAL_STATUS")));

		}
		if (null == customerFromDB || customerFromDB.isEmpty()) {
			System.out.println("Selection failure on customer with customerId: " + custPermId);
			// throw new RuntimeException("Selection query run into some
			// problem.");
		} else {
			System.out.println("Selection was successful.");
		}

		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ally.bits.dao.CustomerDao#addCustomer(com.ally.bits.entity.Customer)
	 */
	@Override
	public void addCustomer(Customer customer) {
		int custPermId = 100000 + random.nextInt(900000);
		Object[] insertValues = { customer.getCustPermId() == null ? custPermId : customer.getCustPermId(),
				customer.getFullName(), customer.getGivenName(), customer.getMiddleName(), customer.getPhone(),
				customer.getEmailAddr(), customer.getAddr1(), customer.getAddr2(), customer.getCity(),
				customer.getStateProv(), customer.getPostalCode(), customer.getAddrType(), customer.getTinType(),
				customer.getTaxId(), customer.getBirthDt(), customer.getGender(), customer.getMaritalStatus() };
		int status = jdbcTemplate.update(ADD, insertValues);
		// System.out.println("CustomerDao: query run with status code: " +
		// status);
		if (status > 0) {
			System.out.println("Insert Successful.");
		} else {
			System.out.println("Insertion failure on customer with customerId: " + custPermId);
			throw new RuntimeException("Insertion query run into some problem.");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ally.bits.dao.CustomerDao#deleteCustomer(java.lang.String)
	 */
	@Override
	public void deleteCustomer(String custPermId) {
		int statusAccount = jdbcTemplate.update(DELETE_ACCOUNT, custPermId);
		if (statusAccount >= 0) {
			System.out.println("Delete Account Successful.");
			int status = jdbcTemplate.update(DELETE, custPermId);
			if (status > 0) {
				System.out.println("Delete Customer Successful with id: " + custPermId);
			} else {
				System.out.println("Deletion failure on customer with customerId: " + custPermId);
				throw new RuntimeException("Deletion query run into some problem.");
			}
		} else {
			System.out.println("Deletion failure on Account for customer with customerId: " + custPermId
					+ " and sql status code: " + statusAccount);
			throw new RuntimeException("Deletion query run into some problem.");
		}

	}

}
