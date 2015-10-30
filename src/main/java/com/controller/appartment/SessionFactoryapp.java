package com.controller.appartment;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.model.appartment.User;

public class SessionFactoryapp {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	public static void main(String[] args) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		/*
		 * User user = new User(); // user.setUserid(1236);
		 * user.setFirstname("DeviKiran"); user.setLastname("setty");
		 * user.setCity("Banglore"); user.setEmailid("devikiran90@gmail.com");
		 * user.setPhonenumber(1234567898); user.setPassword("password");
		 * 
		 * 
		 * 
		 * session.save(user);
		 */
		String result=(String)session.createCriteria(User.class).add(Restrictions.eq("emailid", "dnvsk4u@gmail.com"))
				.setProjection(Property.forName("password")).uniqueResult();
		System.out.println("result is  "+result);
		session.getTransaction().commit();
	}

	private static SessionFactory buildSessionFactory() {
		String hibernatePropsFilePath = "C:\\D\\practisejava\\apartmentDemo\\src\\hibernate.cfg.xml";
		File hibernatePropsFile = new File(hibernatePropsFilePath);

		Configuration configuration = new Configuration();
		configuration.configure(hibernatePropsFile);
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return configuration.buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
