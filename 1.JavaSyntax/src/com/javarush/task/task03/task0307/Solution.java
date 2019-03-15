package com.javarush.task.task03.task0307;

/* 
Привет StarCraft!
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Zerg z1=new Zerg();
        Zerg z2=new Zerg();
        Zerg z3=new Zerg();
        Zerg z4=new Zerg();
        Zerg z5=new Zerg();
        z1.name="z1";
        z2.name="z2";
        z3.name="z3";
        z4.name="z4";
        z5.name="z5";
        Protoss p1=new Protoss();
        Protoss p2=new Protoss();
        Protoss p3=new Protoss();
        p1.name="p1";
        p2.name="p2";
        p3.name="p3";
        Terran t1=new Terran();
        Terran t2=new Terran();
        Terran t3=new Terran();
        Terran t4=new Terran();
        t1.name="t1";
        t2.name="t2";
        t3.name="t3";
        t4.name="t4";
    }

    public static class Zerg {
        public String name;
    }

    public static class Protoss {
        public String name;
    }

    public static class Terran {
        public String name;
    }
}
