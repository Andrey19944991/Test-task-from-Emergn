package accounts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    public Stack<String> list=new Stack<>();

    public AccountService(Stack list) {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        this.list=list;
    }

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        list.push(userProfile.getLogin());
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
