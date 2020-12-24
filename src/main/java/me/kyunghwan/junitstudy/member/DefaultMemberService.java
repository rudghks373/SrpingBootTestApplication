package me.kyunghwan.junitstudy.member;

import me.kyunghwan.junitstudy.domain.Member;
import me.kyunghwan.junitstudy.domain.Study;
import me.kyunghwan.junitstudy.study.StudyService;

import java.util.Optional;

public class DefaultMemberService implements MemberService{

    StudyService studyService;

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.empty();
    }

    @Override
    public void notify(Study newstudy) {
        studyService.hi();
    }

    @Override
    public void validate(Long memberId) {
        studyService.hi();
    }

    @Override
    public void notify(Member member) {

    }
}
