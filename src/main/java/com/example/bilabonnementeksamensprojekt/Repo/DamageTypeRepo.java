package com.example.bilabonnementeksamensprojekt.Repo;

import com.example.bilabonnementeksamensprojekt.Model.DamageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DamageTypeRepo {
    @Autowired
    JdbcTemplate template;

    public List<DamageType> fetchAll() {
        String sql = "SELECT * FROM damagetypes";
        RowMapper<DamageType> rowMapper = new BeanPropertyRowMapper<>(DamageType.class);
        return template.query(sql, rowMapper);
    }

    public DamageType findById(int id) {
        String sql = "SELECT * FROM damagetypes WHERE DamageTypeID = ?";
        RowMapper<DamageType> rowMapper = new BeanPropertyRowMapper<>(DamageType.class);

        try {
            return template.queryForObject(sql, rowMapper, id);
        } catch (Exception e) {
            System.err.println("Error fetching DamageType by ID: " + id);
            e.printStackTrace();
            return null;
        }
    }

}
