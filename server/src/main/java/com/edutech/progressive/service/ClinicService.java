package com.edutech.progressive.service;

import com.edutech.progressive.entity.Clinic;
import java.util.List;

public interface ClinicService {
    List<Clinic> getAllClinics() throws Exception;
    Integer addClinic(Clinic clinic) throws Exception;
    void updateClinic(Clinic clinic) throws Exception;
    void deleteClinic(int clinicId) throws Exception;
    Clinic getClinicById(int clinicId) throws Exception;
}
