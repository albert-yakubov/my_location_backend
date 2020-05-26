package com.stepashka.my_location.services;

import com.stepashka.my_location.models.PostedMaps;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostedMapsService {

    PostedMaps getPostedMapsById(long id);

    List<PostedMaps> findAll();

    PostedMaps save(PostedMaps postedMaps);

    PostedMaps update(PostedMaps postedMaps, long id);

    void delete(long id);

    List<PostedMaps> findByTitleContaining(String title);
    List<PostedMaps> findByAddressContaining(String address);

}
