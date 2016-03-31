package dbService.dao;

import dbService.models.ChatMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ChatHistoryDAO {
    private static final Logger logger = LogManager.getLogger();

    private static List<ChatMessage> history = new ArrayList<ChatMessage>();

    private ChatHistoryDAO() {}

    public static void addMessage(ChatMessage msg) {
        logger.info("addMessage:" + msg);
        history.add(msg);
    }

    public static List<ChatMessage> getChatHistory() {
        return history;
    }
}
