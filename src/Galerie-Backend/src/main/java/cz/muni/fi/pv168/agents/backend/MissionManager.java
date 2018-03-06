package cz.muni.fi.pv168.agents.backend;

import cz.muni.fi.pv168.agents.common.IllegalEntityException;
import cz.muni.fi.pv168.agents.common.ServiceFailureException;
import cz.muni.fi.pv168.agents.common.ValidationException;

import java.util.List;

public interface MissionManager {
    

    void createMission(Mission mis) throws ServiceFailureException, ValidationException, IllegalEntityException;
    
        
    void updateMission(Mission mis) throws ServiceFailureException, ValidationException, IllegalEntityException;
    
    List <Mission> getMissions ();
    
    List <Mission> getUncompletedMissions ();
    
    Mission getMission(Long id) throws ServiceFailureException;
}
