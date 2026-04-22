package daoimpl;

import dao.VisitorDAO;
import db.DBConnection;
import dto.VisitorDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitorDAOImpl implements VisitorDAO {

    public boolean addVisitor(VisitorDTO visitor) {
        String sql = "INSERT INTO visitors(name, purpose) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, visitor.getName());
            ps.setString(2, visitor.getPurpose());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<VisitorDTO> getAllVisitors() {
        List<VisitorDTO> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM visitors")) {

            while (rs.next()) {
                list.add(new VisitorDTO(
                        rs.getInt("visitor_id"),
                        rs.getString("name"),
                        rs.getString("purpose")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean deleteVisitor(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "DELETE FROM visitors WHERE visitor_id=?")) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateVisitor(VisitorDTO v) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE visitors SET name=?, purpose=? WHERE visitor_id=?")) {

            ps.setString(1, v.getName());
            ps.setString(2, v.getPurpose());
            ps.setInt(3, v.getVisitorId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}