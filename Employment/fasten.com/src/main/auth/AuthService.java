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
     * @return error msg if authorization is fail or msg with access token if successful
     */
    public Msg authorization(User user) throws SQLException {
        Msg result;

        DBService dbService = DBService.getDbService();

        if(dbService.isUserExists(user)) {
            AccessToken usersToken = dbService.activeTokenOf(user);

            if (usersToken != null) {
                dbService.closeToken(usersToken);
            }

            dbService.addTokenTo(user);

            result = MsgHelper.getAuthOKMsg(dbService.activeTokenOf(user));
        } else {
            result = MsgHelper.getAuthErrMsg();
        }
        return result;
    }
}
