package com.gemma.spring.web.test.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import org.antlr.v4.runtime.RecognitionException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gemma.web.beans.Audit;
import com.gemma.web.dao.ChartOfAccounts;
import com.gemma.web.dao.ChartOfAccountsDao;
import com.gemma.web.dao.GeneralLedger;
import com.gemma.web.dao.GeneralLedgerDao;
import com.gemma.web.dao.InvoiceHeader;
import com.gemma.web.dao.InvoiceHeaderDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/gemma/web/config/dao-context.xml",
		"classpath:com/gemma/web/config/file-context.xml",
		"classpath:com/gemma/web/config/pager-context.xml",
		"classpath:com/gemma/web/config/security-context.xml",
		"classpath:com/gemma/web/config/service-context.xml",
		"classpath:com/gemma/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)


public class GeneralLedgerTest {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ChartOfAccountsDao chartOfAccountsDao;

	@Autowired
	private GeneralLedgerDao generalLedgerDao;
	
	@Autowired
	private InvoiceHeaderDao invoiceHeaderDao;
	
	@Autowired
	private DataSource dataSource;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	private String initHeader = "INSERT INTO `invoiceheader` (`invoiceNum`, `userID`, `total`," +
			" `totalTax`, `shippingCost`, `modified`, `processed`, `dateShipped`) VALUES" +
			"(50, 24, 260, 5, 0, '2016-03-18 17:03:46', NULL, NULL)," +
			"(51, 24, 2280, 38, 0, '2016-03-18 17:20:12', NULL, NULL)," +
			"(52, 24, 55, 1, 0, '2016-03-18 17:21:30', NULL, NULL)," +
			"(53, 24, 405, 7, 0, '2016-03-18 20:59:09', NULL, NULL)," +
			"(54, 24, 50, 1, 0, '2016-03-19 18:05:30', NULL, NULL)," +
			"(55, 24, 55, 1, 0, '2016-03-19 18:27:28', NULL, NULL);";
	
	private String initItems = "INSERT INTO `invoiceitem` (`invoiceNum`, `amount`, `skuNum`," +
			   " `productName`, `description`, `image`, `price`, `tax`, `amtInStock`, `weight`) VALUES" +
			   "(50, 1, '012044037591', 'Old Spice Deodorant', 'Old Spice underarm deodorant. Fiji spice'," +
			   " 'img1903639752864654849.png', 40, 1, 100, 0)," +
			   "(50, 1, '01788500011', 'Slap ''ya Momma Cajun Spices', 'Slap ya mama Cajun spices (extra hot).'" +
			   ", 'img2420448772141923689.png', 55, 1, 100, 0)," +
			   "(50, 1, '026100003048', 'Newport Cigarettes', 'Newport Menthol 100 Cigarettes, single pack'," +
			   " 'img7116038400427907833.png', 100, 1, 100, 0)," +
			   "(50, 1, '1428500111', 'UFC Banana Catsup', 'UFC Tamis Anghang banana catsup'," +
			   " 'img5027160663613073364.png', 50, 1, 100, 0)," +
			   "(50, 1, '4806502720097', 'Gardenia Classic Bread', 'Gardenia Classic Bread, American Recipe.'," +
			   " 'img1792027658668581741.png', 15, 1, 100, 0)," +
			   "(51, 10, '026100003048', 'Newport Cigarettes', 'Newport Menthol 100 Cigarettes," +
			   " single pack', 'img7116038400427907833.png', 100, 1, 99, 0)," +
			   "(51, 24, '15216', 'Pale Pilsen', 'San Miguel Pale Pilsen beer.', 'img8363452072949527142.png'," +
			   " 50, 1, 100, 0)," +
			   "(51, 4, '2767', '7 up 1 Liter Bottle', '7 up in 1 little bottle', 'img6885617661274203319.png'," +
			   " 20, 1, 100, 0)," +
			   "(52, 1, '01788500011', 'Slap ''ya Momma Cajun Spices', 'Slap ya mama Cajun spices (extra hot).'," +
			   " 'img2420448772141923689.png', 55, 1, 99, 0)," +
			   "(53, 1, '01788500011', 'Slap ''ya Momma Cajun Spices', 'Slap ya mama Cajun spices (extra hot).'," +
			   " 'img2420448772141923689.png', 55, 1, 98, 0)," +
			   "(53, 1, '1428500111', 'UFC Banana Catsup', 'UFC Tamis Anghang banana catsup', 'img5027160663613073364.png'," +
			   " 50, 1, 99, 0)," +
			   "(53, 5, '74848510009', 'Century Hot and Spicy Tuna', 'Century Tuna, Hot and Spicy', 'img5061990955997734610.png'," +
			   " 60, 1, 100, 0)," +
			   "(54, 1, '15216', 'Pale Pilsen', 'San Miguel Pale Pilsen beer.', 'img8363452072949527142.png', 50, 1, 100, 0)," +
			   "(55, 1, '01788500011', 'Slap ''ya Momma Cajun Spices', 'Slap ya mama Cajun spices (extra hot).', 'img2420448772141923689.png', 55, 1, 100, 0);";

