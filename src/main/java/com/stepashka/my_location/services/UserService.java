package com.stepashka.my_location.services;

import com.stepashka.my_location.models.User;
import com.stepashka.my_location.view.UserNameCountEmails;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService
{
    List<User> findAll(Pageable pageable);

    List<User> findByNameContaining(String username,
                                    Pageable pageable);

    User findUserById(long id);

    User findByName(String name);

    void delete(long id);

    User save(User user);

    User update(User user,
                long id);

    void deleteUserRole(long userid,
                        long roleid);

    void addUserRole(long userid,
                     long roleid);

    List<UserNameCountEmails> getCountUserEmails();
}