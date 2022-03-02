package tajbanana.day2.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static tajbanana.day2.repositories.SQL.SQL_GET_USER_BY_USERNAME;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean hasUser(String username) {
        final SqlRowSet rowSet = jdbcTemplate.queryForRowSet(
                SQL_GET_USER_BY_USERNAME, username);

        System.out.println("SQL USER COUNT ROW-SET >>>> "+ rowSet.toString());

        if (rowSet.next()) {
            return rowSet.getInt("user_count") > 0 ;
        }

        return false;
    }
}
