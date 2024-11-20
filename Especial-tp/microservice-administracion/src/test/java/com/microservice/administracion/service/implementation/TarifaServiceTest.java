package com.microservice.administracion.service.implementation;

import com.microservice.administracion.persistence.entity.Tarifa;
import com.microservice.administracion.persistence.repository.TarifaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TarifaServiceTest {
    @Mock
    private TarifaRepo tarifaRepo;
    @InjectMocks
    private TarifaService tarifaService;
    private Tarifa tarifa;


    @Test
    void testAddTarifa_Success() {
        Tarifa tarifa = Tarifa.builder()
                .precio(5.0)
                .extra(1.0)
                .fechaVigencia(LocalDate.now())
                .build();

        when(tarifaRepo.save(any(Tarifa.class))).thenReturn(tarifa);

        Tarifa result = tarifaService.addTarifa(tarifa);

        assertNotNull(result);
        assertEquals(5.0, result.getPrecio());
        assertEquals(1.0, result.getExtra());
        assertEquals(LocalDate.now(), result.getFechaVigencia());

        verify(tarifaRepo).save(any(Tarifa.class));
    }

    @Test
    void testGetTarifaVigente_Success() {
        Tarifa tarifa = Tarifa.builder()
                .precio(5.0)
                .extra(1.0)
                .fechaVigencia(LocalDate.now())
                .build();
        when(tarifaRepo.findByFechaVigencia(LocalDate.now())).thenReturn(tarifa);

        Tarifa result = tarifaService.getTarifaVigente();

        assertNotNull(result);
        assertEquals(5.0, result.getPrecio());
        assertEquals(1.0, result.getExtra());
        assertEquals(LocalDate.now(), result.getFechaVigencia());

        verify(tarifaRepo).findByFechaVigencia(LocalDate.now());
    }

}
