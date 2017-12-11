package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise9Test {

    @Test
    public void testResult() throws Exception {
        Excercise9 test = new Excercise9();

       // List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise9Test.txt");

        String test1 = "{{{},{},{{}}}}";
        String test2 = "{{<!!>},{<!!>},{<!!>},{<!!>}}";
        String test3 = "{{<a!>},{<a!>},{<a!>},{<ab>}}";

        int result = test.getScore(test1);
        assertTrue("Expected value 16 "+"but found " + result,result == 16);
        result = test.getScore(test2);
        assertTrue("Expected value 9 "+"but found " + result,result == 9);
        result = test.getScore(test3);
        assertTrue("Expected value 3 "+"but found " + result,result == 3);

        test1 = "<<<<>";//3
        test2 = "<!!!>>"; //0
        test3 = "<{o\"i!a,<{i<a>"; //10


        result = test.getGarbageCount(test1);
        assertTrue("Expected value 3 "+"but found " + result,result == 3);
        result = test.getGarbageCount(test2);
        assertTrue("Expected value 0 "+"but found " + result,result == 0);
        result = test.getGarbageCount(test3);
        assertTrue("Expected value 10 "+"but found " + result,result == 10);

        test.executeTest();
    }


}
