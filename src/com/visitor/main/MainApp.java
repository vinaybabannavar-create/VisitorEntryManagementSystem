package com.visitor.main;

import com.visitor.dto.VisitorDTO;
import com.visitor.service.VisitorService;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static VisitorService service = new VisitorService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   VISITOR ENTRY MANAGEMENT SYSTEM");
        System.out.println("=========================================");

        while (true) {
            printMenu();
            int choice = -1;
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addVisitor();
                    break;
                case 2:
                    viewAllVisitors();
                    break;
                case 3:
                    searchVisitor();
                    break;
                case 4:
                    System.out.println("Exiting System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Add New Visitor");
        System.out.println("2. View All Visitors");
        System.out.println("3. Search Visitor");
        System.out.println("4. Exit");
    }

    private static void addVisitor() {
        System.out.println("\n--- Add New Visitor ---");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Purpose: ");
        String purpose = scanner.nextLine();

        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();

        VisitorDTO visitor = new VisitorDTO(name, purpose, contact);
        boolean success = service.registerVisitor(visitor);

        if (success) {
            System.out.println("SUCCESS: Visitor registered successfully!");
        } else {
            System.out.println("FAILED: Could not register visitor.");
        }
    }

    private static void viewAllVisitors() {
        System.out.println("\n--- All Visitors List ---");
        List<VisitorDTO> visitors = service.getAllVisitors();

        if (visitors.isEmpty()) {
            System.out.println("No visitors found.");
        } else {
            System.out.printf("%-5s %-20s %-15s %-20s %-20s%n", "ID", "Name", "Contact", "Purpose", "Entry Time");
            System.out.println(
                    "------------------------------------------------------------------------------------------");
            for (VisitorDTO v : visitors) {
                System.out.printf("%-5d %-20s %-15s %-20s %-20s%n",
                        v.getVisitorId(),
                        v.getName(),
                        v.getContactNumber(),
                        v.getPurpose(),
                        v.getEntryTime());
            }
        }
    }

    private static void searchVisitor() {
        System.out.println("\n--- Search Visitor ---");
        System.out.print("Enter Name or Purpose to search: ");
        String keyword = scanner.nextLine();

        List<VisitorDTO> visitors = service.searchVisitors(keyword);

        if (visitors == null || visitors.isEmpty()) {
            System.out.println("No matching visitors found.");
        } else {
            System.out.printf("%-5s %-20s %-15s %-20s %-20s%n", "ID", "Name", "Contact", "Purpose", "Entry Time");
            System.out.println(
                    "------------------------------------------------------------------------------------------");
            for (VisitorDTO v : visitors) {
                System.out.printf("%-5d %-20s %-15s %-20s %-20s%n",
                        v.getVisitorId(),
                        v.getName(),
                        v.getContactNumber(),
                        v.getPurpose(),
                        v.getEntryTime());
            }
        }
    }
}
