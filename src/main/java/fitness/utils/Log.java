package fitness.utils;

public class Log {
    public static void info(String str)
    {
        System.out.println("\033[1;32m[INFO]\033[0m  At "+Thread.currentThread().getName()+": "+str);
    }
    public static void debug(String str)
    {
        System.out.println("\033[1;31m[DEBUG]\033[0m At "+Thread.currentThread().getName()+": "+str);
    }
}
