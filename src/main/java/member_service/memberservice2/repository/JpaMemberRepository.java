package member_service.memberservice2.repository;

import member_service.memberservice2.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/*
* JPA 방식
 - ORM (Object Relational Mapping) 적용
   => 객체와 관계형 DB 를 Mapping
 - 객체에 query 하면, SQL query 문으로 번역됨

 1) JPA 로 관리할 객체 클래스에 Entity 맵핑
   - Member 클래스에 @Entity 선언
   - @Id, @GeneratedValue 선언
 2) 서비스 계층에 Transaction 추가
   - 데이터를 저장 및 변경하는 부분에 @Transactional 선언
   - MemberService 클래스에 @Transactional 선언
*/

public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;
	// JPA 는 EntityManager 로 동작
	// 스프링이 자동으로 EntityManager 생성

	public JpaMemberRepository(EntityManager em) {
		this.em = em;
		// 스프링이 자동으로 생성한 EntityManager 를 생성자를 통하여 DI 받음
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		// em.find(조회할 entity 객체 type, entity 의 primary key)
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery(
				"select m from Member m where m.name = :name",
				Member.class
		).
				setParameter("name", name)
				.getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery(
				"select m from Member m",
				Member.class
		).getResultList();
		// JPQL 로 객체(Member Entity)에 query 하면, SQL query 로 번역됨
		// "select m from Member m": m 은 Entity 객체인 Member
	}
}
