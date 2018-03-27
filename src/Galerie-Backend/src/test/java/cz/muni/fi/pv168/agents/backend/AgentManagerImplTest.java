/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.agents.backend;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.apache.derby.jdbc.ClientDataSource;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * @author Dima
 */
public class AgentManagerImplTest {

    private AgentManagerImpl manager;
    private Agent jamesBond;
    private Agent vonStierlitz;
    private Agent badAgent;


    @Before
    public void setUp() {
        ClientDataSource ds = new ClientDataSource();
        ds.setServerName("localhost");
        ds.setPortNumber(1527);
        ds.setDatabaseName("user-test");

        manager = new AgentManagerImpl(ds);

        jamesBond = new AgentBuilder()
                .name("Bond, James")
                .born(LocalDate.of(1964, Month.JANUARY, 1))
                .level("00")
                .id(null)
                .build();

        vonStierlitz = new AgentBuilder()
                .name("Max Otto vonStierlitz")
                .born(LocalDate.of(1900, Month.OCTOBER, 8))
                .level("00")
                .id(null)
                .build();
        badAgent=new AgentBuilder()
                .name("")
                .born(LocalDate.of(2050, Month.MARCH, 5))
                .level("")
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
    @Test
    public void testCreateFailure() {
        Long badId = manager.create(badAgent);
               
        assertNull(badId);
        
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
    
     @Test
    public void testUpdateFailure() {
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
