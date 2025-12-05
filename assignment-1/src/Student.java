import java.time.LocalDate;

public class Student {
    private String studentId;
    private String fullName;
    private LocalDate dateOfBirth; // sử dụng kiểu dữ liệu thời gian trong dự án
    private double averageGrade;

    public Student() {}

    public Student(String studentId, String fullName, LocalDate dateOfBirth, double averageGrade) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.averageGrade = averageGrade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void toStringStudent() {
        System.out.println("----- Student Information -----");
        System.out.println("Student ID: " + studentId);
        System.out.println("Full Name: " + fullName);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Average Grade: " + averageGrade);
        System.out.println("-------------------------------");
    }
}
