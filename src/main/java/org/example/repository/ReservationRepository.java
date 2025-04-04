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
public class ReservationRepository {

    @PersistenceContext
    private  EntityManager em;


    public List<CoworkingSpace>getAvailableSpaces(){
        try{
            return em.createQuery("SELECT s FROM CoworkingSpace s WHERE s.isAvailable = true", CoworkingSpace.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
    }



    public boolean insertReservation(Reservation reservation){
        try{
            if(reservation == null || reservation.getSpace() == null){
                throw new IllegalArgumentException("Reservation or Space cannot be null!");
            }

            if(!reservation.getSpace().getIsAvailable()){
                throw new RuntimeException("Space is already booked");
            }
            reservation.getSpace().setIsAvailable(false);
            em.merge(reservation.getSpace());
            em.persist(reservation);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error: "+ e.getMessage(), e);
        }
    }



    public Reservation getMyBooking(int bookingID){
        try{
            return em.find(Reservation.class, bookingID);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
    }



    public boolean deleteBooking(int bookingID){
        try{
            Reservation reservation = em.find(Reservation.class,bookingID);
            if(reservation == null){
                throw new IllegalArgumentException("Reservation not found");
            }
            reservation.getSpace().setIsAvailable(true);
            em.merge(reservation.getSpace());
            em.remove(reservation);
            return true;
        }catch (PersistenceException e){
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
    }
}



