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

import org.ifxforum.xsd._1.CustAddRqType;
import org.ifxforum.xsd._1.CustAddRsType;
import org.ifxforum.xsd._1.CustDelRqType;
import org.ifxforum.xsd._1.CustDelRsType;
import org.ifxforum.xsd._1.CustInqRqType;
import org.ifxforum.xsd._1.CustInqRsType;
import org.ifxforum.xsd._1.ObjectFactory;

/**
 *  
 *
 */
@WebService(name = "CustomerApi", targetNamespace = "http://www.ifxopenshift.com/Customer/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ ObjectFactory.class })
public interface CustomerApi {

	/**
	 * 
	 * @param CustInqRqType
	 * @return CustInqRsType
	 */
	@WebMethod(operationName = "inq", action = "urn:IFX")
	@WebResult(name = "CustInqRs", targetNamespace = "urn:ifxforum-org:XSD:1", partName = "rs")
	public CustInqRsType customerInq(
			@WebParam(name = "CustInqRq", targetNamespace = "urn:ifxforum-org:XSD:1", partName = "rq") CustInqRqType custInqRqType);

	/**
	 * 
	 * @param CustAddRqType
	 * @return CustAddRsType
	 */
	@WebMethod(action = "urn:IFX", operationName = "add")
	@WebResult(name = "CustAddRs", targetNamespace = "urn:ifxforum-org:XSD:1", partName = "rs")
	public CustAddRsType customerAdd(
			@WebParam(name = "CustAddRq", targetNamespace = "urn:ifxforum-org:XSD:1", partName = "rq") CustAddRqType custAddRqType);

	/**
	 * 
	 * @param CustDelRqType
	 * @return CustDelRsType
	 */
	@WebMethod(action = "urn:IFX", operationName = "del")
	@WebResult(name = "CustDelRs", targetNamespace = "urn:ifxforum-org:XSD:1", partName = "rs")
	public CustDelRsType customerDel(
			@WebParam(name = "CustDelRq", targetNamespace = "urn:ifxforum-org:XSD:1", partName = "rq") CustDelRqType custDelRqType);

}
