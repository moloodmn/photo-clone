package com.jetbrains.molood.photos.clone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
public class PhotosController {

//    List<Photo> db = List.of(new Photo("1", "photo.jpg"));
    private Map<String, Photo> db = new HashMap<>(){{
        put("1", new Photo("1", "Hello.jpg"));
}};


    ///this is for test
    @GetMapping("/")
   public String hello(){
       return "Hello World!";
   }

    //   public List<Photo> get(){
   @GetMapping("/photos")
   public Collection<Photo> get(){
        return db.values();
   }

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id){
        Photo photo = db.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }


    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id){
        Photo photo = db.remove(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/photos")
    public Photo create(@RequestBody @Valid Photo photo){
        photo.setId(UUID.randomUUID().toString());
        db.put(photo.getId(), photo);
        return photo;
    }


}
