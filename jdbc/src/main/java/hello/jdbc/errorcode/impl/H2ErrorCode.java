package hello.jdbc.errorcode.impl;

import hello.jdbc.errorcode.DBErrorCode;

public class H2ErrorCode implements DBErrorCode{

	// 키 중복 오류 코드
	@Override
	public int DuplicatedPrimaryKeyErrorCode() {
		return 23505;
	}

}
