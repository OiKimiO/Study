package hello.jdbc.service;

import java.sql.SQLException;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceV0 {

	private final MemberRepositoryV1 memberRespository;
	
	public void accountTransfer(String fromId, 
								String toId,
								int money) throws SQLException {
		Member fromMember = memberRespository.findById(fromId);
		Member toMember   = memberRespository.findById(toId);
				
		memberRespository.update(fromId, fromMember.getMoney() - money);
		validation(toMember);
		memberRespository.update(toId, toMember.getMoney() + money);
	}

	private void validation(Member toMember) {
		if(toMember.getMemberId().equals("ex")) {
			throw new IllegalStateException();
		}
	}
}
