package com.microservice.usuario.service.implementation;

import com.microservice.usuario.persistence.entity.Cuenta;
import com.microservice.usuario.persistence.entity.Usuario;
import com.microservice.usuario.persistence.repository.UsuarioRepo;
import com.microservice.usuario.service.exception.UsuarioException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CuentaService cuentaService;

    @PostConstruct
    public void init() {
        Usuario usuario = Usuario.builder()
                .email("pepito@gmail.com")
                .password("1234")
                .nombre("Pepito")
                .apellido("Pepe")
                .ban(false)
                .rol("normal")
                .telefono(12244442)
                .build();
        usuarioRepo.save(usuario);

    }

    public Usuario ban(Long id) {
        try {
            Usuario usuario = usuarioRepo.findById(id).get();
            usuario.setBan(!usuario.getBan());
            usuarioRepo.save(usuario);
            return usuario;
        }catch (Exception e) {
            throw new UsuarioException("Error al actualizar estado del usuario");
        }
    }

    public boolean cobrar(Long idUser,Double monto){
        Usuario usuario = usuarioRepo.findById(idUser).get();
        List<Cuenta> cuentas = usuario.getCuentas();
        for (Cuenta cuenta : cuentas) {
            if(cuenta.getSaldo()>=monto){
                cuenta.setSaldo(cuenta.getSaldo()-monto);
                cuentaService.save(cuenta);
                return true;
            }
        }
        return false;
    }

}
