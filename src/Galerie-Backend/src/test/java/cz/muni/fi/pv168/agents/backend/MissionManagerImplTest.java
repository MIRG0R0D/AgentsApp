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
 *
 * @author Dima
 */
public class MissionManagerImplTest {

    private MissionManagerImpl manager;

    public MissionManagerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         
        //manager = new MissionManagerImpl();
    }
    
    @After
    public void tearDown() {
    }

    private Mission killTerrorists(){
        return new MissionBuilder()
                .setCodeName("killingspree")
                .setDescription("Kill them all")
                .setEnd(LocalDate.of(8, Month.NOVEMBER, 2000))
                .setEnd(LocalDate.of(10, Month.NOVEMBER, 2000))
                .setId(null)
                .setLocation("BRNO")
                .build();
    }

    private Mission InfiltrateSPD(){
        return new MissionBuilder()
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
        manager = new MissionManagerImpl();
        Long terroristId = manager.createMission(killTerrorists());
        Long spdId = manager.createMission(InfiltrateSPD());
        assertFalse (terroristId.equals("null"));
        assertFalse (spdId.equals("null"));
        assertTrue (terroristId != spdId);
        
    }

    /**
     * Test of findMissionById method, of class MissionManagerImpl.
     */
    @Test
    public void testFindMissionById() {
        manager = new MissionManagerImpl();;
        Mission spdMission = InfiltrateSPD();
        Long spdId = manager.createMission(spdMission);
        
        assertEquals(manager.getMission(spdId), spdMission);
        
    }

    /**
     * Test of update method, of class MissionManagerImpl.
     */
    @Test
    public void testUpdate() {
        manager = new MissionManagerImpl();
        Long terroristId = manager.createMission(killTerrorists());
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
        manager = new MissionManagerImpl();
        Mission terroristMission = killTerrorists();
        Mission spdMission = InfiltrateSPD();
        manager.createMission(terroristMission);
        manager.createMission(spdMission);

        List<Mission> result = manager.getUncompletedMissions();
        
        assertTrue(result.size()==1);
        assertFalse(result.contains(terroristMission));
        assertTrue(result.contains(spdMission));
    }

    /**
     * Test of getMissions method, of class MissionManagerImpl.
     */
    @Test
    public void testFindAllMission() {
        manager = new MissionManagerImpl();
        Mission terroristMission = killTerrorists();
        Mission spdMission = InfiltrateSPD();
        Long terroristId = manager.createMission(terroristMission);
        Long spdId = manager.createMission(spdMission);

        List<Mission> result = manager.getMissions();

        assertTrue(result.size()==2);
        assertThat(result, Matchers.hasSize(2));

        assertThat("asldkfjasldkfjaslkdfj", CoreMatchers.allOf(
                CoreMatchers.containsString("jkl"),
                CoreMatchers.startsWith("a")
        ));

        assertTrue(result.contains(terroristMission));
        assertTrue(result.contains(spdMission));
        assertTrue(terroristId != spdId);
    }
    
}
