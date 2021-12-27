package member_service.memberservice2;

import member_service.memberservice2.aop.TimeTraceAop;
import member_service.memberservice2.repository.JdbcMemberRepository;
import member_service.memberservice2.repository.JdbcTemplateMemberRepository;
import member_service.memberservice2.repository.JpaMemberRepository;
import member_service.memberservice2.repository.MemberRepository;
//import member_service.memberservice2.repository.MemoryMemberRepository;
import member_service.memberservice2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
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
//	private final DataSource dataSource;
	// JdbcMemberRepository, JdbcTemplateMemberRepository 에 DI

//	@Autowired
//	public SpringConfig(DataSource dataSource) {
//		this.dataSource = dataSource;
//		// 생성자를 통한 DI, 스프링 부트가 자동으로 DataSource 주입
//	}

	/* 3. JPA - JpaMemberRepository */
//	private final EntityManager em;
//
//	@Autowired
//	public SpringConfig(EntityManager em) {
//		this.em = em;
//		// 생성자를 통한 DI, 스프링 부트가 자동으로 EntityManager 주입
//	}

	/* 4. 스프링 데이터 JPA - SpringDataJpaMemberRepository */
	private final MemberRepository memberRepository;

	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
//		스프링 데이터 JPA 가 SpringDataJpaMemberRepository
//		interface 의 구현체를 자동으로 생성하여 스프링 빈에 등록
//		=> 스프링 빈에 등록된 스프링 데이터 JPA interface 의 구현체를 DI 받음
	}

	@Bean
	public MemberService memberService() {
//		return new MemberService(memberRepository());
		return new MemberService(memberRepository);
	}

	@Bean
	public TimeTraceAop timeTraceAop() {
		return new TimeTraceAop();
	}

//	@Bean
//	public MemberRepository memberRepository() {
////		return new MemoryMemberRepository();
////		return new JdbcMemberRepository(dataSource);
////		return new JdbcTemplateMemberRepository(dataSource)
//		return new JpaMemberRepository(em);
//	}
}
