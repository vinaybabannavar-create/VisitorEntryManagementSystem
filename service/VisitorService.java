package service;

import dao.VisitorDAO;
import daoimpl.VisitorDAOImpl;
import dto.VisitorDTO;
import java.util.List;

public class VisitorService {

    private VisitorDAO dao = new VisitorDAOImpl();

    public boolean addVisitor(String name, String purpose) {
        return dao.addVisitor(new VisitorDTO(0, name, purpose));
    }

    public List<VisitorDTO> getAllVisitors() {
        return dao.getAllVisitors();
    }

    public boolean deleteVisitor(int id) {
        return dao.deleteVisitor(id);
    }

    public boolean updateVisitor(int id, String name, String purpose) {
        return dao.updateVisitor(new VisitorDTO(id, name, purpose));
    }
}