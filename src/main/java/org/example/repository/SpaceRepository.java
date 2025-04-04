package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.example.model.CoworkingSpace;
import org.example.model.Reservation;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
@Transactional
public class SpaceRepository {

    @PersistenceContext
    private  EntityManager em;



    public CoworkingSpace insertSpace(CoworkingSpace space) {
        try {
            em.persist(space);
            return space;
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
    }


    public boolean deleteSpace(int spaceID){
        try{
            CoworkingSpace space = em.find(CoworkingSpace.class, spaceID);
            if(space != null) {
                em.remove(space);
                return true;
            }
            return false;
        } catch (PersistenceException e) {
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
    }


    public List<Reservation> getAllBookings(){
        try{
            return em.createQuery("SELECT r FROM Reservation r", Reservation.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
    }


    public CoworkingSpace findSpaceID(int spaceID){
        return em.find(CoworkingSpace.class, spaceID);
    }
}



