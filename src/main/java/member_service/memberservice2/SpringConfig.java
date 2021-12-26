package member_service.memberservice2;

import member_service.memberservice2.repository.JdbcMemberRepository;
import member_service.memberservice2.repository.MemberRepository;
//import member_service.memberservice2.repository.MemoryMemberRepository;
import member_service.memberservice2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
	/* 1. 순수 JDBC - JdbcMemberRepository */
	private final DataSource dataSource;	// JdbcMemberRepository 에 DI

	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
		// 생성자를 통한 DI, 스프링 부트가 자동으로 DataSource 주입
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
		return new JdbcMemberRepository(dataSource);
	}
}
