CREATE TABLE Account(
  ACCOUNT_ID int(11) NOT NULL AUTO_INCREMENT,
  ACCT_ID varchar(255) DEFAULT NULL,
  ACCT_TYPE varchar(255) DEFAULT NULL,
  AMOUNT double DEFAULT NULL,
  BAL_TYPE varchar(255) DEFAULT NULL,
  BANK_ACCT_STATUS_CODE varchar(255) DEFAULT NULL,
  Cur_Code varchar(255) DEFAULT NULL,
  CUST_ID int(32) DEFAULT NULL,
  NICKNAME varchar(255) DEFAULT NULL,
  OPEN_DT timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  OWNERSHIP varchar(255) DEFAULT NULL,
  PRIMARY KEY (ACCOUNT_ID),
  KEY CUSTOMER_FOREIGN_KEY (CUST_ID),
  CONSTRAINT CUSTOMER_FOREIGN_KEY FOREIGN KEY (CUST_ID) REFERENCES customer (CUST_PERM_ID)
)