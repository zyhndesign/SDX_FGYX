package com.cidic.sdx;

import java.util.List;

import org.junit.Test;

import com.cidic.sdx.util.EHCacheService;
import com.cidic.sdx.util.RandomUtil;

import net.sf.json.JSONObject;

public class EHCacheTest {
	
	@Test
	public void testCache() throws Exception{
		//测试将json对象存入缓存中  
		EHCacheService service = EHCacheService.getInstance();
        JSONObject obj = new JSONObject();  
        obj.put("name","lsz");  
        System.out.println("================:"+service);
        service.addToCache("cache_json",obj);  
  
        //从缓存中获取  
        JSONObject getobj = (JSONObject)service.getCacheElement("cache_json");  
        System.out.println(getobj.toString());  
	}
	
	@Test
	public void testCacheData() throws Exception{
		EHCacheService service = EHCacheService.getInstance();
		JSONObject getobj = (JSONObject)service.getCacheElement("cache_json");  
        System.out.println(getobj.toString());  
        JSONObject obj = new JSONObject();  
        obj.put("name","wwwwww");  
        System.out.println("================:"+service);
        service.addToCache("cache_json",obj); 
        JSONObject getobj2 = (JSONObject)service.getCacheElement("cache_json");  
        System.out.println(getobj2.toString());  
	}
	
	//@Test
	public void getData(){
		List<Integer> list = RandomUtil.getIntegerRangeByRandom(50, 8);
		for (Integer i : list){
			System.out.println(i);
		}
	}
}
