package member_service.memberservice2.service;

import member_service.memberservice2.domain.Member;
import member_service.memberservice2.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// 통합 테스트를 위한 어노테이션 2개: @SpringBootTest, @Transactional

@SpringBootTest
// 스프링 컨테이너와 테스트 함께 실행
@Transactional
// 각 Test 수행하고, DB 다시 롤백 시킴 (테스트 결과 DB 에 반영 X) => @AfterEach 로 clearStore() 불필요
class MemberServiceIntegrationTest {
    // 실제 서비스 코드는 생성자를 통한 DI 를 사용하는 것이 정석
    // But 테스트 코드에서는 간편히 필드를 통한 DI 도 많이 사용
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // Given: 주어진 데이터
        Member member = new Member();
        member.setName("spring");

        // When: 실행했을 때
        Long saveId = memberService.join(member);

        // Then: 실행 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원가입_예외() {
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
    void findMembers() {}

    @Test
    void findOne() {}
}