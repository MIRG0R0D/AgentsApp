package cz.muni.fi.pv168.agents.backend;

import java.util.List;


public class AgentManagerImpl implements AgentManager {

    /**
     * creating new agent
     * @param agent parameters of new agent
     * @return id of the created object
     */
    @Override
    public Long create(Agent agent) {
        return null;
    }

    /**
     * finding some certain agent by it's id
     * @param id ID of the agent
     * @return Agent.class if found, null if not
     */
    @Override
    public Agent findAgentById(Long id) {
        return null;
    }

    /**
     * updating agent info
     * ID and born date will not be changed
     * @param id id of the agent
     * @param agent new Agent info
     */
    @Override
    public void update(Long id, Agent agent) {
        
    }

    /**
     * finding all existing agents
     * @return full list of agents
     */
    @Override
    public List<Agent> findAllAgents() {
        return null;
    }

}
