package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<Path> foundFiles = new ArrayList<Path>();
    private List<String> foundLines = new ArrayList<String>();
    private List<Record> records = new ArrayList<Record>();

    public List<String> getFoundLines() {
        return foundLines;
    }

    public LogParser(Path logDir) {
        this.logDir = logDir;
        getInfoFromPath();
        getRecords();
    }

    private void getInfoFromPath() {
        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        try {
            Files.walkFileTree(logDir, options, Integer.MAX_VALUE, searchFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        foundFiles = searchFileVisitor.getFoundFiles();
        foundLines = searchFileVisitor.getFoundLines();
    }

    private void getRecords() {
        for (String s : foundLines) {
            records.add(new Record(s));
        }
    }

    /**
     * десь и далее, если в методе есть параметры Date after и Date before,
     * то нужно возвратить данные касающиеся только данного периода (включая даты after и before).
     * Если параметр after равен null, то нужно обработать все записи, у которых дата меньше или равна before.
     * Если параметр before равен null, то нужно обработать все записи, у которых дата больше или равна after.
     * Если и after, и before равны null, то нужно обработать абсолютно все записи (без фильтрации по дате).
     *
     * @param checkDate
     * @param after
     * @param before
     * @return
     */
    private boolean isCurrectDate(Date checkDate, Date after, Date before) {
        if (after == null && before == null) return true;
        if (after == null && before != null && checkDate.getTime() <= before.getTime()) return true;
        if (after != null && before == null && checkDate.getTime() >= after.getTime()) return true;
        if (after != null && before != null && checkDate.getTime() >= after.getTime() && checkDate.getTime() <= before.getTime())
            return true;
        return false;
    }
    /**
     * десь и далее, если в методе есть параметры Date after и Date before,
     * то нужно возвратить данные касающиеся только данного периода ( не включая даты after и before).
     * Если параметр after равен null, то нужно обработать все записи, у которых дата меньше или равна before.
     * Если параметр before равен null, то нужно обработать все записи, у которых дата больше или равна after.
     * Если и after, и before равны null, то нужно обработать абсолютно все записи (без фильтрации по дате).
     *
     * @param checkDate
     * @param after
     * @param before
     * @return
     */
    private boolean isCurrectDateNotIclude(Date checkDate, Date after, Date before) {
        if (after == null && before == null) return true;
        if (after == null && before != null && checkDate.getTime() < before.getTime()) return true;
        if (after != null && before == null && checkDate.getTime() > after.getTime()) return true;
        if (after != null && before != null && checkDate.getTime() > after.getTime() && checkDate.getTime() < before.getTime())
            return true;
        return false;
    }

    /**
     * должен возвращать количество уникальных IP адресов за выбранный период
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    /**
     * должен возвращать множество, содержащее все не повторяющиеся IP адреса за выбранный период.
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before)) {
                treeSet.add(r.getIp());
            }
        }
        return treeSet;
    }

    /**
     * Lолжен возвращать IP адреса, с которых работал переданный пользователь за выбранный период.
     *
     * @param user
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && user.equals(r.getUsername())) {
                treeSet.add(r.getIp());
            }
        }
        return treeSet;
    }

    /**
     * Lолжен возвращать IP адреса, с которых было произведено переданное событие за выбранный период.
     *
     * @param event
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && event.equals(r.getEvent())) {
                treeSet.add(r.getIp());
            }
        }
        return treeSet;
    }

    /**
     * Должен возвращать IP адреса, события с которых закончилось переданным статусом за выбранный период.
     *
     * @param status
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && status.equals(r.getStatus())) {
                treeSet.add(r.getIp());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать всех пользователей
     *
     * @return
     */
    @Override
    public Set<String> getAllUsers() {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            treeSet.add(r.getUsername());

        }
        return treeSet;
    }

    /**
     * должен возвращать количество уникальных пользователей за период
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public int getNumberOfUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before)) {
                treeSet.add(r.getUsername());
            }
        }
        return treeSet.size();
    }

    /**
     * должен возвращать количество уникальных событий за период для пользователя.
     *
     * @param user
     * @param after
     * @param before
     * @return
     */
    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        TreeSet<Event> treeSet = new TreeSet<Event>();
        int count = 0;
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && user.equals(r.getUsername())) {
                treeSet.add(r.getEvent());
            }
        }
        return treeSet.size();
    }

    /**
     * должен возвращать пользователей с определенным IP
     *
     * @param ip
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && ip.equals(r.getIp())) {
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые были залогинены.
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Event.LOGIN.equals(r.getEvent())) {
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые скачали плагин.
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Event.DOWNLOAD_PLUGIN.equals(r.getEvent())) {
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые отправили сообщение
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Event.WRITE_MESSAGE.equals(r.getEvent())) {
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые решали любую задачу
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Event.SOLVE_TASK.equals(r.getEvent())) {
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые решали задачу с номером task.
     *
     * @param after
     * @param before
     * @param task
     * @return
     */
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Event.SOLVE_TASK.equals(r.getEvent()) && task == r.getNumEvent()) {
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые решили любую задачу.
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Event.DONE_TASK.equals(r.getEvent())) {
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые решали задачу с номером task.
     *
     * @param after
     * @param before
     * @param task
     * @return
     */
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Event.DONE_TASK.equals(r.getEvent()) && task == r.getNumEvent()) {
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать уникальные даты за период
     *
     * @return
     */
    @Override
    public Set<Date> getUniqueDates(Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before)) {
                treeSet.add(r.getDate());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать даты, когда определенный пользователь произвел определенное событие
     *
     * @param user
     * @param event
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && event.equals(r.getEvent()) && user.equals(r.getUsername())) {
                treeSet.add(r.getDate());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать даты, когда любое событие не выполнилось (статус FAILED).
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Status.FAILED.equals(r.getStatus())) {
                treeSet.add(r.getDate());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать даты, когда любое событие закончилось ошибкой (статус ERROR).
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Status.ERROR.equals(r.getStatus())) {
                treeSet.add(r.getDate());
            }
        }
        return treeSet;
    }

    /**
     * Должен возвращать дату, когда пользователь залогинился впервые за указанный период. Если такой даты в логах нет - null.
     *
     * @param user
     * @param after
     * @param before
     * @return
     */
    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && user.equals(r.getUsername()) && Event.LOGIN.equals(r.getEvent())) {
                treeSet.add(r.getDate());
            }
        }
        if (treeSet.size() == 0) return null;
        return treeSet.first();
    }

    /**
     * должен возвращать дату, когда пользователь впервые попытался решить определенную задачу. Если такой даты в логах нет - null.
     *
     * @param user
     * @param task
     * @param after
     * @param before
     * @return
     */
    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && user.equals(r.getUsername()) && Event.SOLVE_TASK.equals(r.getEvent()) && task == r.getNumEvent()) {
                treeSet.add(r.getDate());
            }
        }
        if (treeSet.size() == 0) return null;
        return treeSet.first();
    }

    /**
     * должен возвращать дату, когда пользователь впервые решил определенную задачу. Если такой даты в логах нет - null.
     *
     * @param user
     * @param task
     * @param after
     * @param before
     * @return
     */
    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && user.equals(r.getUsername()) && Event.DONE_TASK.equals(r.getEvent()) && task == r.getNumEvent()) {
                treeSet.add(r.getDate());
            }
        }
        if (treeSet.size() == 0) return null;
        return treeSet.first();
    }

    /**
     * должен возвращать даты, когда пользователь написал сообщение.
     *
     * @param user
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && user.equals(r.getUsername()) && Event.WRITE_MESSAGE.equals(r.getEvent())) {
                treeSet.add(r.getDate());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать даты, когда пользователь скачал плагин.
     *
     * @param user
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && user.equals(r.getUsername()) && Event.DOWNLOAD_PLUGIN.equals(r.getEvent())) {
                treeSet.add(r.getDate());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать количество уникальных событий за указанный период
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        TreeSet<Event> treeSet = new TreeSet<Event>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before)) {
                treeSet.add(r.getEvent());
            }
        }
        return treeSet.size();
    }

    /**
     * должен возвращать все события за указанный период.
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        TreeSet<Event> treeSet = new TreeSet<Event>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before)) {
                treeSet.add(r.getEvent());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать события, которые происходили с указанного IP.
     *
     * @param ip
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        TreeSet<Event> treeSet = new TreeSet<Event>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && ip.equals(r.getIp())) {
                treeSet.add(r.getEvent());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать события, которые инициировал определенный пользователь.
     *
     * @param user
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        TreeSet<Event> treeSet = new TreeSet<Event>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && user.equals(r.getUsername())) {
                treeSet.add(r.getEvent());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать события, которые не выполнились.
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        TreeSet<Event> treeSet = new TreeSet<Event>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Status.FAILED.equals(r.getStatus())) {
                treeSet.add(r.getEvent());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать события, которые завершились ошибкой.
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        TreeSet<Event> treeSet = new TreeSet<Event>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Status.ERROR.equals(r.getStatus())) {
                treeSet.add(r.getEvent());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать количество попыток решить определенную задачу.
     *
     * @param task
     * @param after
     * @param before
     * @return
     */
    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int count = 0;
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Event.SOLVE_TASK.equals(r.getEvent()) && task == r.getNumEvent()) {
                count++;
            }
        }
        return count;
    }

    /**
     * должен возвращать количество успешных решений определенной задачи.
     *
     * @param task
     * @param after
     * @param before
     * @return
     */
    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int count = 0;
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Event.DONE_TASK.equals(r.getEvent()) && task == r.getNumEvent()) {
                count++;
            }
        }
        return count;
    }

    /**
     * должен возвращать мапу (номер_задачи : * количество_попыток_решить_ее).
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Event.SOLVE_TASK.equals(r.getEvent())) {
                map.put(r.getNumEvent(), map.getOrDefault(r.getNumEvent(), 0) + 1);
            }
        }
        return map;
    }

    /**
     * должен возвращать мапу (номер_задачи : * сколько_раз_ее_решили).
     *
     * @param after
     * @param before
     * @return
     */
    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Record r : records) {
            if (isCurrectDate(r.getDate(), after, before) && Event.DONE_TASK.equals(r.getEvent())) {
                map.put(r.getNumEvent(), map.getOrDefault(r.getNumEvent(), 0) + 1);

            }
        }
        return map;
    }

    @Override
    public Set<Object> execute(String query) {
        //get ip for user = "Eduard Petrovich Morozko" and date between "11.12.2013 0:00:00" and "03.01.2014 23:59:59"
        //get field1 for field2 = "value1" and date between "after" and "before"
        //field1: ip, user, date, event, status
        //field2: ip, user, date, event, status
        //value1 - значение поля field2
        //after и before даты

        String field1 = "";
        Param param1 = null;
        String field2 = "";
        Param param2 = null;
        String value1 = "";
        String after = "";
        String before = "";
        Date afterDate = null;
        Date beforeDate = null;
        int formatQuery = 0;

        Matcher matcher;
        String pattern1 = "get (?<field1>\\w+)";
        matcher = Pattern.compile(pattern1).matcher(query);
        if (matcher.find()) {
            field1 = matcher.group("field1");
            formatQuery = 1; //первый тип запроса
        }

        String pattern2 = "get (?<field1>\\w+) for (?<field2>\\w+) = \"(?<value1>.*?)\"";
        matcher = Pattern.compile(pattern2).matcher(query);
        if (matcher.find()) {
            field1 = matcher.group("field1");
            field2 = matcher.group("field2");
            value1 = matcher.group("value1");
            formatQuery = 2; //второй тип запроса
        }

        String pattern3 = "get (?<field1>\\w+) for (?<field2>\\w+) = \"(?<value1>.*?)\" and date between \"(?<after>.*?)\" and \"(?<before>.*?)\"";
        matcher = Pattern.compile(pattern3).matcher(query);
        if (matcher.find()) {
            field1 = matcher.group("field1");
            field2 = matcher.group("field2");
            value1 = matcher.group("value1");
            after = matcher.group("after");
            before = matcher.group("before");
            formatQuery = 3; //третий тип запроса
        }
        //Переводим значения field1 и field2 из строки в Enum Param, чтобы использвать номер(индекс) значения перечисления как индекс для парсинга строки
        try {
            param1 = Param.valueOf(field1.toUpperCase());
        } catch (IllegalArgumentException e) {

        }
        try {
            param2 = Param.valueOf(field2.toUpperCase());
        } catch (IllegalArgumentException e) {

        }
        //переводим строки в даты
        if (after != "") {
            try {
                afterDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(after);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (before != "") {
            try {
                beforeDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(before);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String finalValue = value1;
        Param finalParam1 = param1;
        Param finalParam2 = param2;
        int finalFormatQuery = formatQuery;
        Date finalAfterDate = afterDate;
        Date finalBeforeDate = beforeDate;
        return foundLines.stream().filter(line -> {
            //Если у нас 1 формат запроса (get ip for user) то не сортируем
            if (finalFormatQuery == 1) {
                //Если у нас 1 формат запроса (get ip for user) то не сортируем
                return true;
            } else if (finalFormatQuery == 2 || finalFormatQuery == 3) {
                //Если у нас 2 формат запроса (get ip for user) то сортируем по значению второго параметра
                //Если у нас 3 формат запроса то дополнительно сортируем по попаданию в интервал
                boolean isCorDate=true; //Флаг попадания в интервал для 3-го типа запроса, по умолчанию true, т.к. он же используется и в запросах второго типа
                if (finalFormatQuery == 3) {
                    //Если у нас 3 формат запроса (get ip for user) то сортируем по значению второго параметра + попадание в интервал дат
                    Date dateFromLine = null;
                    try {
                        dateFromLine = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(line.split("\t")[Param.DATE.ordinal()]);
                        isCorDate = isCurrectDateNotIclude(dateFromLine, finalAfterDate, finalBeforeDate);
                    } catch (Exception e) {
                        isCorDate=false;
                        System.out.println("Не удалось преобразовать дату из строки лога: " + line);
                    }
                }
                if (finalParam2.ordinal() == 2) { //фильтрум конкретной по дате
                    try {
                        Date dateFromLine = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(line.split("\t")[finalParam2.ordinal()]);
                        Date dateFromParam = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(finalValue);
                        return isCorDate && dateFromLine.equals(dateFromParam); //если фильтруем по дате
                    } catch (Exception e) {
                        System.out.println("Не удалось преобразовать дату из строки лога: " + line);
                        System.out.println("или дату из запроса: " + finalValue + " : " + query);
                        return false;
                    }
                }else if (finalParam2.ordinal() == 3) {//если филтруем по событию
                    String s = "";
                    s = line.split("\t")[finalParam2.ordinal()];
                    if (s.contains(" ")) {
                        s = s.substring(0, s.indexOf(" "));
                    }
                    return isCorDate && s.equals(finalValue);
                }else {//фильтруем по IP, USER, Status - они текстовые сними проблем нет
                    return isCorDate && line.split("\t")[finalParam2.ordinal()].equals(finalValue);
                }
            }
            return false; //Если не подошли не по одному условию, то ничего не выводим
        }).map(line -> {
            String s = "";
            if (finalParam1.ordinal() == 3) { //выводим события
                s = line.split("\t")[finalParam1.ordinal()];
                if (s.contains(" ")) {
                    s = s.substring(0, s.indexOf(" "));
                }
                return Event.valueOf(s.toUpperCase());
            } else if (finalParam1.ordinal() == 4) { //выводим статус
                s = line.split("\t")[finalParam1.ordinal()];
                return Status.valueOf(s.toUpperCase());
            } else if (finalParam1.ordinal() != 2) //выводим IP и USERNAME
            { //выводим не дату и не по событию
                s = line.split("\t")[finalParam1.ordinal()];
                return s;
            } else {
                try {
                    Date dl = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(line.split("\t")[finalParam1.ordinal()]);
                    return dl;
                } catch (Exception e) {
                    return null;
                }
            }
        }).collect(Collectors.toSet());
    }
}