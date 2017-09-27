/**
 * 
 */
package com.test.service;

import org.ifxforum.xsd._1.ContactInfoType;
import org.ifxforum.xsd._1.CustAddRqType;
import org.ifxforum.xsd._1.CustAddRsType;
import org.ifxforum.xsd._1.CustDelRqType;
import org.ifxforum.xsd._1.CustDelRsType;
import org.ifxforum.xsd._1.CustIdType;
import org.ifxforum.xsd._1.CustInfoType;
import org.ifxforum.xsd._1.CustInqRqType;
import org.ifxforum.xsd._1.CustInqRsType;
import org.ifxforum.xsd._1.CustRecType;
import org.ifxforum.xsd._1.MsgRsHdrType;
import org.ifxforum.xsd._1.PersonInfoType;
import org.ifxforum.xsd._1.PersonNameType;
import org.ifxforum.xsd._1.PostAddrType;
import org.ifxforum.xsd._1.SeverityType;
import org.ifxforum.xsd._1.StatusType;
import org.ifxforum.xsd._1.TINInfoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.CustomerDao;
import com.test.entity.Customer;
import com.test.utility.CommonUtility;

/**
 *  
 *
 */
@Service
public class CustomerService {

	/**
	 * 
	 */
	public CustomerService() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private CustomerDao customerDao;

	public CustInqRsType customerInq(CustInqRqType custInqRqType) {
		return populateCustomerInqRsType(
				customerDao.getCustomer(custInqRqType.getCustRef().getCustId().getCustPermId()),
				custInqRqType.getRqUID());
	}

	/**
	 * 
	 * @param customer
	 * @param rqId
	 * @return
	 */
	private CustInqRsType populateCustomerInqRsType(Customer customer, String rqId) {
		CustInqRsType custInqRsType = new CustInqRsType();
		custInqRsType.setStatus(populateSuccessStatustype());
		custInqRsType.setRqUID(rqId);
		MsgRsHdrType msgRsHdrType = new MsgRsHdrType();
		msgRsHdrType.setAsyncRqUID(rqId);
		custInqRsType.setMsgRsHdr(msgRsHdrType);
		custInqRsType.getCustRec().add(populateCustRecType(customer));
		return custInqRsType;
	}

	/**
	 * 
	 * @param customer
	 * @return
	 */
	private CustRecType populateCustRecType(Customer customer) {
		CustRecType custRecType = new CustRecType();
		CustIdType custIdType = new CustIdType();
		custIdType.setSPName("com.openshift");
		custIdType.setCustPermId(String.valueOf(customer.getCustPermId()));
		custRecType.setCustId(custIdType);
		CustInfoType custInfoType = new CustInfoType();
		PersonInfoType personInfoType = new PersonInfoType();
		// Person Name type
		PersonNameType personNameType = new PersonNameType();
		personNameType.setFullName(customer.getFullName());
		personNameType.getGivenName().add(customer.getGivenName());
		personNameType.getMiddleName().add(customer.getMiddleName());
		personInfoType.setPersonName(personNameType);

		// contact info type
		ContactInfoType contactInfoType = new ContactInfoType();
		contactInfoType.getPhone().add(customer.getPhone());
		contactInfoType.setEmailAddr(customer.getEmailAddr());
		PostAddrType postAddrType = new PostAddrType();
		postAddrType.setAddr1(customer.getAddr1());
		postAddrType.setAddr2(customer.getAddr2());
		postAddrType.setCity(customer.getCity());
		postAddrType.setStateProv(customer.getStateProv());
		postAddrType.setPostalCode(customer.getPostalCode());
		postAddrType.setAddrType(customer.getAddrType());
		contactInfoType.getPostAddr().add(postAddrType);
		personInfoType.setContactInfo(contactInfoType);

		// Tin info
		TINInfoType tinInfoType = new TINInfoType();
		tinInfoType.setTINType(customer.getTinType());
		tinInfoType.setTaxId(customer.getTaxId());
		personInfoType.setTINInfo(tinInfoType);

		personInfoType.setBirthDt(CommonUtility.xmlGregorianCalendarFromDate(customer.getBirthDt()));
		personInfoType.setGender(customer.getGender());
		personInfoType.setMaritalStatus(customer.getMaritalStatus());

		custInfoType.setPersonInfo(personInfoType);
		custRecType.setCustInfo(custInfoType);
		return custRecType;
	}

