package com.stepashka.my_location.services;

import com.stepashka.my_location.models.PostedMaps;
import com.stepashka.my_location.repos.PostedMapsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "postedMapsService")
public class PostedMapsServiceImpl implements PostedMapsService {

    @Autowired
    private PostedMapsService postedMapsService;
    @Autowired
    private PostedMapsRepository postedMapsRepository;

    @Override
    public PostedMaps getPostedMapsById(long id) {
        return postedMapsRepository.findPostedMapsById(id);
    }

    @Override
    public List<PostedMaps> findAll() {
        List<PostedMaps> list = new ArrayList<>();
        postedMapsRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public PostedMaps save(PostedMaps postedMaps) {

        PostedMaps newPostedMaps = new PostedMaps();
        newPostedMaps.setTitle(postedMaps.getTitle());
        newPostedMaps.setAddress(postedMaps.getAddress());
        newPostedMaps.setMap(postedMaps.getMap());
        newPostedMaps.setCity(postedMaps.getCity());
        newPostedMaps.setState(postedMaps.getState());
        newPostedMaps.setZip(postedMaps.getZip());
        newPostedMaps.setComments(postedMaps.getComments());
        newPostedMaps.setLatitude(postedMaps.getLatitude());
        newPostedMaps.setLongitude(postedMaps.getLongitude());
        newPostedMaps.setCreated_at(postedMaps.getCreated_at());
        newPostedMaps.setUser(postedMaps.getUser());


        return postedMapsRepository.save(newPostedMaps);
    }
    @Transactional
    @Override
    public PostedMaps update(PostedMaps postedMaps, long id) {
        PostedMaps currentMap = getPostedMapsById(id);
        if(currentMap.getTitle() != null) {
            currentMap.setTitle(postedMaps.getTitle());
        }
        if (currentMap.getAddress()!=null) {
            currentMap.setAddress(postedMaps.getAddress());
        }
        if (currentMap.getMap() != null) {
            currentMap.setMap(postedMaps.getMap());
        }
        if (currentMap.getCity()!=null) {

            currentMap.setCity(postedMaps.getCity());
        }
        if (currentMap.getState()!=null) {

            currentMap.setState(postedMaps.getState());
        }
        if (currentMap.getZip()!=null) {

            currentMap.setZip(postedMaps.getZip());
        }
        if (currentMap.getComments()!=null) {

            currentMap.setComments(postedMaps.getComments());
        }
        if (currentMap.getLatitude()!=null) {

            currentMap.setLatitude(postedMaps.getLatitude());
        }
        if (currentMap.getLongitude()!=null) {

            currentMap.setLongitude(postedMaps.getLongitude());
        }
        if (currentMap.getCreated_at()!=null) {

            currentMap.setCreated_at(postedMaps.getCreated_at());
        }
        if (currentMap.getUser()!=null) {

            currentMap.setUser(postedMaps.getUser());
        }

        return postedMapsRepository.save(currentMap);
    }

    @Override
    public void delete(long id) {
        if (getPostedMapsById(id) != null){
            postedMapsRepository.deleteById(id);
        }

    }

    @Override
    public List<PostedMaps> findByTitleContaining(String title) {
        return postedMapsRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<PostedMaps> findByAddressContaining(String address) {
        return postedMapsRepository.findByAddressContainingIgnoreCase(address);
    }


}
