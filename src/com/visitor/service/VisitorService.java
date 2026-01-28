package com.visitor.service;

import com.visitor.dao.VisitorDAO;
import com.visitor.dao.VisitorDAOImpl;
import com.visitor.dto.VisitorDTO;

import java.util.List;

public class VisitorService {

    private VisitorDAO visitorDAO;

    public VisitorService() {
        this.visitorDAO = new VisitorDAOImpl();
    }

    public boolean registerVisitor(VisitorDTO visitor) {
        // Basic Validation
        if (visitor.getName() == null || visitor.getName().trim().isEmpty()) {
            System.out.println("Error: Visitor Name cannot be empty.");
            return false;
        }
        if (visitor.getContactNumber() == null || visitor.getContactNumber().trim().isEmpty()) {
            System.out.println("Error: Contact Number cannot be empty.");
            return false;
        }

        return visitorDAO.addVisitor(visitor);
    }

    public List<VisitorDTO> getAllVisitors() {
        return visitorDAO.getAllVisitors();
    }

    public List<VisitorDTO> searchVisitors(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Error: Search keyword cannot be empty.");
            return null;
        }
        return visitorDAO.searchVisitor(keyword);
    }
}
