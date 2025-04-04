package org.example.service;

import org.example.model.CoworkingSpace;
import org.example.model.Reservation;
import org.example.repository.SpaceRepository;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class CoworkingSpaceService {


    private final SpaceRepository spaceRepository;

    public CoworkingSpaceService(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    public CoworkingSpace addSpace(CoworkingSpace space) {
        return spaceRepository.insertSpace(space);
    }


    public boolean removeSpace(int id) {
        return spaceRepository.deleteSpace(id);
    }


    public List<Reservation> viewAllBookings() {
        return spaceRepository.getAllBookings();
    }

}







