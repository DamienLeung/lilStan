package dfbz.com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {


    /**
     * 验证邮箱正确性
     *
     * @param email 邮箱账户
     * @return true or false
     */
    public static boolean checkEmail(String email) {
        if (null == email || "".equals(email)) {
            return false;
        }

        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        return m.matches();

    }

    /**
     * 随机数生成
     * @return 6位的随机数
     */
    public static String createRandomNum() {
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        return num + "";
    }
}
