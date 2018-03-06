package cz.muni.fi.pv168.agents.backend;

import cz.muni.fi.pv168.agents.common.IllegalEntityException;
import cz.muni.fi.pv168.agents.common.ServiceFailureException;

import java.util.List;


public class SecretManagerImpl implements SecretManager {


    @Override
    public Agent findAlbumWithPhoto(Agent body) throws ServiceFailureException, IllegalEntityException {
        return null;
    }

    @Override
    public List<Agent> findPhotosInAlbum(Mission album) throws ServiceFailureException, IllegalEntityException {
        return null;
    }

    @Override
    public void addPhotoToAlbum(Agent photo, Mission album) throws ServiceFailureException, IllegalEntityException {

    }

    @Override
    public void removePhotoFromAlbum(Agent photo, Mission album) throws ServiceFailureException, IllegalEntityException {

    }
}
