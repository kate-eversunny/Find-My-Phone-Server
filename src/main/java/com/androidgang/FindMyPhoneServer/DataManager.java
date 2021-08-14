package com.androidgang.FindMyPhoneServer;
import com.androidgang.FindMyPhoneServer.entities.DeviceEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "")
public class DataManager {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MongoTemplate mongoTemplate;

    public DataManager(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<DeviceEntity> getAllUsers() {
        logger.info("Getting all users.");
        return mongoTemplate.findAll(DeviceEntity.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DeviceEntity getUser(@PathVariable long id) {
        logger.info("Getting user with ID: {}.", id);
        return mongoTemplate.findById(id, DeviceEntity.class);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public DeviceEntity add(@RequestBody DeviceEntity usersEntity) {
        logger.info("Saving user");
        return mongoTemplate.save(usersEntity);
    }
}