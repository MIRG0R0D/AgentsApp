/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.agents.backend;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Dima
 */
public class MissionManagerImplTest {

    private MissionManagerImpl manager;
    private Mission killTerrorists;
    private Mission infiltrateSPD;

    public MissionManagerImplTest() {
    }


    @Before
    public void setUp() {

        manager = new MissionManagerImpl();

        killTerrorists = new MissionBuilder()
                .setCodeName("killingspree")
                .setDescription("Kill them all")
                .setEnd(LocalDate.of(8, Month.NOVEMBER, 2000))
                .setEnd(LocalDate.of(10, Month.NOVEMBER, 2000))
                .setId(null)
                .setLocation("BRNO")
                .build();

        infiltrateSPD = new MissionBuilder()
                .setCodeName("spdfree")
                .setDescription("Get info about spd organization")
                .setEnd(LocalDate.of(15, Month.MARCH, 2017))
                .setEnd(LocalDate.of(16, Month.MARCH, 2019))
                .setId(null)
                .setLocation("PRAHA")
                .build();

    }


    /**
     * Test of create method, of class MissionManagerImpl.
     */
    @Test
    public void testCreate() {
        Long terroristId = manager.createMission(killTerrorists);
        Long spdId = manager.createMission(infiltrateSPD);
        assertNotNull(terroristId);
        assertNotNull(spdId);
        assertNotEquals(terroristId, spdId);
    }

    /**
     * Test of findMissionById method, of class MissionManagerImpl.
     */
    @Test
    public void testFindMissionById() {

        Long spdId = manager.createMission(infiltrateSPD);
        assertEquals(manager.getMission(spdId), infiltrateSPD);

    }

    /**
     * Test of update method, of class MissionManagerImpl.
     */
    @Test
    public void testUpdate() {
        Long terroristId = manager.createMission(killTerrorists);
        Mission mission = manager.getMission(terroristId);
        String newCodeName = "NewName";
        mission.setCodeName(newCodeName);
        manager.updateMission(terroristId, mission);
        assertEquals(manager.getMission(terroristId).getCodeName(), newCodeName);
    }

    /**
     * Test of getUncompletedMissions method, of class MissionManagerImpl.
     */
    @Test
    public void testFindAllUncompletedMissions() {
        manager.createMission(killTerrorists);
        manager.createMission(infiltrateSPD);

        List<Mission> result = manager.getUncompletedMissions();

        assertTrue(result.size() == 1);
        assertFalse(result.contains(killTerrorists));
        assertTrue(result.contains(infiltrateSPD));
    }

    /**
     * Test of getMissions method, of class MissionManagerImpl.
     */
    @Test
    public void testFindAllMission() {
        Long terroristId = manager.createMission(killTerrorists);
        Long spdId = manager.createMission(infiltrateSPD);

        List<Mission> result = manager.getMissions();

        assertThat(result, Matchers.hasSize(2));
        assertTrue(result.contains(killTerrorists));
        assertTrue(result.contains(infiltrateSPD));
        assertTrue(terroristId != spdId);
    }

}
