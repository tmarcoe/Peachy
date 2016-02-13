package com.gemma.spring.web.test.tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gemma.spring.web.dao.UserProfile;
import com.gemma.spring.web.dao.UserProfileDao;


@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/gemma/spring/web/config/dao-context.xml",
		"classpath:com/gemma/spring/web/config/security-context.xml",
		"classpath:com/gemma/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserProfileDao userProfileDao;


	@Before
	public void init() throws IOException {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

	    jdbc.execute("delete from generalledger");
		jdbc.execute("delete from invoiceheader");
		jdbc.execute("delete from invoiceitem");	
		jdbc.execute("delete from userprofile");
	}
	
	@After
	public void cleanup() throws IOException {
		loadUserProfile();
	}

	@Test
	public void testCreateUserProfile()  {
		List<UserProfile> userList = userProfileDao.getAllUsers();
		assertEquals(0, userList.size());
		UserProfile user1 = new UserProfile();
		user1.setFirstname("Timothy");
		user1.setLastname("Marcoe");
		user1.setMaleFemale("M");
		user1.setaddress1("40 S 7th St");
		user1.setaddress2("212-139");
		user1.setcity("Minneapolis");
		user1.setregion("MN");
		user1.setcountry("USA");
		user1.setHomePhone("16128061663");
		user1.setCellPhone("");
		user1.setUsername("tmtmarcoe80@gmail.com");
		user1.setPassword("helpme");
		user1.setMonthlyMailing(true);
		user1.setAuthority("ROLE_ADMIN");
		user1.setEnabled(true);
		user1.setDailySpecials(false);
		user1.setDateAdded(new Date());
		userProfileDao.create(user1);
		userList = userProfileDao.getAllUsers();
		assertEquals(1, userList.size());
		
		userProfileDao.delete(user1.getUsername());
		userList = userProfileDao.getAllUsers();
		assertEquals(0, userList.size());
		
	}
	
	@Test
	public void testGetAllUsers() throws IOException {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		List<UserProfile> userList = userProfileDao.getAllUsers();
		assertEquals(0, userList.size());
		loadUserProfile();
		userList = userProfileDao.getAllUsers();
		assertEquals(5, userList.size());
		jdbc.execute("delete from userprofile");
	}
	
	@Test
	public void testExists() throws IOException {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		assertEquals(false, userProfileDao.exists("marvak@aol.com"));
		loadUserProfile();
		assertEquals(true, userProfileDao.exists("marvak@aol.com"));
		jdbc.execute("delete from userprofile");
		assertEquals(false, userProfileDao.exists("marvak@aol.com"));
	}
	
	@Test
	public void testGetUser() throws IOException {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		loadUserProfile();
		UserProfile user = userProfileDao.getUser("eleanor_donzal34@yahoo.com");
		assertEquals("Eleanor", user.getFirstname());
		user = userProfileDao.getUserByID(28);
		assertEquals("Matt", user.getFirstname());
		jdbc.execute("delete from userprofile");		
	}
	
	private void loadUserProfile() throws IOException {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String sql = ReadFromFile();
		
		jdbc.execute(sql);
		

	}
	
	
	private String ReadFromFile() throws IOException {
	    BufferedReader reader = new BufferedReader( new FileReader ("C:\\Users\\Timothy Marcoe\\WebSite Archive\\Gemma\\test\\com\\gemma\\spring\\web\\test\\config\\userprofile.sql"));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");
	    try {
	        while( ( line = reader.readLine() ) != null ) {
	            stringBuilder.append( line );
	            stringBuilder.append( ls );
	        }
	        try {
	            while( ( line = reader.readLine() ) != null ) {
	                stringBuilder.append( line );
	                stringBuilder.append( ls );
	            }

	        } finally {
	            reader.close();
	        }
	        
	    } finally {
	        reader.close();
	    }
	    return stringBuilder.toString();
	}
}
