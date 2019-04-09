package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(name = "shop")
@XmlRootElement
public class Shop {
    @XmlElement
    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData;

    @XmlType(name = "goods")
    public static class Goods{
        @XmlAnyElement
        public List<String> names;
    }
}
