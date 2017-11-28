package com.tech.plugandplay.hibernate;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.CachingWrapperFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermQuery;
import org.hibernate.search.annotations.Factory;
import org.hibernate.search.annotations.Key;
import org.hibernate.search.filter.FilterKey;
import org.hibernate.search.filter.StandardFilterKey;

public class SecurityFilterFactory {
    private String[] pnpOffices;

    /**
     * injected parameter
     */
    public void setPnpOffices(String[] pnpOffices) {
        this.pnpOffices = pnpOffices;
    }

    @Key
    public FilterKey getKey() {
        StandardFilterKey key = new StandardFilterKey();
        key.addParameter(pnpOffices);
        return key;
    }

    @Factory
    public Filter getFilter() {
    	PhraseQuery pquery = new PhraseQuery();
    	for(int i = 0; i < pnpOffices.length; i++){
    		pquery.add(new Term("pnpOffice", pnpOffices[i]));
    	}    	
        //Query query = new TermQuery( new Term("level", level.toString() ) );
        return new CachingWrapperFilter( new QueryWrapperFilter(pquery) );
    }
}