package cz.muni.fi.pv168.agents.backend;

import java.util.List;


public interface AgentManager {
    
    Long create(Agent agent) ;

    Agent findAgentById(Long id);
    
    void update(Long id, Agent agent);
    
    List<Agent> findAllAgents();
    
}
