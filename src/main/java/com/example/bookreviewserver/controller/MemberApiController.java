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
//
//    //비밀번호 수정하기
//    @PostMapping("/member/mypage/password")
//    public int changePassword(@RequestParam("originPassword") String originPW, @RequestParam("newPassword") String newPW, Authentication auth) {
//        try{
//            Member changedUser = memberService.changePassword((Member) auth.getPrincipal(), originPW, newPW);
//            memberService.changeSession(changedUser);
//        }catch (Exception e){
//            //에러발생 현재 비밀번호가 일치하지 않을 떄
//            return 0;
//        }
//        return 1;
//    }

}
