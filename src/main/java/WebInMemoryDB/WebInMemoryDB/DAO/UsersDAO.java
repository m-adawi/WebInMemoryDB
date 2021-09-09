package WebInMemoryDB.WebInMemoryDB.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Scope("singleton")
public class UsersDAO {
    private JdbcTemplate jdbcTemplate;
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
}
