package com.brocade.dcm.server.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryUpdatedListener;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.CacheMetrics;
import org.apache.ignite.cache.query.ContinuousQuery;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.mxbean.CacheMetricsMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brocade.dcm.domain.model.Dept;

@Service
public class ObjectCacheLookupService {
	
	@Autowired
	private Ignite ignite;
	
	private IgniteCache deptMapperCache;
	
	@PostConstruct
	public void setupViewCache() {
		System.out.println("*******************TESTING******************* : " + ignite.name());
		deptMapperCache = ignite.cache("com.brocade.dcm.domain.mapper.DeptMapper");
		CacheMetricsMXBean cacheMetricsMXBean = deptMapperCache.localMxBean();
		CacheMetrics cacheMetrics = deptMapperCache.localMetrics();
		System.out.println("*******************TESTING******************* KEY TYPE : " + cacheMetricsMXBean.getKeyType());
		System.out.println("*******************TESTING******************* VALUE TYPE : " + cacheMetricsMXBean.getValueType());
		System.out.println("*******************TESTING******************* KEY TYPE : " + cacheMetrics.getKeyType());
		System.out.println("*******************TESTING******************* VALUE TYPE : " + cacheMetrics.getValueType());
		
		ContinuousQuery<Object, Object> qry = new ContinuousQuery<>();
		qry.setInitialQuery(new ScanQuery<>(new IgniteBiPredicate<Object, Object>() {
            @Override public boolean apply(Object key, Object val) {
                return true;
            }
        }));
			
		qry.setLocalListener(new CacheEntryUpdatedListener<Object, Object>() {
            @Override public void onUpdated(Iterable<CacheEntryEvent<? extends Object, ? extends Object>> evts) {
                for (CacheEntryEvent<? extends Object, ? extends Object> e : evts) {
                    System.out.println("Updated entry [key=" + e.getKey() + ", val=" + e.getValue() + ']');
                	try {
                		for (Dept dept : (List<Dept>)e.getValue()) {
                			System.out.println("*******************TESTING******************* INSIDE LOCAL LISTENER : " + dept);
                		}
                	} catch (Exception ex) {
                		System.out.println("*******************TESTING******************* EXCEPTION INSIDE LOCAL LISTENER : " + ex.getClass().getName() + " : " + ex.getMessage());
                	}
                }
            }
        });
		deptMapperCache.query(qry);
	}
	
	public void testCacheLookup() {
		System.out.println("*******************TESTING******************* : " + ignite.name());
		deptMapperCache = ignite.cache("com.brocade.dcm.domain.mapper.DeptMapper");
		CacheMetricsMXBean cacheMetricsMXBean = deptMapperCache.localMxBean();
		CacheMetrics cacheMetrics = deptMapperCache.localMetrics();
		System.out.println("*******************TESTING******************* KEY TYPE : " + cacheMetricsMXBean.getKeyType());
		System.out.println("*******************TESTING******************* VALUE TYPE : " + cacheMetricsMXBean.getValueType());
		System.out.println("*******************TESTING******************* KEY TYPE : " + cacheMetrics.getKeyType());
		System.out.println("*******************TESTING******************* VALUE TYPE : " + cacheMetrics.getValueType());
	}
	
}
