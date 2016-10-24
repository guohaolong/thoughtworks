package main.java.com.homework.problem3.domain;

/**
 * Created by guohaolong on 16/10/16.
 */
public class RomanNumber {

    /**
     * 名称
     */
    private String name;
    /**
     * 替换名称
     */
    private String replaceName;
    /**
     * 对应的阿拉伯数字数值
     */
    private Integer number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReplaceName() {
        return replaceName;
    }

    public void setReplaceName(String replaceName) {
        this.replaceName = replaceName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
