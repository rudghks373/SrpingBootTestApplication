package me.kyunghwan.junitstudy.member;


import me.kyunghwan.junitstudy.domain.Member;
import me.kyunghwan.junitstudy.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void notify(Study newstudy);

    void validate(Long memberId);

    void notify(Member member);
}