	private String resetAccounts = "INSERT INTO `chartofaccounts` (`accountNum`," + 
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



	
	@Before
	public void init(){
		
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from generalledger");
		jdbc.execute("delete from invoiceheader");
		jdbc.execute("delete from invoiceitem");
	}
	

	@Test
	public void testAudit() throws RecognitionException, IOException, RuntimeException {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute(initHeader);
		jdbc.execute(initItems);
		jdbc.execute("delete from chartofaccounts");
		jdbc.execute(resetAccounts);
		
		List<InvoiceHeader> headers = invoiceHeaderDao.getAllInvoiceHeaders();
		
		for(InvoiceHeader item: headers) {
			invoiceHeaderDao.processShoppingCart(item);
		}
		Audit audit = generalLedgerDao.getAudit();

		assertEquals(audit.getCreditColumn(), audit.getDebitColumn(), .01);
		ChartOfAccounts account = chartOfAccountsDao.getAccout("1000");
		Double total = (double) account.getAccountBalance();
		assertEquals(total, audit.getDebitColumn(), 01);
		Double tax = 0.0;
		
		List<GeneralLedger> ledgerEntrys = generalLedgerDao.getList();
		for(GeneralLedger item: ledgerEntrys) {
			if ("1010".equals(item.getAccountNum())) {
				tax += item.getCreditAmt();
			}
		}
		account = chartOfAccountsDao.getAccout("1010");
		assertEquals(tax, (Float) account.getAccountBalance(), .01);
		Double commision = 0.0;
		
		for(GeneralLedger item: ledgerEntrys) {
			if ("1011".equals(item.getAccountNum())) {
				commision += item.getCreditAmt();
			}
		}
		account = chartOfAccountsDao.getAccout("1011");
		assertEquals(commision, (Float) account.getAccountBalance(), .01);
		
		account = chartOfAccountsDao.getAccout("1009");
		assertEquals(total - (tax + commision), account.getAccountBalance(), .01);
	}


	
	@Test
	public void testAddEntry(){
		GeneralLedger ledger1 = new GeneralLedger();
		ledger1.setAccountNum("1000");
		ledger1.setDebitAmt((float) 100.00);
		ledger1.setDescription("Part of Test case 1a");
		generalLedgerDao.addEntry(ledger1);
		
		List<GeneralLedger> ledgerList = generalLedgerDao.getList();
		
		assertEquals(1,ledgerList.size());
		
		GeneralLedger ledger2 = new GeneralLedger();
		ledger2.setAccountNum("1004");
		ledger2.setCreditAmt((float) 100.00);
		ledger2.setDescription("Part of Test case 1b");
		generalLedgerDao.addEntry(ledger2);
		
		ledgerList = generalLedgerDao.getList();
		
		assertEquals(2,ledgerList.size());
	}
	
}


