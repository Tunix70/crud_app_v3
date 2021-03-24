package com.tunix70.javaio.service;


import com.tunix70.javaio.model.Region;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


class RegionServiceTest {
//    private static RegionService regionServiceMock;
//    private static Region region1;
//    private static Region region2;

    @BeforeClass
//    regionServiceMock = mock(RegionService.class);
//    Region region1 = new Region(1l, "ru");
//    Region region2 = new Region(2l, "eu");
//    List<Region> regionList = new ArrayList<>(Arrays.asList(region1, region2));
//    when(regionServiceMock.getAll()).thenReturn(regionList);

    @Test
    void test_getAll_region() {
        RegionService regionServiceMock = mock(RegionService.class);
        Region region1 = new Region(1l, "ru");
        Region region2 = new Region(2l, "eu");
        List<Region> regionList = new ArrayList<>(Arrays.asList(region1, region2));
        when(regionServiceMock.getAll()).thenReturn(regionList);

        List<Region> regionList1 = regionServiceMock.getAll();
        Region region = regionList1.get(0);
        assertEquals(2, regionList.size());
        assertEquals("ru", region.getName());
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