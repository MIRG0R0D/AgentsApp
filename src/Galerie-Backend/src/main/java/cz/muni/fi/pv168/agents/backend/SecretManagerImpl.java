package cz.muni.fi.pv168.agents.backend;

import cz.muni.fi.pv168.agents.common.IllegalEntityException;
import cz.muni.fi.pv168.agents.common.ServiceFailureException;

import javax.sql.DataSource;
import java.util.List;


public class SecretManagerImpl implements SecretManager {

    public SecretManagerImpl(DataSource ds) {

    }

    /**
     * finding some mission with the certain agent
     * @param agent agent on mission
     * @return mission with the agent
     */
    @Override
    public Mission findMissionWithAgent(Agent agent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * finding agents on the certain mission
     * @param mission mission with agents
     * @return list of agents on the mission
     */
    @Override
    public List<Agent> findAgentsWithMission(Mission mission) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * adding new agent for the mission
     * @param agent new agent for the mission
     * @param mission the mission itself
     */
    @Override
    public void attachAgentToMission(Agent agent, Mission mission) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * finishing the mission
     * @param mission mission to be finished
     */
    @Override
    public void finishTheMission(Mission mission) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
