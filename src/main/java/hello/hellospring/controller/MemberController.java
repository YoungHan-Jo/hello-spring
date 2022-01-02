package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


// 컴포넌트 스캔으로 Spring bean 으로 등록됨.
// 기본적으로 싱글톤으로 등록되기 때문에 유일한 객체로 됨.
@Controller
public class MemberController {

    // private final MemberService memberService = new MemberService();
    // 같은 컨트롤러를 같이 써야함.

    private final MemberService memberService;

    // spring bean에 등록된 memberService를 넣어줌(하나의 memberService를 공유함)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
