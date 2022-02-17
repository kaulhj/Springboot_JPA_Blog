package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User클래스가 Mysql에 테이블이 생성이 된다.
public class User {

    @Id //PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 db넘버링
    private int id; //auto_increment

    @Column(nullable = false,length = 20) //널이될 수 없다.
    private String username; //아이디

    @Column(nullable = false,length = 100)  //123456 =>해쉬 (비밀번호 암호화)
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum을 쓰는게 좋다.

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createdDate;

}
