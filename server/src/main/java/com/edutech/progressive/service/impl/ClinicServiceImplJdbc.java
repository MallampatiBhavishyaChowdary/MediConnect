package com.edutech.progressive.service.impl;

import com.edutech.progressive.dao.ClinicDAO;
import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.service.ClinicService;

import java.sql.SQLException;
import java.util.List;

public class ClinicServiceImplJdbc implements ClinicService {

    private final ClinicDAO clinicDAO;

    public ClinicServiceImplJdbc(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }

    @Override
    public List<Clinic> getAllClinics() throws Exception {
        try {
            return clinicDAO.getAllClinics();
        } catch (SQLException e) {
            throw new Exception("Failed to fetch all clinics", e);
        } finally { }
    }

    @Override
    public Integer addClinic(Clinic clinic) throws Exception {
        try {
            return clinicDAO.addClinic(clinic);
        } catch (SQLException e) {
            throw new Exception("Failed to add clinic", e);
        } finally { }
    }

    @Override
    public void updateClinic(Clinic clinic) throws Exception {
        try {
            clinicDAO.updateClinic(clinic);
        } catch (SQLException e) {
            throw new Exception("Failed to update clinic", e);
        } finally { }
    }

    @Override
    public void deleteClinic(int clinicId) throws Exception {
        try {
            clinicDAO.deleteClinic(clinicId);
        } catch (SQLException e) {
            throw new Exception("Failed to delete clinic", e);
        } finally { }
    }

    @Override
    public Clinic getClinicById(int clinicId) throws Exception {
        try {
            return clinicDAO.getClinicById(clinicId);
        } catch (SQLException e) {
            throw new Exception("Failed to fetch clinic by id", e);
        } finally { }
    }
}