	public CustAddRsType customerAdd(CustAddRqType custAddRqType) {

		Customer customer = populateAddCustomer(custAddRqType);
		CustAddRsType custAddRsType = new CustAddRsType();
		custAddRsType.setRqUID(custAddRqType.getRqUID());
		MsgRsHdrType msgRsHdrType = new MsgRsHdrType();
		msgRsHdrType.setAsyncRqUID(custAddRqType.getMsgRqHdr().getAsyncRqUID());
		custAddRsType.setMsgRsHdr(msgRsHdrType);
		// try {
		customerDao.addCustomer(customer);
		System.out.println("Insertion successfull for Id: " + custAddRqType.getRqUID());
		custAddRsType.setStatus(populateSuccessStatustype());
		// } catch (Exception exception) {
		// exception.printStackTrace();
		// System.out.println("Insertion Failed for rqid: " +
		// custAddRqType.getRqUID());
		// }
		return custAddRsType;
	}

	private StatusType populateSuccessStatustype() {
		StatusType statusType = new StatusType();
		statusType.setStatusCode(0);
		statusType.setSeverity(SeverityType.INFO);
		statusType.setStatusDesc("Success");
		return statusType;
	}

	private Customer populateAddCustomer(CustAddRqType custAddRqType) {
		Customer customer = new Customer();
		customer.setFullName(custAddRqType.getCustInfo().getPersonInfo().getPersonName().getFullName());
		customer.setGivenName(custAddRqType.getCustInfo().getPersonInfo().getPersonName().getGivenName().get(0));
		customer.setMiddleName(custAddRqType.getCustInfo().getPersonInfo().getPersonName().getMiddleName().get(0));
		customer.setPhone(custAddRqType.getCustInfo().getPersonInfo().getContactInfo().getPhone().get(0));
		customer.setEmailAddr(custAddRqType.getCustInfo().getPersonInfo().getContactInfo().getEmailAddr());
		customer.setAddr1(custAddRqType.getCustInfo().getPersonInfo().getContactInfo().getPostAddr().get(0).getAddr1());
		customer.setAddr2(custAddRqType.getCustInfo().getPersonInfo().getContactInfo().getPostAddr().get(0).getAddr2());
		customer.setCity(custAddRqType.getCustInfo().getPersonInfo().getContactInfo().getPostAddr().get(0).getCity());
		customer.setStateProv(
				custAddRqType.getCustInfo().getPersonInfo().getContactInfo().getPostAddr().get(0).getStateProv());
		customer.setPostalCode(
				custAddRqType.getCustInfo().getPersonInfo().getContactInfo().getPostAddr().get(0).getPostalCode());
		customer.setAddrType(
				custAddRqType.getCustInfo().getPersonInfo().getContactInfo().getPostAddr().get(0).getAddrType());
		customer.setTinType(custAddRqType.getCustInfo().getPersonInfo().getTINInfo().getTINType());
		customer.setTaxId(custAddRqType.getCustInfo().getPersonInfo().getTINInfo().getTaxId());
		customer.setBirthDt(
				CommonUtility.dateFromXMLGregorianCalendar(custAddRqType.getCustInfo().getPersonInfo().getBirthDt()));
		customer.setGender(custAddRqType.getCustInfo().getPersonInfo().getGender());
		customer.setMaritalStatus(custAddRqType.getCustInfo().getPersonInfo().getMaritalStatus());
		return customer;
	}

	public CustDelRsType customerDelete(CustDelRqType custDelRqType) {
		CustDelRsType custDelRsType = new CustDelRsType();
		custDelRsType.setRqUID(custDelRqType.getRqUID());
		MsgRsHdrType msgRsHdrType = new MsgRsHdrType();
		msgRsHdrType.setAsyncRqUID(custDelRqType.getMsgRqHdr().getAsyncRqUID());
		custDelRsType.setMsgRsHdr(msgRsHdrType);
		// try {
		customerDao.deleteCustomer(custDelRqType.getCustRef().getCustId().getCustPermId());
		System.out.println("Delete Success for Id: " + custDelRqType.getRqUID());
		custDelRsType.setStatus(populateSuccessStatustype());
		// } catch (Exception exception) {
		// exception.printStackTrace();
		// System.out.println("Deletion Failed for rqid: " +
		// custDelRqType.getRqUID());
		// }
		return custDelRsType;
	}

	/**
	 * @return the customerDao
	 */
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	/**
	 * @param customerDao
	 *            the customerDao to set
	 */
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
}
