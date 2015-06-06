package org.springframework.samples.petclinic.service;

public class FirstAndLastName {
    public String First_Name;
    public String Last_Name;

    public FirstAndLastName( String first_Name, String last_Name ) {
        First_Name = first_Name;
        Last_Name = last_Name;
    }

    @Override
    public String toString() {
        return First_Name + " " + Last_Name;
    }
}
