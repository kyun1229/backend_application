package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/dogs/updateKind/{name}/{kind}")
    public void updateKind(@PathVariable String name, @PathVariable String kind) {
        dogManagementService.updateKind(name, kind);
    }

    @PatchMapping("/dogs/updateMedicalRecords/{name}/{addMedicalRecords}")
    public void updateMedicalRecords(@PathVariable String name, @PathVariable String addMedicalRecords) {
        dogManagementService.updateMedicalRecords(name, addMedicalRecords);
    }
}
