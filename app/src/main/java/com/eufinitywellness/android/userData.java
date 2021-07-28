//Eufinity Wellness
//Script Written by Areeb Rehman on 7/28/2021
//Usage: This script is used to feed the user data into our firebase database. Simply fill all the required
//fields and parse it into the database using a reference in order to get the desired result.

package com.eufinitywellness.android;

public class userData {
    String email, firstName, lastName, dateOfBirth, gender;

    public userData(String email, String firstName, String lastName, String dateOfBirth, String gender) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public userData() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
