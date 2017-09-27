/**
 * 
 */
package com.test.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.ifxforum.ifx_2x.AcctBalType;
import org.ifxforum.ifx_2x.AcctTypeType;
import org.ifxforum.ifx_2x.BalTypeType;
import org.ifxforum.ifx_2x.BankAcctInfoType;
import org.ifxforum.ifx_2x.CurAmtType;
import org.ifxforum.ifx_2x.CurCodeType;
import org.ifxforum.ifx_2x.CustIdType;
import org.ifxforum.ifx_2x.DepAcctIdType;
import org.ifxforum.ifx_2x.DepAppAcctIdType;
import org.ifxforum.ifx_2x.DepAppInfoType;
import org.ifxforum.ifx_2x.DepAppRecType;
import org.ifxforum.ifx_2x.DepApplicantType;
import org.ifxforum.ifx_2x.PartyAcctRelInfoType;
import org.ifxforum.ifx_2x.PartyAcctRelRecType;
import org.ifxforum.ifx_2x.SeverityType;
import org.ifxforum.ifx_2x.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ally.ifx.account.AccountAddRqType;
import com.ally.ifx.account.AccountAddRsType;
import com.ally.ifx.account.AccountInqRqType;
import com.ally.ifx.account.AccountInqRsType;
import com.test.dao.AccountDao;
import com.test.dao.CustomerDao;
import com.test.entity.Account;
import com.test.entity.Customer;
import com.test.utility.CommonUtility;

/**
 *  
 *
 */
@Service
public class AccountService {

	private static final String USD = "USD";
	private Random random = new Random();

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private CustomerDao customerDao;

	public AccountInqRsType accountInq(AccountInqRqType accountInqRqType) {
		List<Account> accounts = accountDao.getAccount(accountInqRqType.getCustId().getCustPermId());
		System.out.println("************** Number of accounts recd = "+accounts.size());
		return getAccountFromAccountList(accounts, accountInqRqType);
	}

	/**
	 * 
	 * @param accounts
	 * @return AccountInqRsType
	 */
	private AccountInqRsType getAccountFromAccountList(List<Account> accounts, AccountInqRqType accountInqRqType) {

		AccountInqRsType accountInqRsType = new AccountInqRsType();
		CustIdType custIdType = new CustIdType();
		custIdType.setCustPermId(accountInqRqType.getCustId().getCustPermId());
		if (null != accounts && !accounts.isEmpty()) {
			System.out.println("************** getAccountFromAccountList ...got accounts  ");
			accountInqRsType.setStatus(populateSuccessStatustype());
			PartyAcctRelRecType partyAcctRelRecType = null;
			for (Account account : accounts) {
				System.out.println("************** getAccountFromAccountList ...Account  "+account);
				partyAcctRelRecType = new PartyAcctRelRecType();
				partyAcctRelRecType.setPartyAcctRelId(account.getAcctId());
				PartyAcctRelInfoType partyAcctRelInfoType = new PartyAcctRelInfoType();
				DepAcctIdType depAcctIdType = new DepAcctIdType();
				depAcctIdType.setAcctId(account.getAcctId());
				AcctTypeType acctTypeType = new AcctTypeType();
				acctTypeType.setAcctTypeValue(account.getAcctTypeValue());
				depAcctIdType.setAcctType(acctTypeType);
				partyAcctRelInfoType.setDepAcctId(depAcctIdType);
				partyAcctRelRecType.setPartyAcctRelInfo(partyAcctRelInfoType);
				partyAcctRelRecType.setBankAcctStatusCode(account.getBankAcctStatusCode());
				partyAcctRelRecType.setOpenDt(CommonUtility.xmlGregorianCalendarFromDate(account.getOpenDt()));
				partyAcctRelRecType.setNickname(account.getNickname());
				AcctBalType acctBalType = new AcctBalType();
				BalTypeType balTypeType = new BalTypeType();
				balTypeType.setBalTypeValues(account.getBalTypeValues());
				acctBalType.setBalType(balTypeType);
				CurAmtType curAmtType = new CurAmtType();
				curAmtType.setAmt(new BigDecimal(account.getAmt()));
				CurCodeType curCodeType = new CurCodeType();
				curCodeType.setCurCodeValue(account.getCurCodeValue());
				curAmtType.setCurCode(curCodeType);
				acctBalType.setCurAmt(curAmtType);
				partyAcctRelRecType.getAcctBal().add(acctBalType);
				accountInqRsType.getPartyAcctRelRec().add(partyAcctRelRecType);
			}
		} else {
			custIdType.setCustPermId("10000011245");
		}
		accountInqRsType.setRqUID(accountInqRqType.getRqUID());
		accountInqRsType.setCustId(custIdType);
		return accountInqRsType;
	}

	private StatusType populateSuccessStatustype() {
		StatusType statusType = new StatusType();
		statusType.setStatusCode(0);
		statusType.setSeverity(SeverityType.INFO);
		statusType.setStatusDesc("Success");
		return statusType;
	}

