package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {

    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDogs(@RequestBody Dog dog){
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs/find/{name}/{ownerName}/{ownerPhoneNumber}")
    public Dog getDog(@PathVariable String name, @PathVariable String ownerName, @PathVariable String ownerPhoneNumber){
        return dogManagementService.searchDog(name, ownerName, ownerPhoneNumber);
    }

//    // localhost:8080/dogs/ian
//    @GetMapping("/dogs/{name}")
//    public Dog getDogByName(@PathVariable String name){
//        return dogManagementService.getDogByName(name);
//    }

}
