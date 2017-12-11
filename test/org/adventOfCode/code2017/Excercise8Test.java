package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise8Test {

    @Test
    public void testResult() throws Exception {
        Excercise8 test = new Excercise8();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise8Test.txt");


        int result = test.getMaxRegisterValue(input);
        assertTrue("Expected value 1 "+"but found " + result,result == 1);

        result = test.getAbsoluteMaxRegisterValue(input);
        assertTrue("Expected value 10 "+"but found " + result,result == 10);

        test.executeTest();
    }


}
