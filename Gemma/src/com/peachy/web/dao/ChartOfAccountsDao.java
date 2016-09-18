package com.peachy.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("chartOfAccountsDao")
public class ChartOfAccountsDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void create(ChartOfAccounts chartOfAccounts) {
		session().save(chartOfAccounts);
		session().disconnect();

	}

	public void update(List<ChartOfAccounts> accounts) {
		for (ChartOfAccounts account : accounts) {
			session().saveOrUpdate(account);
		}
		session().disconnect();
	}

	public boolean delete(String accountNum) {
		ChartOfAccounts chartOfAccounts = getAccount(accountNum);
		session().delete("ChartOfAccounts", chartOfAccounts);
		session().disconnect();

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<ChartOfAccounts> listAccounts() {
		Criteria crit = session().createCriteria(ChartOfAccounts.class);
		session().disconnect();

		return crit.list();
	}

	public ChartOfAccounts getAccount(String Key) {
		Criteria crit = session().createCriteria(ChartOfAccounts.class);
		crit.add(Restrictions.idEq(Key));
		ChartOfAccounts chartOfAccounts = (ChartOfAccounts) crit.uniqueResult();
		session().disconnect();

		return chartOfAccounts;
	}

	public void save(ChartOfAccounts charOfaccounts) {
		session().save(charOfaccounts);
		session().disconnect();

	}

	public void update(ChartOfAccounts chartOfAccounts) {
		session().update(chartOfAccounts);
		session().disconnect();

	}

	public void debitAccount(ChartOfAccounts accounts, double amount) {
		if (accounts.isDebitAccount() == true) {
			amount *= -1;
		}
		String hql = "update ChartOfAccounts set accountBalance = accountBalance - :amount where accountNum = :accountNum";
		session().createQuery(hql).setDouble("amount", amount)
				.setString("accountNum", accounts.getAccountNum())
				.executeUpdate();
		session().disconnect();
	}

	public void creditAccount(ChartOfAccounts accounts, double amount) {
		if (accounts.isDebitAccount() == true) {
			amount *= -1;
		}
		String hql = "update ChartOfAccounts set accountBalance = accountBalance + :amount where accountNum = :accountNum";
		session().createQuery(hql).setDouble("amount", amount)
				.setString("accountNum", accounts.getAccountNum())
				.executeUpdate();
		session().disconnect();
	}

	public ChartOfAccounts getAccout(String account) {

		Criteria crit = session().createCriteria(ChartOfAccounts.class);
		crit.add(Restrictions.eq("accountNum", account));
		ChartOfAccounts chartOfAccounts = (ChartOfAccounts) crit.uniqueResult();
		session().disconnect();
		
		return chartOfAccounts;
	}

	public boolean exists(String key) {

		return (getAccount(key) != null);
	}

}
