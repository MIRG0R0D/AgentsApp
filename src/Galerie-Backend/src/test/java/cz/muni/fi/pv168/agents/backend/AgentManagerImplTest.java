/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.agents.backend;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Dima
 */
public class AgentManagerImplTest {

    private AgentManagerImpl manager;
    private Agent jamesBond;
    private Agent vonStierlitz;


    @Before
    public void setUp() {
        manager = new AgentManagerImpl();

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

    }

    /**
     * Test of create method, of class AgentManagerImpl.
     */
    @Test
    public void testCreate() {
        Long bondId = manager.create(jamesBond);
        Long stierId = manager.create(vonStierlitz);
               
        assertNotNull(bondId);
        assertNotNull(stierId);
        
        assertNotEquals(bondId,null);
        assertNotEquals(stierId,null);
        
        assertEquals(bondId, stierId);

    }

    /**
     * Test of findAgentById method, of class AgentManagerImpl.
     */
    @Test
    public void testFindAgentById() {
        Agent bond = jamesBond;
        Long id = manager.create(jamesBond);
        assertEquals(manager.findAgentById(id), jamesBond);
    }

    /**
     * Test of update method, of class AgentManagerImpl.
     */
    @Test
    public void testUpdate() {
        Agent stierlitz = vonStierlitz;
        Long id = manager.create(vonStierlitz);
        String newName = "Vsevolod";
        stierlitz.setName(newName);
        manager.update(id, stierlitz);
        
        assertEquals(manager.findAgentById(id).getName(), newName);
        assertEquals(manager.findAgentById(id), stierlitz);

    }

    /**
     * Test of findAllAgents method, of class AgentManagerImpl.
     */
    @Test
    public void testFindAllAgents() {
        Agent bond = jamesBond;
        Agent stierlitz = vonStierlitz;
        
        Long bondID = manager.create(bond);
        Long stierId = manager.create(stierlitz);

        List<Agent> result = manager.findAllAgents();

        assertEquals(2, result.size());
        assertTrue(result.contains(stierlitz));
        assertTrue(result.contains(bond));
        assertNotEquals(bondID, stierId);
    }

}
