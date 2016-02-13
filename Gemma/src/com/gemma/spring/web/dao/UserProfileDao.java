package com.gemma.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
@Component("userProfileDao")
public class UserProfileDao {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void create(UserProfile user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		session().save(user);
	}
	
	public boolean delete(String username) {
		Query query = session().createQuery("delete from UserProfile where username=:username");
		query.setString("username", username);
		return query.executeUpdate() == 1;
	}
	
	public UserProfile getUser(String username) {
		Criteria crit = session().createCriteria(UserProfile.class);
		crit.add(Restrictions.eq("username", username));
		return (UserProfile)crit.uniqueResult();
	}

	public boolean exists(String username) {
		UserProfile user = getUser(username);
		return user != null;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> getAllUsers() {
		
		Criteria crit = session().createCriteria(UserProfile.class);
		return crit.list();
	}


	public void partialUpdate(UserProfile user) {
		Transaction tx = session().beginTransaction();
		updateBilling(user);
		updateContactInfo(user);
		updateMisc(user);
		tx.commit();
	}
	
	public void updateBilling(UserProfile user) {
		String hqlUpdate = "update UserProfile as u set address1 = :address1, " +
												"address2 = :address2, " +
												"city = :city, " +
												"region = :region, " +
												"postalCode = :postalCode, " +
												"country = :country " +
												"where userID=:userID";
		
		session().createQuery(hqlUpdate)
			.setString("address1", user.getaddress1())
			.setString("address2", user.getaddress2())
			.setString("city", user.getcity())
			.setString("region", user.getregion())
			.setString("postalCode", user.getpostalCode())
			.setString("country", user.getcountry())
			.setInteger("userID", user.getUserID())
			.executeUpdate();	
	}
	
	
	public void updateContactInfo(UserProfile user) {
		String hqlUpdate = "update UserProfile as u set firstname = :firstname, " +
												"lastname = :lastname, " +
												"maleFemale = :maleFemale, " +
												"homePhone = :homePhone, " +
												"cellPhone = :cellPhone, " +
												"username = :username " +
												"where userID=:userID";
	
		session().createQuery(hqlUpdate).setString("firstname", user.getFirstname())
										.setString("lastname", user.getLastname())
										.setString("maleFemale", user.getMaleFemale())
										.setString("homePhone", user.getHomePhone())
										.setString("cellPhone", user.getCellPhone())
										.setString("username",user.getUsername())
										.setInteger("userID", user.getUserID())
										.executeUpdate();	
	}
	
	public void updateMisc(UserProfile user) {
		String hqlUpdate = "update UserProfile as u set monthlyMailing = :monthlyMailing, " +
												"authority = :authority, " +
												"enabled = :enabled, " +
												"dailySpecials = :dailySpecials, " +
												"enabled = :enabled " +
												"where userID=:userID";
		
		session().createQuery(hqlUpdate).setBoolean("monthlyMailing", user.isMonthlyMailing())
										.setString("authority", user.getAuthority())
										.setBoolean("enabled", user.isEnabled())
										.setBoolean("dailySpecials", user.isDailySpecials())
										.setBoolean("enabled", user.isEnabled())
										.setInteger("userID", user.getUserID())
										.executeUpdate();	
	}
	
	public void updatePassword(UserProfile user) {
		String hqlUpdate = "update UserProfile as u set password = :password where userID=:userID";
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().createQuery(hqlUpdate).setString("password", user.getPassword())
										.setInteger("userID", user.getUserID())
										.executeUpdate();	
	}

	public void updateProfile(UserProfile user) {
		session().saveOrUpdate(user);
	}

	public UserProfile getUserByID(int userID) {
		String hql = "from UserProfile where userID = :userID";
		
		return (UserProfile) session().createQuery(hql).setInteger("userID", userID).uniqueResult();
	}
	
	
}
