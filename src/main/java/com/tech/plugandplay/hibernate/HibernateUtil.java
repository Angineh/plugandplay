package com.tech.plugandplay.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.tech.plugandplay.model.Business;
import com.tech.plugandplay.model.Cluster;
import com.tech.plugandplay.model.Nodes;
import com.tech.plugandplay.model.Ventures;

public class HibernateUtil {
	
	private static Log log = LogFactory.getLog(HibernateUtil.class);
	
	public static Cluster getCluster(int clusterrequestid) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     Cluster cluster = (Cluster) session.get(Cluster.class, clusterrequestid);
	     session.getTransaction().commit();
	     
	     return cluster;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static Ventures getCompany(int id) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     Ventures company = (Ventures) session.get(Ventures.class, id);
	     session.getTransaction().commit();
	     
	     return company;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static Business getBusiness(int id) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     Business business = (Business) session.get(Business.class, id);
	     session.getTransaction().commit();
	     
	     return business;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static Nodes getNode(int nodeId) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     Nodes node = (Nodes) session.get(Nodes.class, nodeId);
	     session.getTransaction().commit();
	     
	     return node;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}

	public static Cluster newCluster(Cluster cluster) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(cluster);
			session.getTransaction().commit();
			return cluster;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Ventures newCompany(Ventures company) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(company);
			session.getTransaction().commit();
			return company;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Business newCompany(Business business) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(business);
			session.getTransaction().commit();
			return business;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Cluster updateCluster(Cluster cluster) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(cluster);
			session.getTransaction().commit();
			return cluster;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Ventures updateCompany(Ventures company) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(company);
			session.getTransaction().commit();
			return company;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Business updateBusiness(Business business) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(business);
			session.getTransaction().commit();
			return business;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static void deleteCluster(Cluster cluster) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();		
			session.delete(cluster);
			session.getTransaction().commit();
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static void deleteCompany(Ventures company) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();		
			session.delete(company);
			session.getTransaction().commit();
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static List<Cluster> getAllClusters() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Cluster> clusters = session.createCriteria(Cluster.class).list();
	     session.getTransaction().commit();
	     
	     return clusters;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Ventures> getAllCompanies() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Ventures> companies = session.createCriteria(Ventures.class).list();
	     session.getTransaction().commit();
	     
	     return companies;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Business> getAllBusinesses() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Business> businesses = session.createCriteria(Business.class).list();
	     session.getTransaction().commit();
	     
	     return businesses;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
}
