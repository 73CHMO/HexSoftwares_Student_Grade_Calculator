package org.hexsoftwares;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private String studentID;
    ArrayList<Double> grades;

    public Student(String name, String studentID) {
        this.name = name;
        this.studentID = studentID;
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public void displayGrades() {
        System.out.println("Grades for " + name + " (" + studentID + "):");
        for (double grade : grades) {
            System.out.println(grade);
        }
    }

    public boolean hasPassed() {
        return calculateAverage() >= 50.0;
    }

    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }
}

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Student ID: ");
            String studentID = scanner.nextLine();

            Student student = new Student(name, studentID);

            System.out.print("Enter the number of grades: ");
            int numberOfGrades = scanner.nextInt();

            for (int j = 0; j < numberOfGrades; j++) {
                System.out.print("Enter grade " + (j + 1) + ": ");
                double grade = scanner.nextDouble();
                student.addGrade(grade);
            }
            scanner.nextLine(); // Consume newline
            students.add(student);
        }

        // Display individual grades and pass/fail status
        for (Student student : students) {
            student.displayGrades();
            System.out.printf("Average grade: %.2f\n", student.calculateAverage());
            System.out.println(student.hasPassed() ? "Status: Passed" : "Status: Failed");
            System.out.println();
        }

        // Calculate class average
        double totalSum = 0;
        int totalGrades = 0;

        for (Student student : students) {
            totalSum += student.calculateAverage() * student.grades.size();
            totalGrades += student.grades.size();
        }

        double classAverage = totalGrades > 0 ? totalSum / totalGrades : 0;
        System.out.printf("Class average grade: %.2f\n", classAverage);

        scanner.close();
    }
}