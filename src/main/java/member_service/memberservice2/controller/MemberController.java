package member_service.memberservice2.controller;

import member_service.memberservice2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// MemberController 를 스프링 빈에 등록
@Controller
public class MemberController {
	private final MemberService memberService;

	// 생성자에 @Autowired 추가
	// => 스프링 빈에 등록된(스프링 컨테이너에 있는) MemberService 를 가져와서 연결
	// DI (Dependency Injection, 의존성 주입)
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
}
