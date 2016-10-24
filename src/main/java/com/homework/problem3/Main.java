package main.java.com.homework.problem3;

import main.java.com.homework.problem3.service.TradeService;

public class Main {

    public static void main(String[] args) {
        String filePath = "src/resources/input.txt";
        if(args != null && args.length > 0){
            filePath = args[0];
        }
        TradeService.start(filePath);
    }
}
