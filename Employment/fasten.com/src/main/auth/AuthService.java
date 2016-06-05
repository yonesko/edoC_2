package main.auth;

import dbservice.DBService;
import main.models.AccessToken;
import main.models.Msg;
import main.models.MsgHelper;
import main.models.User;

import java.sql.SQLException;

public class AuthService {
    private DBService dbService = DBService.getDbService();;

    /**
     * Checks if the user persists in the database.
     * If the user exists this method closes the user's active token and add new one.
     * @return error msg if authorization is fail or msg with access token if successful
     */
    public Msg authorization(User user) throws SQLException {
        Msg result;

        if(dbService.isUserExists(user)) {
            AccessToken usersToken = DBService.getDbService().activeTokenOf(user);

            if (usersToken != null) {
                DBService.getDbService().closeToken(usersToken);
            }

            DBService.getDbService().addTokenTo(user);

            result = MsgHelper.getAuthOKMsg(DBService.getDbService().activeTokenOf(user));
        } else {
            result = MsgHelper.getAuthErrMsg();
        }
        return result;
    }
}
