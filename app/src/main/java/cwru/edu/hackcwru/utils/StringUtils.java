package cwru.edu.hackcwru.utils;

public class StringUtils {
    public static boolean isEmpty(String... args) {
        for (String arg : args) {
            if (arg.equals(""))
                return true;
        }
        return false;
    }
}
