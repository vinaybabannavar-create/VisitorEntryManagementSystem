package dao;

import dto.VisitorDTO;
import java.util.List;

public interface VisitorDAO {

    boolean addVisitor(VisitorDTO visitor);

    List<VisitorDTO> getAllVisitors();

    boolean deleteVisitor(int id);

    boolean updateVisitor(VisitorDTO visitor);
}