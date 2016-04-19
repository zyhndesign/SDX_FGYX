package com.cidic.sdx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {

	public static List<Integer> getIntegerRangeByRandom(int range,int randomNumber){
		List<Integer> list = new ArrayList<Integer>(randomNumber);
		
		while (list.size() < randomNumber){
			Random random = new Random();
			int data = random.nextInt(range);
			if (!list.contains(data)){
				list.add(data);
			}
		}
		
		return list;
	}
}
