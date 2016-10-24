package main.java.com.homework.problem3.edomain;

/**
 * Created by guohaolong on 16/10/16.
 */
public enum ErrorCode {

    HAVE_NO_IDEA("000001", " I have no idea what you are talking about");

    /**
     * code
     */
    private final String code;
    /**
     * message
     */
    private final String message;


    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
