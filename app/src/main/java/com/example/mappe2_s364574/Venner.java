package com.example.mappe2_s364574;

public class Venner {

    private long id;
    String navn;
    String telefonnummer;



    public Venner() {
    }
    //konstrøkører
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

    // getter og settere

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

    // metode for å få navn i spinneren i avtale siden
    @Override
    public String toString() {
        return getNavn();
    }
}
