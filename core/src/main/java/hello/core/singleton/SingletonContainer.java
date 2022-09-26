package hello.core.singleton;

public class SingletonContainer {
	/* Singleton 단점 
	 *  1. 코드가 길어진다.
	 *  2. DIP, OCP를 위반한다. 
	 *    - 역할 뿐만이 아니라 구체에 의존하기때문
	 *  3. private 생성자로 자식 클래스를 만들기 어렵다.
	 */
	private final static SingletonContainer instance = new SingletonContainer();
	
	private SingletonContainer() {}
	
	public static SingletonContainer getInstance() {
		return instance;
	}
	
	public String method1() {
		return "씐기한 싱글톤";
	}
}
