package com.example.bilabonnementeksamensprojekt.Service;

import com.example.bilabonnementeksamensprojekt.Model.DamageReport;
import com.example.bilabonnementeksamensprojekt.Model.DamageType;
import com.example.bilabonnementeksamensprojekt.Repo.DamageReportRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DamageReportService {

    @Autowired
    private DamageReportRepo damageReportRepo;

    public int createDamageReport(int carID, List<DamageType> selectedDamageTypes, double totalPrice) {
        int reportID = damageReportRepo.createDamageReport(carID, totalPrice);
        for (DamageType damageType : selectedDamageTypes) {
            damageReportRepo.addDamageReportDetails(reportID, damageType.getDamageTypeID(), damageType.getCost(), 1);
        }
        return reportID;
    }

    public DamageReport getDamageReportWithDetails(int reportID) {
        return damageReportRepo.getDamageReportWithDetails(reportID);
    }

    public List<DamageReport> findDamageReportByCarID(int carID) {
        return damageReportRepo.findDamageReportByCarID(carID);
    }

}
