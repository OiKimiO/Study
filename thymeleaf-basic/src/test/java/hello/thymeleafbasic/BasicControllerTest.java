package hello.thymeleafbasic;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import lombok.Data;


public class BasicControllerTest {
	
	@Test
	public void test() {
		User userA = new User("A",10);
		userA.getUsername();
		assertThat(userA.getUsername()).isSameAs("A");
	}

	@Data 
	static class User{
		private String username;
		private int age;
		
		public User(String username, int age) {
			this.username = username;
			this.age = age;
		}
		
	}
}
