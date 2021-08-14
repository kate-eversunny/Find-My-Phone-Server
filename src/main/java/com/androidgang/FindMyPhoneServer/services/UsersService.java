package com.androidgang.FindMyPhoneServer.services;

import com.androidgang.FindMyPhoneServer.entities.DeviceEntity;
import com.androidgang.FindMyPhoneServer.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//помечаем что этот бин - сервис
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;  //final переменная репозитория

    public Optional<DeviceEntity> getById(Long id) {
        return usersRepository.findById(id);
    }

    public void delById(Long id) {
        usersRepository.deleteById(id);
    }

    public Boolean exist(Example<? extends DeviceEntity> example) {
        return usersRepository.exists(example);
    }

    //создали публичный метод (название любое может быть)
//на вход принимает сущность и сохраняет ее в базу
    public void save(DeviceEntity usersEntity){
        usersRepository.save(usersEntity); //реализовали метод внедренного бина
    }

    public void saveAll(List<DeviceEntity> entities){
            usersRepository.saveAll(entities);
    }

    //возвращает лист всех сущностей из базы
    public List<DeviceEntity> getAll(){
        return usersRepository.findAll(); //реализовали метод внедренного бина
    }

    public void drop() {
        usersRepository.deleteAll();
    }
}
