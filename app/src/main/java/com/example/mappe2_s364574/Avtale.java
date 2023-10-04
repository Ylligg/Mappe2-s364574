package com.example.mappe2_s364574;

public class Avtale {

    private long id;
    String navnpåPerson;
    String datoforMøte;
    String klokkeslettforMøte;
    String møtested;



    public Avtale(long id, String navnpåPerson, String datoforMøte, String klokkeslettforMøte, String møtested){
        this.id = id;
        this.navnpåPerson = navnpåPerson;
        this.datoforMøte = datoforMøte;
        this.klokkeslettforMøte = klokkeslettforMøte;
        this.møtested = møtested;
    }

    public Avtale(String navnpåPerson, String datoforMøte, String klokkeslettforMøte, String møtested) {
        this.navnpåPerson = navnpåPerson;
        this.datoforMøte = datoforMøte;
        this.klokkeslettforMøte = klokkeslettforMøte;
        this.møtested = møtested;
    }

    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setNavnpåPerson(String navnpåPerson) {
        this.navnpåPerson = navnpåPerson;
    }

    public void setDatoforMøte(String datoforMøte) {
        this.datoforMøte = datoforMøte;
    }

    public void setKlokkeslettforMøte(String klokkeslettforMøte) {
        this.klokkeslettforMøte = klokkeslettforMøte;
    }

    public void setMøtested(String møtested) {
        this.møtested = møtested;
    }
}
