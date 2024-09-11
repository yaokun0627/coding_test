package com.exam.spring.coding_test;

import java.util.function.Function;

/**
 * 对连续3个或更多个如果字符相同，请将其从字符串中进行替换
 *
 * @author: yaokun
 * @date: 2023/09/11 12:46
 */
public class Stage2 {

    public static void main(String[] args) {
        String input = "aaad";
        String output = replaceSequences(input);
        // 输出结果应为: abbbad
        System.out.println(output);
    }

    /**
     * 替换字符串中的连续3个或更多个如果字符相同重复字符
     *
     * @param s
     * @return
     */
    public static String replaceSequences(String s) {
        Function<StringBuilder, StringBuilder> replaceFunction = sb -> {
            boolean hasChanged = false;
            int length = sb.length();
            int i = 0;
            // 遍历字符串，查找连续3个或更多个相同字符
            while (i < length - 2) {
                char currentChar = sb.charAt(i);
                // 检查是否连续3个或更多个相同字符
                if (currentChar == sb.charAt(i + 1) && currentChar == sb.charAt(i + 2)) {
                    // 发现了连续3个或更多个相同字符，进行替换
                    String replacementChar = getReplacementChar(currentChar);
                    int j = i + 3;
                    while (j < length && sb.charAt(j) == currentChar) {
                        j++;
                    }
                    sb.delete(i, j);
                    sb.insert(i, replacementChar);
                    length = sb.length();
                    hasChanged = true;
                    // 无需移动i指针，因为已替换字符
                } else {
                    i++;
                }
            }

            return hasChanged ? (StringBuilder) Function.identity().apply(sb) : sb;
        };

        StringBuilder sb = new StringBuilder(s);
        return replaceFunction.apply(sb).toString();
    }

    /**
     * 获取替换字符
     * ccc -> b
     * bbb -> a
     *
     * @param c
     * @return
     */
    private static String getReplacementChar(char c) {
        if (c == 'b') {
            return "a";
        } else if (c == 'c') {
            return "b";
        }else if (c == 'a') {
            return "";
        }
        // 可以根据需求添加更多字符的替换规则
        return ""; // 如果没有匹配的替换规则，则返回原字符
    }
}
