package com.cos.blog.test;

import lombok.*;


//@RequiredArgsConstructor    //파이널 붙은 애들에 대한 생성자들을 생성
   //게터세터 생성
//@AllArgsConstructor //생성자 생성
@Data
@NoArgsConstructor  //빈 생성자 생성
public class Member {
    private int id;//불변성 유지, 파이널은, 변경해야한다면 파이널 붙이면 안됨
    private String username;
    private String password;
    private String email;


    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
