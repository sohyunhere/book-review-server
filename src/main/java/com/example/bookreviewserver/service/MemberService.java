package com.example.bookreviewserver.service;

import com.example.bookreviewserver.dto.SignupDto;
import com.example.bookreviewserver.model.Member;
import com.example.bookreviewserver.repo.MemberQueryRepo;
import com.example.bookreviewserver.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepo memberRepo;
    private final MemberQueryRepo memberQueryRepo;
    //회원가입
    @Transactional
    public void join(SignupDto signupDto) {

        Member saveMember = signupDto.toEntity();
        memberRepo.save(saveMember);
    }

    //회원 email에 대한 회원 조회
    public Member findMemberByEmail(String email){
        Optional<Member> result = Optional.ofNullable(memberRepo.findByMemberEmail(email).orElseThrow(
                () -> new IllegalStateException("email에 해당하는 사용자가 존재하지 않습니다."))
        );

        return result.get();
    }

    //닉네임 수정
    @Transactional
    public Member updateNickname(Long id, String nickname){

        Optional<Member> result = memberQueryRepo.updateNickname(id, nickname);

        if(result.isEmpty()){
            //예외처리 없을때
            throw new IllegalArgumentException();
        }
        return result.get();

    }

//    @Transactional
//    //비밀번호 수정
//    public Member changePassword (Member member, String originPW, String newPW) throws Exception {
//       if(passwordEncoder.matches(originPW, member.getMemberPassword())){
//           //true
//           Optional<Member> result =  memberQueryRepo.updatePassword(member.getMemberId(), newPW);
//           if(result.isEmpty()){
//               //예외처리 없을때
//               throw new IllegalArgumentException();
//           }
//           return result.get();
//       }else{
//           throw new Exception();
//       }
//
//    }

}
