package com.ruben.gm.models;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: r.deluca
 * Date: 03/07/13
 * Time: 11.56
 * To change this template use File | Settings | File Templates.
 */
public class Vehicle implements Serializable{
    private String vin;
    private String make;
    private String model;
    private String year;
    private String manufacturer;
    private String phone;
    private String unitType;
    private String primaryDriverId;
    private String url;
    private String primaryDriverUrl;

    public Vehicle(String vin, String make, String model, String year, String manufacturer, String phone, String unitType, String primaryDriverId, String url, String primaryDriverUrl) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.manufacturer = manufacturer;
        this.phone = phone;
        this.unitType = unitType;
        this.primaryDriverId = primaryDriverId;
        this.url = url;
        this.primaryDriverUrl = primaryDriverUrl;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getPrimaryDriverId() {
        return primaryDriverId;
    }

    public void setPrimaryDriverId(String primaryDriverId) {
        this.primaryDriverId = primaryDriverId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrimaryDriverUrl() {
        return primaryDriverUrl;
    }

    public void setPrimaryDriverUrl(String primaryDriverUrl) {
        this.primaryDriverUrl = primaryDriverUrl;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin='" + vin + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", phone='" + phone + '\'' +
                ", unitType='" + unitType + '\'' +
                ", primaryDriverId='" + primaryDriverId + '\'' +
                ", url='" + url + '\'' +
                ", primaryDriverUrl='" + primaryDriverUrl + '\'' +
                '}';
    }
}
