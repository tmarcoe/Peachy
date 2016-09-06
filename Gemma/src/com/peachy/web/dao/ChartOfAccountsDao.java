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

import com.peachy.web.orm.ChartOfAccounts;

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

	}

	public void update(List<ChartOfAccounts> accounts) {
		for (ChartOfAccounts account : accounts) {
			session().saveOrUpdate(account);
		}
	}

	public boolean delete(String accountNum) {
		ChartOfAccounts chartOfAccounts = getAccount(accountNum);
		session().delete("ChartOfAccounts", chartOfAccounts);

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<ChartOfAccounts> listAccounts() {
		Criteria crit = session().createCriteria(ChartOfAccounts.class);

		return crit.list();
	}

	public ChartOfAccounts getAccount(String Key) {
		Criteria crit = session().createCriteria(ChartOfAccounts.class);
		crit.add(Restrictions.idEq(Key));

		return (ChartOfAccounts) crit.uniqueResult();
	}

	public void save(ChartOfAccounts charOfaccounts) {
		session().save(charOfaccounts);

	}

	public void update(ChartOfAccounts chartOfAccounts) {
		session().update(chartOfAccounts);

	}

	public void debitAccount(ChartOfAccounts accounts, double amount) {
		if (accounts.isDebitAccount() == true) {
			amount *= -1;
		}
		String hql = "update ChartOfAccounts set accountBalance = accountBalance - :amount where accountNum = :accountNum";
		session().createQuery(hql).setDouble("amount", amount)
				.setString("accountNum", accounts.getAccountNum())
				.executeUpdate();
	}

	public void creditAccount(ChartOfAccounts accounts, double amount) {
		if (accounts.isDebitAccount() == true) {
			amount *= -1;
		}
		String hql = "update ChartOfAccounts set accountBalance = accountBalance + :amount where accountNum = :accountNum";
		session().createQuery(hql).setDouble("amount", amount)
				.setString("accountNum", accounts.getAccountNum())
				.executeUpdate();
	}

	public ChartOfAccounts getAccout(String account) {

		Criteria crit = session().createCriteria(ChartOfAccounts.class);
		crit.add(Restrictions.eq("accountNum", account));
		return (ChartOfAccounts) crit.uniqueResult();
	}

	public boolean exists(String key) {

		return (getAccount(key) != null);
	}

}
