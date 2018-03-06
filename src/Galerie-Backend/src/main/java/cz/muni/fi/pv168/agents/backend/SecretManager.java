package cz.muni.fi.pv168.agents.backend;

import cz.muni.fi.pv168.agents.common.IllegalEntityException;
import cz.muni.fi.pv168.agents.common.ServiceFailureException;

import java.util.List;


public interface SecretManager {
    

    Agent findAlbumWithPhoto(Agent body) throws ServiceFailureException, IllegalEntityException;
    

    List<Agent> findPhotosInAlbum(Mission album) throws ServiceFailureException, IllegalEntityException;


    void addPhotoToAlbum(Agent photo, Mission album) throws ServiceFailureException, IllegalEntityException;
    

    void removePhotoFromAlbum(Agent photo, Mission album) throws ServiceFailureException, IllegalEntityException;
    
}
