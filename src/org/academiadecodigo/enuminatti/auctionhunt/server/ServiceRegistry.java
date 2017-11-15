package org.academiadecodigo.enuminatti.auctionhunt.server;

import org.academiadecodigo.enuminatti.auctionhunt.Service;

import java.util.HashMap;
import java.util.Map;

public final class ServiceRegistry {

    private Map<String, Service> serviceMap = new HashMap<>(); //Container of services
    private static ServiceRegistry instance = null;

    /**
     *
     */
    private ServiceRegistry() {

    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @param string
     * @param service
     */
    public void addService(String string, Service service){

        serviceMap.put(string, service);

    }

    /**
     *
     * @param string
     * @return
     */
    public Service getService(String string){

        return serviceMap.get(string);
    }

}
