package edu.cmu.cs.analysis;

public class Student {

    // Each student can take up to 5 courses
    private static final int MAX_COURSES = 5;

    // Attributes
    private String name;
    private String major;
    private String[] courses;

    // Create the Constructor
    public Student(String name, String major) {
        this.name = name;
        this.major = major;
        this.courses = new String[MAX_COURSES];
    }

    // Get the name of the student
    public String getName() {
        return this.name;
    }

    // Get the major of the student
    public String getMajor() {
        return this.major;
    }

    // Get the courses of the student
    public String[] getCourses() {
        return this.courses;
    }

    // Set the courses of the student
    public void setCourses(String[] courses) {
        this.courses = courses;
    }
}
