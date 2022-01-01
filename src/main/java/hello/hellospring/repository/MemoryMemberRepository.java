package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// 인터페이스를 상속받은 구현체
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null값일 때 Optional로 감싸기
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // store map을 loop문 돌려서 필터링으로 찾아내는것
        // member에서 name을 꺼내서 파라미터 name랑 같은게 있는지.
        // findAny는 하나라도 발견되면 리턴한다. 없으면 optional로 감싼 null
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // store 맵에 있는 값들을 새 List로 리턴
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
