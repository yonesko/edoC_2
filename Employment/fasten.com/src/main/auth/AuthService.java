package main.auth;

import dbservice.DBService;
import main.models.AccessToken;
import main.models.Msg;
import main.models.MsgHelper;
import main.models.User;

import java.sql.SQLException;

public class AuthService {
    /**
     * Checks if the user persists in the database.
     * If the user exists this method closes the user's active token and add new one.
     * @return error msg if authorize is fail or msg with access token if successful
     */
    public Msg authorize(User user) throws SQLException {
        Msg result;

        DBService dbService = new DBService();

        if (dbService.isUserExists(user)) {

            AccessToken activeToken = dbService.checkAndAddTokenTo(user);

            result = MsgHelper.getAuthOKMsg(activeToken);
        } else {
            result = MsgHelper.getAuthErrMsg();
        }
        return result;
    }
}
