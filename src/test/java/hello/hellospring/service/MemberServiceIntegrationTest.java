package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//스프링 부트 테스트
@SpringBootTest
@Transactional // 테스트에 달면 각 테스트를 커밋하지 않고 롤백함
class MemberServiceIntegrationTest {

    //테스트 할때는 그냥 Autowired해도됨
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    // MemberRepository로 찾아도 구현체에 연결되어있어서 동작함.
    
    // beforEach, afterEach 필요없음 -> @transactional애노테이션
    // 하지만 스프링 테스트보다
    // 순수한 단위테스트가 더 좋은 테스트일 가능성이 높다.
    
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberRepository.findById(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() { // 예외가 잘 터지는지 확인
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // 해당 Exception이 터지는지 확인
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // assertThrows(NullPointerException.class, () -> memberService.join(member2));
/*
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/


        //then
    }

}