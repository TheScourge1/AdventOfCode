package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise19Test {

    @Test
    public void testResult() throws Exception {
        Excercise19 test = new Excercise19();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise19Test.txt");


        int result2 = test.getStepCount(input);
        assertTrue("Expected path 38 "+"but found " + result2,result2 == 38 );

        /*   result = test.getSendCount(input);
        assertTrue("Expected send cound of 3  "+"but found " + result,result == 3);*/

        test.executeTest();
    }



}
