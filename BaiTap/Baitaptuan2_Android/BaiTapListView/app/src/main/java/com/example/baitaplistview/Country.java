package com.example.baitaplistview;

public class Country {
    private String countryName;
    private String countryImg;
    private int population;

    public Country(String countryName, String countryImg, int population) {
        this.countryName = countryName;
        this.countryImg = countryImg;
        this.population = population;
    }

    public Country(){
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryImg() {
        return countryImg;
    }

    public int getPopulation() {
        return population;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryImg(String countryImg) {
        this.countryImg = countryImg;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString()  {
        return this.countryName+" (Population: "+ this.population+")";
    }
}
