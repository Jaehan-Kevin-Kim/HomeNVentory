package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author 841898
 */
public class AccountService {

    public User login(String email, String password) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }

    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }

    public void insert(String email, boolean active, String firstName, String lastName, String password, int roleId) throws Exception {
        User user = new User(email, active, firstName, lastName, password);
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleId);
        user.setRole(role);

        UserDB userDB = new UserDB();
        userDB.insert(user);
    }

    public void update(String email, boolean active, String firstName, String lastName, String password, int roleId) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setActive(active);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);

        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleId);
        user.setRole(role);

        userDB.update(user);
    }

    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);

    }
}
