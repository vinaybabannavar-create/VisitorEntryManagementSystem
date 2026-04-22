package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import dto.VisitorDTO;
import service.VisitorService;

public class MainApp {
    public static void main(String[] args) {
        // Launch the Dashboard
        new Dashboard();
    }
}

// 1. Dashboard Window
class Dashboard extends JFrame {
    public Dashboard() {
        setTitle("Visitor Management System");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));

        JButton addBtn = new JButton("Add Visitor");
        JButton viewBtn = new JButton("View Visitors");

        // Set button sizes to match image style
        addBtn.setPreferredSize(new Dimension(150, 40));
        viewBtn.setPreferredSize(new Dimension(150, 40));

        addBtn.addActionListener(e -> new AddVisitorFrame());
        viewBtn.addActionListener(e -> new ViewVisitorsFrame());

        add(addBtn);
        add(viewBtn);

        setVisible(true);
    }
}

// 2. Add Visitor Window
class AddVisitorFrame extends JFrame {
    private VisitorService service = new VisitorService();

    public AddVisitorFrame() {
        setTitle("Add Visitor");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(null); // Absolute positioning to match the manual layout in image

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 30, 80, 25);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(130, 30, 180, 25);
        add(nameField);

        JLabel purposeLabel = new JLabel("Purpose:");
        purposeLabel.setBounds(50, 70, 80, 25);
        add(purposeLabel);

        JTextField purposeField = new JTextField();
        purposeField.setBounds(130, 70, 180, 25);
        add(purposeField);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(130, 120, 100, 30);
        add(submitBtn);

        submitBtn.addActionListener(e -> {
            String name = nameField.getText();
            String purpose = purposeField.getText();

            if (!name.isEmpty() && !purpose.isEmpty()) {
                boolean success = service.addVisitor(name, purpose);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Visitor Added Successfully!");
                    dispose(); // Close window after success
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Add Visitor.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            }
        });

        setVisible(true);
    }
}

// 3. View Visitors Window (with Update/Delete support)
class ViewVisitorsFrame extends JFrame {
    private VisitorService service = new VisitorService();
    private DefaultTableModel model;
    private JTable table;

    public ViewVisitorsFrame() {
        setTitle("Visitor List");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Add ID column so we can perform updates/deletes
        String[] columns = {"ID", "Name", "Purpose"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable direct editing in table
            }
        };
        table = new JTable(model);

        loadData();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        JButton updateBtn = new JButton("Update Selected");
        JButton deleteBtn = new JButton("Delete Selected");

        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // --- Delete Logic ---
        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) model.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this visitor?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    if (service.deleteVisitor(id)) {
                        JOptionPane.showMessageDialog(this, "Visitor Deleted Successfully");
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Delete Failed.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        });

        // --- Update Logic ---
        updateBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) model.getValueAt(selectedRow, 0);
                String oldName = (String) model.getValueAt(selectedRow, 1);
                String oldPurpose = (String) model.getValueAt(selectedRow, 2);

                // Simple Input Dialogs for update
                String newName = JOptionPane.showInputDialog(this, "Update Name:", oldName);
                if (newName == null) return; // User cancelled

                String newPurpose = JOptionPane.showInputDialog(this, "Update Purpose:", oldPurpose);
                if (newPurpose == null) return; // User cancelled

                if (service.updateVisitor(id, newName, newPurpose)) {
                    JOptionPane.showMessageDialog(this, "Visitor Updated Successfully");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Update Failed.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.");
            }
        });

        setVisible(true);
    }

    private void loadData() {
        model.setRowCount(0);
        List<VisitorDTO> list = service.getAllVisitors();
        for (VisitorDTO v : list) {
            model.addRow(new Object[]{v.getVisitorId(), v.getName(), v.getPurpose()});
        }
    }
}