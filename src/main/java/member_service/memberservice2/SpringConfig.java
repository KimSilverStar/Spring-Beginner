package member_service.memberservice2;

import member_service.memberservice2.repository.JdbcMemberRepository;
import member_service.memberservice2.repository.JdbcTemplateMemberRepository;
import member_service.memberservice2.repository.MemberRepository;
//import member_service.memberservice2.repository.MemoryMemberRepository;
import member_service.memberservice2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*
* 자바 코드로 직접 스프링 빈 등록
 - SpringConfig 클래스: @Configuration
 - @Bean 으로 스프링 빈에 등록할 객체
*/

@Configuration
public class SpringConfig {
	/* 1. 순수 JDBC - JdbcMemberRepository */
	/* 2. JDBC Template - JdbcTemplateMemberRepository */
	private final DataSource dataSource;
	// JdbcMemberRepository, JdbcTemplateMemberRepository 에 DI

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
//		return new JdbcMemberRepository(dataSource);
		return new JdbcTemplateMemberRepository(dataSource);
	}
}
