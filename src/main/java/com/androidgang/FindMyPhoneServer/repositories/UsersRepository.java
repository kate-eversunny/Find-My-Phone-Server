package com.androidgang.FindMyPhoneServer.repositories;

import com.androidgang.FindMyPhoneServer.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository <UserEntity, String> {

}