package main;

import dbservice.DBException;
import dbservice.DBService;
import dbservice.datasets.UsersDataSet;

/**
 * Created by mk-orzu on 10.01.2017.
 */
public class TestMain {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser("test","test");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser("test");
            System.out.println("User data set: " + dataSet);

        } catch (
                DBException e) {
            e.printStackTrace();
        }
    }
}
