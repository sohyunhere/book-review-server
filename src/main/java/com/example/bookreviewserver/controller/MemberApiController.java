package com.example.bookreviewserver.controller;

import com.example.bookreviewserver.dto.SignupDto;
import com.example.bookreviewserver.model.Member;
import com.example.bookreviewserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@RestController
public class MemberApiController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/member/register")
    public int register(@RequestBody SignupDto dto) throws Exception {

        memberService.join(dto);
        return 1;
    }

    //이메일이 중복하는지
    @PostMapping("/member/emailCheck")
    public int emailCheck(@RequestBody String email){

        try{
            memberService.findMemberByEmail(email);
        }catch(IllegalStateException e){
            return 0;//이메일 중복아님
        }
        return 1; //중북
    }

    //loaduserbyusername
    @PostMapping("/member/findByMemberEmail")
    public Member findByMemberEmail(@RequestBody String username) {

        return memberService.findMemberByEmail(username);
    }

    //닉네임 수정하기
    @PostMapping("/member/mypage/nickname")
    public Member changeNickname(@RequestBody Map<String, Object> map) {
        String nickname = (String) map.get("nickname");
        String email = (String) map.get("email");

        Member user = memberService.findMemberByEmail(email);

        Member changedUser = memberService.updateNickname(user.getMemberId(), nickname);

        return changedUser;
    }

    //비밀번호 수정하기
    @PostMapping("/member/mypage/password")
    public int changePassword(@RequestBody Map<String, Object> map) throws Exception {

            String email = (String) map.get("email");
            String newPW = (String) map.get("newPW");

            Member changedUser = memberService.changePassword(email, newPW);

        return 1;
    }

}
