import org.junit.Test;

public class test01 {

    @Test
    public void mappingName() {
        String s = "registerTime";
        StringBuilder name = new StringBuilder(s);
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length) {
            if (Character.isUpperCase(chars[i])) {
                name.insert(name.indexOf(String.valueOf(chars[i])), "_");
                name.setCharAt(name.indexOf(String.valueOf(chars[i])), Character.toLowerCase(chars[i]));

            }
            i ++;
        }
        System.out.println(chars);
        System.out.println(name.toString());
    }
}
