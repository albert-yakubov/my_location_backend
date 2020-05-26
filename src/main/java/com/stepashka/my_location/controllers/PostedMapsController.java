package com.stepashka.my_location.controllers;

import com.stepashka.my_location.models.ErrorDetail;
import com.stepashka.my_location.models.PostedMaps;
import com.stepashka.my_location.services.PostedMapsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/postedmaps")
public class PostedMapsController {

    @Autowired
    private PostedMapsService postedMapsService;

    private static final Logger logger = LoggerFactory.getLogger(PostedMapsController.class);
    @ApiOperation(value = "returns all PostedMaps",
            response = PostedMaps.class,
            responseContainer = "List")
    // http://localhost:2221/postedmaps/postedmaps
    @GetMapping(value = "/postedmaps", produces = "application/json")
    ResponseEntity<?> getPostedMaps(){
        logger.info("postedmaps/postedmaps Accessed");
        List<PostedMaps> postedMaps = postedMapsService.findAll();
        return new ResponseEntity<>(postedMaps, HttpStatus.OK);
    }

    //CREATE
    //http://localhost:2221/postedmaps/postedmap
    @PostMapping(value = "/postedmap",
            consumes = {"application/json"})
    public ResponseEntity<?> addNewPostedMap(
            @Valid
            @RequestBody PostedMaps newPostedMap){
        newPostedMap = postedMapsService.save(newPostedMap);
        HttpHeaders responseHeader = new HttpHeaders();
        URI newCustomerUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPostedMap.getId())
                .toUri();
        responseHeader.setLocation(newCustomerUri);

        return new ResponseEntity<>(null, responseHeader, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retrieve a user based of off user id",
            response = PostedMaps.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Map Found",
            response = PostedMaps.class), @ApiResponse(code = 404,
            message = "Map Not Found",
            response = ErrorDetail.class)})
    // http://localhost:2221/postedmaps/postedmap/1
    @PutMapping(value = "/postedmaps/{id}",
            consumes = {"application/json"})
    public ResponseEntity<?> updatePostedMap(@RequestBody PostedMaps updatePostedMap,
                                            @ApiParam(value = "id",
                                                    required = true,
                                                    example = "4")
                                            @PathVariable long id){
        postedMapsService.update(updatePostedMap, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // http://localhost:2221/postedmaps/postedmap/1
    @DeleteMapping(value = "/postedmap/{id}")
    public ResponseEntity<?> deletePostedMap(@PathVariable long id){
        postedMapsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2221/postedmaps/title/like/title
    @GetMapping(value = "/postedmaps/title/like/{title}",
            produces = {"application/json"})
    public ResponseEntity<?> getPostedMapTitleLike(
            @PathVariable
                    String title,
            @PageableDefault(page = 0,
                    size = 5)
                    Pageable pageable)
    {
        List<PostedMaps> postedMaps = postedMapsService.findByTitleContaining(title);
        return new ResponseEntity<>(postedMaps,
                HttpStatus.OK);
    }
    // http://localhost:2221/postedmaps/address/like/address
    @GetMapping(value = "/postedmaps/address/like/{address}",
            produces = {"application/json"})
    public ResponseEntity<?> getAddressLike(
            @PathVariable
                    String address,
            @PageableDefault(page = 0,
                    size = 5)
                    Pageable pageable)
    {
        List<PostedMaps> postedMaps = postedMapsService.findByAddressContaining(address);
        return new ResponseEntity<>(postedMaps,
                HttpStatus.OK);
    }



}
