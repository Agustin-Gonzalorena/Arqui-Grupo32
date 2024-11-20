package com.microservice.usuario.service.implementation;

import com.microservice.usuario.persistence.entity.Cuenta;
import com.microservice.usuario.persistence.entity.Usuario;
import com.microservice.usuario.persistence.repository.UsuarioRepo;
import com.microservice.usuario.presentation.dto.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock
    private UsuarioRepo usuarioRepo;

    @Mock
    private CuentaService cuentaService;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBan() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setBan(false);

        when(usuarioRepo.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepo.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDTO result = usuarioService.ban(1L);

        assertNotNull(result);
        assertTrue(result.getBan());
    }

    @Test
    void testCobrar_SufficientBalance() {

        Cuenta cuenta = new Cuenta();
        cuenta.setSaldo(100.0);
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setCuentas(List.of(cuenta));


        when(usuarioRepo.findById(1L)).thenReturn(Optional.of(usuario));
        when(cuentaService.save(any(Cuenta.class))).thenReturn(cuenta);

        boolean result = usuarioService.cobrar(1L, 50.0);

        assertTrue(result);
        assertEquals(50.0, cuenta.getSaldo());
    }

    @Test
    void testCobrar_InsufficientBalance() {
        Cuenta cuenta = new Cuenta();
        cuenta.setSaldo(20.0);
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setCuentas(List.of(cuenta));

        when(usuarioRepo.findById(1L)).thenReturn(Optional.of(usuario));
        boolean result = usuarioService.cobrar(1L, 50.0);
        assertFalse(result);
    }


}
