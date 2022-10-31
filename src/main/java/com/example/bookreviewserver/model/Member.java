package com.example.bookreviewserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Builder
@Getter
@AllArgsConstructor
@Table(name="MEMBERINFO")
public class Member implements Serializable{
    private static final long serialVersionUID = 174726374856727L;

    @Id
    @Column(name = "MEMBER_ID")
    @SequenceGenerator(name = "MEMBER_NO_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_NO_GENERATOR")
    private Long memberId;

    @Column(name = "MEMBER_EMAIL")
    private String memberEmail;
    @Column(name = "MEMBER_PASSWORD")
    private String memberPassword;
    @Column(name = "MEMBER_NICKNAME")
    private String memberNickname;

    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "member",fetch=FetchType.EAGER)
    @OrderBy("commentId desc ")
    private List<Comments> comments;

    public Member() {

    }

    public Member(Long memberId, String memberEmail, String memberPassword, String memberNickname) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
    }

    public Member(Long memberId, String memberEmail, String memberPassword, String memberNickname, String role) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.role = role;
    }

}