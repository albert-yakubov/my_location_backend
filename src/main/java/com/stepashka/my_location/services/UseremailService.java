package com.stepashka.my_location.services;

import com.stepashka.my_location.models.Useremail;

import java.util.List;

public interface UseremailService
{
    List<Useremail> findAll();

    Useremail findUseremailById(long id);

    List<Useremail> findByUserName(String username);

    void delete(long id);

    Useremail update(long useremailid,
                     String emailaddress);

    // note emails are added through the user process
}