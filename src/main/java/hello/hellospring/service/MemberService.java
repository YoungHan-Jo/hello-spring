package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// ctrl + shift + t 눌리면 test 클래스 바로 만들어짐

// jpa 사용할때는 트랜잭션 필요
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // 외부에서 memberRepository를 주입 받아서 같은 객체를 사용 할 수 있음.

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */
    // Service 메소드 네이밍은 보면 딱 알 수 있도록 하기
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // 요즘 트랜드는 null 체크를 위한 Optional로 한번 감싸줌 -> optional 관련 메서드 사용가능
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        validateDuplicateMember(member); // 중복회원 검증
        // ctrl + alt + m 으로 리팩토링

        memberRepository.save(member);
        return member.getId();

    }

    // 리팩토링 관련 커맨드 ctrl + alt + shift + t
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
