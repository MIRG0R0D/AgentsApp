package cz.muni.fi.pv168.agents.backend;

import cz.muni.fi.pv168.agents.common.IllegalEntityException;
import cz.muni.fi.pv168.agents.common.ServiceFailureException;
import cz.muni.fi.pv168.agents.common.ValidationException;

import java.util.List;


public interface AgentManager {
    
    void create(Agent agent) throws ServiceFailureException, ValidationException, IllegalEntityException;

    Agent findAgentById(Long id) throws ServiceFailureException;
    
    void update(Agent agent) throws ServiceFailureException, ValidationException, IllegalEntityException;
    
    List<Agent> findAllAgents() throws ServiceFailureException;
    
}
