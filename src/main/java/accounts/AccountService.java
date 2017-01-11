package accounts;

import dbservice.DBException;
import dbservice.DBService;
import dbservice.datasets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mk-orzu on 08.01.2017.
 */
public class AccountService {

    //private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    DBService dbService;



    public AccountService() {
        dbService = new DBService();
        dbService.printConnectInfo();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) throws DBException{
        try {
        if (userProfile !=null) {
            dbService.addUser(userProfile.getLogin(), userProfile.getPassword());
        }else{
            throw new DBException(new Exception("null argument was sent"));
        }
        }catch (Exception e){
            throw new DBException(e);
        }
    }

    public UserProfile getUserByLogin(String login){
        UserProfile userProfile = null;
        try {
            UsersDataSet usersDataSet = dbService.getUser(login);
            if(usersDataSet != null) {
                userProfile = new UserProfile(usersDataSet.getLogin(), usersDataSet.getPassword());
            }
        }catch (DBException e){
            e.printStackTrace();
        }finally {
            return userProfile;
        }
    }

    public void addSession(String sessionId, UserProfile userProfile){
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public UserProfile getUserProfileBySessionId(String sessionId){
        return sessionIdToProfile.get(sessionId);
    }

    public void deleteSession(String sessionId){
        sessionIdToProfile.remove(sessionId);
    }
}
