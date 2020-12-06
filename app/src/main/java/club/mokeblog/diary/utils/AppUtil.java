package club.mokeblog.diary.utils;

//app信息类
public class AppUtil {
    private static String MY_APP_NAME = "Diary";
    private static String MY_APP_VERSION = "1.0";
    private static String BUILD_VERSION_RELEASE;
    private static String BUILD_MANUFACTURER;

    public static String getMyAppName() {
        return MY_APP_NAME;
    }

    public static String getMyAppVersion() {
        return MY_APP_VERSION;
    }

    public static String getBuildVersionRelease() {
        return BUILD_VERSION_RELEASE;
    }

    public static String getBuildManufacturer() {
        return BUILD_MANUFACTURER;
    }

    public static String getBuildId() {
        return BUILD_ID;
    }

    private static String BUILD_ID;
}
