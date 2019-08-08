package core.db;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import next.model.User;

public class DataBase {
    private static Map<String, User> users = Maps.newHashMap();
    private static Logger Log = LoggerFactory.getLogger(DataBase.class);

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
        Log.debug("user:{}",user);
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static Collection<User> findAll() {
        return users.values();
    }
}
