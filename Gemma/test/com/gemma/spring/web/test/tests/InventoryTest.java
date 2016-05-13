package com.gemma.spring.web.test.tests;

import java.util.List;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gemma.web.dao.Inventory;
import com.gemma.web.dao.InventoryDao;
import com.gemma.web.dao.InvoiceItem;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/gemma/web/config/dao-context.xml",
		"classpath:com/gemma/web/config/file-context.xml",
		"classpath:com/gemma/web/config/pager-context.xml",
		"classpath:com/gemma/web/config/security-context.xml",
		"classpath:com/gemma/web/config/service-context.xml",
		"classpath:com/gemma/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class InventoryTest {
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Autowired
	private DataSource dataSource;

	@Before
	public void init(){
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String resetInventory = "INSERT INTO `inventory` (`skuNum`, `productName`," +
								" `category`, `subcategory`, `amtInStock`," +
								" `amtCommitted`, `minQuantity`, `salePrice`," +
								" `discountPrice`, `onSale`, `taxAmt`, `weight`, `description`, `image`) VALUES" +
								"('0049000032536', 'Coca Cola', 'Drink', 'Coke', 100, 0, 10, 20, 15, b'1', 1, 0," +
								" 'Coca Cola in 1 liter bottle.', 'img2868572884261730510.png')," +
								"('012044037591', 'Old Spice Deodorant', 'Personal Care', 'Deodorant'," +
								" 100, 0, 10, 40, 35, b'0', 1, 0, 'Old Spice underarm deodorant. Fiji spice'," +
								" 'img1903639752864654849.png')," +
								"('01788500011', 'Slap ''ya Momma Cajun Spices', 'Food', 'Seasoning', 99, 0," +
								" 10, 60, 55, b'1', 1, 0, 'Slap ya mama Cajun spices (extra hot).'," +
								" 'img2420448772141923689.png')," +
								"('026100003048', 'Newport Cigarettes', 'Cigarettes', 'Menthol', 100," +
								" 0, 10, 100, 95, b'0', 1, 0, 'Newport Menthol 100 Cigarettes, single pack'," +
								" 'img7116038400427907833.png')," +
								"('028200006202', 'Basic Filtered Cigarettes', 'Cigarettes', 'Non-Menthol'," +
								" 200, 0, 10, 110, 100, b'0', 1, 0, 'Basic Non-menthol filtered cigarettes'," +
								" 'img8134954751409035556.png')," +
								"('12345664', 'Test Product', 'Test', 'Test', 100, 0, 10, 20, 15, b'0', 0, 0," +
								" 'This is a test product', 'img6723207089305036380.png')," +
								"('1428500111', 'UFC Banana Catsup', 'Food', 'Seasoning', 100, 0, 10, 50, 45," +
								" b'0', 1, 0, 'UFC Tamis Anghang banana catsup', 'img5027160663613073364.png')," +
								"('15216', 'Pale Pilsen', 'Drink', 'Beer', 88, 0, 10, 50, 45, b'0', 1, 0," +
								" 'San Miguel Pale Pilsen beer.', 'img8363452072949527142.png')," +
								"('2767', '7 up 1 Liter Bottle', 'Drink', '7 up', 100, 0, 10, 20, 15," +
								" b'0', 1, 0, '7 up in 1 little bottle', 'img6885617661274203319.png')," +
								"('4002309006567', 'Goya Hazelnut Choco spread', 'Food', 'Spread', 100," +
								" 0, 10, 25, 20, b'0', 1, 0, 'Goya choco Hazelnut Spread', 'img1537100259815714985.png')," +
								"('4800361310031', 'Nestl&eacute; Corn Flakes', 'Food', 'Cereal', 100, 0, 10, 32, 30, b'0'," +
								" 1, 0, 'Nestl&eacute; Corn Flakes, made with whole grain. Econo Pack'," +
								" 'img7563086705897286784.png')," +
								"('4800361386036', 'Nescaf&eacute; 3 in 1', 'Food', 'Coffee', 100, 0, 10," +
								" 10, 9, b'0', 1, 0, 'Nescaf&eacute; 3 in 1 Original instant coffee mix.'," +
								" 'img5068127674836206749.png')," +
								"('4806502720097', 'Gardenia Classic Bread', 'Food', 'Bread', 100, 0, 10, 15," +
								" 10, b'0', 1, 0, 'Gardenia Classic Bread, American Recipe.', 'img1792027658668581741.png')," +
								"('4807770272134', 'Supreme Chicken Mami', 'Food', 'Soup', 100, 0, 10, 22, 18, b'1', 1, 0," +
								" 'Lucky Me Supreme Chicken Mami noodles', 'img6971504026996172176.png')," +
								"('4808680230979', 'Knorr Liquid Seasoning', 'Food', 'Seasoning', 100, 0, 10," +
								" 30, 25, b'0', 1, 0, 'Knorr liquid seasoning, Original flavor', 'img3412089260494104721.png')," +
								"('4809014224282', 'Cosmo Skin Advanced Skin Whitening Technique'," +
								" 'Personal Care', 'Skin Cream', 100, 0, 10, 60, 55, b'0', 1, 0," +
								" 'Cosmo Skin Advanced Whitening, Nourishing and Collagen.', 'img197251475069241250.png')," +
								"('74848510009', 'Century Hot and Spicy Tuna', 'Food', 'Seafood', 100, 0, 10, 60, 55, b'0'," +
								" 1, 0, 'Century Tuna, Hot and Spicy', 'img5061990955997734610.png')," +
								"('955519250443', 'Wrigley''s Extra', 'Snacks', 'Gum', 100, 0, 10, 12," +
								" 9, b'0', 1, 0, 'Wrigley''s Etra Sweetmint Flavor gum', 'img5777594822204493399.png');";

		
		jdbc.execute("delete from inventory");
		jdbc.execute(resetInventory);
	}
	
	@Test
	public void testCreateDelete() {
		List<Inventory> invList = inventoryDao.listProducts();
		assertEquals(18, invList.size());
		inventoryDao.delete("0049000032536");
		invList = inventoryDao.listProducts();
		assertEquals(17, invList.size());
		Inventory inventory = new Inventory();
		inventory.setSkuNum("0049000032536");
		inventory.setProductName("Coca Cola");
		inventory.setCategory("Drink");
		inventory.setSubCategory("Coke");
		inventory.setAmtInStock(100);
		inventory.setAmtCommitted(0);
		inventory.setMinQuantity(10);
		inventory.setSalePrice(20);
		inventory.setDiscountPrice(15);
		inventory.setOnSale(false);
		inventory.setTaxAmt(1);
		inventory.setWeight(0);
		inventory.setDescription("Coca Cola in 1 liter bottle.");
		inventory.setImage("img2868572884261730510.png");
		inventoryDao.create(inventory);
		invList = inventoryDao.listProducts();
		assertEquals(18, invList.size());
	}
	
	@Test
	public void testReplenish() {
		Inventory inventory = inventoryDao.getItem("4002309006567");
		List<Inventory> invList = inventoryDao.getReplenishList();
		assertEquals(0, invList.size());
		
		inventory.setAmtInStock(9);
		inventoryDao.update(inventory);
		 invList = inventoryDao.getReplenishList();
		
		assertEquals(1, invList.size());
		inventory = inventoryDao.getItem("4002309006567");
		inventory.setAmtInStock(100);
		inventoryDao.update(inventory);
		
		invList = inventoryDao.getReplenishList();
		assertEquals(0, invList.size());
	}
	
	@Test
	public void testSaleItems() {
		List<Inventory> inv = inventoryDao.listSaleItems();
		assertEquals(3, inv.size());
	}

	@Test
	public void testGetItem() {
		Inventory inv = inventoryDao.getItem("955519250443");
		
		assertEquals("Wrigley's Extra", inv.getProductName());
		
	}
	
	@Test
	public void testDepleteInventory() {
		Inventory inv = inventoryDao.getItem("15216");
		assertEquals(88, inv.getAmtInStock());
		InvoiceItem item = new InvoiceItem(inv);
		item.setAmount(1);
		inventoryDao.depleteInventory(item);
		inv = inventoryDao.getItem(item.getSkuNum());
		assertEquals(87, inv.getAmtInStock());
		inv.setAmtInStock(88);
		inventoryDao.update(inv);
		inv = inventoryDao.getItem(item.getSkuNum());
		assertEquals(88, inv.getAmtInStock());
	}

}
