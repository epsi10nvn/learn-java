import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Student Management System");

        Scanner scanner = new Scanner(System.in);
        Classroom classroom = new Classroom("C101", "Fundamentals of Java Programming");
        int choice = -1;

        do {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Add new Student");
            System.out.println("2. Search Student");
            System.out.println("3. Display all Students");
            System.out.println("4. Remove Student");
            System.out.println("5. Calculate Average Grade for a Classroom");
            System.out.println("0. Exit");
            System.out.print("→ Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
                continue;
            }
//            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    // Call functions to add a new student
                    System.out.println("Adding a new student...");
                    Student student = new Student();

                    boolean isUnique = false;
                    String studentId = "";

                    // validate unique ID
                    do {
                        System.out.println("Enter Student ID: ");
                        studentId = scanner.nextLine();
                        if (isUniqueId(studentId, classroom)) {
                            isUnique = true;
                            student.setStudentId(studentId);
                        } else {
                            System.out.println("Student ID already exists. Please enter a unique ID.");
                        }
                    } while (!isUnique);

                    System.out.println("Enter Full Name: ");
                    String fullName = scanner.nextLine();

                    LocalDate dob = null;
                    // validate date of birth format
                    do {
                        System.out.println("Enter Date of Birth (YYYY-MM-DD): ");
                        String dobInput = scanner.nextLine();
                        dob = isValidDate(dobInput);
                    } while (dob == null);

                    // validate average grade
                    System.out.println("Enter Average Grade: ");
                    double averageGrade = -1;
                    do {
                        String gradeInput = scanner.nextLine();
                        averageGrade = isValidAverageGrade(gradeInput);
                    } while (averageGrade == -1);

                    student.setStudentId(studentId);
                    student.setFullName(fullName);
                    student.setDateOfBirth(dob);
                    student.setAverageGrade(averageGrade);

                    classroom.addStudent(student);
                    break;
                case 2:
                    // Call Student search functions
                    System.out.println("Search Student by: ");
                    System.out.println("1. Student ID");
                    System.out.println("2. Name");
                    System.out.print("→ Enter your choice: ");

                    int searchChoice;
                    try {
                        searchChoice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Returning to main menu...");
                        break;
                    }
                    if (searchChoice == 1) {
                        System.out.println("Enter Student ID to search: ");
                        String idToSearch = scanner.nextLine();
                        classroom.searchStudentById(idToSearch);
                    } else if (searchChoice == 2) {
                        System.out.println("Enter Full Name to search: ");
                        String nameToSearch = scanner.nextLine();
                        classroom.searchStudentByName(nameToSearch);
                    } else {
                        System.out.println("Invalid choice. Returning to main menu...");
                    }
                    break;
                case 3:
                    // Call functions to display all students
                    boolean isAscending;
                    do {
                        System.out.println("Display students in ascending order of average grade? (Y/N): ");
                        String orderChoice = scanner.nextLine();
                        if (orderChoice.equalsIgnoreCase("Y")) {
                            isAscending = true;
                            break;
                        } else if (orderChoice.equalsIgnoreCase("N")) {
                            isAscending = false;
                            break;
                        } else {
                            System.out.println("Invalid choice. Please enter Y or N.");
                        }
                    } while (true);
                    classroom.displayAllStudents(isAscending);
                    break;
                case 4:
                    // Call functions to remove a student
                    System.out.println("Enter Student ID to remove: ");
                    String idToRemove = scanner.nextLine();
                    classroom.removeStudentById(idToRemove);
                    break;
                case 5:
                    // Call functions to calculate average grade
                    classroom.calculateClassAverage();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }

    // validate functions

    private static boolean isUniqueId(String id, Classroom classroom) {
        for (Student s : classroom.getStudents()) {
            if (s.getStudentId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    private static LocalDate isValidDate(String dateStr) {
        LocalDate date = null;
        try {
            date = java.time.LocalDate.parse(dateStr);
            return date;
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            return date;
        }
    }

    private static double isValidAverageGrade(String gradeStr) {
        double grade = -1;
        try {
            grade = Double.parseDouble(gradeStr);
            if (grade < 0 || grade > 10) {
                System.out.println("Average grade must be between 0 and 10.");
                return -1;
            }
            return grade;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric value for average grade.");
            return -1;
        }
    }
}
