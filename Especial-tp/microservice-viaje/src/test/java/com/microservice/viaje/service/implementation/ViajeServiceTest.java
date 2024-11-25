package com.microservice.viaje.service.implementation;

import com.microservice.viaje.persistence.entity.Viaje;
import com.microservice.viaje.persistence.repository.ViajeRepo;
import com.microservice.viaje.presentation.dto.MonopatinResponseDTO;
import com.microservice.viaje.presentation.dto.ViajeCreateDTO;
import com.microservice.viaje.presentation.response.ApiResponse;
import com.microservice.viaje.service.http.AdministracionClient;
import com.microservice.viaje.service.http.MonopatinClient;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ViajeServiceTest {
    @InjectMocks
    private ViajeService viajeService;

    @Mock
    private ViajeRepo viajeRepo;

    @Mock
    private MonopatinClient monopatinClient;



    @Before("")
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        ViajeCreateDTO viajeCreateDTO = new ViajeCreateDTO();
        viajeCreateDTO.setMonopatinId(1L);
        viajeCreateDTO.setUsuarioId(1L);

        MonopatinResponseDTO monopatinResponseDTO = mock(MonopatinResponseDTO.class);
        when(monopatinResponseDTO.isEnMantenimiento()).thenReturn(false);

        ApiResponse<MonopatinResponseDTO> monopatinApiResponse = mock(ApiResponse.class);
        when(monopatinApiResponse.getData()).thenReturn(monopatinResponseDTO);

        ResponseEntity<ApiResponse<MonopatinResponseDTO>> responseEntity = ResponseEntity.ok(monopatinApiResponse);
        when(monopatinClient.getMonopatinById(1L)).thenReturn(responseEntity);

        Viaje viaje = new Viaje(viajeCreateDTO);
        when(viajeRepo.save(any(Viaje.class))).thenReturn(viaje);

        Viaje result = viajeService.create(viajeCreateDTO);

        assertNotNull(result);
        verify(viajeRepo).save(any(Viaje.class));
    }

    @Test
    public void testPausar() {
        Long idUsuario = 1L;
        Viaje viaje = new Viaje();
        viaje.setId(1L);

        LocalDateTime inicio = LocalDateTime.now().minusMinutes(30);
        viaje.setInicio(inicio);
        viaje.setFin(null);


        when(viajeRepo.findByActivoByUsuarioId(idUsuario)).thenReturn(viaje);
        when(viajeRepo.save(any(Viaje.class))).thenReturn(viaje);

        Viaje result = viajeService.pausar(idUsuario);

        assertNotNull(result);
        assertNotNull(result.getInicioPausa());
        verify(viajeRepo).save(any(Viaje.class));
    }

    @Test
    public void testFinPausa() {
        Long idUsuario = 1L;
        Viaje viaje = new Viaje();
        viaje.setId(1L);
        viaje.setInicio(LocalDateTime.now().minusMinutes(30));
        viaje.setFin(null);
        viaje.setInicioPausa(LocalDateTime.now().minusMinutes(5));


        when(viajeRepo.findByActivoByUsuarioId(idUsuario)).thenReturn(viaje);
        when(viajeRepo.save(any(Viaje.class))).thenReturn(viaje);

        Viaje result = viajeService.finPausa(idUsuario);

        assertNotNull(result);
        assertNotNull(result.getFinPausa());
        verify(viajeRepo).save(any(Viaje.class));
    }

    @Test
    public void testMonopatinesIdsPorViajes() {

        int viajes = 10;
        int anio = 2024;
        List<Long> expectedIds = Arrays.asList(1L, 2L, 3L);

        when(viajeRepo.monopatinIdsPorViajes(viajes, anio)).thenReturn(expectedIds);

        List<Long> result = viajeService.monopatinesIdsPorViajes(viajes, anio);

        assertNotNull(result);
        assertEquals(expectedIds, result);
        verify(viajeRepo).monopatinIdsPorViajes(viajes, anio);
    }

}
