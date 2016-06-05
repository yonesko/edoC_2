package dbservice.dao;

import dbservice.executor.Executor;
import main.models.AccessToken;
import main.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class AuthDAO {
    private static final Logger logger = LogManager.getLogger();
    private Executor executor;

    public AuthDAO(Connection connection) {
        this.executor = new Executor(connection);
    }
    /**
     * @return true if pair of email and password exist in one row
     */
    public boolean isUserExists(User user) throws SQLException {
        logger.entry(user);
        String query = String.format(
                "SELECT 1 FROM users " +
                "WHERE password = '%s' " +
                "AND email = '%s'", user.getPassword(), user.getEmail());
        logger.trace(query);
        return logger.exit(executor.execQuery(query, ResultSet::next));
    }
    /**
     * token is active if its expiration timestamp is after current
     */
    public AccessToken activeTokenOf(User user) throws SQLException {
        logger.entry(user);
        String query = String.format(
                "SELECT token, expiration FROM tokens " +
                        "WHERE expiration >= '%s' " +
                        "AND email = '%s'", Timestamp.from(Instant.now()), user.getEmail());
        logger.trace(query);
        return logger.exit(executor.execQuery(query, resultSet -> {
            AccessToken result = null;
            if (resultSet.next()) {
                result = new AccessToken(resultSet.getString("token"), resultSet.getTimestamp("expiration"));
            }
            //if more then one active token
            if (resultSet.next())
                throw new SQLException("more then one active token of " + user);
            return result;
        }));
    }

    /**
     * Add new random token to user's email.
     * Duration of the token is one day;
     */
    public void addTokenTo(User user) throws SQLException {
        logger.entry(user);
        String update = String.format("INSERT INTO tokens(email, token, expiration) " +
                        "VALUES('%s', '%s', '%s')",
                user.getEmail(), UUID.randomUUID(), Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS)));
        logger.trace(update);
        executor.execUpdate(update);
    }

    /**
     * Sets token's expiration date to now.
     */
    public void closeToken(AccessToken token) throws SQLException {
        logger.entry(token);
        String update = String.format("UPDATE tokens " +
                        "SET expiration = '%s' " +
                        "WHERE token = '%s'",
                Timestamp.from(Instant.now()).toString(), token.getVal());
        logger.trace(update);
        executor.execUpdate(update);
    }
}
