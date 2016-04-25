package com.cidic.sdx.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EHCacheService {

	private static CacheManager cacheManager;
	private static Cache cache;
	
	private EHCacheService() {
	}

	private static EHCacheService service = null;

	public static EHCacheService getInstance() {
		if (service == null) {
			cacheManager = new CacheManager();
			cacheManager.addCache("StyleImage");
			cache = cacheManager.getCache("StyleImage");
			service = new EHCacheService();
		}
		return service;
	}

	public Object getCacheElement(String cacheKey) throws Exception {
		Element e = cache.get(cacheKey);
		if (e == null) {
			return null;
		}
		return e.getValue();
	}

	public void addToCache(String cacheKey, Object result) throws Exception {
		Element element = new Element(cacheKey, result);
		cache.put(element);
	}

	public void removeFromCache(String cacheKey) throws Exception {
		cache.remove(cacheKey);
	}
}
