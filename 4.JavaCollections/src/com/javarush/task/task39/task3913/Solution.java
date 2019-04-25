package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("d:/logs/"));
        //System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));

        //System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, null));
        //System.out.println(logParser.getAllDoneTasksAndTheirNumber(null, null));
        System.out.println(logParser.execute("get ip"));
    }

}