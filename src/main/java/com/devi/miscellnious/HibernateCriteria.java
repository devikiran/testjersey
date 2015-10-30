package com.devi.miscellnious;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.controller.appartment.SessionFactoryapp;
import com.model.appartment.User;

public class HibernateCriteria extends SessionFactoryapp{
public static void main(String[] args) {
	Session session = getSessionFactory().openSession();
	Transaction tx = null;
	Criteria crit = session.createCriteria(User.class);
	crit.add( Restrictions.eq("emailid", "devikiran90@gmail.com"));
	//crit.setProjection(Projections.rowCount());
	System.out.println(" hello "+crit.setProjection(Projections.property("activation")));
	System.out.println(crit.uniqueResult());
/*	Long count =  (Long) crit.uniqueResult();
	System.out.println("result is "+count);*/
	/*Criteria crit = session.createCriteria(User.class);
	crit.add( Restrictions.eq("emailid", "dnvsk4u@gmail.com"));
	crit.setProjection(Projections.property("activation"));
	System.out.println(crit.uniqueResult());*/
	
}
}
