package com.edutech.progressive.service.impl;

import com.edutech.progressive.dao.PatientDAO;
import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.service.PatientService;

import java.sql.SQLException;
import java.util.List;

public class PatientServiceImplJdbc implements PatientService {

    private final PatientDAO patientDAO;

    public PatientServiceImplJdbc(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    public List<Patient> getAllPatients() throws Exception {
        try {
            return patientDAO.getAllPatients();
        } catch (SQLException e) {
            throw new Exception("Failed to fetch all patients", e);
        } finally { }
    }

    @Override
    public Integer addPatient(Patient patient) throws Exception {
        try {
            return patientDAO.addPatient(patient);
        } catch (SQLException e) {
            throw new Exception("Failed to add patient", e);
        } finally { }
    }

    @Override
    public void updatePatient(Patient patient) throws Exception {
        try {
            patientDAO.updatePatient(patient);
        } catch (SQLException e) {
            throw new Exception("Failed to update patient", e);
        } finally { }
    }

    @Override
    public void deletePatient(int patientId) throws Exception {
        try {
            patientDAO.deletePatient(patientId);
        } catch (SQLException e) {
            throw new Exception("Failed to delete patient", e);
        } finally { }
    }

    @Override
    public Patient getPatientById(int patientId) throws Exception {
        try {
            return patientDAO.getPatientById(patientId);
        } catch (SQLException e) {
            throw new Exception("Failed to fetch patient by id", e);
        } finally { }
    }

    @Override
    public List<Patient> getAllPatientSortedByName() throws Exception {
        try {
            return patientDAO.getAllPatientSortedByName();
        } catch (SQLException e) {
            throw new Exception("Failed to fetch patients sorted by name", e);
        } finally { }
    }
}