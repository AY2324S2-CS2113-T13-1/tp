package seedu.duke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class trial {
    private static ArrayList<Integer> parseNumbers(String problem) {

        // 使用正则表达式匹配数字和运算符
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(problem);

        // 提取匹配到的数字
        ArrayList<Integer> numbers = new ArrayList<>();
        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        return numbers;
    }

    public static void main(String[] args) {
        assertEquals(0.7,0.7);
        System.out.println(parseNumbers("324 / 24 / 36 /48")
        );
    }
}

