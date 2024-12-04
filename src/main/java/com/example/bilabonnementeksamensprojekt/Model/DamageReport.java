package com.example.bilabonnementeksamensprojekt.Model;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;
import java.util.List;

public class DamageReport {
    private int damageReportID;
    private int carID;
    private Date reportDate;
    private String status;
    private double totalCost;

    private List<DamageReportDetail> damageDetails; // To hold associated damage details

    public DamageReport() {
    }

    public DamageReport(int damageReportID, int carID, Date reportDate, String status, double totalCost) {
        this.damageReportID = damageReportID;
        this.carID = carID;
        this.reportDate = reportDate;
        this.status = status;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public int getDamageReportID() {
        return damageReportID;
    }

    public void setDamageReportID(int damageReportID) {
        this.damageReportID = damageReportID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<DamageReportDetail> getDamageDetails() {
        return damageDetails;
    }

    public void setDamageDetails(List<DamageReportDetail> damageDetails) {
        this.damageDetails = damageDetails;
    }
}
