package com.androidgang.FindMyPhoneServer.entities;


import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@Accessors(chain = true)
public class UserEntity {

    @Id
    private String email;

    private List<DeviceEntity> devices = new ArrayList<>();

    public void addDevices(DeviceEntity d) {
        devices.add(d);
    }
}
