package com.maksym.service;

import com.maksym.model.HospitalStaff;

import java.util.List;

public interface HospitalStaffService {

    HospitalStaff findById(Integer id);

    void saveHospitalStaff(HospitalStaff hospitalStaff);

    void updateHospitalStaff(HospitalStaff hospitalStaff);

    void deleteHospitalStaffById(Integer id);

    void deleteAllHospitalStaffs();

    List<HospitalStaff> findAllHospitalStaffs();

    boolean isHospitalStaffExist(HospitalStaff hospitalStaff);
}
