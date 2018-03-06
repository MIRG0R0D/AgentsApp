package cz.muni.fi.pv168.agents.backend;

import cz.muni.fi.pv168.agents.common.IllegalEntityException;
import cz.muni.fi.pv168.agents.common.ServiceFailureException;
import cz.muni.fi.pv168.agents.common.ValidationException;

import java.util.List;


public interface AgentManager {
    

    void createPhoto(Agent photo) throws ServiceFailureException, ValidationException, IllegalEntityException;

    Agent getPhoto(Long id) throws ServiceFailureException;
    

    void updatePhoto(Agent photo) throws ServiceFailureException, ValidationException, IllegalEntityException;
    

    void deletePhoto(Agent photo) throws ServiceFailureException, IllegalEntityException;
    

    List<Agent> findAllPhotos() throws ServiceFailureException;
    
}
