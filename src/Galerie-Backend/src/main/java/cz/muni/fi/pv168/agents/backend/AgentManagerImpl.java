package cz.muni.fi.pv168.agents.backend;

import java.util.List;


public class AgentManagerImpl implements AgentManager {

    /**
     * creating new agent
     * @param agent parameters of new agent
     */
    @Override
    public Long create(Agent agent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * finding some certain agent by it's id
     * @param id ID of the agent
     * @return Agent.class if found, null if not
     */
    @Override
    public Agent findAgentById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * updating agent info
     * ID and born date will not be changed
     * @param agent new Agent info
     */
    @Override
    public void update(Long id, Agent agent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * finding all existing agents
     * @return full list of agents
     */
    @Override
    public List<Agent> findAllAgents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
