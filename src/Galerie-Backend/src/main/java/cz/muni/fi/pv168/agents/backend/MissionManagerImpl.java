package cz.muni.fi.pv168.agents.backend;

import cz.muni.fi.pv168.agents.common.IllegalEntityException;
import cz.muni.fi.pv168.agents.common.ServiceFailureException;
import cz.muni.fi.pv168.agents.common.ValidationException;

import java.util.List;


public class MissionManagerImpl implements MissionManager {


    @Override
    public void createAlbum(Mission album) throws ServiceFailureException, ValidationException, IllegalEntityException {

    }

    @Override
    public Mission getAlbum(Long id) throws ServiceFailureException {
        return null;
    }

    @Override
    public void udateBody(Mission album) throws ServiceFailureException, ValidationException, IllegalEntityException {

    }

    @Override
    public void deleteAlbum(Mission album) throws ServiceFailureException, IllegalEntityException {

    }

    @Override
    public List<Mission> findAllAlbums() throws ServiceFailureException {
        return null;
    }

    @Override
    public Mission findAlbumByName(String name) throws ServiceFailureException {
        return null;
    }
}
