package hello.jdbc.errorcode.settings;

import hello.jdbc.errorcode.impl.H2ErrorCode;
import hello.jdbc.errorcode.impl.MySQLErrorCode;
import hello.jdbc.errorcode.DBErrorCode;

/**
 * DBErrorCode를 관리
 *  현재 사용중인 에러코드를 운영과 테스트로 분리하여 관리
 * */
public class DBErrorCodeSettings {

	public static final DBErrorCode TEST_DB_ERROR_CODE = new H2ErrorCode();
	public static final DBErrorCode OP_DB_ERROR_CODE   = new MySQLErrorCode();

}
