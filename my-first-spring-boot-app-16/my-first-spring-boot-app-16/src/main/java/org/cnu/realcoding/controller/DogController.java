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

    @GetMapping("/dogs")
    public void getDog(@RequestBody Dog dog){
        dogManagementService.searchDog(dog);
    }

    @GetMapping("/dogs/findByName/{name}")
    public Dog getDogByName(@PathVariable String name){
        return dogManagementService.getDogByName(name);
    }

    @GetMapping("/dogs/findByOwnerName/{ownerName}")
    public Dog getDogByOwnerName(@PathVariable String ownerName){
        return dogManagementService.getDogByOwnerName(ownerName);
    }

    @GetMapping("/dogs/findByOwnerPhoneNumber/{ownerPhoneNumber}")
    public Dog getDogByOwnerPhoneNumber(@PathVariable String ownerPhoneNumber){
        return dogManagementService.getDogByOwnerPhoneNumber(ownerPhoneNumber);
    }

    @PutMapping("/dogs/{prevName}/{newName}/{newKind}/{newOwnerName}/{newOwnerPhoneNumber}/{addMedicalRecords}")
    public void updateDogInform(@PathVariable String prevName, @PathVariable String newName, @PathVariable String newKind, @PathVariable String newOwnerName, @PathVariable String newOwnerPhoneNumber, @PathVariable String addMedicalRecords){
        dogManagementService.updateDog(prevName,newName,newKind,newOwnerName,newOwnerPhoneNumber,addMedicalRecords);
    }
}