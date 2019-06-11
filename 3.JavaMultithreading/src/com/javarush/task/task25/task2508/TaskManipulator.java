package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread thread;
    @Override
    public void start(String threadName) {
        //Метод start должен создавать, сохранять во внутреннее поле и запускать нить с именем, которое передано через аргумент метода.
        this.thread = new Thread(this,threadName);
        this.thread.start();
    }

    @Override
    public void stop() {
    //Метод stop должен прерывать последнюю созданную классом TaskManipulator нить.
        this.thread.interrupt();
    }

    @Override
    public void run() {
        while (!this.thread.isInterrupted()){
            System.out.println(thread.getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}
