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


//    public Dog getDogByName(String name) {
//       Dog dog = dogRepository.findDog(name);
//
//       if(dog == null){
//           throw new DogNotFoundException();
//       }
//       return dog;
//    }
}
