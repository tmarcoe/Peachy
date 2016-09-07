package com.peachy.spring.web.test.tests;

import static org.junit.Assert.*;

import java.util.Date;
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

import com.peachy.web.dao.Inventory;
import com.peachy.web.dao.InventoryDao;
import com.peachy.web.dao.InvoiceHeader;
import com.peachy.web.dao.InvoiceHeaderDao;
import com.peachy.web.dao.InvoiceItem;
import com.peachy.web.dao.InvoiceItemDao;

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
public class InvoiceTest {
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private InvoiceItemDao invoiceItemDao;
	
	@Autowired
	private InvoiceHeaderDao invoiceHeaderDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Before
	public void init(){
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from generalledger");
		jdbc.execute("delete from invoiceheader");
		jdbc.execute("delete from invoiceitem");	
	}

	@Test
	public void testAddHeader() {
		InvoiceHeader header = new InvoiceHeader();
		header.setUserID(24);
		header.setModified(new Date());
		invoiceHeaderDao.createHeader(header);
		header = invoiceHeaderDao.getOpenOrder(24);
		assertEquals(24, header.getUserID());
		Inventory inventory1 = inventoryDao.getItem("0049000032536");
		InvoiceItem item1 = new InvoiceItem(inventory1);
		item1.setInvoiceNum(header.getInvoiceNum());
		item1.setAmount(2);
		invoiceItemDao.addLineItem(item1);
		InvoiceItem itemCompare = invoiceItemDao.getInvoiceItem(item1.getInvoiceNum(),item1.getSkuNum());
		assertEquals(2, itemCompare.getAmount());
		List<InvoiceItem> invList = invoiceItemDao.getInvoice(header);
		assertEquals(1, invList.size());
		Inventory inventory2 = inventoryDao.getItem("01788500011");
		InvoiceItem item2 = new InvoiceItem(inventory2);
		item2.setInvoiceNum(header.getInvoiceNum());
		item2.setAmount(5);
		invoiceItemDao.addLineItem(item2);
		itemCompare = invoiceItemDao.getInvoiceItem(item2.getInvoiceNum(),item2.getSkuNum());
		assertEquals(5, itemCompare.getAmount());
		invList = invoiceItemDao.getInvoice(header);
		assertEquals(2, invList.size());
		invoiceItemDao.addLineItem(item1);
		invList = invoiceItemDao.getInvoice(header);
		assertEquals(2, invList.size());
		assertEquals(4, item1.getAmount());
		invoiceItemDao.deleteInvoiceItem(header.getInvoiceNum(), "0049000032536");
		invList = invoiceItemDao.getInvoice(header);
		assertEquals(1, invList.size());
	}

}
