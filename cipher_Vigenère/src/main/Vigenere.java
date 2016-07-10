package main;

/**
 * https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher
 */
public class Vigenere {

    public static void main(String[] args) {

        String key = "качерыжка";

        String text = "Привет, Люда!";

        String ciphertext = encode(key, text);

        String decodedText = decode(key, ciphertext);


        System.out.println("text=" + text);
        System.out.println("ciphertext=" + ciphertext);
        System.out.println("decodedText=" + decodedText);
    }

    public static String decode(String key, String ciphertext) {
        return op(key, ciphertext, false);
    }

    public static String encode(String key, String text) {
        return op(key, text, true);
    }

    private static String op(String key, String text, boolean toEncode) {
        StringBuilder result;

        if (text == null || text.length() == 0 || key == null || key.length() == 0)
            return text;

        result = new StringBuilder(text.length());
        char[] keyArray = key.toCharArray();
        char[] charArray = text.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            result.append((char) (c + (toEncode ? 1 : -1) *
                    keyArray[i % keyArray.length] % Character.MAX_CODE_POINT));
        }

        return result.toString();
    }


}
