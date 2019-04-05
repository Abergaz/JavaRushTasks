package com.javarush.task.task32.task3212;

import com.javarush.task.task32.task3212.contex.InitialContext;
import com.javarush.task.task32.task3212.service.Service;

/*
Service Locator
Прочитать о паттерне Service locator.

Реализуй логику метода getService(String jndiName) в ServiceLocator.
В нем будет реализована работа с контекстом и кэшем.

1) Верни из кэша нужный сервис.
2) Если в кэше нет нужного сервиса то:
2.1) Создай контекст.
2.2) Возьми у контекста нужный сервис.
2.3) Добавь сервис в кеш и верни его.


Требования:
1. Класс ServiceLocator должен содержать метод Service getService(String jndiName).
2. Если нужный сервис находится в кэше, метод getService(String jndiName) должен возвращать сервис из кэша.
3. Если нужный сервис НЕ находится в кэше, метод getService(String jndiName) должен создавать контекст.
4. Если нужный сервис НЕ находится в кэше, метод getService(String jndiName) должен искать нужный сервис в контексте.
5. Если нужный сервис НЕ находится в кэше, метод getService(String jndiName) должен добавлять в кэш сервис, найденный в контексте и возвращать его.
*/


public class ServiceLocator {
    private static Cache cache;

    static {
        cache = new Cache();
    }

    /**
     * First, check for a service object in the cache
     * If a service object is not in the cache, perform a lookup using
     * the JNDI initial context and get the service object. Add it to
     * the cache for future use.
     *
     * @param jndiName The name of the service object in the context
     * @return Object mapped to the name in context
     */
    public static Service getService(String jndiName) {
        Service service = cache.getService(jndiName);
        if (service==null){
            InitialContext context = new InitialContext();
            service = (Service) context.lookup(jndiName);
            cache.addService(service);
        }
        return service;
    }
}
