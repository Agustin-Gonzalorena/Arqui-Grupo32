package com.microservice.monopatin.service.implementation;

import com.microservice.monopatin.persistence.entity.Monopatin;
import com.microservice.monopatin.persistence.repository.MonopatinRepo;
import com.microservice.monopatin.presentation.dto.EnOperacionDTO;
import com.microservice.monopatin.presentation.dto.MonopatinCreateDTO;
import com.microservice.monopatin.presentation.dto.ReporteMonopatinDTO;
import com.microservice.monopatin.presentation.response.ApiResponse;
import com.microservice.monopatin.service.exception.MonopatinException;
import com.microservice.monopatin.service.http.ParadaClient;
import com.microservice.monopatin.service.http.ViajeClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MonopatinServiceTest {
    @Mock
    private MonopatinRepo monopatinRepo;

    @Mock
    private ParadaClient paradaClient;

    @Mock
    private ViajeClient viajeClient;

    @InjectMocks
    private MonopatinService monopatinService;

    private MonopatinCreateDTO monopatinCreateDTO;

    @BeforeEach
    void setUp() {
        monopatinCreateDTO = new MonopatinCreateDTO();
        monopatinCreateDTO.setParadaId(String.valueOf(1L));
    }

    @Test
    void testCreate_Success() {
        when(paradaClient.getParadaById(String.valueOf(1L))).thenReturn(null);

        Monopatin savedMonopatin = new Monopatin(monopatinCreateDTO);
        when(monopatinRepo.save(any(Monopatin.class))).thenReturn(savedMonopatin);

        Monopatin result = monopatinService.create(monopatinCreateDTO);

        assertNotNull(result);
        verify(paradaClient).getParadaById(String.valueOf(1L));
        verify(monopatinRepo).save(any(Monopatin.class));
    }


    @Test
    void testCreate_ThrowsException_WhenParadaNotFound() {
        doThrow(new RuntimeException("Parada no encontrada")).when(paradaClient).getParadaById(String.valueOf(1L));

        assertThrows(MonopatinException.class, () -> monopatinService.create(monopatinCreateDTO));

        verify(monopatinRepo, never()).save(any(Monopatin.class));
    }

    @Test
    void testPonerEnMantenimiento_Success() {
        Long id = 1L;
        Monopatin monopatin = new Monopatin();
        monopatin.setId(id);
        monopatin.setEnMantenimiento(false);

        when(monopatinRepo.findById(id)).thenReturn(Optional.of(monopatin));
        when(monopatinRepo.save(any(Monopatin.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Monopatin updatedMonopatin = monopatinService.ponerEnMantenimiento(id);

        assertNotNull(updatedMonopatin);
        assertTrue(updatedMonopatin.isEnMantenimiento());
        verify(monopatinRepo).findById(id);
        verify(monopatinRepo).save(monopatin);
    }

    @Test
    void testContarPorEstado_Success() {
        EnOperacionDTO mockResponse = new EnOperacionDTO(10L, 5L);

        when(monopatinRepo.contarPorEstado()).thenReturn(mockResponse);

        EnOperacionDTO result = monopatinService.contarPorEstado();

        assertNotNull(result);
        assertEquals(10, result.getDisponibles());
        assertEquals(5, result.getEnMantenimiento());
        verify(monopatinRepo).contarPorEstado();
    }

    @Test
    void testPorCantidadViajes_Success() {
        List<Long> monopatinIds = List.of(1L, 2L, 3L);
        List<Monopatin> expectedMonopatines = new ArrayList<>();

        MonopatinCreateDTO dto1 = new MonopatinCreateDTO();
        dto1.setParadaId(String.valueOf(101L));
        Monopatin monopatin1 = new Monopatin(dto1);

        MonopatinCreateDTO dto2 = new MonopatinCreateDTO();
        dto2.setParadaId(String.valueOf(102L));
        Monopatin monopatin2 = new Monopatin(dto2);

        MonopatinCreateDTO dto3 = new MonopatinCreateDTO();
        dto3.setParadaId(String.valueOf(103L));
        Monopatin monopatin3 = new Monopatin(dto3);

        expectedMonopatines.add(monopatin1);
        expectedMonopatines.add(monopatin2);
        expectedMonopatines.add(monopatin3);

        ApiResponse<List<Long>> mockApiResponse = new ApiResponse<>(200, monopatinIds);
        ResponseEntity<ApiResponse<List<Long>>> mockResponse = ResponseEntity.ok(mockApiResponse);

        when(viajeClient.getIdmonopatinPorViajes(anyInt(), anyInt())).thenReturn(mockResponse);

        when(monopatinRepo.findById(1L)).thenReturn(Optional.of(monopatin1));
        when(monopatinRepo.findById(2L)).thenReturn(Optional.of(monopatin2));
        when(monopatinRepo.findById(3L)).thenReturn(Optional.of(monopatin3));

        List<Monopatin> result = monopatinService.porCantidadViajes(5, 2024);

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(viajeClient).getIdmonopatinPorViajes(5, 2024);
        verify(monopatinRepo, times(3)).findById(anyLong());
    }

    @Test
    void testGenerarReporte_Success_ConPausa() {
        boolean conPausa = true;
        List<ReporteMonopatinDTO> expectedReporte = new ArrayList<>();

        ReporteMonopatinDTO reporte1 = new ReporteMonopatinDTO(1L, 100.0,20.0);

        ReporteMonopatinDTO reporte2 = new ReporteMonopatinDTO(2L,150.0,25.0);

        expectedReporte.add(reporte1);
        expectedReporte.add(reporte2);

        when(monopatinRepo.reporte(conPausa)).thenReturn(expectedReporte);

        List<ReporteMonopatinDTO> result = monopatinService.reporte(conPausa);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(reporte1.getId(), result.get(0).getId());
        assertEquals(reporte2.getId(), result.get(1).getId());

        verify(monopatinRepo).reporte(conPausa);
    }

}
