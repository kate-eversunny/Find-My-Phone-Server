package com.androidgang.FindMyPhoneServer.utils;

import com.androidgang.FindMyPhoneServer.entities.DeviceEntity;
import com.androidgang.FindMyPhoneServer.entities.Metrics;
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
    private int id = 0;
//
    @Override
    public void run(String... args) throws Exception {
//
//создаем несколько сущностей фруктов, через сеттеры заполняем поля
        usersService.drop();

        metrics.setLongitude(122);
        metrics.setLatitude(53);

        DeviceEntity devicesEntity1 = new DeviceEntity();
//        devicesEntity1.setId(++id);
        devicesEntity1.setUserId(new ObjectId().toString());
        devicesEntity1.setName("device1");
        devicesEntity1.addMetrics(metrics);
        devicesEntity1.addMetrics(metrics);
//        usersEntity1.setUserName("user1");
//        usersEntity1.setEmail("a@a.ru");

        DeviceEntity devicesEntity2 = new DeviceEntity();
//        devicesEntity2.setId(++id);
        devicesEntity2.setUserId(new ObjectId().toString());
        devicesEntity2.setName("device2");
        devicesEntity2.addMetrics(metrics);
//        usersEntity2.setUserName("user2");
//        usersEntity2.setEmail("b@b.ru");

        DeviceEntity devicesEntity3 = new DeviceEntity();
//        devicesEntity3.setId(++id);
        devicesEntity3.setUserId(new ObjectId().toString());
        devicesEntity3.setName("device3");
        devicesEntity3.addMetrics(metrics);
//        usersEntity3.setUserName("user3");
//        usersEntity3.setEmail("c@c.ru");

//с помощью переменной сервиса вызываем методы сохранения в базу, по разу для одного объекта
        usersService.save(devicesEntity1);
        usersService.save(devicesEntity2);
        usersService.save(devicesEntity3);

//здесь вытаскиваем базу обратно
        List<DeviceEntity> all = usersService.getAll();

//и выводим что получилось
        for (DeviceEntity entity : all) {
            System.out.println(entity);
        }
    }
}