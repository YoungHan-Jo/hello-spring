package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 스프링 빈을 java 코드로 설정 하기
// 인터페이스의 구현체를 바꿀 때 ( db 변경할때)
// 코드 그대로 다 살리고 여기서 하나만 바꾸면 db 적용시킬 수 있음
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
