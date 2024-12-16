package com.example.bilabonnementeksamensprojekt.Repo;

import com.example.bilabonnementeksamensprojekt.Model.DamageType;
import com.example.bilabonnementeksamensprojekt.Model.Car;
import com.example.bilabonnementeksamensprojekt.Model.DamageReport;
import com.example.bilabonnementeksamensprojekt.Model.DamageReportDetail;
import com.example.bilabonnementeksamensprojekt.Service.DamageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DamageReportRepo {

    @Autowired
    JdbcTemplate template;
    @Autowired
    private DamageTypeService damageTypeService;

    public List<Car> fetchAll() {
        String sql = "SELECT DamageReportID as id, CarID, ReportDate, Status, TotalCost FROM damagereports";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, rowMapper);
    }

    public int createDamageReport(int carID, double totalCost) {
        String sql = "INSERT INTO DamageReports (CarID, ReportDate, TotalCost) VALUES (?, CURDATE(), ?)";
        template.update(sql, carID, totalCost);
        // Retrieve the last inserted report id
        return template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    public void addDamageReportDetails(int reportID, int damageTypeID, double lineCost, int quantity) {
        String sql = "INSERT INTO DamageReportDetails (DamageReportID, DamageTypeID, LineCost, Quantity) VALUES (?, ?, ?, ?)";
        template.update(sql, reportID, damageTypeID, lineCost, quantity);
    }

    // Fetch report summary
    public DamageReport getDamageReportWithDetails(int reportID) {
        // SQL query to fetch the damage report
        String reportSql = "SELECT * FROM DamageReports WHERE DamageReportID = ?";

        // SQL query to fetch the associated damage details
        String detailsSql = "SELECT * FROM DamageReportDetails WHERE DamageReportID = ?";

        // Fetch the damage report
        DamageReport report = template.queryForObject(reportSql, new Object[]{reportID}, (rs, rowNum) -> {
            DamageReport dr = new DamageReport();
            dr.setDamageReportID(rs.getInt("DamageReportID"));
            dr.setCarID(rs.getInt("CarID"));
            dr.setReportDate(rs.getDate("ReportDate"));
            dr.setTotalCost(rs.getDouble("TotalCost"));
            return dr;
        });

        List<DamageReportDetail> damageDetails = template.query(detailsSql, new Object[]{reportID}, (rs, rowNum) -> {
            DamageReportDetail detail = new DamageReportDetail();
            detail.setDamageTypeID(rs.getInt("DamageTypeID"));
            detail.setLineCost(rs.getDouble("LineCost"));
            detail.setQuantity(rs.getInt("Quantity"));

            DamageType damageType = damageTypeService.findById(detail.getDamageTypeID());
            if (damageType != null) {
                detail.setName(damageType.getName());
            }

            return detail;
        });

        // Attach damage details to the report
        report.setDamageDetails(damageDetails);

        return report;
    }

    public List<DamageReport> findDamageReportByCarID(int carID) {
        String sql = "SELECT * FROM DamageReports WHERE CarID = ?";
        return template.query(sql, new Object[]{carID}, (rs, rowNum) -> {
            DamageReport report = new DamageReport();
            report.setDamageReportID(rs.getInt("DamageReportID"));
            report.setCarID(rs.getInt("CarID"));
            report.setReportDate(rs.getDate("ReportDate"));
            report.setTotalCost(rs.getDouble("TotalCost"));
            return report;
        });
    }

}
