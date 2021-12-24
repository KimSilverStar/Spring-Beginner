package member_service.memberservice.service;

import member_service.memberservice.domain.Member;
import member_service.memberservice.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
	MemberService memberService;
	MemoryMemberRepository memberRepository ;

	// 기존 코드) MemberService memberService = new MemberService();
	// MemberService 의 멤버 변수 memberRepository
	// != 현재 클래스 MemberServiceTest 의 멤버 변수 memberRepository (즉, 서로 다른 저장소)

	// MemberService 의 생성자에서 memberRepository 를 주입(DI)하여, 같은 memberRepository 를 사용
	// @BeforeEach 에서 수행하여, memberRepository 와 memberService 객체를 새로 생성
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	public void 회원가입() {
		// Given: 주어진 데이터
		Member member = new Member();
		member.setName("spring");

		// When: 실행했을 때
		Long saveId = memberService.join(member);

		// Then: 실행 결과
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member).isEqualTo(findMember);
	}

	@Test
	public void 중복_회원가입_예외() {
		// Given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");

		// When: 같은 name 의 Member 가 join 할때
		memberService.join(member1);

		// Then: IllegalStateException 예외 발생하면 Test 통과
		// 방법 1) assertThrows 메소드
		IllegalStateException e = assertThrows(
				IllegalStateException.class,
				() -> memberService.join(member2)
		);
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

		// 방법 2) try ~ catch 문
		try {
			memberService.join(member2);
			fail();
		}
		catch (IllegalStateException e2) {
			assertThat(e2.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		}
	}

	@Test
	public void findMembers() {}

	@Test
	public void findOne() {}
}