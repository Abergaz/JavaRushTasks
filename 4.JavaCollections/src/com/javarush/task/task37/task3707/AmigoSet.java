package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable{
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        this.map = new HashMap<E,Object>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max(16, (int)(collection.size()/.75f)+1);
        this.map = new HashMap<>(capacity);
        for(E element :collection ){
            add(element);
        }
    }

    @Override
    public boolean add(E e) {
        if (map.containsKey(e)){
            return false;
        }else{
            map.put(e,AmigoSet.PRESENT);
            return true;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        if (map.remove(o)==null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Object clone() {
        try {
            AmigoSet copy = (AmigoSet)super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        }
        catch (Exception e) {
            throw new InternalError();
        }
    }
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        try
        {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(map.keySet().size());
            for (E elem : map.keySet())
            {
                objectOutputStream.writeObject(elem);
            }

            objectOutputStream.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
            objectOutputStream.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try
        {
            objectInputStream.defaultReadObject();

            Set<E> set = new HashSet<>();
            int size = (int) objectInputStream.readObject();
            for (int i = 0; i < size; i++)
            {
                set.add((E) objectInputStream.readObject());
            }

            int capacity = (int) objectInputStream.readObject();
            float loadFactor = (float) objectInputStream.readObject();
            map = new HashMap<>(capacity, loadFactor);
            for (E elem : set)
            {
                map.put(elem, PRESENT);
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}

