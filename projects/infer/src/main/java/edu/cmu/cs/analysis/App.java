package main.java.edu.cmu.cs.analysis;

import edu.cmu.cs.analysis.Student;

public class App {
    public static void main(String[] args) {

        // Create the Student
        Student student = new Student("Student1", "Computer Science");

        // Create the Courses
        String[] courses = {"18-653", "18-658", "18-654", "18-659", "18-613", "18-XXX"};
        
        // Set the Courses
        student.setCourses(courses);
    }
}
