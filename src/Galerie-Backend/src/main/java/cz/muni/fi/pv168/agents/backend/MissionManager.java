package cz.muni.fi.pv168.agents.backend;

import java.util.List;

public interface MissionManager {
    

    Long createMission(Mission mis);
    
        
    void updateMission(Long id, Mission mis);
    
    List <Mission> getMissions ();
    
    List <Mission> getUncompletedMissions ();
    
    Mission getMission(Long id);
}
