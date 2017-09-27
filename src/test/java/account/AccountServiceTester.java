package account;


import org.ifxforum.ifx_2x.CustIdType;
import org.ifxforum.ifx_2x.MsgRqHdrType;
import com.ally.ifx.account.AccountInqRqType;
import com.ally.ifx.account.AccountInqRsType;
import com.test.api.AccountApi;

public class AccountServiceTester {

	AccountApi account;

	public AccountApi getAccount() {
		return account;
	}

	public void setAccount(AccountApi account) {
		this.account = account;
	}
	
	public void testAccountInq(){
		AccountInqRqType requestMessage = new AccountInqRqType();
		
		requestMessage.setRqUID("981d2f9e-d7ae-40d8-8642-723b8d36b230");
		
		CustIdType custId = new CustIdType();
		custId.setCustPermId("1");
		requestMessage.setCustId(custId);
		MsgRqHdrType msgHeader = new MsgRqHdrType();
		
		System.out.println("Sending request for Account Inq");
		AccountInqRsType accountInq = account.accountInq(requestMessage);
		System.out.println("Respone Recd "+accountInq.getRqUID());
	}
}
