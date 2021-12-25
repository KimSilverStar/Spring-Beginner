package member_service.memberservice2.repository;

import member_service.memberservice2.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;		// JUnit 으로 테스트 케이스 작성

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();

	// @Test 각 테스트 케이스 메소드가 끝난 후, 실행되는 afterEach() 메소드
	// 테스트 케이스는 순서, 의존 관계에 상관없이 수행되어도 만족되어야 함
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}

	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		repository.save(member);

		Member result = repository.findById(member.getId()).get();
		// findById 메소드: 반환형이 Optional 이므로 get() 메소드로 꺼냄

		// 테스트 방법 1) JUnit 의 assertEquals 메소드
		assertEquals(member, result);

		// 테스트 방법 2) assertj 의 assertThat 메소드
		assertThat(member).isEqualTo(result);
	}

	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		Member result = repository.findByName("spring1").get();
		assertThat(result).isEqualTo(member1);
	}

	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(2);
	}
}