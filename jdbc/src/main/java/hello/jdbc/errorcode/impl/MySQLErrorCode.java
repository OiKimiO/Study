package hello.jdbc.errorcode.impl;

import hello.jdbc.errorcode.DBErrorCode;

public class MySQLErrorCode implements DBErrorCode{

	// 키 중복 오류 코드
	@Override
	public int DuplicatedPrimaryKeyErrorCode() {
		return 1062;
	}

}
