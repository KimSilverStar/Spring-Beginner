package member_service.memberservice2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// JPA 가 관리하는 Entity 가 됨
@Entity
public class Member {
	// id: DB 에 값을 넣으면, DB 가 자동으로 id 할당 (IDENTITY 전략)
	// e.g. insert into member(name) values("spring") => id 5번 할당
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
