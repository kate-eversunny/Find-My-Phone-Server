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
public class DeviceEntity {

    @Id//аннотация из пакета javax.persistence.*, помечает поле как id
    private String userId;

    private String deviceId;
    private String name;
    private List<Metrics> metrics = new ArrayList<>();

    public void addMetrics(Metrics m) {
        metrics.add(m);
    }
}
