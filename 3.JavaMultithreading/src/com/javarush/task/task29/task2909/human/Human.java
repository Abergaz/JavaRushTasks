package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;

    private Size size;

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    private BloodGroup bloodGroup;
    private List<Human> children = new ArrayList<>();

    public void setBloodGroup(int code) {
        switch (code) {
            case 1:
                setBloodGroup(BloodGroup.first());
                break;
            case 2:
                setBloodGroup(BloodGroup.second());
                break;
            case 3:
                setBloodGroup(BloodGroup.third());
                break;
            case 4:
                setBloodGroup(BloodGroup.fourth());
                break;
            default:
                setBloodGroup(null);
        }

    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public Human() {
        this.id = nextId;
        nextId++;
    }

    public Human(String name, int age) {
        this();
        this.age = age;
        this.name = name;
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

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Human human) {
        children.add(human);
    }

    public void removeChild(Human human) {
        children.remove(human);
    }

    public String getPosition() {
        return "Человек";
    }


    public int getId() {
        return id;
    }

    public void printSize() {
        System.out.println("Рост: " +
                size.height + " Вес: " + size.weight);
    }

    public void printData() {
        System.out.println(getPosition() + ": " + name);
    }

    public void live() {
    }

    public class Size {
        public int height;
        public int weight;
    }

}