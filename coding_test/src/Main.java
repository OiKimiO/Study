class ClassName<Ele>{
	// 제너릭 타입 변수
	private Ele element; 
	
	// 제너릭 파라미터 메서드
	void set(Ele element) {
		this.element = element;
	}
	
	Ele get() {
		return element;
	}
	
	// 클래스에서 지정한 제네릭 유형이외의 다른 제네릭 유형을 사용 가능
	// 제네릭을 타입으로 사용하는 경우 메서드 호출시 입력된 파라미터의 값을 타입으로 본다.
	<T> T genericMethod(T o) {
		return o;
	}
	
	
	/*
	 클래스와 같은 E타입이라도 static 메서드는 객체가 생성되기 이전 시점에 메모리에 생성되기 때문에 
     E유형을 클래스로 부터 얻을 방법이 없다.
	static E genericMethod(E o) {
		
	}
	*/
	
	// 아래 메서드의 E타입은 제네릭 클래스의 E타입과는 다른 독립적인 타입이다.
	static <E> E genericMethod1(E o) {
		return o;
	}

}


interface A<N extends Number>{
	
	A<N> print(A<N> arg);
}

interface B<T extends B<T>> implements A<Integer>{

	B<T> print(B<T> arg) {
		return arg;
	}

	@Override
	public A<Integer> print(A<Integer> arg) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


public class Main {
	public static void main(String[] args) {
		ClassName<String> a  = new ClassName<String>();
		ClassName<Integer> b = new ClassName<Integer>();
		
		a.set("10");
		b.set(10);
		
		System.out.println("a data   : " + a.get());
		System.out.println("a E Type : " + a.get().getClass().getName());
		
		System.out.println();
		
		System.out.println("<T> returnType : "+a.genericMethod(3).getClass().getName());
		
		B<?> d = new B<>();
		
		// SaltClass<Student> c = new SaltClass<Student>();
	}
	/*
	// B타입 이하만 사용 가능
	// B,C 사용 가능
	<T extends B> T print(T arg) {
		return arg;
	}
	*/
}
/*
class SaltClass <E extends Comparable<E>>{
	
}

class Student implements Comparable<Student>{

	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}*/



/*
class C extends B{
	
}

class D extends A{
	
}

class E extends D{
	
}*/
