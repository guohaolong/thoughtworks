package main.java.com.homework.problem3.utils;

/**
 * Created by guohaolong on 16/10/16.
 * 罗马数字与阿拉伯数字转化工具类
 */
public class RomanNumberUtil {
    private final static String rnums[] = {"m", "cm", "d", "cd", "c", "xc", "l", "xl", "x", "Mx", "v", "Mv", "M", "CM",
            "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}; //保存所有罗马数字
    private final static int anums[] = {1000000, 900000, 500000, 400000, 100000, 90000, 50000, 40000, 10000, 9000, 5000,
            4000, 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}; //保存罗马数字对应的值

    /**
     * 阿拉伯数字转为罗马数字
     *
     * @param number
     * @return
     */
    public static String toRomanNumber(int number) {
        if (number == 0) {
            return "ZERO";
        } else if (number < 0 || number > 3999999) {
            return "OVERFLOW";
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; number > 0 && i < anums.length; i++) {
            while (number >= anums[i]) {
                number -= anums[i];
                output.append(rnums[i]);
            }
        }
        return output.toString();
    }


    /**
     * 罗马数字转为阿拉伯数字
     *
     * @return
     */
    public static int toArabicNumber(String romanNumber) {
        int number = 0;
        StringBuilder Buffer = new StringBuilder(romanNumber);
        for (int i = 0; Buffer.length() > 0 && i < anums.length; i++) {
            while (Buffer.indexOf(rnums[i]) == 0) {
                number += anums[i];
                Buffer.delete(0, rnums[i].length());
            }
        }
        return number;
    }
}
