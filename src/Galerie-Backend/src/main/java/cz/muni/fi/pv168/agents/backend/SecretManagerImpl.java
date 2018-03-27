package cz.muni.fi.pv168.agents.backend;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SecretManagerImpl implements SecretManager {
    private final AgentManagerImpl agentManager;
    private final MissionManager missionManager;

    private final DataSource ds;
    
    public SecretManagerImpl(DataSource ds) {
        missionManager = new MissionManagerImpl(ds);
        agentManager = new AgentManagerImpl(ds);
        this.ds=ds;

    }


    /**
     * finding some mission with the certain agent
     * @param agent agent on mission
     * @return mission with the agent
     */
    
    // can 1 agent be on several missions?????
    @Override
    public Mission findMissionWithAgent(Agent agent) {

        List <Agent> agents = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from APP.MISSION_ASSIGNMENT WHERE AGENT_ID = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, agent.getId());
            ResultSet rs = ps.executeQuery();

            if  (rs.next()) {
                long missionId = rs.getLong(2);
                long agentId = rs.getLong(3);

                return missionManager.getMission(missionId);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AgentManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        
        // need to initialize first entry
        
        List <Agent> agents = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from APP.MISSION_ASSIGNMENT WHERE MISSION_ID = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, mission.getId());
            ResultSet rs = ps.executeQuery();

            if  (rs.next()) {
                agents = Arrays.asList( rs.getArray(2));
                
                LocalDate bord = rs.getDate(2).toLocalDate();
                String level = rs.getString(3);
                String name = rs.getString(4);
                Agent agent = new Agent(id, bord, level, name);
                return agent;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AgentManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    /**
     * finishing the mission
     * @param mission mission to be finished
     */
    @Override
    public void finishTheMission(Mission mission) {
        mission.setEnd(LocalDate.now());
        missionManager.updateMission(mission.getId(), mission);
        
    }


}
