package com.vishrut.VaccineManagement.exception;

public class patientNotFoundException extends RuntimeException{
    public patientNotFoundException(String message){
        super(message);
    }
}
