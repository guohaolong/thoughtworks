package main.java.com.homework.problem3.service;

import main.java.com.homework.problem3.common.KeyWords;
import main.java.com.homework.problem3.domain.Metal;
import main.java.com.homework.problem3.domain.RomanNumber;
import main.java.com.homework.problem3.edomain.ErrorCode;
import main.java.com.homework.problem3.utils.RomanNumberUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guohaolong on 16/10/16.
 */
public class TradeService {

    //定义静态变量
    public static Map<String, RomanNumber> romanNumberMap = new HashMap<>();

    public static Map<String, Metal> metalMap = new HashMap<>();

    /**
     * 程序开始
     */
    public static void start(String filePath) {
        List<String> lines = readText(filePath);
        if (lines != null && !lines.isEmpty()) {
            for (String line : lines) {
                line = line.trim();
                //先判断多少单词,如果是三个,且包含is和罗马数字,则认为是替换的条件
                String[] split = line.split("\\s+");
                if (split != null && split.length == 3) {
                    if (KeyWords.IS.equals(split[1])) {
                        String replaceName = split[0];
                        String roman = split[2];
                        RomanNumber romanNumber = new RomanNumber();
                        romanNumber.setName(roman);
                        romanNumber.setNumber(RomanNumberUtil.toArabicNumber(roman));
                        romanNumber.setReplaceName(replaceName);
                        romanNumberMap.put(replaceName, romanNumber);
                    }
                }
                //判断是否是Credits结尾的条件
                else if (line.endsWith(KeyWords.CREDITS) && split != null && split.length >= 5 && KeyWords.IS.equals(split[split.length - 3])) {
                    //金属物品名称
                    String metalName = split[split.length - 4];
                    //金属物品的数量
                    String romanNum = "";
                    for (int i = 0; i < split.length - 3; i++) {
                        if (romanNumberMap.get(split[i]) != null) {
                            RomanNumber romanNumber = romanNumberMap.get(split[i]);
                            romanNum = romanNum + romanNumber.getName();
                        }
                    }
                    int num = RomanNumberUtil.toArabicNumber(romanNum);
                    float totalPrice = Float.parseFloat(split[split.length - 2]);
                    float price = 0;
                    if (totalPrice > 0 && num > 0) {
                        price = totalPrice / num;
                    }
                    Metal metal = new Metal();
                    metal.setName(metalName);
                    metal.setPrice(price);
                    metalMap.put(metalName, metal);
                }

                //判断是否how much问题开始
                else if (line.startsWith(KeyWords.HOWMUCHIS) && line.endsWith("?") && split != null && split.length >= 5) {
                    String romanStr = "";
                    String romanNum = "";
                    for (int i = 3; i < split.length - 1; i++) {
                        romanStr += " " + split[i];
                        if (romanNumberMap.get(split[i]) != null) {
                            RomanNumber romanNumber = romanNumberMap.get(split[i]);
                            romanNum = romanNum + romanNumber.getName();
                        }
                    }
                    int num = RomanNumberUtil.toArabicNumber(romanNum);
                    System.out.println(romanStr + " " + KeyWords.IS + " " + num);
                }

                //判断是否how many问题开始
                else if (line.startsWith(KeyWords.HOWMANY) && line.endsWith("?") && split != null && split.length >= 5) {
                    String romanStr = "";
                    String romanNum = "";
                    for (int i = 4; i < split.length - 2; i++) {
                        romanStr += " " + split[i];
                        if (romanNumberMap.get(split[i]) != null) {
                            RomanNumber romanNumber = romanNumberMap.get(split[i]);
                            romanNum = romanNum + romanNumber.getName();
                        }
                    }
                    int num = RomanNumberUtil.toArabicNumber(romanNum);
                    //金属名称
                    String metalName = split[split.length - 2];
                    //判断是否存在该金属的信息
                    if (metalMap.get(metalName) == null) {
                        System.out.println(ErrorCode.HAVE_NO_IDEA.getMessage());
                    }
                    Metal metal = metalMap.get(metalName);
                    float totalPrice = num * metal.getPrice();
                    System.out.println(romanStr + " " + metalName + " " + KeyWords.IS + " " + totalPrice + " " + KeyWords.CREDITS);
                } else {
                    System.out.println(ErrorCode.HAVE_NO_IDEA.getMessage());
                }
            }
        }
    }

    /**
     * 读取文本文件
     *
     * @return
     */
    public static List<String> readText(String filePath) {
        Path path = Paths.get(filePath);
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path, Charset.forName("utf-8"));
        } catch (IOException e) {
            System.err.println("该路径下找不到该文件,请重试: " + e.getMessage());
            System.exit(1);
        }
        return lines;
    }

}
