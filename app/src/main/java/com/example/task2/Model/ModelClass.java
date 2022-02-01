package com.example.task2.Model;

public class ModelClass {

    private String Fname;
    private String Lname;
    private String Email;

    public ModelClass(String fname, String lname, String email) {
        Fname = fname;
        Lname = lname;
        Email = email;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
