package com.jesusfs.tienda.repository;

import com.jesusfs.tienda.domain.user.User;
import com.jesusfs.tienda.domain.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_SaveAll_ReturnSavedUser() {
//      Arrange
        User user = new User();
        user.setUsername("jesusfs");
        user.setPassword("1234");

//      Act
        User createdUser = userRepository.save(user);

//      Assert
        Assertions.assertEquals(createdUser.getUsername(), user.getUsername());
        Assertions.assertEquals(createdUser.getPassword(), user.getPassword());
        Assertions.assertTrue(createdUser.getId() > 0);
    }

}
