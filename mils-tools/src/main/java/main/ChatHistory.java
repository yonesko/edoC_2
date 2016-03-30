package main;

/**
 * Created by gleb on 30.03.16.
 */
public class ChatHistory {
    private static StringBuilder hist = new StringBuilder();

    public static StringBuilder getHist() {
        return hist;
    }

    public static void addMsg(String author, String msg) {
        if (author == null || msg == null)
            return;
        if (author.length() > 0 && msg.length() > 0) {
            hist.append(author);
            hist.append(':');
            hist.append(msg);
            hist.append('\n');
        }
    }

    private ChatHistory() {
    }
}
