package com.tech.plugandplay.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tech.plugandplay.model.Business;
import com.tech.plugandplay.model.Cluster;
import com.tech.plugandplay.model.Nodes;
import com.tech.plugandplay.model.Top100;
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
	
/*	public static Ventures getCompany(int id) {
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
	}*/
	
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
	
	public static Ventures newVenture(Ventures venture) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(venture);
			session.getTransaction().commit();
			return venture;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Top100 addTop100(Top100 top100) {
	
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(top100);
			session.getTransaction().commit();
			return top100;
		  
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
	
	public static Ventures updateVenture(Ventures venture) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(venture);
			session.getTransaction().commit();
			return venture;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Top100 updateTop100(Top100 top100) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(top100);
			session.getTransaction().commit();
			return top100;
		  
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
	
	public static void deleteVenture(Ventures venture) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();		
			session.delete(venture);
			session.getTransaction().commit();
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static void deleteTop100(Top100 top100) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();		
			session.delete(top100);
			session.getTransaction().commit();
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static void deleteVentureTop100(int id) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			SQLQuery sqlQuery = session.createSQLQuery("update Ventures set venture_id=NULL where id = "+id);
			sqlQuery.executeUpdate();
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
	
	public static List<Ventures> getAllVentures() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Ventures> ventures = session.createCriteria(Ventures.class).addOrder(Order.desc("id")).list();
	     session.getTransaction().commit();
	     
	     return ventures;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Ventures> getVentureTop100(String ids) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
	     //List<Ventures> ventures = session.createCriteria(Ventures.class).add(Restrictions.in("id", ids)).list();
		 List<Ventures> ventures = session.createQuery("from Ventures where id in ("+ids+")").list();
	     session.getTransaction().commit();
	     
	     return ventures;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Top100> getAllTop100() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Top100> top100 = session.createCriteria(Top100.class).addOrder(Order.asc("order")).list();
	     session.getTransaction().commit();
	     
	     return top100;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Ventures> getPortfolioCompanies() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Ventures> ventures = session.createQuery("from Ventures where portfolio = 1").list();
	     session.getTransaction().commit();
	     
	     return ventures;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
		public static Ventures getVenture(int id) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     Ventures ventures = (Ventures) session.get(Ventures.class, id);
	     session.getTransaction().commit();
	     
	     return ventures;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
		
	public static Top100 getTop100(int venture_id) {
			 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		     try {
		     session.getTransaction().begin();
		     
		     Top100 top100 = (Top100) session.get(Top100.class, venture_id);
		     session.getTransaction().commit();
		     
		     return top100;
		      
		     } catch (RuntimeException e) {
		         session.getTransaction().rollback();
		         log.fatal(e.getMessage(), e.fillInStackTrace());
		         return null;
		         //throw e;
		     }
	}
	
	public static List<Business> getAllBusinesses() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Business> businesses = session.createCriteria(Business.class).addOrder(Order.desc("id")).list();
	     session.getTransaction().commit();
	     
	     return businesses;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
}
