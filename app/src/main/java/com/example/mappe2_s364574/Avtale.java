package com.example.mappe2_s364574;

public class Avtale {

    private long id;
    String navnpåPerson;
    String datoforMøte;
    String klokkeslettforMøte;
    String møtested;

    public Avtale(String navnpåPerson, String datoforMøte, String klokkeslettforMøte, String møtested){
        this.navnpåPerson = navnpåPerson;
        this.datoforMøte = datoforMøte;
        this.klokkeslettforMøte = klokkeslettforMøte;
        this.møtested = møtested;
    }

    public String getNavnpåPerson() {
        return navnpåPerson;
    }

    public String getDatoforMøte() {
        return datoforMøte;
    }

    public String getKlokkeslettforMøte() {
        return klokkeslettforMøte;
    }

    public String getMøtested() {
        return møtested;
    }
}
