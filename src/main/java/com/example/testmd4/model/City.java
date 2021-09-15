package com.example.testmd4.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Name must be longer than 1 character")
    private String name;
    @ManyToOne
    private Country Country;
    @NotNull
    @Pattern(regexp = "^[0-9]+",message = "Population Must be a number")
    private String population;
    @NotNull
    @Pattern(regexp = "^[0-9]+",message = "Size Must be a number")
    private String size;
    @NotNull
    @Pattern(regexp = "^[0-9]+",message = "GDP Must be a number")
    private String GDP;
    private String description;

    public City() {
    }

    public City(long id, String name, com.example.testmd4.model.Country country, String population, String size, String GDP, String description) {
        this.id = id;
        this.name = name;
        Country = country;
        this.population = population;
        this.size = size;
        this.GDP = GDP;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.example.testmd4.model.Country getCountry() {
        return Country;
    }

    public void setCountry(com.example.testmd4.model.Country country) {
        Country = country;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGDP() {
        return GDP;
    }

    public void setGDP(String GDP) {
        this.GDP = GDP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
