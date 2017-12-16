package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise13Test {

    @Test
    public void testResult() throws Exception {
        Excercise13 test = new Excercise13();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise13Test.txt");


        int result = test.getseverity(input);
        assertTrue("Expected severity 24 "+"but found " + result,result == 24);

        result = test.getRequiredDelay(input);
        assertTrue("Expected delay 10 "+"but found " + result,result == 10);

        test.executeTest();
    }

}
