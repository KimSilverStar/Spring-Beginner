package member_service.memberservice2;

import member_service.memberservice2.repository.MemberRepository;
import member_service.memberservice2.repository.MemoryMemberRepository;
import member_service.memberservice2.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
