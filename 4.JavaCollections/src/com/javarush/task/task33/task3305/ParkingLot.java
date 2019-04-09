package com.javarush.task.task33.task3305;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property="className")
public class ParkingLot {
    public String name;
    public String city;
    @JsonDeserialize(as = ArrayList.class, contentAs = Vehicle.class)
    public List<Vehicle> vehicles;

    public ParkingLot(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}