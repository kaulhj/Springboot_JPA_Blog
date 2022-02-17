package com.cos.blog.model;

import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired  //의존성 주입 , 메모리에 뜸
    private UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    //한페이지당 2개의 데이터 리턴하기
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다, id");
            }
        });
        return user;
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        try {
            System.out.println("id: " + id);
            System.out.println("password: " + requestUser.getPassword());
            System.out.println("email: " + requestUser.getEmail());


            User user = userRepository.findById(id).orElseThrow(() -> {
                return new IllegalArgumentException("수정에 실패하였습니다.");
            });

            user.setPassword(requestUser.getPassword());
            user.setEmail(requestUser.getEmail());

            //userRepository.save(requestUser);
            return null;
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }



    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id:"+user.getId());
        System.out.println("username:"+user.getUsername());
        System.out.println("password:"+user.getPassword());
        System.out.println("email:"+user.getEmail());
        System.out.println("role:"+user.getRole());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

}
