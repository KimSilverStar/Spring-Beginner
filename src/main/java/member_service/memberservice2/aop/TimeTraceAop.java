package member_service.memberservice2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.swing.*;

/*
* AOP (Aspect Oriented Programming, 관점 지향 프로그래밍)
 - 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern) 분리
   => 공통 관심 사항: 메소드 시간 측정 (Time Trace)
 - AOP 클래스도 Controller, Service, Repository 와 마찬가지로
   Spring Bean (Spring Container)에 등록 해줘야 함
   => AOP 는 보통 Spring Config 에 직접 명시하여 Spring Bean 에 등록

   1) 컴포넌트 스캔 방식
     - @Component, @Controller, @Service, @Repository 등 선언
     - Controller, Service, Repository 처럼
       정형화된 방식에서 주로 많이 쓰이는 방식
   2) Spring Config 로 직접 Spring Bean 등록
     - @Bean 선언하여 직접 명시
     - 해당 패키지의 모든 하위 클래스에 @Around(execution) 을 적용하면,
       Spring Config 의 AOP 생성 메소드에 순환 참조가 발생하여 에러 발생 가능
       => @Around(execution) 에서 Spring Config 는 AOP 대상에서 제외시킴
*/

//@Component
@Aspect
public class TimeTraceAop {
//    1) 컴포넌트 스캔 방식으로 Spring Bean 에 AOP 등록
//	@Around("execution(* member_service.memberservice2..*(..))")

//    2) SpringConfig 에 명시하여 직접 스프링 빈에 등록
//	  - 순환 참조를 피하기 위해 SpringConfig 는 제외
    @Around("execution(* member_service.memberservice2..*(..)) && " +
            "!target(member_service.memberservice2.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());       // 실행 메소드 이름 출력
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("FINISH: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
