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

import com.tech.plugandplay.model.Batch;
import com.tech.plugandplay.model.BatchList;
import com.tech.plugandplay.model.Business;
import com.tech.plugandplay.model.Cluster;
import com.tech.plugandplay.model.Dealflow;
import com.tech.plugandplay.model.DealflowList;
import com.tech.plugandplay.model.Nodes;
import com.tech.plugandplay.model.Top100;
import com.tech.plugandplay.model.Top100List;
import com.tech.plugandplay.model.Top20;
import com.tech.plugandplay.model.Top20List;
import com.tech.plugandplay.model.Users;
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
	
	public static Dealflow addDealflow(Dealflow dealflow) {
		
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(dealflow);
			session.getTransaction().commit();
			return dealflow;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Batch addBatch(Batch batch) {
		
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(batch);
			session.getTransaction().commit();
			return batch;
		  
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
	
	public static DealflowList addDealflowList(DealflowList dealflowlist) {
		
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(dealflowlist);
			session.getTransaction().commit();
			return dealflowlist;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static BatchList addBatchList(BatchList batchlist) {
		
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(batchlist);
			session.getTransaction().commit();
			return batchlist;
		  
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
	
	public static Dealflow updateDealflow(Dealflow dealflow) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(dealflow);
			session.getTransaction().commit();
			return dealflow;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static Batch updateBatch(Batch batch) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(batch);
			session.getTransaction().commit();
			return batch;
		  
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
	
	public static DealflowList updateDealflowList(DealflowList dealflowlist) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(dealflowlist);
			session.getTransaction().commit();
			return dealflowlist;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static BatchList updateBatchList(BatchList batchlist) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(batchlist);
			session.getTransaction().commit();
			return batchlist;
		  
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
	
	public static void deleteBatch(Batch batch) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();		
			session.delete(batch);
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
	
	public static void deleteVentureBatch(int id) {
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
			 page = (page-1)*10;
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
			 page = (page-1)*10;
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
	
	public static List<Ventures> getVenturesFilterPage(int page, String companyName, String verticals, String tags, String stage, String blurb, 
			String location, String website, String pnpContact, String contactName, String phoneNumber, String totalMoneyRaised,
			String b2bb2c, String employees, String city, String competition, String advantage, String background, String founded,
			String partnerInterests, String caseStudy, String comments, String dateOfInvestment) {
		 if(page == 1){
			 page = 0;
		 } else {
			 page = (page-1)*10;
		 }
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		 
	     try {
	    	session.getTransaction().begin();
	    	
	    	if(companyName != null){
	    		session.enableFilter("byCompanyName").setParameter("companyNameFilter", '%'+companyName+'%');
	    	}
	    	if(verticals != null){
	    		session.enableFilter("byVerticals").setParameter("verticalsFilter", '%'+verticals+'%');
	    	}
	    	if(tags != null){
	    		session.enableFilter("byTags").setParameter("tagsFilter", '%'+tags+'%');
	    	}
	    	if(stage != null){
	    		session.enableFilter("byStage").setParameter("stageFilter", '%'+stage+'%');
	    	}
	    	if(blurb != null){
	    		session.enableFilter("byBlurb").setParameter("blurbFilter", '%'+blurb+'%');
	    	}
	    	if(location != null){
	    		session.enableFilter("byLocation").setParameter("locationFilter", '%'+location+'%');
	    	}
	    	if(website != null){
	    		session.enableFilter("byWebsite").setParameter("websiteFilter", '%'+website+'%');
	    	}
	    	if(pnpContact !=null){
	    		session.enableFilter("byPnpContact").setParameter("pnpContactFilter", '%'+pnpContact+'%');
	    	}
	    	if(contactName !=null){
	    		session.enableFilter("byContactName").setParameter("contactNameFilter", '%'+contactName+'%');
	    	}
	    	if(phoneNumber !=null){
	    		session.enableFilter("byPhoneNumber").setParameter("phoneNumberFilter", '%'+phoneNumber+'%');
	    	}
	    	if(totalMoneyRaised !=null){
	    		session.enableFilter("byTotalMoneyRaised").setParameter("totalMoneyRaisedFilter", '%'+totalMoneyRaised+'%');
	    	}
	    	if(b2bb2c !=null){
	    		session.enableFilter("byB2bb2c").setParameter("b2bb2cFilter", '%'+b2bb2c+'%');
	    	}
	    	if(employees != null){
	    		session.enableFilter("byEmployees").setParameter("employeesFilter", '%'+employees+'%');
	    	}
	    	if(city != null){
	    		session.enableFilter("byCity").setParameter("cityFilter", '%'+city+'%');
	    	}
	    	if(competition != null){
	    		session.enableFilter("byCompetition").setParameter("competitionFilter", '%'+competition+'%');
	    	}
	    	if(advantage != null){
	    		session.enableFilter("byAdvantage").setParameter("advantageFilter", '%'+advantage+'%');
	    	}
	    	if(background != null){
	    		session.enableFilter("byBackground").setParameter("backgroundFilter", '%'+background+'%');
	    	}
	    	if(founded != null){
	    		session.enableFilter("byFounded").setParameter("foundedFilter", '%'+founded+'%');
	    	}
	    	if(partnerInterests != null){
	    		session.enableFilter("byPartnerInterests").setParameter("partnerInterestsFilter", '%'+partnerInterests+'%');
	    	}
	    	if(caseStudy != null){
	    		session.enableFilter("byCaseStudy").setParameter("caseStudyFilter", '%'+caseStudy+'%');
	    	}
	    	if(comments != null){
	    		session.enableFilter("byComments").setParameter("commentsFilter", '%'+comments+'%');
	    	}
	    	if(dateOfInvestment != null){
	    		session.enableFilter("byDateOfInvestment").setParameter("dateOfInvestmentFilter", '%'+dateOfInvestment+'%');
	    	}
	    	
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
	
	public static int getVenturesFilterCount(String companyName, String verticals, String tags, String stage, String blurb, 
			String location, String website, String pnpContact, String contactName, String phoneNumber, String totalMoneyRaised,
			String b2bb2c, String employees, String city, String competition, String advantage, String background, String founded,
			String partnerInterests, String caseStudy, String comments, String dateOfInvestment) {

		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		 
	     try {
	    	session.getTransaction().begin();
		    
	    	if(companyName != null){
	    		session.enableFilter("byCompanyName").setParameter("companyNameFilter", '%'+companyName+'%');
	    	}
	    	if(verticals != null){
	    		session.enableFilter("byVerticals").setParameter("verticalsFilter", '%'+verticals+'%');
	    	}
	    	if(tags != null){
	    		session.enableFilter("byTags").setParameter("tagsFilter", '%'+tags+'%');
	    	}
	    	if(stage != null){
	    		session.enableFilter("byStage").setParameter("stageFilter", '%'+stage+'%');
	    	}
	    	if(blurb != null){
	    		session.enableFilter("byBlurb").setParameter("blurbFilter", '%'+blurb+'%');
	    	}
	    	if(location != null){
	    		session.enableFilter("byLocation").setParameter("locationFilter", '%'+location+'%');
	    	}
	    	if(website != null){
	    		session.enableFilter("byWebsite").setParameter("websiteFilter", '%'+website+'%');
	    	}
	    	if(pnpContact !=null){
	    		session.enableFilter("byPnpContact").setParameter("pnpContactFilter", '%'+pnpContact+'%');
	    	}
	    	if(contactName !=null){
	    		session.enableFilter("byContactName").setParameter("contactNameFilter", '%'+contactName+'%');
	    	}
	    	if(phoneNumber !=null){
	    		session.enableFilter("byPhoneNumber").setParameter("phoneNumberFilter", '%'+phoneNumber+'%');
	    	}
	    	if(totalMoneyRaised !=null){
	    		session.enableFilter("byTotalMoneyRaised").setParameter("totalMoneyRaisedFilter", '%'+totalMoneyRaised+'%');
	    	}
	    	if(b2bb2c !=null){
	    		session.enableFilter("byB2bb2c").setParameter("b2bb2cFilter", '%'+b2bb2c+'%');
	    	}
	    	if(employees != null){
	    		session.enableFilter("byEmployees").setParameter("employeesFilter", '%'+employees+'%');
	    	}
	    	if(city != null){
	    		session.enableFilter("byCity").setParameter("cityFilter", '%'+city+'%');
	    	}
	    	if(competition != null){
	    		session.enableFilter("byCompetition").setParameter("competitionFilter", '%'+competition+'%');
	    	}
	    	if(advantage != null){
	    		session.enableFilter("byAdvantage").setParameter("advantageFilter", '%'+advantage+'%');
	    	}
	    	if(background != null){
	    		session.enableFilter("byBackground").setParameter("backgroundFilter", '%'+background+'%');
	    	}
	    	if(founded != null){
	    		session.enableFilter("byFounded").setParameter("foundedFilter", '%'+founded+'%');
	    	}
	    	if(partnerInterests != null){
	    		session.enableFilter("byPartnerInterests").setParameter("partnerInterestsFilter", '%'+partnerInterests+'%');
	    	}
	    	if(caseStudy != null){
	    		session.enableFilter("byCaseStudy").setParameter("caseStudyFilter", '%'+caseStudy+'%');
	    	}
	    	if(comments != null){
	    		session.enableFilter("byComments").setParameter("commentsFilter", '%'+comments+'%');
	    	}
	    	if(dateOfInvestment != null){
	    		session.enableFilter("byDateOfInvestment").setParameter("dateOfInvestmentFilter", '%'+dateOfInvestment+'%');
	    	}
	    	
		    int count = session.createCriteria(Ventures.class).list().size();
		    session.getTransaction().commit();
		     
	     return count;
	      
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
	
	public static List<Ventures> getVentureDealflow(String ids) {
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
	
	public static List<Ventures> getVentureBatch(String ids) {
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
	
	public static List<Dealflow> getAllDealflow(String listName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<Dealflow> dealflow = session.createCriteria(Dealflow.class).add(Restrictions.eq("listNameDealflow", listName)).addOrder(Order.asc("order")).list();
	     session.getTransaction().commit();
	     
	     return dealflow;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<Batch> getAllBatch(String batchName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<Batch> batch = session.createCriteria(Batch.class).add(Restrictions.eq("batchName", batchName)).addOrder(Order.asc("order")).list();
	     session.getTransaction().commit();
	     
	     return batch;
	      
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
	
	public static List<Batch> getAllBatch() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<Batch> batch = session.createCriteria(Batch.class).addOrder(Order.asc("order")).list();
	     session.getTransaction().commit();
	     
	     return batch;
	      
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
	
	public static List<DealflowList> getDealflowLists() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<DealflowList> dealflowlist = session.createCriteria(DealflowList.class).add(Restrictions.eq("archive", new Boolean(false))).list();
	     session.getTransaction().commit();
	     
	     return dealflowlist;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<BatchList> getBatchLists() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<BatchList> batchlist = session.createCriteria(BatchList.class).add(Restrictions.eq("archive", new Boolean(false))).list();
	     session.getTransaction().commit();
	     
	     return batchlist;
	      
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
	
	public static List<DealflowList> getDealflowListsArchived() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<DealflowList> dealflowlist = session.createCriteria(DealflowList.class).add(Restrictions.eq("archive", new Boolean(true))).list();
	     session.getTransaction().commit();
	     
	     return dealflowlist;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         throw e;
	     }
	}
	
	public static List<BatchList> getBatchListsArchived() {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 List<BatchList> batchlist = session.createCriteria(BatchList.class).add(Restrictions.eq("archive", new Boolean(true))).list();
	     session.getTransaction().commit();
	     
	     return batchlist;
	      
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
	
	public static Dealflow getDealflow(int venture_id, String listName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Dealflow> dealflowlist = (List<Dealflow>) session.createCriteria(Dealflow.class).add(Restrictions.eq("listNameDealflow", listName)).add(Restrictions.eq("venture_id", venture_id)).list();
	    		 //session.get(Top100.class, venture_id);
	     session.getTransaction().commit();
	     Dealflow dealflow = new Dealflow();
	     if(!dealflowlist.isEmpty()){
	    	 dealflow = (Dealflow) dealflowlist.get(0);
		     //log.info("Found top20: "+top20.getVenture_id()+" "+top20.getListName());
		     return dealflow; 
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
	
	public static Batch getBatch(int venture_id, String batchName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Batch> batchlist = (List<Batch>) session.createCriteria(Batch.class).add(Restrictions.eq("batchName", batchName)).add(Restrictions.eq("venture_id", venture_id)).list();
	    		 //session.get(Top100.class, venture_id);
	     session.getTransaction().commit();
	     Batch batch = new Batch();
	     if(!batchlist.isEmpty()){
	    	 batch = (Batch) batchlist.get(0);
		     //log.info("Found top20: "+top20.getVenture_id()+" "+top20.getListName());
		     return batch; 
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
	
	public static DealflowList getDealflowList(int id) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     DealflowList dealflowlist = (DealflowList) session.get(DealflowList.class, id);
	     session.getTransaction().commit();
	     
	     return dealflowlist;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         return null;
	         //throw e;
	     }
	}
	
	public static BatchList getBatchList(int id) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     BatchList batchlist = (BatchList) session.get(BatchList.class, id);
	     session.getTransaction().commit();
	     
	     return batchlist;
	      
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
	
	public static DealflowList getDealflowListByName(String listName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     @SuppressWarnings("rawtypes")
		 List tmp = session.createCriteria(DealflowList.class).add(Restrictions.eq("listNameDealflow", listName)).list();
	     session.getTransaction().commit();
	     DealflowList dealflowlist = new DealflowList();
	     if(!tmp.isEmpty()){
	    	 dealflowlist = (DealflowList) tmp.get(0);
		     //log.info("Found list: "+top20list.getListName());
		     return dealflowlist; 
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
	
	public static BatchList getBatchListByName(String listName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     @SuppressWarnings("rawtypes")
		 List tmp = session.createCriteria(BatchList.class).add(Restrictions.eq("listName", listName)).list();
	     session.getTransaction().commit();
	     BatchList batchlist = new BatchList();
	     if(!tmp.isEmpty()){
	    	 batchlist = (BatchList) tmp.get(0);
		     //log.info("Found list: "+top20list.getListName());
		     return batchlist; 
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
	
	public static List<Ventures> getVenturesByEmail(String email) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Ventures> ventures = session.createCriteria(Ventures.class).add(Restrictions.like("email", email)).list();
	     session.getTransaction().commit();
	     
	     return ventures;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         return null;
	     }
	}
	
	public static Ventures getVentureByCompanyName(String companyName) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Ventures> ventures = session.createCriteria(Ventures.class).add(Restrictions.like("companyName", companyName)).list();
	     session.getTransaction().commit();
	     
	     return ventures.get(0);
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         return null;
	     }
	}
	
	public static Users newUser(Users user) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(user);
			session.getTransaction().commit();
			return user;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
	
	public static List<Users> getUserByEmail(String email) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		List<Users> users = session.createCriteria(Users.class).add(Restrictions.like("email", email)).list();
	     session.getTransaction().commit();
	     
	     return users;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         return null;
	     }
	}
	
	public static Users getUserById(int id) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
		 Users user = (Users) session.get(Users.class, id);
	     session.getTransaction().commit();
	     
	     return user;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         return null;
	     }
	}
	
	public static List<Users> getUserByApiKey(String api_key) {
		 Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
	     try {
	     session.getTransaction().begin();
	     
	     @SuppressWarnings("unchecked")
	     List<Users> users = session.createCriteria(Users.class).add(Restrictions.like("api_key", api_key)).list();
	     session.getTransaction().commit();
	     
	     return users;
	      
	     } catch (RuntimeException e) {
	         session.getTransaction().rollback();
	         log.fatal(e.getMessage(), e.fillInStackTrace());
	         return null;
	     }
	}
	public static Users updateUser(Users user) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.update(user);
			session.getTransaction().commit();
			return user;
		  
		} catch (RuntimeException e) {
		     session.getTransaction().rollback();
		     log.fatal(e.getMessage(), e.fillInStackTrace());
		     throw e;
		}
	}
}
