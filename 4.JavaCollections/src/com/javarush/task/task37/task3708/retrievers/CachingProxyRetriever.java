package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever{
    private OriginalRetriever retriever;
    LRUCache lruCache = new LRUCache(16);

    public CachingProxyRetriever(Storage storage) {

        this.retriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object o=lruCache.find(id);
        if (o!=null) return o;
        o=retriever.retrieve(id);
        if (o!=null) lruCache.set(id,o);
        return o;
    }
}
