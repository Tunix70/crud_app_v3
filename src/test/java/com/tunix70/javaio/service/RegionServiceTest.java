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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


class RegionServiceTest {
//    @Mock
    private static RegionService regionServiceMock;
    private static Region region1;
    private static Region region2;
    private static List<Region> regionList;
//
//    @BeforeClass
//    public static void setup() {
//    regionServiceMock = mock(RegionService.class);
//    region1 = new Region(1l, "ru");
//    region2 = new Region(2l, "eu");
//    List<Region> regionList = new ArrayList<>(Arrays.asList(region1, region2));
//    when(regionServiceMock.getAll()).thenReturn(regionList);
//    }

    @Test
    void test_getAll_region() {
        regionServiceMock = mock(RegionService.class);
        region1 = new Region(1l, "ru");
        region2 = new Region(2l, "eu");
        List<Region> regionList = new ArrayList<>(Arrays.asList(region1, region2));
        when(regionServiceMock.getAll()).thenReturn(regionList);

        Long id = 1l;
        List<Region> regionListTest = regionServiceMock.getAll();
        Region region = regionListTest.get(0);
        assertEquals(2, regionListTest.size());
        assertEquals(id, region.getId());

        assertNotNull(regionListTest);

    }

    @Test
    void getById() {
        regionServiceMock = mock(RegionService.class);
        region1 = new Region(1l, "ru");
        region2 = new Region(2l, "eu");
        List<Region> regionList = new ArrayList<>(Arrays.asList(region1, region2));
        when(regionServiceMock.getAll()).thenReturn(regionList);
        when(regionServiceMock.getById(1l)).thenReturn(region1);

//        Long id = 2l;
//        Region testRegion = regionServiceMock.getById(2l);
//        assertNotNull(testRegion);
//        assertEquals(id, testRegion.getId());
//        assertEquals("eu", testRegion.getName());

    }

    @Test
    void save() {
        regionServiceMock = mock(RegionService.class);
        region1 = new Region(1l, "ru");
        region2 = new Region(2l, "eu");
        List<Region> regionList = new ArrayList<>(Arrays.asList(region1, region2));
        when(regionServiceMock.getAll()).thenReturn(regionList);
        when(regionServiceMock.save(region1)).thenReturn(region1);

        Region testRegion = regionServiceMock.save(region1);
        assertNotNull(testRegion);
        assertEquals("ru", testRegion.getName());
    }

    @Test
    void update() {
        regionServiceMock = mock(RegionService.class);
        region1 = new Region(1l, "ru");
        region2 = new Region(2l, "eu");
        List<Region> regionList = new ArrayList<>(Arrays.asList(region1, region2));
        when(regionServiceMock.getAll()).thenReturn(regionList);
        when(regionServiceMock.update(region1)).thenReturn(region1);

        Region testRegion = regionServiceMock.update(region1);
        assertNotNull(testRegion);
        assertEquals("ru", testRegion.getName());
    }

    @Test
    void deleteById() {
//        regionServiceMock = mock(RegionService.class);
//        region1 = new Region(1l, "ru");
//        region2 = new Region(2l, "eu");
//        List<Region> regionList = new ArrayList<>(Arrays.asList(region1, region2));
//        when(regionServiceMock.getAll()).thenReturn(regionList);
//        doNothing().when(regionServiceMock.deleteById(1l)).
//
//        regionServiceMock.deleteById(1l);
//        assertFalse(regionList.contains(region1));

    }
}