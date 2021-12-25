package member_service.memberservice2.controller;

import member_service.memberservice2.domain.Member;
import member_service.memberservice2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
		// "localhost:8080/members/new" 접속 => createMemberForm.html
	}

	// "/members/new" 에서 POST
	// => createMemberForm.html 에서 입력한 name(MemberForm 의 name) 전달
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		memberService.join(member);

		return "redirect:/";
		// "localhost:8080/" home 으로 redirect
	}

	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		// key: "members", value: members (List<Member>)

		return "members/memberList";
		// "localhost:8080/members" 접속 => memberList.html
	}
}
