package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = bufferedReader.readLine();
            secondFileName = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        //add your code here - добавьте код тут
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface extends Runnable{

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут
    public static class ReadFileThread extends Thread implements ReadFileInterface{
        private File file;
        private String fileContent="";
        @Override
        public void setFileName(String fullFileName) {
            this.file=new File(fullFileName);
        }

        @Override
        public String getFileContent() {
            return this.fileContent;
        }

        @Override
        public void start() {
          super.start();
        }

        @Override
        public void run() {
            try {
                String s;
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((s=bufferedReader.readLine()) != null){
                    this.fileContent=this.fileContent+" "+ s;
                }
                bufferedReader.close();
                fileReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
