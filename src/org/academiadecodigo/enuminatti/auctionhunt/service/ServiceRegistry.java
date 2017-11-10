package org.academiadecodigo.enuminatti.auctionhunt.service;

import java.util.HashMap;
import java.util.Map;

public final class ServiceRegistry {

    private Map<String, Service> serviceMap = new HashMap<>(); //Container of services
    private static ServiceRegistry instance = null;


    private ServiceRegistry() {

    }

    public static ServiceRegistry getInstance(){

        if(instance == null){
            synchronized (ServiceRegistry.class) {
                if (instance == null) {
                    return instance = new ServiceRegistry();
                }
            }
        }
        return instance;
    }

    public void addService(String string, Service service){

        serviceMap.put(string, service);

    }

    public Service getService(String string){

        return serviceMap.get(string);
    }

}
