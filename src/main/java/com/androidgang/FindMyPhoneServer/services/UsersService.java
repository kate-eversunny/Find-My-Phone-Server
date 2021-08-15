package com.androidgang.FindMyPhoneServer.services;

import com.androidgang.FindMyPhoneServer.entities.UserEntity;
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

    public Optional<UserEntity> getById(String id) {
        return usersRepository.findById(id);
    }

    public void delById(String id) {
        usersRepository.deleteById(id);
    }

    public Boolean exist(Example<? extends UserEntity> example) {
        return usersRepository.exists(example);
    }

    //создали публичный метод (название любое может быть)
//на вход принимает сущность и сохраняет ее в базу
    public void save(UserEntity usersEntity){
        usersRepository.save(usersEntity); //реализовали метод внедренного бина
    }

    public void saveAll(List<UserEntity> entities){
            usersRepository.saveAll(entities);
    }

    //возвращает лист всех сущностей из базы
    public List<UserEntity> getAll(){
        return usersRepository.findAll(); //реализовали метод внедренного бина
    }

    public void drop() {
        usersRepository.deleteAll();
    }
}
