package com.visitor.dao;

import com.visitor.dto.VisitorDTO;
import java.util.List;

public interface VisitorDAO {
    boolean addVisitor(VisitorDTO visitor);

    List<VisitorDTO> getAllVisitors();

    List<VisitorDTO> searchVisitor(String keyword);
}
