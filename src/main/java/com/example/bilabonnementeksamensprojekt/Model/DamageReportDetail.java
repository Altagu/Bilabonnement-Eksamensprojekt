package com.example.bilabonnementeksamensprojekt.Model;

public class DamageReportDetail {
    private int reportDetailID;
    private int damageReportID;
    private int damageTypeID;
    private int quantity;
    private double lineCost;
    private String name;

    public DamageReportDetail() {
    }

    public DamageReportDetail(int reportDetailID, int damageReportID, int damageTypeID, int quantity, double lineCost) {
        this.reportDetailID = reportDetailID;
        this.damageReportID = damageReportID;
        this.damageTypeID = damageTypeID;
        this.quantity = quantity;
        this.lineCost = lineCost;

    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReportDetailID() {
        return reportDetailID;
    }

    public void setReportDetailID(int reportDetailID) {
        this.reportDetailID = reportDetailID;
    }

    public int getDamageReportID() {
        return damageReportID;
    }

    public void setDamageReportID(int damageReportID) {
        this.damageReportID = damageReportID;
    }

    public int getDamageTypeID() {
        return damageTypeID;
    }

    public void setDamageTypeID(int damageTypeID) {
        this.damageTypeID = damageTypeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getLineCost() {
        return lineCost;
    }

    public void setLineCost(double lineCost) {
        this.lineCost = lineCost;
    }
}
