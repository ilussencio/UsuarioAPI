package com.usuario.api.usuarioapi.usuario.controller;

import com.usuario.api.usuarioapi.usuario.DTO.UserDTO;
import com.usuario.api.usuarioapi.usuario.model.UserModel;
import com.usuario.api.usuarioapi.usuario.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UserController {
    final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO userDTO){
//        if(userService.existsByEmail(userDTO.getEmail())){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario já cadastrado");
//        }

        System.out.println(userDTO);
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        userModel.setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setUpdateAt(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

    @GetMapping
    public ResponseEntity<Page<UserModel>> findAll(@PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
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
        userModel.setUpdateAt(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }


}