	/**
	 * 
	 * @param accountAddRqType
	 * @return
	 */
	@Transactional
	public AccountAddRsType accountAdd(AccountAddRqType accountAddRqType) {
		Customer customer = customerDao
				.getCustomer(accountAddRqType.getDepAppInfo().getDepApplicant().get(0).getCustId().getCustPermId());
		if (null == customer) {
			customer = populateAddCustomer(accountAddRqType.getDepAppInfo().getDepApplicant().get(0));
			customerDao.addCustomer(customer);
		}
		Account account = populateAccountEntity(accountAddRqType);

		try {
			accountDao.addAccount(account);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		AccountAddRsType accountAddRsType = new AccountAddRsType();
		accountAddRsType.setStatus(populateSuccessStatustype());
		accountAddRsType.setRqUID(accountAddRqType.getRqUID());
		DepAppRecType depAppRecType = new DepAppRecType();
		DepAppInfoType depAppInfoType = new DepAppInfoType();
		DepApplicantType depApplicantType = new DepApplicantType();
		depAppInfoType.getDepApplicant().add(depApplicantType);
		DepAppAcctIdType depAppAcctIdType = new DepAppAcctIdType();
		AcctTypeType acctTypeType = new AcctTypeType();
		acctTypeType.setAcctTypeValue("");
		depAppAcctIdType.setAcctType(acctTypeType);
		depAppInfoType.setDepAppAcctId(depAppAcctIdType);
		BankAcctInfoType bankAcctInfoType = new BankAcctInfoType();
		CurCodeType curCodeType = new CurCodeType();
		curCodeType.setCurCodeValue(USD);
		bankAcctInfoType.setCurCode(curCodeType);
		depAppInfoType.setBankAcctInfo(bankAcctInfoType);
		depAppRecType.setDepAppInfo(depAppInfoType);
		depAppRecType
				.setDepAppId(accountAddRqType.getDepAppInfo().getDepApplicant().get(0).getCustId().getCustPermId());
		accountAddRsType.setDepAppRec(depAppRecType);

		return accountAddRsType;

	}

	/**
	 * 
	 * @param accountAddRqType
	 * @return
	 */
	private Account populateAccountEntity(AccountAddRqType accountAddRqType) {
		Account account = new Account();
		account.setCustId(accountAddRqType.getDepAppInfo().getDepApplicant().get(0).getCustId().getCustPermId());
		account.setAcctId(String.valueOf(1000000 + random.nextInt(9000000)));
		account.setAcctTypeValue(accountAddRqType.getDepAppInfo().getDepAppAcctId().getAcctType().getAcctTypeValue());
		account.setCurCodeValue(accountAddRqType.getDepAppInfo().getBankAcctInfo().getCurCode().getCurCodeValue());
		account.setOwnership(accountAddRqType.getDepAppInfo().getBankAcctInfo().getOwnership());
		account.setBalTypeValues(accountAddRqType.getDepAppInfo().getAcctBal().getBalType().getBalTypeValues());
		account.setAmt(accountAddRqType.getDepAppInfo().getAcctBal().getCurAmt().getAmt().doubleValue());
		account.setNickname(
				accountAddRqType.getDepAppInfo().getDepApplicant().get(0).getCustInfo().getCustName().getNickname());

		return account;
	}

	private Customer populateAddCustomer(DepApplicantType depApplicantType) {
		// depApplicantType.getCustInfo().getCustName().get
		Customer customer = new Customer();
		customer.setCustPermId(Integer.valueOf(depApplicantType.getCustId().getCustPermId()));
		customer.setFullName(depApplicantType.getCustInfo().getCustName().getLegalName());
		customer.setGivenName(depApplicantType.getCustInfo().getCustName().getFirstName().get(0));
		customer.setMiddleName(depApplicantType.getCustInfo().getCustName().getMiddleName().get(0));
		customer.setPhone(depApplicantType.getCustInfo().getCustContact().getDayPhone());
		customer.setEmailAddr(depApplicantType.getCustInfo().getCustContact().getEmailAddr());
		customer.setAddr1(depApplicantType.getCustInfo().getPostAddr().getAddr1());
		customer.setAddr2(depApplicantType.getCustInfo().getPostAddr().getAddr2());
		customer.setCity(depApplicantType.getCustInfo().getPostAddr().getCity());
		customer.setStateProv(depApplicantType.getCustInfo().getPostAddr().getStateProv());
		customer.setPostalCode(depApplicantType.getCustInfo().getPostAddr().getPostalCode());
		customer.setAddrType(depApplicantType.getCustInfo().getPostAddr().getAddrType());
		// customer.setTinType(custAddRqType.getCustInfo().getPersonInfo().getTINInfo().getTINType());
		// customer.setTaxId(custAddRqType.getCustInfo().getPersonInfo().getTINInfo().getTaxId());
		customer.setBirthDt(new Date());
		// CommonUtility.dateFromXMLGregorianCalendar(custAddRqType.getCustInfo().getPersonInfo().getBirthDt()));
		customer.setGender("Sample");
		customer.setMaritalStatus("Sample");
		return customer;
	}

	/**
	 * @return the accountDao
	 */
	public AccountDao getAccountDao() {
		return accountDao;
	}

	/**
	 * @param accountDao
	 *            the accountDao to set
	 */
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
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
