package com.stepashka.my_location.repos;

import com.stepashka.my_location.models.PostedMaps;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.List;

public interface PostedMapsRepository extends PagingAndSortingRepository<PostedMaps, Long> {
    PostedMaps findPostedMapsById(long id);

    List<PostedMaps> findByTitleContainingIgnoreCase(String title);
    List<PostedMaps> findByAddressContainingIgnoreCase(String address);

}
