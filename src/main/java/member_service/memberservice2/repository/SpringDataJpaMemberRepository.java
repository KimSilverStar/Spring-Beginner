package member_service.memberservice2.repository;

/*
* Spring Data JPA 방식
 - JpaRepository<엔티티, 엔티티의 primary key> 를
   extends 한 interface 를 정의해놓으면,
   Spring Data JPA 가 해당 interface 의 구현체를
   자동으로 생성하여 Spring Bean 에 등록함
   => 개발자는 interface 만을 정의하면 됨 !!
 - SpringConfig 클래스에서는 단순히 MemberRepository 를
   멤버 변수로 선언하고 생성자에서 DI 하면 됨
   => Spring Data JPA 가 생성한 구현체를 자동으로 주입시킴
*/

import member_service.memberservice2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository
		extends JpaRepository<Member, Long>, MemberRepository {
	@Override
	Optional<Member> findByName(String name);

	/*
	* findById() 와 같이 기본적인 CRUD 메소드는 모두 Spring Data JPA 가 제공
	* findByName() 와 같이 사용자 정의가 필요한 메소드는
	  interface 에 선언해놓으면, 스프링 데이터 JPA 가
	  규칙에 따라 JPQL 생성하여 처리
	  ex) findByName(name) => "select m from Member m where m.name = ?"
	*/
}
