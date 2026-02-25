package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Doctor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class DoctorServiceImplArraylist {

    private static final List<Doctor> doctorList = new ArrayList<>();
    private static int nextId = 1;

   
    public DoctorServiceImplArraylist() {
        
        if (!doctorList.isEmpty()) {
            doctorList.clear();
            nextId = 1;
        }
    }

    public void emptyArrayList() {
        doctorList.clear();
        nextId = 1;
    }

    private static int generateId() { return nextId++; }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctorList);
    }

    public Integer addDoctor(Doctor doctor) {
        if (doctor == null) return -1;
        if (doctor.getDoctorId() <= 0) {
            doctor.setDoctorId(generateId());
        } else {
            deleteDoctor(doctor.getDoctorId());
            if (doctor.getDoctorId() >= nextId) nextId = doctor.getDoctorId() + 1;
        }
        doctorList.add(doctor);
        return doctor.getDoctorId();
    }

    public Doctor getDoctorById(int doctorId) {
        for (Doctor d : doctorList) if (d.getDoctorId() == doctorId) return d;
        return null;
    }

    public void updateDoctor(Doctor doctor) {
        if (doctor == null || doctor.getDoctorId() <= 0) return;
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorList.get(i).getDoctorId() == doctor.getDoctorId()) {
                doctorList.set(i, doctor);
                return;
            }
        }
    }

    public void deleteDoctor(int doctorId) {
        doctorList.removeIf(d -> d.getDoctorId() == doctorId);
    }

    public List<Doctor> getDoctorSortedByExperience() {
        List<Doctor> copy = new ArrayList<>(doctorList);
        copy.sort(Comparator.comparingInt(Doctor::getYearsOfExperience));
        return copy;
    }
}