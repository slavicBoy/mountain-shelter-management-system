package com.example.demo;

import com.example.demo.model.user.User;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class IncrementTest {

           @Test
           public void value_should_be_incremented() {
               //given
               List<User> userList = new ArrayList<>();
               User user = new User();
               user.setNotification(1);
               User user1 = new User();
               user.setNotification(1);
               User user2 = new User();
               user.setNotification(1);
               userList.add(user);
               userList.add(user1);
               userList.add(user2);
               //when
                 userList.forEach(User::incrementNotification);
                 //then
               assertThat(user.getNotification(), is(2));


           }

}
