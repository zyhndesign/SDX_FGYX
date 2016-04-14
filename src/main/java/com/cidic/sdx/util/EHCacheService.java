package com.cidic.sdx.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EHCacheService {
	private static final CacheManager cacheManager = new CacheManager();
	private Cache cache;

	public EHCacheService() {
		this.cache = cacheManager.getCache("ehcacheName");
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	/*
	 * 通过名称从缓存中获取数据
	 */
	public Object getCacheElement(String cacheKey) throws Exception {
		net.sf.ehcache.Element e = cache.get(cacheKey);
		if (e == null) {
			return null;
		}
		return e.getValue();
	}

	/*
	 * 将对象添加到缓存中
	 */
	public void addToCache(String cacheKey, Object result) throws Exception {
		Element element = new Element(cacheKey, result);
		cache.put(element);
	}

}
