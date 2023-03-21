package hello.jdbc;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashMapExceptionTest {
	
	HashMap<String,Object> map;
	
	@Test
	public void processHashMapNullPointException() {
		// map = new HashMap<>(); map.put(null,11);
		// map의 key,value 값은 null을 넣을 수 있음
		// wrapper class는 null이 올 수 있음
		assertThrows(NullPointerException.class, ()->{
			try {
				map.put(null,123);
				map.put("1",1);
			}catch(Exception e){
				throw e;
			}
		});
	}
	
	@Test
	public void processHashMapClassCastException() {	
		// 해시맵 자체가 null인 경우는 Exception 처리가 됨
		assertThrows(ClassCastException.class, ()->{
			map = new HashMap<>();
			map.put("int","rsd");
			Integer intValue = (Integer) map.get("int");
		});
	}
	
}
