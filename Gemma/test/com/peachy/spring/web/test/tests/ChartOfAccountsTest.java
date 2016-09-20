package com.peachy.spring.web.test.tests;


import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peachy.web.dao.ChartOfAccounts;
import com.peachy.web.dao.ChartOfAccountsDao;
import com.peachy.web.dao.InventoryDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/peachy/web/config/dao-context.xml",
		"classpath:com/peachy/web/config/config-context.xml",
		"classpath:com/peachy/web/config/pager-context.xml",
		"classpath:com/peachy/web/config/security-context.xml",
		"classpath:com/peachy/web/config/service-context.xml",
		"classpath:com/peachy/spring/web/test/config/datasource.xml",
		"classpath:com/peachy/web/config/email-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ChartOfAccountsTest {

	@Autowired
	private ChartOfAccountsDao chartOfAccountsDao;
	
	@SuppressWarnings("unused")
	@Autowired
	private InventoryDao inventoryDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init(){
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String resetAccounts = "INSERT INTO `chartofaccounts` (`accountNum`," + 
							   " `Description`, `accountBalance`, `accountName`," + 
							   " `debitAccount`) VALUES" + 
							   "('1000', 'This Account is used for cash.', 0, 'Cash Account', b'1')," +
							   "('1001', 'This account is for Expense', 0, 'Expenses', b'1')," +
							   "('1002', 'This account is for assets', 0, 'Assets', b'1')," +
							   "('1003', 'This account is for purchasing inventory', 0, 'Inventory', b'0')," +
							   "('1004', 'This account is for revenues', 0, 'Revenues', b'0')," +
							   "('1005', 'This account is for losses', 0, 'Losses', b'1')," +
							   "('1006', 'This account is for gains', 0, 'Gains', b'0')," +
							   "('1007', 'This account is for income', 0, 'Income', b'0')," +
							   "('1008', 'This account is for Liabilities', 0, 'Liabilities', b'0')," +
							   "('1009', 'Account for Sales', 0, 'Sales', b'0')," +
							   "('1010', 'Account for Sales Tax', 0, 'Sales Tax', b'0')," +
							   "('1011', 'This Account is for sale commision', 0, 'Per Sale Reembersement', b'0');";
		
		jdbc.execute("delete from generalledger");
		jdbc.execute("delete from invoiceheader");
		jdbc.execute("delete from invoiceitem");
		jdbc.execute("delete from chartofaccounts");
		jdbc.execute(resetAccounts);		
	}
	@Test
	public void testAccountAudit() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("update inventory set amtInStock = minQuantity");
		
	}
	
	@Test
	public void testDeleteAccount() {
		List<ChartOfAccounts> accounts = chartOfAccountsDao.listAccounts();
		assertEquals(12, accounts.size());
		chartOfAccountsDao.delete("1000");
		accounts = chartOfAccountsDao.listAccounts();
		assertEquals(11, accounts.size());
		ChartOfAccounts account = new ChartOfAccounts();
		account.setAccountBalance((float) 100.00);
		account.setAccountName("Cash Account");
		account.setDescription("This Account is used for cash.");
		account.setDebitAccount(true);
		account.setAccountNum("1000");
		chartOfAccountsDao.create(account);
		accounts = chartOfAccountsDao.listAccounts();
		assertEquals(12, accounts.size());
	}
	
	@Test
	public void testDebitCredit() {
		ChartOfAccounts cash = chartOfAccountsDao.getAccount("1000");
		assertEquals("Cash Account", cash.getAccountName());
		ChartOfAccounts sales = chartOfAccountsDao.getAccount("1009");
		assertEquals("Sales", sales.getAccountName());
		chartOfAccountsDao.debitAccount(cash, 50);
		cash = chartOfAccountsDao.getAccount("1000");
		assertEquals(50, cash.getAccountBalance(), .01);
		chartOfAccountsDao.creditAccount(sales, 50);
		sales = chartOfAccountsDao.getAccount("1009");
		assertEquals(50, sales.getAccountBalance(), .01);
	}
	

}
