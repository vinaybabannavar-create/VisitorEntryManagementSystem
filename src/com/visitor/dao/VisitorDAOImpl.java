package com.visitor.dao;

import com.visitor.dto.VisitorDTO;
import com.visitor.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitorDAOImpl implements VisitorDAO {

    private static final String INSERT_VISITOR = "INSERT INTO visitors (name, purpose, contact_number) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_VISITORS = "SELECT * FROM visitors ORDER BY entry_time DESC";
    private static final String SEARCH_VISITOR = "SELECT * FROM visitors WHERE name LIKE ? OR purpose LIKE ?";

    @Override
    public boolean addVisitor(VisitorDTO visitor) {
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(INSERT_VISITOR)) {

            pstmt.setString(1, visitor.getName());
            pstmt.setString(2, visitor.getPurpose());
            pstmt.setString(3, visitor.getContactNumber());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<VisitorDTO> getAllVisitors() {
        List<VisitorDTO> visitors = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_ALL_VISITORS)) {

            while (rs.next()) {
                visitors.add(mapResultSetToDTO(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visitors;
    }

    @Override
    public List<VisitorDTO> searchVisitor(String keyword) {
        List<VisitorDTO> visitors = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SEARCH_VISITOR)) {

            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    visitors.add(mapResultSetToDTO(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visitors;
    }

    private VisitorDTO mapResultSetToDTO(ResultSet rs) throws SQLException {
        VisitorDTO visitor = new VisitorDTO();
        visitor.setVisitorId(rs.getInt("visitor_id"));
        visitor.setName(rs.getString("name"));
        visitor.setPurpose(rs.getString("purpose"));
        visitor.setContactNumber(rs.getString("contact_number"));
        visitor.setEntryTime(rs.getTimestamp("entry_time"));
        return visitor;
    }
}
