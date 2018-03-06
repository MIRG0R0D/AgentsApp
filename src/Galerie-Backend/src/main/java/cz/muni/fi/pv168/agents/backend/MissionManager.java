package cz.muni.fi.pv168.agents.backend;

import cz.muni.fi.pv168.agents.common.IllegalEntityException;
import cz.muni.fi.pv168.agents.common.ServiceFailureException;
import cz.muni.fi.pv168.agents.common.ValidationException;

import java.util.List;

public interface MissionManager {
    

    void createAlbum(Mission album) throws ServiceFailureException, ValidationException, IllegalEntityException;
    

    Mission getAlbum(Long id) throws ServiceFailureException;
    

    void udateBody(Mission album) throws ServiceFailureException, ValidationException, IllegalEntityException;
    

    void deleteAlbum(Mission album) throws ServiceFailureException, IllegalEntityException;
    

    List<Mission> findAllAlbums() throws ServiceFailureException;

    Mission findAlbumByName(String name) throws ServiceFailureException;
    
}
