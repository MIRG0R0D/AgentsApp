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

        //manager = new AgentManagerImpl();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class AgentManagerImpl.
     */
    @Test
    public void testCreate() {
        Long bondId = manager.create(jamesBond);
        Long stierId = manager.create(vonStierlitz());
        assertNotNull(bondId);
        assertFalse(bondId.equals("null"));
        assertFalse(stierId.equals("null"));
        assertTrue(bondId != stierId);

    }

    /**
     * Test of findAgentById method, of class AgentManagerImpl.
     */
    @Test
    public void testFindAgentById() {
        Agent bond = jamesBond();
        Long id = manager.create(jamesBond());
        assertEquals(manager.findAgentById(id), bond);
    }

    /**
     * Test of update method, of class AgentManagerImpl.
     */
    @Test
    public void testUpdate() {
        Agent stierlitz = vonStierlitz();
        Long id = manager.create(vonStierlitz());
        String newName = "Vsevolod";
        stierlitz.setName(newName);
        manager.update(id, stierlitz);
        assertEquals(manager.findAgentById(id).getName(), newName);

    }

    /**
     * Test of findAllAgents method, of class AgentManagerImpl.
     */
    @Test
    public void testFindAllAgents() {
        manager = new AgentManagerImpl();
        Agent bond = jamesBond();
        Agent stierlitz = vonStierlitz();
        Long bondID = manager.create(bond);
        Long stierId = manager.create(stierlitz);

        List<Agent> result = manager.findAllAgents();

        assertEquals(2, result.size());
        assertTrue(result.contains(stierlitz));
        assertTrue(result.contains(bond));
        assertTrue(bondID != stierId);
    }

}
