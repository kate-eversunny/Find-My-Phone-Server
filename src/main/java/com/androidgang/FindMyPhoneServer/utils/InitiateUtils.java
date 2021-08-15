package com.androidgang.FindMyPhoneServer.utils;

import com.androidgang.FindMyPhoneServer.entities.DeviceEntity;
import com.androidgang.FindMyPhoneServer.entities.Metrics;
import com.androidgang.FindMyPhoneServer.entities.UserEntity;
import com.androidgang.FindMyPhoneServer.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InitiateUtils implements CommandLineRunner {
//
    private final UsersService usersService;
    private Metrics metrics = new Metrics();
//
    @Override
    public void run(String... args) throws Exception {
//
//создаем несколько сущностей фруктов, через сеттеры заполняем поля
        usersService.drop();

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setEmail("a@a.ru");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("b@b.ru");

        metrics.setLongitude(122);
        metrics.setLatitude(53);

        DeviceEntity devicesEntity1 = new DeviceEntity();
        devicesEntity1.setUserId(new ObjectId().toString());
        devicesEntity1.setName("device1");
        devicesEntity1.addMetrics(metrics);
        devicesEntity1.addMetrics(metrics);

        DeviceEntity devicesEntity2 = new DeviceEntity();
        devicesEntity2.setUserId(new ObjectId().toString());
        devicesEntity2.setName("device2");
        devicesEntity2.addMetrics(metrics);

        DeviceEntity devicesEntity3 = new DeviceEntity();
        devicesEntity3.setUserId(new ObjectId().toString());
        devicesEntity3.setName("device3");
        devicesEntity3.addMetrics(metrics);

//с помощью переменной сервиса вызываем методы сохранения в базу, по разу для одного объекта
        userEntity1.addDevices(devicesEntity1);
        userEntity1.addDevices(devicesEntity2);
        userEntity1.addDevices(devicesEntity3);
        usersService.save(userEntity1);

        userEntity2.addDevices(devicesEntity1);
        userEntity2.addDevices(devicesEntity2);
        userEntity2.addDevices(devicesEntity3);
        usersService.save(userEntity2);

//здесь вытаскиваем базу обратно
        List<UserEntity> all = usersService.getAll();

    }
}