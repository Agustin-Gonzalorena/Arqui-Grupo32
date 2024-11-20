package com.microservice.parada.service.implementation;

import com.microservice.parada.persistence.entity.Parada;
import com.microservice.parada.persistence.repository.ParadaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParadaServiceTest {
    @Mock
    private ParadaRepo paradaRepo;

    @InjectMocks
    private ParadaService paradaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        paradaService.initData();
    }

    @Test
    void testCerca() {
        List<Parada> mockParadas = new ArrayList<>();
        mockParadas.add(new Parada("9d2c1824-91db-49ba-b3c0-63f2f15ba0ea", -37.329617, -59.137109));
        mockParadas.add(new Parada("4c8c2d0d-e9f2-44f8-9975-cb921c3a3e84", -37.341537, -59.130793));

        when(paradaRepo.cercanas(anyDouble(), anyDouble(), anyDouble(), anyDouble())).thenReturn(mockParadas);

        List<Parada> result = paradaService.cerca(-37.330000, -59.137000);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
