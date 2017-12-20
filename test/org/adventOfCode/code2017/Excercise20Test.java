package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;

public class Excercise20Test {

    @Test
    public void testResult() throws Exception {
        Excercise20 test = new Excercise20();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise20Test.txt");

        int result = test.getClosestParticle(input);
        assertTrue("Expected particle 0 "+"but found " + result,result == 0 );

        input = FileUtil.readStringsFromFile("resource/2017/Excercise20TestB.txt");
        result = test.getNonCollides(input);
        assertTrue("Expected send count of 1  "+"but found " + result,result == 1);

        test.executeTest();
    }



}
