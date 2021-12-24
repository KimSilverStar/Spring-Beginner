package member_service.memberservice.service;

import member_service.memberservice.domain.Member;
import member_service.memberservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// MemberService 를 스프링 빈에 등록
@Service
public class MemberService {
	private final MemberRepository memberRepository;

	// 생성자에 @Autowired 추가
	// => 스프링 빈에 등록된(스프링 컨테이너에 있는) MemberRepository 를 가져와서 연결
	// DI (Dependency Injection, 의존성 주입)
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// 회원 가입
	public Long join(Member member) {
		validateDuplicateMember(member);	// 같은 name 의 중복 회원 X
		memberRepository.save(member);
		return member.getId();
	}
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
				.ifPresent(m -> {
					throw new IllegalStateException("이미 존재하는 회원입니다.");
				});
	}

	// 전체 회원 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	// 회원 조회
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
