package com.gemma.spring.web.test.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

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

import com.gemma.web.dao.GeneralLedger;
import com.gemma.web.dao.GeneralLedgerDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/gemma/web/config/dao-context.xml",
		"classpath:com/gemma/web/config/security-context.xml",
		"classpath:com/gemma/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class GeneralLedgerTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GeneralLedgerDao generalLedgerDao;

	@Autowired
	private DataSource dataSource;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	
	@Before
	public void init(){
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from generalledger");
		jdbc.execute("delete from invoiceheader");
		jdbc.execute("delete from invoiceitem");
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


