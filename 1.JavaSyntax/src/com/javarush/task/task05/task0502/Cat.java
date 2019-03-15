package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
*/

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        //напишите тут ваш код
        if (this.strength>anotherCat.strength*2) return true; // в два раза сильнее
        if (this.age*2<anotherCat.age) return true; // в два раза моложе
        if (this.weight>anotherCat.weight*2) return true; // в два раза тяжелее
        if (anotherCat.strength>this.strength*2) return false; // в два раза сильнее
        if (anotherCat.age*2<this.age) return false; // в два раза моложе
        if (anotherCat.weight*2>this.weight) return false; // в два раза тяжелее

        if (this.age<anotherCat.age){
            //текщий моложе
            if (this.weight>anotherCat.weight && this.strength>anotherCat.strength)
                // но он усильнее и тяжелле, то он победит
                return true;
            else
                return false;
        }else{
            //текущий старее
            if (this.weight>anotherCat.weight && this.strength<anotherCat.strength)
            // и он слабее и легче молодого кота то он проигралл
                return false;
            else
                return true;
        }
    }

    public static void main(String[] args) {

    }
}
