package hello.jdbc.exception.basic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckedTest {

	@Test
	void checked_catch() {
		Service service = new Service();
		service.callCatch();
	}
	
	@Test
	void checked_throw() {
		Service service = new Service();
		assertThatThrownBy(() -> service.callThrow())
						.isInstanceOf(MyCheckedExcpetion.class);
	}
	
	/**
	 * RuntimeException을 상속받은 예외는 언체크 예외가 됨
	 * */
	static class MyCheckedExcpetion extends Exception{
		public MyCheckedExcpetion(String message) {
			super(message);
		}
	}

	/** 
	 * Unchecked 예외는 
	 * 예외를 잡거나, 던지지 않아도 된다.
	 * 예외를 잡지 않으면 자동으로 밖으로 던진다.
	 */
	static class Service{

		Repository repository = new Repository();
		
		/**
		 * 필요한 경우 예외를 잡아서 처리하면 된다.
		 * */
		public void callCatch() {
			try {
				repository.call();
			}catch(MyCheckedExcpetion e) {
				// 예외 처리 로직
				// 마지막 파라미터로 e를 던지면 에러 로그를 찍는다.
				log.info("예외 처리, message={}", e.getMessage(),e);
			}
		}
		
		/**
		 * 체크 예외를 밖으로 던지는 코드
		 * 쳬크 예외를 잡지 않고 밖으로 던지려면 throws 예외를 메서드에 필수로 선언해야 한다.
		 * */
		public void callThrow() throws MyCheckedExcpetion{
			repository.call();
		}
		
	}
	
	static class Repository{
		public void call() throws MyCheckedExcpetion{
			throw new MyCheckedExcpetion("ex");
		}
	}
}
