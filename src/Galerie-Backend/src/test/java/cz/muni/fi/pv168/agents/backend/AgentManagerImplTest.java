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
 * @author Dima
 */
public class AgentManagerImplTest {
    
    private AgentManagerImpl manager;
    
    public AgentManagerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         
        manager = new AgentManagerImpl();
    }
    
    @After
    public void tearDown() {
    }
    private Agent JamesBond(){
        return new AgentBuilder()
                .name("Bond, James")
                .born(LocalDate.of(1, Month.JANUARY, 1964))
                .level("00")
                .id(null)
                .build();
    }
    private Agent vonStierlitz(){// 8 октября 1900 года 
        return new AgentBuilder()
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
        Agent bond = JamesBond();
        Agent stierlitz  = vonStierlitz();
        assertTrue (!bond.getId().equals("null"));
        assertTrue (!stierlitz.getId().equals("null"));
        assertTrue (bond.hashCode() != stierlitz.hashCode());
        
    }

    /**
     * Test of findAgentById method, of class AgentManagerImpl.
     */
    @Test
    public void testFindAgentById() {
        Agent bond = JamesBond();
        Long id = manager.create(bond);
        
        
        assertEquals(manager.findAgentById(id), bond);
        
    }

    /**
     * Test of update method, of class AgentManagerImpl.
     */
    @Test
    public void testUpdate() {
         Agent stierlitz  = vonStierlitz();
         Long id = manager.create(stierlitz);
         String newName = "Vsevolod";
         stierlitz.setName(newName);
         manager.update(id, stierlitz);
         assertEquals(manager.findAgentById(id).getName(),newName);
         
    }

    /**
     * Test of findAllAgents method, of class AgentManagerImpl.
     */
    @Test
    public void testFindAllAgents() {
        System.out.println("findAllAgents");
        
        Agent bond = JamesBond();
        Agent stierlitz  = vonStierlitz();
        
        
        List<Agent> result = manager.findAllAgents();
        
        assertTrue(result.size()==2);
        assertTrue(result.contains(stierlitz));
        assertTrue(result.contains(bond));
        
    }
    
}
