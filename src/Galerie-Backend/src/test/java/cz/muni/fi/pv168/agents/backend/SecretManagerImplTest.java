/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.agents.backend;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author xdovgan
 */
public class SecretManagerImplTest {
    private SecretManager manager;
    private Agent jamesBond;
    private Agent vonStierlitz;
    private Mission infiltrateSPD;

    @Before
    public void setUp() {
        manager = new SecretManagerImpl();
        
        jamesBond = new AgentBuilder()
                .name("Bond, James")
                .born(LocalDate.of(1, Month.JANUARY, 1964))
                .level("00")
                .id(null)
                .build();

        vonStierlitz = new AgentBuilder()
                .name("Max Otto vonStierlitz")
                .born(LocalDate.of(8, Month.OCTOBER, 1900))
                .level("00")
                .id(null)
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
     * Test of findMissionWithAgent method, of class SecretManagerImpl.
     */
    @Test
    public void testFindMissionWithAgent() {
        
        Agent agent = jamesBond;
        Mission mission = infiltrateSPD;
        manager.attachAgentToMission(agent, mission);
        
        assertEquals(manager.findMissionWithAgent(agent), mission);
        
    }

    /**
     * Test of findAgentsWithMission method, of class SecretManagerImpl.
     */
    @Test
    public void testFindAgentsWithMission() {
        
        Agent stier = vonStierlitz;
        Agent bond = jamesBond;
        Mission mission = infiltrateSPD;
        
        manager.attachAgentToMission(stier, mission);
        manager.attachAgentToMission(bond, mission);
        
        List <Agent> result = manager.findAgentsWithMission(mission);
        
        assertTrue(result.size()==2);
        assertTrue(result.contains(stier));
        assertTrue(result.contains(bond));
        
    }

    /**
     * Test of attachAgentToMission method, of class SecretManagerImpl.
     */
    @Test
    public void testAttachAgentToMission() {
        Agent agent = jamesBond;
        Mission mission = infiltrateSPD;
        manager.attachAgentToMission(agent, mission);
        
    }

    /**
     * Test of finishTheMission method, of class SecretManagerImpl.
     */
    @Test
    public void testFinishTheMission() {
        
        MissionManagerImpl missionManager = new MissionManagerImpl();
        Agent agent = jamesBond;
        Mission mission = infiltrateSPD;
        
        manager.attachAgentToMission(agent, mission);
        manager.finishTheMission(mission);
        
        mission = missionManager.getMission(mission.getId());
        
        assertTrue(mission.getEnd().isBefore(LocalDate.now()));
    }
    
}
