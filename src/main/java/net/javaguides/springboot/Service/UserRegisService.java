package net.javaguides.springboot.Service;


import net.javaguides.springboot.Model.User;
import net.javaguides.springboot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegisService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public void addUser(User user){
        userRepository.save(user);
    }

    public void removeUser(long id){
        userRepository.deleteById(id);
    }

    public Optional<User> getUserID(long id){
        return userRepository.findById(id);
    }

}
