/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.agents.backend;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xdovgan
 */
public class SecretManagerImplTest {
    private SecretManager manager;
    
    public SecretManagerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    private Agent JamesBond(){
        return new AgentBuilder()
                .name("Bond, James")
                .born(LocalDate.of(1, Month.JANUARY, 1964))
                .level("00")
                .id(7L)
                .build();
    }
    private Agent English(){
        return new AgentBuilder()
                .name("Johnny English")
                .born(LocalDate.of(25, Month.FEBRUARY, 1973))
                .level("OO")
                .id(13L)
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
     * Test of findMissionWithAgent method, of class SecretManagerImpl.
     */
    @Test
    public void testFindMissionWithAgent() {
        
        manager = new SecretManagerImpl();
        Agent agent = English();
        Mission mission = InfiltrateSPD();
        manager.attachAgentToMission(agent, mission);
        
        assertEquals(manager.findMissionWithAgent(agent), mission);
        
    }

    /**
     * Test of findAgentsWithMission method, of class SecretManagerImpl.
     */
    @Test
    public void testFindAgentsWithMission() {
        
        manager = new SecretManagerImpl();
        Agent english = English();
        Agent bond = JamesBond();
        Mission mission = InfiltrateSPD();
        manager.attachAgentToMission(english, mission);
        manager.attachAgentToMission(bond, mission);
        List <Agent> result = manager.findAgentsWithMission(mission);
        
        assertTrue(result.size()==2);
        assertTrue(result.contains(english));
        assertTrue(result.contains(bond));
        
    }

    /**
     * Test of attachAgentToMission method, of class SecretManagerImpl.
     */
    @Test
    public void testAttachAgentToMission() {
        manager = new SecretManagerImpl();
        Agent agent = English();
        Mission mission = InfiltrateSPD();
        manager.attachAgentToMission(agent, mission);
    }

    /**
     * Test of finishTheMission method, of class SecretManagerImpl.
     */
    @Test
    public void testFinishTheMission() {
        MissionManagerImpl missionManager = new MissionManagerImpl();
        manager = new SecretManagerImpl();
        Agent agent = English();
        Mission mission = InfiltrateSPD();
        manager.attachAgentToMission(agent, mission);
        manager.finishTheMission(mission);
        mission = missionManager.getMission(mission.getId());
        assertTrue(mission.getEnd().isBefore(LocalDate.now()));
    }
    
}
