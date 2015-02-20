package com.bananaj.user.service

import com.bananaj.user.domain.User
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/application-context.xml")
class UserServiceTest {


    @Autowired
    private UserService userService

    @Test
    void test() {
        println("test")
    }

    @Test
    void testGreet() {
        userService.greet()
    }

    @Test
    void testFind() {
        userService.addUser("user1", "pw1")
        User u = userService.getUserByUserName("user1")
        println(u)
    }
}
