package com.tech.plugandplay.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.tech.plugandplay.model.Business;
import com.tech.plugandplay.model.Cluster;
import com.tech.plugandplay.model.Nodes;
import com.tech.plugandplay.model.Top100;
import com.tech.plugandplay.model.Top100List;
import com.tech.plugandplay.model.Top20;
import com.tech.plugandplay.model.Top20List;
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
	
	public static Top20 addTop20(Top20 top20) {
		
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(top20);
			session.getTransaction().commit();
			return top20;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Top100List addTop100List(Top100List top100list) {
		
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(top100list);
			session.getTransaction().commit();
			return top100list;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Top20List addTop20List(Top20List top20list) {
		
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(top20list);
			session.getTransaction().commit();
			return top20list;
		  
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
	
	public static Top20 updateTop20(Top20 top20) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(top20);
			session.getTransaction().commit();
			return top20;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Top100List updateTop100List(Top100List top100list) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(top100list);
			session.getTransaction().commit();
			return top100list;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Top20List updateTop20List(Top20List top20list) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(top20list);
			session.getTransaction().commit();
			return top20list;
		  
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
	
	public static void deleteTop20(Top20 top20) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();		
			session.delete(top20);
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
			SQLQuery sqlQuery = session.createSQLQuery("update ventures set venture_id=NULL where id = "+id);
			sqlQuery.executeUpdate();
			session.getTransaction().commit();
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static void deleteVentureTop20(int id) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			SQLQuery sqlQuery = session.createSQLQuery("update ventures set venture_id=NULL where id = "+id);
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
	
	public static List<Ventures> getVenturesPage(int page) {
		if(page == 1){
			 page = 0;
		 } else {
			 page = page*10;
		 }
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Ventures> ventures = session.createCriteria(Ventures.class).addOrder(Order.desc("id")).setFirstResult(page).setMaxResults(10).list();
	     session.getTransaction().commit();
	     
	     return ventures;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static void updateLuceneIndex() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		 FullTextSession fullTextSession = Search.getFullTextSession(session);
		 try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			log.fatal("Could not create hibernate search index: "+e1.getMessage(),e1.fillInStackTrace());
		}
	}
	
	public static List<Ventures> getVenturesPage(int page, String query) {
		 if(page == 1){
			 page = 0;
		 } else {
			 page = page*10;
		 }
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		 FullTextSession fullTextSession = Search.getFullTextSession(session);
	     try {
	    	 Transaction tx = fullTextSession.beginTransaction();
	    	// create native Lucene query unsing the query DSL
	    	// alternatively you can write the Lucene query using the Lucene query parser
	    	// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
	    	QueryBuilder qb = fullTextSession.getSearchFactory()
	    	    .buildQueryBuilder().forEntity( Ventures.class ).get();
	    	org.apache.lucene.search.Query lq = qb.keyword().onFields("companyName", "blurb", "verticals", "website", "pnpContact", "contactName", "email", "phoneNumber", "totalMoneyRaised", "stage", "b2bb2c", "employees", "location", "city", "competition", "advantage", "background", "founded", "partnerInterests", "caseStudy", "comments", "tags").matching(query).createQuery();
	   
	    	// wrap Lucene query in a org.hibernate.Query
	    	org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(lq, Ventures.class);

	    	// execute search
	    	@SuppressWarnings("unchecked")
			List<Ventures> ventures = hibQuery.setFirstResult(page).setMaxResults(10).list();
	    	  
	    	tx.commit();
	     
	     return ventures;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	public static int getVenturesSearchCount(String query) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		 FullTextSession fullTextSession = Search.getFullTextSession(session);
	     try {
	    	 Transaction tx = fullTextSession.beginTransaction();
	    	// create native Lucene query unsing the query DSL
	    	// alternatively you can write the Lucene query using the Lucene query parser
	    	// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
	    	QueryBuilder qb = fullTextSession.getSearchFactory()
	    	    .buildQueryBuilder().forEntity( Ventures.class ).get();
	    	org.apache.lucene.search.Query lq = qb.keyword().onFields("companyName", "blurb", "verticals", "website", "pnpContact", "contactName", "email", "phoneNumber", "totalMoneyRaised", "stage", "b2bb2c", "employees", "location", "city", "competition", "advantage", "background", "founded", "partnerInterests", "caseStudy", "comments", "tags").matching(query).createQuery();
	    	
	    	// wrap Lucene query in a org.hibernate.Query
	    	int count = fullTextSession.createFullTextQuery(lq, Ventures.class).getResultSize();

	    	tx.commit();
	     
	     return count;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static long getVenturesCount() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 long count = (long) session.createCriteria(Ventures.class).setProjection(Projections.rowCount()).uniqueResult();
	     session.getTransaction().commit();
	     
	     return count;
	      
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
	     //log.info("Ids:"+ids);
	     @SuppressWarnings("unchecked")
	     //List<Ventures> ventures = session.createCriteria(Ventures.class).add(Restrictions.in("id", ids)).list();
		 List<Ventures> ventures = session.createQuery("from Ventures where id in ("+ids+") ORDER BY FIELD(id,"+ids+")").list();
	     session.getTransaction().commit();
	     
	     return ventures;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Ventures> getVentureTop20(String ids) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     //log.info("Ids:"+ids);
	     @SuppressWarnings("unchecked")
	     //List<Ventures> ventures = session.createCriteria(Ventures.class).add(Restrictions.in("id", ids)).list();
		 List<Ventures> ventures = session.createQuery("from Ventures where id in ("+ids+") ORDER BY FIELD(id,"+ids+")").list();
	     session.getTransaction().commit();
	     
	     return ventures;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Top100> getAllTop100(String listName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<Top100> top100 = session.createCriteria(Top100.class).add(Restrictions.eq("listName", listName)).addOrder(Order.asc("order")).list();
	     session.getTransaction().commit();
	     
	     return top100;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Top20> getAllTop20(String listName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<Top20> top20 = session.createCriteria(Top20.class).add(Restrictions.eq("listNameTop20", listName)).addOrder(Order.asc("order")).list();
	     session.getTransaction().commit();
	     
	     return top20;
	      
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
	
	public static List<Top20> getAllTop20() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<Top20> top20 = session.createCriteria(Top20.class).addOrder(Order.asc("order")).list();
	     session.getTransaction().commit();
	     
	     return top20;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Top100List> getTop100Lists() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<Top100List> top100list = session.createCriteria(Top100List.class).add(Restrictions.eq("archive", new Boolean(false))).list();
	     session.getTransaction().commit();
	     
	     return top100list;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Top20List> getTop20Lists() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<Top20List> top20list = session.createCriteria(Top20List.class).add(Restrictions.eq("archive", new Boolean(false))).list();
	     session.getTransaction().commit();
	     
	     return top20list;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Top100List> getTop100ListsArchived() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<Top100List> top100list = session.createCriteria(Top100List.class).add(Restrictions.eq("archive", new Boolean(true))).list();
	     session.getTransaction().commit();
	     
	     return top100list;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Top20List> getTop20ListsArchived() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<Top20List> top20list = session.createCriteria(Top20List.class).add(Restrictions.eq("archive", new Boolean(true))).list();
	     session.getTransaction().commit();
	     
	     return top20list;
	      
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
		
	public static Top100 getTop100(int venture_id, String listName) {
			 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		     try {
		     session.getTransaction().begin();
		     
		     @SuppressWarnings("unchecked")
			List<Top100> top100list = (List<Top100>) session.createCriteria(Top100.class).add(Restrictions.eq("listName", listName)).add(Restrictions.eq("venture_id", venture_id)).list();
		    		 //session.get(Top100.class, venture_id);
		     session.getTransaction().commit();
		     Top100 top100 = new Top100();
		     if(!top100list.isEmpty()){
		    	 top100 = (Top100) top100list.get(0);
			     //log.info("Found top100: "+top100.getVenture_id()+" "+top100.getListName());
			     return top100; 
		     }else{
		    	 return null;
		     }
		      
		     } catch (RuntimeException e) {
		         session.getTransaction().rollback();
		         log.fatal(e.getMessage(), e.fillInStackTrace());
		         return null;
		         //throw e;
		     }
	}
	
	public static Top20 getTop20(int venture_id, String listName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Top20> top20list = (List<Top20>) session.createCriteria(Top20.class).add(Restrictions.eq("listNameTop20", listName)).add(Restrictions.eq("venture_id", venture_id)).list();
	    		 //session.get(Top100.class, venture_id);
	     session.getTransaction().commit();
	     Top20 top20 = new Top20();
	     if(!top20list.isEmpty()){
	    	 top20 = (Top20) top20list.get(0);
		     //log.info("Found top20: "+top20.getVenture_id()+" "+top20.getListName());
		     return top20; 
	     }else{
	    	 return null;
	     }
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         return null;
	         //throw e;
	     }
}
	
	public static Top100List getTop100List(int id) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     Top100List top100list = (Top100List) session.get(Top100List.class, id);
	     session.getTransaction().commit();
	     
	     return top100list;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         return null;
	         //throw e;
	     }
	}
	
	public static Top20List getTop20List(int id) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     Top20List top20list = (Top20List) session.get(Top20List.class, id);
	     session.getTransaction().commit();
	     
	     return top20list;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         return null;
	         //throw e;
	     }
	}
	
	public static Top100List getTop100ListByName(String listName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     @SuppressWarnings("rawtypes")
		 List tmp = session.createCriteria(Top100List.class).add(Restrictions.eq("listName", listName)).list();
	     session.getTransaction().commit();
	     Top100List top100list = new Top100List();
	     if(!tmp.isEmpty()){
	    	 top100list = (Top100List) tmp.get(0);
		     //log.info("Found list: "+top100list.getListName());
		     return top100list; 
	     }else{
	    	 return null;
	     }
	     
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         return null;
	         //throw e;
	     }
	}
	
	public static Top20List getTop20ListByName(String listName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     @SuppressWarnings("rawtypes")
		 List tmp = session.createCriteria(Top20List.class).add(Restrictions.eq("listNameTop20", listName)).list();
	     session.getTransaction().commit();
	     Top20List top20list = new Top20List();
	     if(!tmp.isEmpty()){
	    	 top20list = (Top20List) tmp.get(0);
		     //log.info("Found list: "+top20list.getListName());
		     return top20list; 
	     }else{
	    	 return null;
	     }
	     
	      
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
