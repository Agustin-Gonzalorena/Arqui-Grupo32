package com.microservice.usuario.service.implementation;

import com.microservice.usuario.persistence.entity.Cuenta;
import com.microservice.usuario.persistence.repository.CuentaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {
    @Autowired
    private CuentaRepo cuentaRepo;

    public Cuenta save(Cuenta cuenta) {
        return cuentaRepo.save(cuenta);
    }
}
