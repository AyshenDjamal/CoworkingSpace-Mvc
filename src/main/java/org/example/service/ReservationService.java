package org.example.service;

import org.example.model.CoworkingSpace;
import org.example.model.Reservation;
import org.example.repository.SpaceRepository;
import org.example.repository.ReservationRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReservationService {


    private final ReservationRepository reservationRepository;
    private  final SpaceRepository spaceRepository;

    public ReservationService(ReservationRepository reservationRepository, SpaceRepository spaceRepository) {
        this.reservationRepository = reservationRepository;
        this.spaceRepository = spaceRepository;
    }

    public List<CoworkingSpace> viewSpaces() {
        return reservationRepository.getAvailableSpaces();
    }



    public boolean bookSpace(Reservation reservation) {
        return reservationRepository.insertReservation(reservation);
    }



    public CoworkingSpace findSpaceID(int spaceID){
        return spaceRepository.findSpaceID(spaceID);
    }


    public Reservation myBookings(int bookingID) {
        if (bookingID<=0) {
            throw new IllegalArgumentException("Invalid booking ID ");
        } else {
            return reservationRepository.getMyBooking(bookingID);
        }
    }



    public boolean cancelBooking(int canID) {
        return reservationRepository.deleteBooking(canID);
    }
}





