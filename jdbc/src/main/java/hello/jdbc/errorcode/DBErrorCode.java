package hello.jdbc.errorcode;

/**
 * DB별로 Error 코드를 정의한 인터페이스
 * */
public interface DBErrorCode {

	// 키 중복 오류
	public int DuplicatedPrimaryKeyErrorCode();
}
