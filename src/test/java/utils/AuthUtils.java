package utils;

import java.io.InputStream;
import java.util.Properties;

public class AuthUtils {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = AuthUtils.class.getClassLoader()
                .getResourceAsStream("config/auth.properties")) {
            properties.load(input);
        } catch (Exception e) {

        }
    }

    public static String getValidLogin() {
        return properties.getProperty("valid.login");
    }

    public static String getValidPassword() {
        return properties.getProperty("valid.password");
    }

    public static String getInvalidLogin() {
        return properties.getProperty("invalid.login");
    }

    public static String getInvalidPassword() {
        return properties.getProperty("invalid.password");
    }

    public static String getEmptyLogin() {
        return properties.getProperty("negative.empty_login");
    }

    public static String getEmptyPassword() {
        return properties.getProperty("negative.empty_password");
    }

}
