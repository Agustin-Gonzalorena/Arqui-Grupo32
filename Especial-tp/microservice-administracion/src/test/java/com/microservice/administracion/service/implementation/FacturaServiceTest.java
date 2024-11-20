package com.microservice.administracion.service.implementation;

import com.microservice.administracion.persistence.entity.Factura;
import com.microservice.administracion.persistence.entity.Tarifa;
import com.microservice.administracion.persistence.repository.FacturaRepo;
import com.microservice.administracion.presentation.dto.ViajeDTO;
import com.microservice.administracion.service.exception.AdministracionException;
import com.microservice.administracion.service.http.UsuarioClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTest {

    @Mock
    private FacturaRepo facturacionRepo;
    @Mock
    private TarifaService tarifaService;
    @Mock
    private UsuarioClient usuarioClient;
    @InjectMocks
    private FacturaService facturaService;

    @Test
    void testCreateFactura_Success() {
        ViajeDTO viajeDTO = new ViajeDTO();
        viajeDTO.setUsuarioId(1L);
        viajeDTO.setInicio(LocalDate.now().atStartOfDay());
        viajeDTO.setFin(LocalDate.now().atTime(1, 0));

        Tarifa tarifa = Tarifa.builder()
                .precio(1.0)
                .extra(2.0)
                .build();

        when(tarifaService.getTarifaVigente()).thenReturn(tarifa);

        Factura mockFactura = new Factura(viajeDTO, 3600.0);
        when(facturacionRepo.save(any(Factura.class))).thenReturn(mockFactura);

        Factura result = facturaService.create(viajeDTO);

        assertNotNull(result);
        assertEquals(3600.0, result.getMontoCobrado());
        verify(usuarioClient).cobrar(viajeDTO.getUsuarioId(), 3600.0);
        verify(facturacionRepo).save(any(Factura.class));
    }

    @Test
    void testCreateFactura_ExceptionThrown() {

        ViajeDTO viajeDTO = new ViajeDTO();
        viajeDTO.setUsuarioId(1L);
        viajeDTO.setInicio(LocalDate.now().atStartOfDay());
        viajeDTO.setFin(LocalDate.now().atTime(1, 0));

        Tarifa tarifa = Tarifa.builder().precio(1.0).extra(2.0).build();
        when(tarifaService.getTarifaVigente()).thenReturn(tarifa);

        doThrow(new RuntimeException("Error al cobrar")).when(usuarioClient).cobrar(anyLong(), anyDouble());

        assertThrows(AdministracionException.class, () -> facturaService.create(viajeDTO));

        verify(facturacionRepo, never()).save(any(Factura.class));
    }

    @Test
    void testTotalFacturado_Success() {
        when(facturacionRepo.totalFacturado(2024, 1, 6)).thenReturn(1000.0);
        Double result = facturaService.totalFacturado(2024, 1, 6);
        assertNotNull(result);
        assertEquals(1000.0, result);
        verify(facturacionRepo).totalFacturado(2024, 1, 6);
    }

    @Test
    void testTotalFacturado_ExceptionThrown() {

        when(facturacionRepo.totalFacturado(anyInt(), anyInt(), anyInt()))
                .thenThrow(new RuntimeException("Error al consultar"));

        assertThrows(AdministracionException.class, () -> facturaService.totalFacturado(2024, 1, 6));
    }



}

