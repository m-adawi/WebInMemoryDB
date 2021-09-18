package WebInMemoryDB.WebInMemoryDB.DAO;

import WebInMemoryDB.WebInMemoryDB.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Scope("singleton")
public class UsersDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public UsersDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addUser(String username, String password, String role) {
        if(!role.equals("ADMIN") && !role.equals("WRITER") && !role.equals("READER"))
            return;
        role = "ROLE_" + role;
        jdbcTemplate.update("insert into users values (?, ?, 1)", username, passwordEncoder.encode(password));
        jdbcTemplate.update("insert into authorities values (?, ?)", username, role);
    }

    public boolean removeUser(String username) {
        jdbcTemplate.update("delete from authorities where username = ?", username);
        int numberOfDeletedRows = jdbcTemplate.update("delete from users where username = ?", username);
        return numberOfDeletedRows != 0;
    }

    public boolean areValidCredentials(String username, String password) {
        try {
            String passwordHash = jdbcTemplate.queryForObject("select password from users where username = ?", String.class, username);
            return passwordEncoder.matches(password, passwordHash);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public void changePassword(String username, String password) {
        jdbcTemplate.update("update users set password = ? where username = ?", passwordEncoder.encode(password), username);
    }

    public boolean isAllowedToWrite(Authentication auth) {
        return auth.getAuthorities().stream().anyMatch(a ->
                a.getAuthority().equals("ROLE_ADMIN") ||
                a.getAuthority().equals("ROLE_WRITER"));
    }

    public boolean isAllowedToWrite(String username) {
        String role = getRole(username);
        return role.equals("ADMIN") || role.equals("WRITER");
    }

    public boolean isAdmin(Authentication auth) {
        return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public boolean isAdmin(String username) {
        return getRole(username).equals("ADMIN");
    }

    private String getRole(String username) {
        String authority = jdbcTemplate.queryForObject("select authority from authorities where username = ?", String.class, username);
        if(authority == null)
            return "";
        return authority.replaceFirst("^ROLE_", "");
    }

    public List<User> getUsers() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select username, authority from authorities");
        List<User> users = new ArrayList<>();
        for(Map map : list) {
            User user = new User();
            user.setUsername(map.get("username").toString());
            user.setRole(map.get("authority").toString().replaceFirst("^ROLE_", ""));
            users.add(user);
        }
        return users;
    }
}
