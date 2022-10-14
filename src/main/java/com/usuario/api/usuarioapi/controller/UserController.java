package com.usuario.api.usuarioapi.controller;

import com.usuario.api.usuarioapi.DTO.UserDTO;
import com.usuario.api.usuarioapi.model.UserModel;
import com.usuario.api.usuarioapi.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {
    final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO userDTO){
        if(userService.existsByEmail(userDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario já cadastrado");
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        System.out.println(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value="id") long id){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") long id){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }
        userService.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletedo com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") long id, @RequestBody @Valid UserDTO userDTO){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }
        UserModel userModel = userModelOptional.get();
        userModel.setNome(userDTO.getNome());
        userModel.setTelefone(userDTO.getTelefone());
        userModel.setEmail(userDTO.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }


}
