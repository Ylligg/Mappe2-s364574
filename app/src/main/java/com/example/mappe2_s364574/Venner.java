package com.example.mappe2_s364574;

public class Venner {

    private long id;
    String navn;
    String telefonnummer;



    public Venner() {
    }

    public Venner(String navn) {
        this.navn = navn;
    }

    public Venner(String navn, String telefonnummer) {
        this.navn = navn;
        this.telefonnummer = telefonnummer;
    }
    public Venner(long id, String navn, String telefonnummer) {
        this.id = id;
        this.navn = navn;
        this.telefonnummer = telefonnummer;
    }

    public long getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public  void setId(long id) {
        this.id = id;
    }

    public  void setNavn(String navn) {
        this.navn = navn;
    }

    public  void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    @Override
    public String toString() {
        return getNavn(); // You can add anything else like maybe getDrinkType()
    }
}
