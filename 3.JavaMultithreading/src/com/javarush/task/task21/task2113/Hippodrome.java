package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    static Hippodrome game;

    private List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public static void main(String[] args) {
        Hippodrome.game = new Hippodrome(new ArrayList<Horse>());
        Hippodrome.game.horses.add(new Horse("Первая", 3, 0));
        Hippodrome.game.horses.add(new Horse("Вторая", 3, 0));
        Hippodrome.game.horses.add(new Horse("Третья", 3, 0));
        Hippodrome.game.run();
        Hippodrome.game.printWinner();
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
            for (int i = 0; i < 10; i++)
                System.out.println();
        }

    }
    public Horse getWinner(){
        Horse horseWinner=new Horse("",0,0);
        for (Horse horse : horses) {
            if (horse.getDistance()>horseWinner.getDistance()){
                horseWinner=horse;
            }
        }
        return horseWinner;
    }

    public void printWinner(){
        System.out.println(String.format("Winner is %s!",getWinner().getName()));
    }
}
