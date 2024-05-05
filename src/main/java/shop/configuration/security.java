package shop.configuration;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import shop.models.entities.User;
import shop.services.sql.DAOModel;
import shop.services.sql.SqlUser;

public class security {

    private final static SqlUser udao = DAOModel.USER;
    private static Optional<User> optional = Optional.empty();

    public static boolean isLogin() {
        return security.optional.isPresent();
    }

    public static boolean hasAnyRoles(AuthAccess.ROLE... roles) {
        if (optional.isPresent()) {
            Set auths = optional.get().getAuths();
            for (AuthAccess.ROLE role : roles) {
                if (auths.contains(role.value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getUid() {
        return optional.isPresent() ? optional.get().getUsername() : null;
    }

    public static User getUser() {
        return optional.isPresent() ? optional.get() : null;
    }

    public static User login(String username, String password) throws SQLException {
        User user = udao.login(username, password);
        security.optional = Optional.of(user);
        return user;
    }

    public static void logout() {
        security.optional = Optional.empty();
    }
}
