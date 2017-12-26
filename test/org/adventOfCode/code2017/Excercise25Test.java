package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Excercise25Test {

    @Test
    public void testResult() throws Exception {
        Excercise25 test = new Excercise25();

       List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise25Test.txt");

        int result = test.getChecksum(input);
        assertTrue("Expected 3 "+"but found " + result,result == 3 );

   /*     result = test.getLongestBridgeStrength(input);
        assertTrue("Expected 19 "+"but found " + result,result == 19 );*/

        test.executeTest();
    }




}
