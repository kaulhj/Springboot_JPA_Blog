package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답(DATA)

//사용자가 요청 -> 응답(HTML파일)
//@Controller

@RestController
public class HttpControllerTest {
    private static final String TAG ="HttpControllerTest:";
    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
        System.out.println(TAG+"getter :"+m.getUsername());
        m.setUsername("cos");
        System.out.println(TAG+"setter:"+m.getUsername());
        Member m2 = new Member();
        return "lombok test 완료";
    }

    //http://localhost:8080/http/get
    //인터넷 브라우저 요청은 get밖에 할 수 없다.
    @GetMapping("/http/get")
    public String getTest(Member m){

        return "get 요청:"+m.getId()+","+m.getPassword()+","+m.getEmail();
    }

    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        return "post 요청:" +m.getId()+","+m.getPassword()+","+m.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){
        return "put요청:" +m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
    }
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete요청";
    }

}
