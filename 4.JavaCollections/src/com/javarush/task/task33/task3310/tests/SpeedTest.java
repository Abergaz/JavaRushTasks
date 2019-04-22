package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date startDateTime = new Date();
        for (String s : strings){
            ids.add(shortener.getId(s));
        }
        Date finishDateTime = new Date();
        return finishDateTime.getTime() - startDateTime.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date startDateTime = new Date();
        for (Long l : ids){
            strings.add(shortener.getString(l));
        }
        Date finishDateTime = new Date();
        return finishDateTime.getTime() - startDateTime.getTime();
    }
    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<String>();
        for (int i=0;i<1000; i++){
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids1 = new HashSet<>();
        long t1 = getTimeToGetIds(shortener1,origStrings, ids1);

        Set<Long> ids2 = new HashSet<>();
        long t2 = getTimeToGetIds(shortener2,origStrings, ids2);

        Assert.assertTrue(t1>t2);

        long t3 = getTimeToGetStrings(shortener1,ids1, new HashSet<String>());

        long t4 = getTimeToGetStrings(shortener2,ids2, new HashSet<String>());

        Assert.assertEquals((float)t3, (float)t4, 30);




    }
}
