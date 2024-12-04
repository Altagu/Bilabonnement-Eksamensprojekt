package com.example.bilabonnementeksamensprojekt.Service;

import com.example.bilabonnementeksamensprojekt.Model.DamageType;
import com.example.bilabonnementeksamensprojekt.Repo.DamageTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageTypeService {
    @Autowired
    DamageTypeRepo damageTypeRepo;

    public List<DamageType> fetchAll(){
        return damageTypeRepo.fetchAll();
    }

    public DamageType findById(int id){
        return damageTypeRepo.findById(id);
    }

}
