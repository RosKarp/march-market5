package ru.geekbrains.march.market.auth.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.geekbrains.march.market.auth.entities.Role;
import ru.geekbrains.march.market.auth.entities.User;
import ru.geekbrains.march.market.auth.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DataJpaTest
public class RepositoriesTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void userRepositoryTest() {
        Collection<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("User");
        roles.add(role);

        User user = new User();
        user.setUsername("Foma");
        user.setPassword("simple");
        user.setEmail("foma@ya.ru");
        user.setRoles(roles);
        entityManager.persist(user);
        entityManager.persist(role);
        entityManager.flush();

        List<User> users = userRepository.findAll();

        Assertions.assertEquals(3, users.size());
        Assertions.assertEquals("Foma", users.get(2).getUsername());
    }
}
