package com.maksym.service;

import com.maksym.model.HospitalStaff;
import com.maksym.repositories.HospitalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("hospitalStaffService")
@Transactional
public class HospitalStaffServiceImpl implements HospitalStaffService{
    
    @Autowired
    HospitalStaffRepository hospitalStaffRepository;
    
    public HospitalStaff findById(Integer id) {
        return hospitalStaffRepository.findOne(id);
    }
    
    public void saveHospitalStaff(HospitalStaff hospitalStaff) {
        hospitalStaffRepository.save(hospitalStaff);
    }
    
    public void updateHospitalStaff(HospitalStaff hospitalStaff) {
        saveHospitalStaff(hospitalStaff);
    }
    
    public void deleteHospitalStaffById(Integer id) {
        hospitalStaffRepository.delete(id);
    }
    
    public void deleteAllHospitalStaffs() {
        hospitalStaffRepository.deleteAll();
    }
    
    public List<HospitalStaff> findAllHospitalStaffs() {
        return hospitalStaffRepository.findAll();
    }
    
    public boolean isHospitalStaffExist(HospitalStaff hospitalStaff) {
        return hospitalStaffRepository.exists(hospitalStaff.getIdHs());
    }
}
