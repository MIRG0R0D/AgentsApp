package cz.muni.fi.pv168.agents.backend;

import java.util.List;


public interface AgentManager {
    
    public Long create(Agent agent) ;

    public Agent findAgentById(Long id);
    
    public void update(Long id, Agent agent);
    
    public List<Agent> findAllAgents();

}
