package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise3Test {

    @Test
    public void testResult() throws Exception {
        Excercise3 test = new Excercise3();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise2Test.txt");

        long result = test.calculateSteps(1);
        assertTrue("steps expect 0 "+"but found " + result,result == 0);

        result = test.calculateSteps(12);
        assertTrue("steps expect 3 "+"but found " + result,result == 3);
        result = test.calculateSteps(23);
        assertTrue("steps expect 2 "+"but found " + result,result == 2);
        result = test.calculateSteps(1024);
        assertTrue("steps expect 31 "+"but found " + result,result == 31);

        result = test.getStressTestValue(6);
        assertTrue("steps expect 10 "+"but found " + result,result == 10);
        result = test.getStressTestValue(60);
        assertTrue("steps expect 122 "+"but found " + result,result == 122);

        test.executeTest();
    }


}
