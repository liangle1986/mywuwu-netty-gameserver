package com.mywuwu.gameserver.data.monoRepository;

import com.mywuwu.gameserver.data.monoModel.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<UserModel,String> {

    Optional<UserModel> findById(long id);
    UserModel  findByNameAndPassword(String name, String password);
    Optional<UserModel>  findByName(String name);
    UserModel  findByNameAndUserType(String name, int userType);
}