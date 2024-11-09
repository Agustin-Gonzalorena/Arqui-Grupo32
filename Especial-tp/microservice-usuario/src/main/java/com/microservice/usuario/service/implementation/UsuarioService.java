package com.microservice.usuario.service.implementation;

import com.microservice.usuario.persistence.entity.Usuario;
import com.microservice.usuario.persistence.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepo usuarioRepo;

    //anular cuenta TODO
    public Usuario ban(Long id) {
        try {
            Usuario usuario = usuarioRepo.findById(id).get();
            usuario.setBan(!usuario.getBan());
            usuarioRepo.save(usuario);
            return usuario;
        }catch (Exception e) {
            e.printStackTrace(); //TODO
            return null;
        }
    }

}
