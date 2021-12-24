package member_service.memberservice.repository;

import member_service.memberservice.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// MemoryMemberRepository 를 스프링 빈에 등록
@Repository
public class MemoryMemberRepository implements MemberRepository {
	// !실무에서는 static 멤버 변수(클래스 변수) 사용 시, 동시성 문제 고려!
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;

	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
		// store HashMap 에 해당 id 의 Member 가 없는 경우, NULL 발생
		// => Optional 로 감싸서 반환
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
		// filter().findAny() => 조건 만족하는 요소 1개라도 찾으면 반환
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

	public void clearStore() {
		store.clear();
	}
}
