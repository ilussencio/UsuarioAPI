package com.usuario.api.usuarioapi.usuario.controller;

import com.usuario.api.usuarioapi.email.model.EmailModel;
import com.usuario.api.usuarioapi.email.services.EmailService;
import com.usuario.api.usuarioapi.usuario.DTO.UserDTO;
import com.usuario.api.usuarioapi.usuario.enums.Access;
<<<<<<< Updated upstream
import com.usuario.api.usuarioapi.usuario.error.ErrorMessage;
=======
import com.usuario.api.usuarioapi.usuario.ErrorMessage.ErrorMessage;
import com.usuario.api.usuarioapi.usuario.model.CodResetModel;
>>>>>>> Stashed changes
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
import java.util.Random;

@RestController
@RequestMapping("/usuario")
public class UserController {
    final UserService userService;
    final EmailService emailService;

    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    //CRIAR USUARIO
    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO userDTO){
        if(userService.existsByEmail(userDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage("E-mail já cadastrado!"));
        }

        if(userService.existsByUsername(userDTO.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage("Usuario já cadastrado!"));
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        userModel.setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setUpdateAt(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setNivel_access(Access.USER);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }
    //LISTAR TODOS OS USUARIOS
    @GetMapping
    public ResponseEntity<Page<UserModel>> findAll(@PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value="id") long id){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Usuario não encontrado"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    //DELETAR POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") long id){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Usuario não encontrado"));
        }
        userService.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage("Usuario deletado com sucesso!"));
    }

    //ALTERAR POR ID
    @PutMapping("/buscar/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") long id, @RequestBody @Valid UserDTO userDTO){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Usuario não encontrado"));
        }

        UserModel userModel = userModelOptional.get();
        userModel.setName(userDTO.getName());
        userModel.setFone(userDTO.getFone());
        userModel.setEmail(userDTO.getEmail());

        userModel.setUpdateAt(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));

    }
    @GetMapping("/buscar/{pesquisa}")
    public ResponseEntity<Object> findByAll(@PathVariable(value = "pesquisa") String pesquisa){
        Optional<UserModel> userModelOptional = userService.findByUsername(pesquisa);
        if(userModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());

        userModelOptional = userService.findByEmail(pesquisa);

        if(userModelOptional.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Usuario não encontrado"));
    }

<<<<<<< Updated upstream
=======
    //RESET PASSWORD
    @GetMapping("/token_password/{id}")
    public ResponseEntity<Object> generateCod(@PathVariable(value = "id") long id){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Usuario não encontrado"));
        }
        Random gerador = new Random();
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < 6; i ++){
            str.append(gerador.nextInt(10));
        }
        CodResetModel codModel = new CodResetModel();
        codModel.setCodigo(Integer.parseInt(str.toString()));
        codModel.setUser(userModelOptional.get());
        codModel.setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));
        codModel.setExpiration(LocalDateTime.now(ZoneId.of("UTC")).plusHours(2));
        codModel.setValidacao(false);
        codResetService.createCode(codModel);

        EmailModel emailModel = new EmailModel();
        emailModel.setEmailTo(userModelOptional.get().getEmail());
        emailModel.setEmailFrom("ilussencio@gmail.com");
        emailModel.setOwnerRef(userModelOptional.get().getUsername());
        emailModel.setSubject("SUPORTE: Redefinição de senha");
        emailModel.setText("<html><center><b>Olá "+userModelOptional.get().getNome()+"</b> <p> Vimos que você solicitou uma alteração de senha. Utilize este PIN para redefinir sua senha. </p> <p>PIN temporário:</p> <h1>"+codModel.getCodigo()+"</h1> <p> Caso tenha alguma dúvida entre em contato com nosso suporte ficaremos felizes em ajudar.</center></html>");

        return ResponseEntity.status(HttpStatus.OK).body(codResetService.createCode(codModel));
    }
>>>>>>> Stashed changes

}
