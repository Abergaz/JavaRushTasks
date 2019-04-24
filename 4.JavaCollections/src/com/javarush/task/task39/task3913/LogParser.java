package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class LogParser implements IPQuery {
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
}