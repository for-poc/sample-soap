/**
 * 
 */
package com.test.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.ally.ifx.account.AccountAddRqType;
import com.ally.ifx.account.AccountAddRsType;
import com.ally.ifx.account.AccountInqRqType;
import com.ally.ifx.account.AccountInqRsType;

/**
 *  
 *
 */
@WebService(targetNamespace = "http://ally.com/wsd/Account/v1/", name = "AccountApi")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ com.ally.ifx.account.ObjectFactory.class, com.ally.ifx.common.ObjectFactory.class,
		org.ifxforum.ifx_2x.ObjectFactory.class })
public interface AccountApi {

	@WebMethod(operationName = "Inq", action = "http://ally.com/ws/Account/Inq")
	@WebResult(name = "AccountInqRs", targetNamespace = "http://www.ally.com/ifx/Account/", partName = "ResponseMessage")
	public AccountInqRsType accountInq(
			@WebParam(name = "AccountInqRq", targetNamespace = "http://www.ally.com/ifx/Account/", partName = "RequestMessage") AccountInqRqType accountInqRqType);
	
	
	@WebMethod(operationName = "Add", action = "http://ally.com/ws/Account/Add")
    @WebResult(name = "AccountAddRs", targetNamespace = "http://www.ally.com/ifx/Account/", partName = "ResponseMessage")
    public AccountAddRsType accountAdd(
        @WebParam(name = "AccountAddRq", targetNamespace = "http://www.ally.com/ifx/Account/", partName = "RequestMessage")
        AccountAddRqType requestMessage);

}
