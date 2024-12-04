package com.example.bilabonnementeksamensprojekt.Model;

public class DamageType {
    private int damageTypeID;
    private String name;
    private String Description;
    private double cost;

    public DamageType(){
    }

    public DamageType(int damageTypeID, String name, String Description, double cost){
        this.damageTypeID=damageTypeID;
        this.name=name;
        this.Description=Description;
        this.cost=cost;
    }

    public int getDamageTypeID() {
        return damageTypeID;
    }
    public String getName(){
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public double getCost() {
        return cost;
    }

    public void setDamageTypeID(int damageTypeID) {
        this.damageTypeID = damageTypeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
