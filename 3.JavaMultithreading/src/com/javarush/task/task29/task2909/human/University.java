package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private int age;
    private String name;
    private List<Student> students = new ArrayList<Student>();

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public University(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student max = null;
        for (Student student : students) {
            if (max == null || max.getAverageGrade() < student.getAverageGrade()) max = student;
        }
        return max;
    }

    public Student getStudentWithMinAverageGrade() {
        Student max = null;
        for (Student student : students) {
            if (max == null || max.getAverageGrade() > student.getAverageGrade()) max = student;
        }
        return max;
    }

    public void expel(Student student) {
        this.students.remove(student);
    }
}