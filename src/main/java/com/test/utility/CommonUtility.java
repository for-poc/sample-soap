/**
 * 
 */
package com.test.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *  
 *
 */
public class CommonUtility {
	
	private static final String dateFormat = "YYYY-MM-dd";

	/**
	 * 
	 */
	public CommonUtility() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar xmlGregorianCalendarFromDate(Date date) {
		DateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		XMLGregorianCalendar xmlGregorianCalendar = null;
//		GregorianCalendar gregorianCalendar = new GregorianCalendar();
//		gregorianCalendar.setTime(date);
//		System.out.println("Date is : "+date);
		try {
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(simpleDateFormat.format(date));
		} catch (DatatypeConfigurationException datatypeConfigurationException) {
			datatypeConfigurationException.printStackTrace();
		}
		return xmlGregorianCalendar;
	}

	/**
	 * 
	 * @param xmlGregorianCalendar
	 * @return
	 */
	public static Date dateFromXMLGregorianCalendar(XMLGregorianCalendar xmlGregorianCalendar) {
		return xmlGregorianCalendar.toGregorianCalendar().getTime();
	}

}
