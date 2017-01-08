package accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mk-orzu on 08.01.2017.
 */
public class AccountService {

    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile){
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUserByLogin(String login){
        return loginToProfile.get(login);
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
