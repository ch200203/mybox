package com.numble.mybox.service;

import com.numble.mybox.entity.Member;
import com.numble.mybox.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMember(Long id) {
        return memberRepository.findUser(id);
    }

    @Transactional
    public Long createMember(Member member) {

    }

    private void validateDuplicateUser(Member member) {

    }
}
