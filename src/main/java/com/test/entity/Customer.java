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
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8682185206365653494L;

	/**
	 * 
	 */
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	private Integer customerId;
	private Integer custPermId;
	private String fullName;
	private String givenName;
	private String middleName;
	private String phone;
	private String emailAddr;
	private String addr1;
	private String addr2;
	private String city;
	private String stateProv;
	private String postalCode;
	private String addrType;
	private String tinType;
	private String taxId;
	private Date birthDt;
	private String gender;
	private String maritalStatus;

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the custPermId
	 */
	public Integer getCustPermId() {
		return custPermId;
	}
	/**
	 * @param custPermId the custPermId to set
	 */
	public void setCustPermId(Integer custPermId) {
		this.custPermId = custPermId;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}
	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the emailAddr
	 */
	public String getEmailAddr() {
		return emailAddr;
	}
	/**
	 * @param emailAddr the emailAddr to set
	 */
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	/**
	 * @return the addr1
	 */
	public String getAddr1() {
		return addr1;
	}
	/**
	 * @param addr1 the addr1 to set
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	/**
	 * @return the addr2
	 */
	public String getAddr2() {
		return addr2;
	}
	/**
	 * @param addr2 the addr2 to set
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the stateProv
	 */
	public String getStateProv() {
		return stateProv;
	}
	/**
	 * @param stateProv the stateProv to set
	 */
	public void setStateProv(String stateProv) {
		this.stateProv = stateProv;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the addrType
	 */
	public String getAddrType() {
		return addrType;
	}
	/**
	 * @param addrType the addrType to set
	 */
	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}
	/**
	 * @return the tinType
	 */
	public String getTinType() {
		return tinType;
	}
	/**
	 * @param tinType the tinType to set
	 */
	public void setTinType(String tinType) {
		this.tinType = tinType;
	}
	/**
	 * @return the taxId
	 */
	public String getTaxId() {
		return taxId;
	}
	/**
	 * @param taxId the taxId to set
	 */
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	/**
	 * @return the birthDt
	 */
	public Date getBirthDt() {
		return birthDt;
	}
	/**
	 * @param birthDt the birthDt to set
	 */
	public void setBirthDt(Date birthDt) {
		this.birthDt = birthDt;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	
	

}
