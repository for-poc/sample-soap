CREATE TABLE Customer (
  CUSTOMER_ID int(32) NOT NULL AUTO_INCREMENT COMMENT 'Auto generated index for table and primary key',
  CUST_PERM_ID int(32) NOT NULL COMMENT 'unique customer id',
  FULL_NAME varchar(100) NOT NULL COMMENT 'customer full name',
  GIVEN_NAME varchar(100) DEFAULT NULL COMMENT 'customer given name',
  MIDDLE_NAME varchar(100) DEFAULT NULL COMMENT 'customer middle name',
  PHONE varchar(12) NOT NULL COMMENT 'customer phone number',
  EMAIL_ADDR varchar(100) NOT NULL COMMENT 'customer email address',
  ADDR1 varchar(100) NOT NULL COMMENT 'customer address block 1',
  ADDR2 varchar(100) DEFAULT NULL COMMENT 'customer address block 2',
  CITY varchar(100) NOT NULL COMMENT 'customer city',
  STATE_PROV varchar(100) DEFAULT NULL,
  POSTAL_CODE varchar(5) NOT NULL COMMENT 'customer postal code',
  ADDR_TYPE varchar(20) NOT NULL COMMENT 'customer address type',
  TIN_TYPE varchar(20) DEFAULT NULL,
  TAX_ID varchar(11) DEFAULT NULL,
  BIRTH_DT date NOT NULL COMMENT 'customer birth date',
  GENDER varchar(20) NOT NULL COMMENT 'customer gender',
  MARITAL_STATUS varchar(100) NOT NULL COMMENT 'customer marital status',
  PRIMARY KEY (CUSTOMER_ID),
  UNIQUE KEY CUST_PERM_ID (CUST_PERM_ID)
)