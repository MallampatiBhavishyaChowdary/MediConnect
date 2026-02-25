package com.edutech.progressive.dao;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Clinic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClinicDAOImpl implements ClinicDAO {

    @Override
    public int addClinic(Clinic clinic) throws SQLException {
        String sql = "INSERT INTO clinic (clinic_name, location, doctor_id, contact_number, established_year) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, clinic.getClinicName());
            ps.setString(2, clinic.getLocation());
            if (clinic.getDoctorId() != 0) {
                ps.setInt(3, clinic.getDoctorId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setString(4, clinic.getContactNumber());
            if (clinic.getEstablishedYear() != 0) {
                ps.setInt(5, clinic.getEstablishedYear());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
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
    public Clinic getClinicById(int clinicId) throws SQLException {
        String sql = "SELECT clinic_id, clinic_name, location, doctor_id, contact_number, established_year FROM clinic WHERE clinic_id = ?";
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, clinicId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Clinic c = new Clinic();
                c.setClinicId(rs.getInt("clinic_id"));
                c.setClinicName(rs.getString("clinic_name"));
                c.setLocation(rs.getString("location"));
                c.setDoctorId(rs.getInt("doctor_id"));
                c.setContactNumber(rs.getString("contact_number"));
                c.setEstablishedYear(rs.getInt("established_year"));
                return c;
            }
            return null;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeQuietly(rs); closeQuietly(ps); closeQuietly(conn);
        }
    }

    @Override
    public void updateClinic(Clinic clinic) throws SQLException {
        String sql = "UPDATE clinic SET clinic_name = ?, location = ?, doctor_id = ?, contact_number = ?, established_year = ? WHERE clinic_id = ?";
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, clinic.getClinicName());
            ps.setString(2, clinic.getLocation());
            if (clinic.getDoctorId() != 0) {
                ps.setInt(3, clinic.getDoctorId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setString(4, clinic.getContactNumber());
            if (clinic.getEstablishedYear() != 0) {
                ps.setInt(5, clinic.getEstablishedYear());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            ps.setInt(6, clinic.getClinicId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeQuietly(ps); closeQuietly(conn);
        }
    }

    @Override
    public void deleteClinic(int clinicId) throws SQLException {
        String sql = "DELETE FROM clinic WHERE clinic_id = ?";
        Connection conn = null; PreparedStatement ps = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, clinicId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeQuietly(ps); closeQuietly(conn);
        }
    }

    @Override
    public List<Clinic> getAllClinics() throws SQLException {
        String sql = "SELECT clinic_id, clinic_name, location, doctor_id, contact_number, established_year FROM clinic";
        List<Clinic> result = new ArrayList<>();
        Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clinic c = new Clinic();
                c.setClinicId(rs.getInt("clinic_id"));
                c.setClinicName(rs.getString("clinic_name"));
                c.setLocation(rs.getString("location"));
                c.setDoctorId(rs.getInt("doctor_id"));
                c.setContactNumber(rs.getString("contact_number"));
                c.setEstablishedYear(rs.getInt("established_year"));
                result.add(c);
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