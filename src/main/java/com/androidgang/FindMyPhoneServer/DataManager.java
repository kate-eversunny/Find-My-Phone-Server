package com.androidgang.FindMyPhoneServer;

import com.androidgang.FindMyPhoneServer.entities.DeviceEntity;
import com.androidgang.FindMyPhoneServer.entities.Metrics;
import com.androidgang.FindMyPhoneServer.entities.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
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
    public List<UserEntity> getAllUsers() {
        logger.info("Getting all users.");
        return mongoTemplate.findAll(UserEntity.class);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public UserEntity getUser(@PathVariable String email) {
        logger.info("Getting user with ID: {}.", email);
        return mongoTemplate.findById(email, UserEntity.class);
    }

    @RequestMapping(value = "/{email}/{deviceId}", method = RequestMethod.GET)
    public DeviceEntity getDevice(@PathVariable String email, @PathVariable String id) {
        logger.info("Getting user with ID: {}.", id);
        UserEntity user = mongoTemplate.findById(email, UserEntity.class);
        if (user != null) {
            ArrayList<DeviceEntity> devices = (ArrayList<DeviceEntity>) user.getDevices();
            for (DeviceEntity device : devices) {
                if (device.getDeviceId().equals(id)) {
                    return device;
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public UserEntity add(@RequestBody UserEntity userEntity) {
        logger.info("Saving user");
        String email = userEntity.getEmail();
        if (mongoTemplate.findById(email, UserEntity.class) == null) {
            ArrayList<DeviceEntity> devices = (ArrayList<DeviceEntity>) userEntity.getDevices();
            if (!devices.isEmpty()) {
                for (DeviceEntity device : devices) {
                    device.setUserId(new ObjectId().toString());
                }
            }
            return mongoTemplate.save(userEntity);
        }
        return null;
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.POST)
    public DeviceEntity addMetrics(@PathVariable String email, @RequestBody DeviceEntity deviceEntity) {
        logger.info("Saving device data");
        UserEntity user = mongoTemplate.findById(email, UserEntity.class);
        if (user != null) {
            ArrayList<DeviceEntity> devices = (ArrayList<DeviceEntity>) user.getDevices();
            if (!devices.isEmpty()) {
                for (DeviceEntity device : devices) {
                    if (device.getDeviceId().equals(deviceEntity.getDeviceId())) {

                        ArrayList<Metrics> metrics = (ArrayList<Metrics>) deviceEntity.getMetrics();
                        for (Metrics m : metrics) {
                            device.addMetrics(m);
                        }
                        return device;
                    }
                }
            } else {
                DeviceEntity device = deviceEntity;
                device.setUserId(new ObjectId().toString());
                user.addDevices(device);
                return device;
            }
        }
        return null;
    }
}