package com.edutech.progressive.service.impl;

import com.edutech.progressive.dao.DoctorDAO;
import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.service.DoctorService;

import java.sql.SQLException;
import java.util.List;

public class DoctorServiceImplJdbc implements DoctorService {

    private final DoctorDAO doctorDAO;

    public DoctorServiceImplJdbc(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    public List<Doctor> getAllDoctors() throws Exception {
        try {
            return doctorDAO.getAllDoctors();
        } catch (SQLException e) {
            throw new Exception("Failed to fetch all doctors", e);
        } finally { }
    }

    @Override
    public Integer addDoctor(Doctor doctor) throws Exception {
        try {
            return doctorDAO.addDoctor(doctor);
        } catch (SQLException e) {
            throw new Exception("Failed to add doctor", e);
        } finally { }
    }

    @Override
    public void updateDoctor(Doctor doctor) throws Exception {
        try {
            doctorDAO.updateDoctor(doctor);
        } catch (SQLException e) {
            throw new Exception("Failed to update doctor", e);
        } finally { }
    }

    @Override
    public void deleteDoctor(int doctorId) throws Exception {
        try {
            doctorDAO.deleteDoctor(doctorId);
        } catch (SQLException e) {
            throw new Exception("Failed to delete doctor", e);
        } finally { }
    }

    @Override
    public Doctor getDoctorById(int doctorId) throws Exception {
        try {
            return doctorDAO.getDoctorById(doctorId);
        } catch (SQLException e) {
            throw new Exception("Failed to fetch doctor by id", e);
        } finally { }
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() throws Exception {
        try {
            return doctorDAO.getDoctorSortedByExperience();
        } catch (SQLException e) {
            throw new Exception("Failed to fetch doctors sorted by experience", e);
        } finally { }
    }
}