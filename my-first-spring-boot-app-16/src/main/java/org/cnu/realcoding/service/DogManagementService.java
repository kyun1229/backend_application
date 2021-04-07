package org.cnu.realcoding.service;

import lombok.Getter;
import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertDog(Dog dog) {
        if (mongoTemplate
                .findOne(
                        Query.query(Criteria.where("name").is(dog.getName())),
                        Dog.class
                ) == null && mongoTemplate
                .findOne(
                        Query.query(Criteria.where("ownerName").is(dog.getOwnerName())),
                        Dog.class
                ) == null && mongoTemplate
                .findOne(
                        Query.query(Criteria.where("ownerPhoneNumber").is(dog.getOwnerPhoneNumber())),
                        Dog.class
                ) == null
        ){
            dogRepository.insertDog(dog);
        }
        else{
            throw new DogNotFoundException();
        }
//        if(dogRepository.findName(dog.getName())!=null || dogRepository.findOwnerName(dog.getOwnerName())!=null || dogRepository.findPhoneNum(dog.getOwnerPhoneNumber())!=null){
//            dogRepository.insertDog(dog);
//        }
//        else{
//            throw new DogNotFoundException();
//        }
    }

    public Dog searchDog(String name, String ownerName, String ownerPhoneNumber){
        Dog dog = dogRepository.findDog(name, ownerName, ownerPhoneNumber);

        if(dog == null){
            throw new DogNotFoundException();
        }
        return dog;
    }


    public Dog getDogByName(String name) {
       Dog dog = dogRepository.findDogByName(name);

       if(dog == null){
           throw new DogNotFoundException();
       }
       return dog;
    }

    public Dog getDogByOwnerName(String ownerName) {
        Dog dog = dogRepository.findDogByOwnerName(ownerName);

        if(dog == null){
            throw new DogNotFoundException();
        }
        return dog;
    }

    public Dog getDogByOwnerPhoneNumber(String ownerPhoneNumber) {
        Dog dog = dogRepository.findDogByOwnerPhoneNumber(ownerPhoneNumber);

        if(dog == null){
            throw new DogNotFoundException();
        }
        return dog;
    }

    public void updateDog(String prevName, String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber, String addMedicalRecords) {
        dogRepository.updateDog(prevName, newName, newKind, newOwnerName, newOwnerPhoneNumber, addMedicalRecords);
    }

    public void updateKind(String name, String kind) {
        dogRepository.updateKind(name, kind);
    }
}
