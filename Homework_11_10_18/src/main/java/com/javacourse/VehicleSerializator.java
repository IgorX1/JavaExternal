package com.javacourse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.*;

import static com.javacourse.ConstantValues.LOG_CONFIG_PATH;

abstract class VehicleSerializator{

    //Log4j logger
    static Logger logger;

    static {
        logger = Logger.getLogger(VehicleController.class);
    }

    public VehicleSerializator() {
        //Set configuration file for the log4j logger
        DOMConfigurator.configure(LOG_CONFIG_PATH);
    }

    boolean serialization(Vehicle vehicle, String filename){
        ObjectOutputStream objectOutputStream = null;
        boolean flag = false;
        try{
            FileOutputStream fo = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fo);
            objectOutputStream.writeObject(vehicle);
            flag = true;
        }catch (IOException e){
            logger.debug(e.getMessage()+":Serialization unsuccessful");
        }finally {
            if(objectOutputStream!=null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    logger.debug(e.getMessage()+":Serialization unsuccessful");
                }
            }
        }
        return flag;
    }
    abstract Vehicle deserialization(String fileName) throws InvalidObjectException;

    Object objectDeserialization(String fileName) throws InvalidObjectException {
        File file = new File(fileName);
        ObjectInputStream objectInputStream = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException e){
            logger.debug(e.getMessage()+":Deserialization unsuccessful");
        }
        finally {
            try {
                if(objectInputStream!=null){
                    objectInputStream.close();
                }
            } catch (IOException e) {
                logger.debug(e.getMessage()+":Deserialization unsuccessful");
            }
        }
        throw new InvalidObjectException("Object not deserialized");
    }
}
