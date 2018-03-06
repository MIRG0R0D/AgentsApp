package cz.muni.fi.pv168.agents.backend;

import cz.muni.fi.pv168.agents.common.IllegalEntityException;
import cz.muni.fi.pv168.agents.common.ServiceFailureException;
import cz.muni.fi.pv168.agents.common.ValidationException;

import java.util.List;


public class AgentManagerImpl implements AgentManager {


    @Override
    public void createPhoto(Agent photo) throws ServiceFailureException, ValidationException, IllegalEntityException {

    }

    @Override
    public Agent getPhoto(Long id) throws ServiceFailureException {
        return null;
    }

    @Override
    public void updatePhoto(Agent photo) throws ServiceFailureException, ValidationException, IllegalEntityException {

    }

    @Override
    public void deletePhoto(Agent photo) throws ServiceFailureException, IllegalEntityException {

    }

    @Override
    public List<Agent> findAllPhotos() throws ServiceFailureException {
        return null;
    }
}
