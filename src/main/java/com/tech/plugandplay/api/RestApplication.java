package com.tech.plugandplay.api;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.ws.rs.core.Application;

public class RestApplication extends Application {
	
    private Set<Object> singletons = new HashSet<Object>();
    public RestApplication() {
        singletons.add(new RestService());
    }
    @Override
    public Set<Object> getSingletons() {
    	 if (singletons == null) {
             CORSFilter corsFilter = new CORSFilter();

             singletons = new LinkedHashSet<Object>();
             singletons.add(corsFilter);
         }
         return singletons;
    }
} 
