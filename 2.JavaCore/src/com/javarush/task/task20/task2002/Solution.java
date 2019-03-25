package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.DateFormat;
import java.util.*;

/* 
Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush.
Метод main реализован только для вас и не участвует в тестировании.


Требования:
1. Логика чтения/записи реализованная в методах save/load должна работать корректно в случае, если список users пустой.
2. Логика чтения/записи реализованная в методах save/load должна работать корректно в случае, если список users не пустой.
3. Класс Solution.JavaRush не должен поддерживать интерфейс Serializable.
4. Класс Solution.JavaRush должен быть публичным.
5. Класс Solution.JavaRush не должен поддерживать интерфейс Externalizable.
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("test", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user1= new User();
            user1.setFirstName("Игорь");
            user1.setLastName("Графин");
            user1.setCountry(User.Country.RUSSIA);
            Calendar calendar = new GregorianCalendar();
            calendar.set(1989,06,11);
            user1.setBirthDate(calendar.getTime());
            javaRush.users.add(user1);
            User user2= new User();
            user2.setFirstName("Игорь");
            user2.setLastName("Графин");
            user2.setCountry(User.Country.RUSSIA);
            Calendar calendar2 = new GregorianCalendar();
            calendar2.set(1989,06,11);
            user2.setBirthDate(calendar2.getTime());
            javaRush.users.add(user2);


            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the codeGym object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны

            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            if (users==null){
                bufferedWriter.write("users=null");
            }else {
                bufferedWriter.write("users!=null");
                bufferedWriter.newLine();
                for (User u: users) {
                    bufferedWriter.write(u.getFirstName());
                    bufferedWriter.newLine();
                    bufferedWriter.write(u.getLastName());
                    bufferedWriter.newLine();
                    bufferedWriter.write(String.valueOf(u.isMale()));
                    bufferedWriter.newLine();
                    bufferedWriter.write(String.valueOf(u.getBirthDate().getTime()));
                    bufferedWriter.newLine();
                    bufferedWriter.write(u.getCountry().getDisplayName());
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            outputStream.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            if (bufferedReader.readLine().equals("users!=null")){
                while (bufferedReader.ready()){
                    User u = new User();
                    u.setFirstName(bufferedReader.readLine());
                    u.setLastName(bufferedReader.readLine());
                    u.setMale(Boolean.parseBoolean(bufferedReader.readLine()));
                    Date d = new Date();
                    d.setTime(Long.parseLong(bufferedReader.readLine()));
                    u.setBirthDate(d);
                    String c=bufferedReader.readLine().toUpperCase();
                    User.Country country =User.Country.valueOf(c);
                    u.setCountry(country);
                    this.users.add(u);
                }
            }
            bufferedReader.close();
            inputStream.close();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
