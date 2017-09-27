/**
 * 
 */
package com.test.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *  
 *
 */
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7382982835055005495L;
	private Integer accountId;
	private String  acctId;
	private String acctTypeValue;
	private String curCodeValue;
	private String ownership;
	private String balTypeValues;
	private double amt;
	private String bankAcctStatusCode;
	private Date openDt;
	private String nickname;
	private String custId;



	/**
	 * @return the acctId
	 */
	public String getAcctId() {
		return acctId;
	}
	/**
	 * @param acctId the acctId to set
	 */
	public void setAcctId(final String acctId) {
		this.acctId = acctId;
	}
	/**
	 * @return the bankAcctStatusCode
	 */
	public String getBankAcctStatusCode() {
		return bankAcctStatusCode;
	}
	/**
	 * @param bankAcctStatusCode the bankAcctStatusCode to set
	 */
	public void setBankAcctStatusCode(final String bankAcctStatusCode) {
		this.bankAcctStatusCode = bankAcctStatusCode;
	}
	/**
	 * @return the openDt
	 */
	public Date getOpenDt() {
		return openDt;
	}
	/**
	 * @param openDt the openDt to set
	 */
	public void setOpenDt(final Date openDt) {
		this.openDt = openDt;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(final String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(final Integer accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the acctTypeValue
	 */
	public String getAcctTypeValue() {
		return acctTypeValue;
	}
	/**
	 * @param acctTypeValue the acctTypeValue to set
	 */
	public void setAcctTypeValue(final String acctTypeValue) {
		this.acctTypeValue = acctTypeValue;
	}
	/**
	 * @return the curCodeValue
	 */
	public String getCurCodeValue() {
		return curCodeValue;
	}
	/**
	 * @param curCodeValue the curCodeValue to set
	 */
	public void setCurCodeValue(final String curCodeValue) {
		this.curCodeValue = curCodeValue;
	}
	/**
	 * @return the ownership
	 */
	public String getOwnership() {
		return ownership;
	}
	/**
	 * @param ownership the ownership to set
	 */
	public void setOwnership(final String ownership) {
		this.ownership = ownership;
	}
	/**
	 * @return the balTypeValues
	 */
	public String getBalTypeValues() {
		return balTypeValues;
	}
	/**
	 * @param balTypeValues the balTypeValues to set
	 */
	public void setBalTypeValues(final String balTypeValues) {
		this.balTypeValues = balTypeValues;
	}
	/**
	 * @return the amt
	 */
	public double getAmt() {
		return amt;
	}
	/**
	 * @param amt the amt to set
	 */
	public void setAmt(final double amt) {
		this.amt = amt;
	}
	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}





}