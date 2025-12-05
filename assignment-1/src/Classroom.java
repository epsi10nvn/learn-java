import java.util.ArrayList;

public class Classroom {
    private String classId;
    private String className;
    private ArrayList<Student> students;

    public Classroom() {
        this.students = new ArrayList<>();
    }

    public Classroom(String classId, String className) {
        this.classId = classId;
        this.className = className;
        this.students = new ArrayList<>();
    }

    // Getters and Setters

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    // Specific Methods
    public void addStudent(Student student) {
        String id = student.getStudentId();
        for (Student s : students) {
            if (s.getStudentId().equals(id)) {
                System.out.println("Student with ID " + id + " already exists.");
                return;
            }
        }
        this.students.add(student);
    }

    public Student searchStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.toStringStudent();
                return student;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
        return null;
    }

    public void searchStudentByName(String name) {
        boolean found = false;
        for (Student student : students) {
            if (student.getFullName().toLowerCase().contains(name.toLowerCase())) {
                student.toStringStudent();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students found with name containing: " + name);
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the class.");
            return;
        }
        for (Student student : students) {
            student.toStringStudent();
        }
    }

    public void displayAllStudents(boolean ascending) {
        if (students.isEmpty()) {
            System.out.println("No students in the class.");
            return;
        }
        students.sort((s1, s2) -> {
            if (ascending) {
                return Double.compare(s1.getAverageGrade(), s2.getAverageGrade());
            } else {
                return Double.compare(s2.getAverageGrade(), s1.getAverageGrade());
            }
        });
        displayAllStudents();
    }

    public void removeStudentById(String studentId) {
        Student student = searchStudentById(studentId);
        if (student == null) {
            System.out.println("Cannot remove. Student with ID " + studentId + " does not exist.");
            return;
        }
        students.remove(student);
        System.out.println("Student with ID " + studentId + " has been removed.");
    }

    public void calculateClassAverage() {
        if (students.isEmpty()) {
            System.out.println("No students in the class.");
            return;
        }
        double total = 0;
        for (Student student : students) {
            total += student.getAverageGrade();
        }
        double classAverage = total / students.size();
        System.out.printf("Class Average Grade: %.2f%n", classAverage);
    }
}
