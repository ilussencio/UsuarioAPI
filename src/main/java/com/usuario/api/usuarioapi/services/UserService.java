package com.usuario.api.usuarioapi.services;

import com.usuario.api.usuarioapi.model.UserModel;
import com.usuario.api.usuarioapi.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    @Transactional
    public UserModel save(UserModel user){
        return userRepository.save(user);
    }

    public Page<UserModel> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public Optional<UserModel> findById(long id){
        return userRepository.findById(id);
    }

    @Transactional
    public void delete(UserModel userModel){
        userRepository.delete(userModel);
    }

}
