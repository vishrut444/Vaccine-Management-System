package com.vishrut.VaccineManagement.exception;

public class AppointmentNotBooked extends RuntimeException {
    public AppointmentNotBooked(String message){
        super(message);
    }
}
