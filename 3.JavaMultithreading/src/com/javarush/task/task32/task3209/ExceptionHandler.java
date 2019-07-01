package com.javarush.task.task32.task3209;

/**
 * Это будет наш обработчик исключительных ситуаций, который ты в дальнейшем сможешь переопределить
 */
public class ExceptionHandler{
    public static void log(Exception e){
        System.out.println(e.toString());
    }
}
