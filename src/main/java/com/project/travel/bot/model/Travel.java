package com.project.travel.bot.model;

import javax.persistence.*;


@Entity
@Table(name = "City")
public class Travel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column
    private String country;

    @Column
    private String cityDescription;

    @Column
    private String city;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String nameTown) {
        this.country = nameTown;
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setCityDescription(String cityDescription) {
        this.cityDescription = cityDescription;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Travel() {

    }

    public Travel(String nameTown, String cityDescription) {
        this.country = nameTown;
        this.cityDescription = cityDescription;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", nameTown='" + country + '\'' +
                ", cityDescription='" + cityDescription + '\'' +
                '}';
    }
}
