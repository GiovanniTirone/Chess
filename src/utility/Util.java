package utility;

public class Util {
    public static int mod9 (int number) {
        if(number>=0) return number%9;
        else return (number+9*(f(number)))%9;
    }

    private static int f (int number) {
        if(number < 9 ) return 1;
        else return number/9;
    }

    public static boolean numberIsInBoard (int num) {
        return num==-1 || num==8 ? false : true;
    }
}
