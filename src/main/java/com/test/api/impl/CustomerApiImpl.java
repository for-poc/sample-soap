/**
 * 
 */
package com.test.api.impl;

import javax.jws.WebService;

import org.apache.cxf.annotations.SchemaValidation;
import org.ifxforum.xsd._1.CustAddRqType;
import org.ifxforum.xsd._1.CustAddRsType;
import org.ifxforum.xsd._1.CustDelRqType;
import org.ifxforum.xsd._1.CustDelRsType;
import org.ifxforum.xsd._1.CustInqRqType;
import org.ifxforum.xsd._1.CustInqRsType;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.api.CustomerApi;
import com.test.service.CustomerService;

/**
 *  
 *
 */
@WebService(serviceName = "Customer", endpointInterface = "com.ally.bits.api.CustomerApi", portName = "CustomerPort", wsdlLocation = "WEB-INF/classes/WSDL/Customer.wsdl", targetNamespace = "http://www.ifxopenshift.com/Customer/")
@SchemaValidation
public class CustomerApiImpl implements CustomerApi {

	@Autowired
	private CustomerService customerService;

	/**
	 * 
	 */
	public CustomerApiImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ally.bits.api.CustomerApi#customerInq(org.ifxforum.xsd._1.
	 * CustInqRqType)
	 */
	@Override
	public CustInqRsType customerInq(CustInqRqType custInqRqType) {
		return customerService.customerInq(custInqRqType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ally.bits.api.CustomerApi#customerAdd(org.ifxforum.xsd._1.
	 * CustAddRqType)
	 */
	@Override
	public CustAddRsType customerAdd(CustAddRqType custAddRqType) {
		return customerService.customerAdd(custAddRqType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ally.bits.api.CustomerApi#customerDel(org.ifxforum.xsd._1.
	 * CustDelRqType)
	 */
	@Override
	public CustDelRsType customerDel(CustDelRqType custDelRqType) {
		return customerService.customerDelete(custDelRqType);
	}

	/**
	 * @return the customerService
	 */
	public CustomerService getCustomerService() {
		return customerService;
	}

	/**
	 * @param customerService
	 *            the customerService to set
	 */
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
