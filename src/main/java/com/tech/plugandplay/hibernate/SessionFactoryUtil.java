package com.tech.plugandplay.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionFactoryUtil {
	
	 private static final SessionFactory sessionFactory;
	 private static final ServiceRegistry serviceRegistry;
	 private static final Configuration configuration;
	    
	    static {
	        try {            
	            /*
	             * Build a SessionFactory object from session-factory configuration 
	             * defined in the hibernate.cfg.xml file. In this file we register 
	             * the JDBC connection information, connection pool, the hibernate 
	             * dialect that we used and the mapping to our hbm.xml file for each 
	             * POJO (Plain Old Java Object).
	             * 
	             */
	        	configuration = new Configuration();
	            configuration.configure("hibernate.cfg.xml");
				serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry(); 
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	        } catch (Throwable e) {
	            System.err.println("Error in creating SessionFactory object." 
	                + e.getMessage());
	            throw new ExceptionInInitializerError(e);
	        }
	    }
	    
	    /*
	     * A static method for other application to get SessionFactory object 
	     * initialized in this helper class.
	     * 
	     */
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

}
