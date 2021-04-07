package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Repository
public class DogRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Dog findDog(String name){
        return mongoTemplate
                .findOne(
                        Query.query(Criteria.where("name").is(name)),
                    Dog.class
                );
    }

    public boolean exists(String name){
        return mongoTemplate.exists(
                Query.query(Criteria.where("name").is(name)),
                Dog.class
        );
    }

    public Dog findDogByName(String name){
        return mongoTemplate
                .findOne(
                        Query.query(Criteria.where("name").is(name)),
                        Dog.class
                );
    }

    public Dog findDogByOwnerName(String ownerName){
        return mongoTemplate
                .findOne(
                        Query.query(Criteria.where("ownerName").is(ownerName)),
                        Dog.class
                );
    }

    public Dog findDogByOwnerPhoneNumber(String ownerPhoneNumber){
        return mongoTemplate
                .findOne(
                        Query.query(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)),
                        Dog.class
                );
    }

    public void updateDog(String prevName, String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber, String addMedicalRecords) {
        Query query = new Query(Criteria.where("name").is(prevName));

        Dog prev = mongoTemplate
                .findOne(
                        query,
                        Dog.class
                );

        if(prev == null){
            throw new DogNotFoundException();
        }

        Update update = new Update();
        List medical = prev.getMedicalRecords();
        medical.add(addMedicalRecords);
        update.set("name",newName).set("kind",newKind).set("ownerName",newOwnerName).set("ownerPhoneNumber",newOwnerPhoneNumber).set("medicalRecords",medical);
        mongoTemplate.updateMulti(query, update, Dog.class);
    }

    public void insertDog(Dog dog) {
        mongoTemplate.insert(dog);
    }
}
