package com.dao.apartment.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.controller.appartment.SessionFactoryapp;
import com.dao.apartment.UserDao;
import com.model.appartment.User;

public class UserDaoImpl extends SessionFactoryapp implements UserDao {
	Session session = getSessionFactory().openSession();
	Transaction tx = null;

	public boolean userLogin(String u, String p) {
		tx = session.beginTransaction();
		System.out.println("user name is "+u);
		System.out.println("password is "+p);
		
		String result=(String)session.createCriteria(User.class).add(Restrictions.eq("emailid", u))
				.setProjection(Property.forName("password")).uniqueResult();
		System.out.println("result is  "+result);
		return result.equalsIgnoreCase(p);
	}

}
