package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery {
    private Path logDir;
    private List<Path> foundFiles = new ArrayList<Path>();
    private List<String> foundLines = new ArrayList<String>();
    private List<Record> records = new ArrayList<Record>();


    public LogParser(Path logDir) {
        this.logDir = logDir;
        getInfoFromPath();
        getRecords();
    }
    private void getInfoFromPath(){
        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        try {
            Files.walkFileTree(logDir,options,Integer.MAX_VALUE,searchFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        foundFiles = searchFileVisitor.getFoundFiles();
        foundLines = searchFileVisitor.getFoundLines();
    }
    private void getRecords(){
        for(String s : foundLines){
            records.add(new Record(s));
        }
    }

    /**
     * десь и далее, если в методе есть параметры Date after и Date before,
     * то нужно возвратить данные касающиеся только данного периода (включая даты after и before).
     * Если параметр after равен null, то нужно обработать все записи, у которых дата меньше или равна before.
     * Если параметр before равен null, то нужно обработать все записи, у которых дата больше или равна after.
     * Если и after, и before равны null, то нужно обработать абсолютно все записи (без фильтрации по дате).
     * @param checkDate
     * @param after
     * @param before
     * @return
     */
    private boolean isCurrectDate(Date checkDate,Date after, Date before){
        if (after==null && before==null) return true;
        if (after==null && before!=null && checkDate.getTime()<=before.getTime()) return true;
        if (after!=null && before==null && checkDate.getTime()>=after.getTime()) return true;
        if (after!=null && before!=null && checkDate.getTime()>=after.getTime() && checkDate.getTime()<=before.getTime()) return true;
        return false;
    }

    /**
     * должен возвращать количество уникальных IP адресов за выбранный период
     * @param after
     * @param before
     * @return
     */
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after,before).size();
    }

    /**
     * должен возвращать множество, содержащее все не повторяющиеся IP адреса за выбранный период.
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before)){
                treeSet.add(r.getIp());
            }
        }
        return treeSet;
    }

    /**
     * Lолжен возвращать IP адреса, с которых работал переданный пользователь за выбранный период.
     * @param user
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && user.equals(r.getUsername())){
                treeSet.add(r.getIp());
            }
        }
        return treeSet;
    }

    /**
     * Lолжен возвращать IP адреса, с которых было произведено переданное событие за выбранный период.
     * @param event
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && event.equals(r.getEvent())){
                treeSet.add(r.getIp());
            }
        }
        return treeSet;
    }

    /**
     * Должен возвращать IP адреса, события с которых закончилось переданным статусом за выбранный период.
     * @param status
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && status.equals(r.getStatus())){
                treeSet.add(r.getIp());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать всех пользователей
     * @return
     */
    @Override
    public Set<String> getAllUsers() {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
                treeSet.add(r.getUsername());

        }
        return treeSet;
    }

    /**
     * должен возвращать количество уникальных пользователей за период
     * @param after
     * @param before
     * @return
     */
    @Override
    public int getNumberOfUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before)){
                treeSet.add(r.getUsername());
            }
        }
        return treeSet.size();
    }

    /**
     * должен возвращать количество уникальных событий за период для пользователя.
     * @param user
     * @param after
     * @param before
     * @return
     */
    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        TreeSet<Event> treeSet = new TreeSet<Event>();
        int count=0;
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && user.equals(r.getUsername())) {
                treeSet.add(r.getEvent());
            }
        }
        return treeSet.size();
    }

    /**
     * должен возвращать пользователей с определенным IP
     * @param ip
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && ip.equals(r.getIp())){
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые были залогинены.
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && Event.LOGIN.equals(r.getEvent())){
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые скачали плагин.
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && Event.DOWNLOAD_PLUGIN.equals(r.getEvent())){
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые отправили сообщение
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && Event.WRITE_MESSAGE.equals(r.getEvent())){
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые решали любую задачу
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && Event.SOLVE_TASK.equals(r.getEvent())){
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые решали задачу с номером task.
     * @param after
     * @param before
     * @param task
     * @return
     */
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && Event.SOLVE_TASK.equals(r.getEvent()) && task==r.getNumEvent()){
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые решили любую задачу.
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && Event.DONE_TASK.equals(r.getEvent())){
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать пользователей, которые решали задачу с номером task.
     * @param after
     * @param before
     * @param task
     * @return
     */
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        TreeSet<String> treeSet = new TreeSet<String>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && Event.DONE_TASK.equals(r.getEvent()) && task==r.getNumEvent()){
                treeSet.add(r.getUsername());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать даты, когда определенный пользователь произвел определенное событие
     * @param user
     * @param event
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && event.equals(r.getEvent()) && user.equals(r.getUsername())){
                treeSet.add(r.getDate());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать даты, когда любое событие не выполнилось (статус FAILED).
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && Status.FAILED.equals(r.getStatus())){
                treeSet.add(r.getDate());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать даты, когда любое событие закончилось ошибкой (статус ERROR).
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && Status.ERROR.equals(r.getStatus())){
                treeSet.add(r.getDate());
            }
        }
        return treeSet;
    }

    /**
     * Должен возвращать дату, когда пользователь залогинился впервые за указанный период. Если такой даты в логах нет - null.
     * @param user
     * @param after
     * @param before
     * @return
     */
    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && user.equals(r.getUsername()) && Event.LOGIN.equals(r.getEvent())){
                treeSet.add(r.getDate());
            }
        }
        if (treeSet.size()==0) return null;
        return treeSet.first();
    }

    /**
     * должен возвращать дату, когда пользователь впервые попытался решить определенную задачу. Если такой даты в логах нет - null.
     * @param user
     * @param task
     * @param after
     * @param before
     * @return
     */
    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && user.equals(r.getUsername()) && Event.SOLVE_TASK.equals(r.getEvent()) && task==r.getNumEvent()){
                treeSet.add(r.getDate());
            }
        }
        if (treeSet.size()==0) return null;
        return treeSet.first();
    }

    /**
     * должен возвращать дату, когда пользователь впервые решил определенную задачу. Если такой даты в логах нет - null.
     * @param user
     * @param task
     * @param after
     * @param before
     * @return
     */
    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && user.equals(r.getUsername()) && Event.DONE_TASK.equals(r.getEvent()) && task==r.getNumEvent()){
                treeSet.add(r.getDate());
            }
        }
        if (treeSet.size()==0) return null;
        return treeSet.first();
    }

    /**
     * должен возвращать даты, когда пользователь написал сообщение.
     * @param user
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && user.equals(r.getUsername()) && Event.WRITE_MESSAGE.equals(r.getEvent())){
                treeSet.add(r.getDate());
            }
        }
        return treeSet;
    }

    /**
     * должен возвращать даты, когда пользователь скачал плагин.
     * @param user
     * @param after
     * @param before
     * @return
     */
    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        TreeSet<Date> treeSet = new TreeSet<Date>();
        for (Record r : records){
            if (isCurrectDate(r.getDate(),after,before) && user.equals(r.getUsername()) && Event.DOWNLOAD_PLUGIN.equals(r.getEvent())){
                treeSet.add(r.getDate());
            }
        }
        return treeSet;
    }
}