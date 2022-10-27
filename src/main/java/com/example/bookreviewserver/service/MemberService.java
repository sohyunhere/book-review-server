package com.example.bookreviewserver.service;

import com.example.bookreviewserver.dto.SignupDto;
import com.example.bookreviewserver.model.Member;
import com.example.bookreviewserver.repo.MemberQueryRepo;
import com.example.bookreviewserver.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepo memberRepo;
    private final MemberQueryRepo memberQueryRepo;
    private final PasswordEncoder passwordEncoder;

    @Override// 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 MemberInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        Optional<Member> result = Optional.ofNullable(memberRepo.findByMemberEmail(username).orElseThrow(
                () -> new IllegalStateException("사용자가 존재하지 않습니다.")
        ));
        return result.get();
    }

    //회원가입
    @Transactional
    public void join(SignupDto signupDto) throws Exception {
        String encodedPassword = passwordEncoder.encode(signupDto.getPassword());

        signupDto.setPassword(encodedPassword);

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

    @Transactional
    //비밀번호 수정
    public Member changePassword (Member member, String originPW, String newPW) throws Exception {
       if(passwordEncoder.matches(originPW, member.getMemberPassword())){
           //true
           Optional<Member> result =  memberQueryRepo.updatePassword(member.getMemberId(), newPW);
           if(result.isEmpty()){
               //예외처리 없을때
               throw new IllegalArgumentException();
           }
           return result.get();
       }else{
           throw new Exception();
       }

    }

    public void changeSession(Member member){
        //세션 수정
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(member , member.getPassword(), member.getAuthorities());
        Authentication newAuth = token;
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return;
    }
}
