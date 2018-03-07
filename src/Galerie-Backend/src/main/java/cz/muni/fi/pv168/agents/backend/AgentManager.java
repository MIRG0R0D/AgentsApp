package cz.muni.fi.pv168.agents.backend;

import java.util.List;


public interface AgentManager {
    
    void create(Agent agent1) ;

    Agent findAgentById(Long id);
    
    void update(Agent agent);
    
    List<Agent> findAllAgents();
    
}
