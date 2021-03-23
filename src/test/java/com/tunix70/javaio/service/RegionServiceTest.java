package com.tunix70.javaio.service;


import com.tunix70.javaio.model.Region;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;


class RegionServiceTest {
    private static Region region1;
    private static Region region2;

    @BeforeClass
    public static void setUp() {
        //create mock object of RegionService
        RegionService regionService = mock(RegionService.class);

        //create new instances of Region class
        region1 = new Region(1L, "RU");
        region2 = new Region(2L, "UA");

        when(regionService.getAll()).thenReturn(Arrays.asList(region1, region2));
        when(regionService.getById(1l)).thenReturn(region1);

    }
    @Test
    void test_getAll_region() {

    }

    @Test
    void getById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}