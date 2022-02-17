package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob    //대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count; //조회수

    @ManyToOne  //Many = Board, User = One, 여러개의 게시글은 한명의 유저에 의해 쓰여진다.
    @JoinColumn(name = "userId")
    private User user;  //데이터베이스는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장 가능능

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)  //기본전략,mappedBY 연관관계의 주인이 아니다. DB에 칼럼을 만들지 말기,reply테이블의 boadr객체에 의해 만들어짐
    private List<Reply> reply;

   @CreationTimestamp  //데이터 인서트, 업데이트시 자동으로 시간 들어감
    private Timestamp createDate;

}
