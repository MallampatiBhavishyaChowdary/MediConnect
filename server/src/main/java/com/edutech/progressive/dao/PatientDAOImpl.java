package com.edutech.progressive.dao;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public int addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient (full_name, date_of_birth, contact_number, email, address) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, patient.getFullName());
            if (patient.getDateOfBirth() != null) {
                ps.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
            } else {
                ps.setNull(2, Types.DATE);
            }
            ps.setString(3, patient.getContactNumber());
            ps.setString(4, patient.getEmail());
            ps.setString(5, patient.getAddress());
            int affected = ps.executeUpdate();
            if (affected == 0) return -1;

            rs = ps.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : -1;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeQuietly(rs); closeQuietly(ps); closeQuietly(conn);
        }
    }

    @Override
    public Patient getPatientById(int patientId) throws SQLException {
        String sql = "SELECT patient_id, full_name, date_of_birth, contact_number, email, address FROM patient WHERE patient_id = ?";
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, patientId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getInt("patient_id"));
                p.setFullName(rs.getString("full_name"));
                Date dob = rs.getDate("date_of_birth");
                p.setDateOfBirth(dob != null ? new java.util.Date(dob.getTime()) : null);
                p.setContactNumber(rs.getString("contact_number"));
                p.setEmail(rs.getString("email"));
                p.setAddress(rs.getString("address"));
                return p;
            }
            return null;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeQuietly(rs); closeQuietly(ps); closeQuietly(conn);
        }
    }

    @Override
    public void updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE patient SET full_name = ?, date_of_birth = ?, contact_number = ?, email = ?, address = ? WHERE patient_id = ?";
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, patient.getFullName());
            if (patient.getDateOfBirth() != null) {
                ps.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
            } else {
                ps.setNull(2, Types.DATE);
            }
            ps.setString(3, patient.getContactNumber());
            ps.setString(4, patient.getEmail());
            ps.setString(5, patient.getAddress());
            ps.setInt(6, patient.getPatientId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeQuietly(ps); closeQuietly(conn);
        }
    }

    @Override
    public void deletePatient(int patientId) throws SQLException {
        String sql = "DELETE FROM patient WHERE patient_id = ?";
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, patientId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeQuietly(ps); closeQuietly(conn);
        }
    }

    @Override
    public List<Patient> getAllPatients() throws SQLException {
        String sql = "SELECT patient_id, full_name, date_of_birth, contact_number, email, address FROM patient";
        List<Patient> result = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getInt("patient_id"));
                p.setFullName(rs.getString("full_name"));
                Date dob = rs.getDate("date_of_birth");
                p.setDateOfBirth(dob != null ? new java.util.Date(dob.getTime()) : null);
                p.setContactNumber(rs.getString("contact_number"));
                p.setEmail(rs.getString("email"));
                p.setAddress(rs.getString("address"));
                result.add(p);
            }
            return result;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeQuietly(rs); closeQuietly(ps); closeQuietly(conn);
        }
    }

    @Override
    public List<Patient> getAllPatientSortedByName() throws SQLException {
        String sql = "SELECT patient_id, full_name, date_of_birth, contact_number, email, address FROM patient ORDER BY full_name ASC";
        List<Patient> result = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getInt("patient_id"));
                p.setFullName(rs.getString("full_name"));
                Date dob = rs.getDate("date_of_birth");
                p.setDateOfBirth(dob != null ? new java.util.Date(dob.getTime()) : null);
                p.setContactNumber(rs.getString("contact_number"));
                p.setEmail(rs.getString("email"));
                p.setAddress(rs.getString("address"));
                result.add(p);
            }
            return result;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeQuietly(rs); closeQuietly(ps); closeQuietly(conn);
        }
    }

    private void closeQuietly(AutoCloseable c) {
        if (c == null) return;
        try { c.close(); } catch (Exception ignored) {}
    }
}