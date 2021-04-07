package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;

@Repository
public class DogRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Dog findDog(String name, String ownerName, String ownerPhoneNumber) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("name").is(name),Criteria.where("ownerName").is(ownerName),Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber));
        query.addCriteria(criteria);
        return mongoTemplate
                .findOne(
                        query,
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

    public void insertDog(Dog dog) {
        mongoTemplate.insert(dog);
    }
}
