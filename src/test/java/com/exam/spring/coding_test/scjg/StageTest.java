package com.exam.spring.coding_test.scjg;

import com.exam.spring.SpringDemoApplication;
import com.exam.spring.coding_test.Stage2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.exam.spring.coding_test.Stage1.removeSequencesMethod1;
import static com.exam.spring.coding_test.Stage1.removeSequencesMethod2;
import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * @author: yaokun
 * @desc 单元测试coding test
 * @date 2024/09/11 12:45
 * @Version 1.0
 */
@SpringBootTest(classes = SpringDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ActiveProfiles(value = "dev")
public class StageTest {

    @Test
    public void testStage1() {
        String input = "aabcccbbad";

        // 方法1: Lambda包装函数实现
        String activeOutput1 = removeSequencesMethod1(input);
        // 方法2: 栈实现
        String activeOutput2 = removeSequencesMethod2(input);

        assertEquals("期望 aabcccbbad 转为 aabbbad", "aabbbad", activeOutput2);
        assertEquals("期望方法1和方法2两种实现的结果一致", activeOutput1, activeOutput2);

        for (int i = 1; i <= 3; i++) {
            String currentInput = input;
            input = removeSequencesMethod1(input);
            System.out.println("当前入参:" + currentInput + ", 第" + i + "次输出:  " + input);

            if (i == 1) {
                assertEquals("aabcccbbad 期望转为 aabbbad", "aabbbad", input);
            } else if (i == 2) {
                assertEquals("aabbbad 期望转为 aaad", "aaad", input);
            } else {
                assertEquals("aaad 期望转为 d", "d", input);
            }
        }
    }

    @Test
    public void testStage2() {
        String input = "abcccbad";

        for (int i = 1; i <= 3; i++) {
            String currentInput = input;
            input = Stage2.replaceSequences(input);
            System.out.println("当前入参:" + currentInput + ", 第" + i + "次输出:  " + input);

            if (i == 1) {
                assertEquals("abcccbad 期望转为 abbbad", "abbbad", input);
            } else if (i == 2) {
                assertEquals("abbbad 期望转为 aaad", "aaad", input);
            } else {
                assertEquals("aaad 期望转为 d", "d", input);
            }
        }
    }


}
