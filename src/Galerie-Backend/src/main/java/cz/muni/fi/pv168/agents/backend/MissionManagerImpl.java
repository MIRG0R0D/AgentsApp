package cz.muni.fi.pv168.agents.backend;

import java.util.List;


public class MissionManagerImpl implements MissionManager {

    /**
     * create new mission
     * @param mis params of new mission
     * @return id of new mission
     */
    @Override
    public Long createMission(Mission mis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * update existing mission
     * @param id id of the mission
     * @param mis new params
     */
    @Override
    public void updateMission(Long id, Mission mis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * get all missions
     * @return list of missions
     */
    @Override
    public List<Mission> getMissions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * get all uncompleted missions
     * @return list of missions
     */
    @Override
    public List<Mission> getUncompletedMissions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * get certain mission by it's id
     * @param id id of the mission
     * @return mission with the same id
     */
    @Override
    public Mission getMission(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
