package member_service.memberservice.service;

import member_service.memberservice.domain.Member;
import member_service.memberservice.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
	private final MemberRepository memberRepository;

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
