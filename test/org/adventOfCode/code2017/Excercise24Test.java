package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Excercise24Test {

    @Test
    public void testResult() throws Exception {
        Excercise24 test = new Excercise24();

       List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise24Test.txt");

        int result = test.getMaxBridgeStrenght(input);
        assertTrue("Expected 31 "+"but found " + result,result == 31 );

        result = test.getLongestBridgeStrength(input);
        assertTrue("Expected 19 "+"but found " + result,result == 19 );

   /*     result = test.countInfectedNodeEvolvedBursts(input,10000000);
        assertTrue("Expected 26 "+"but found " + result,result == 2511944 );*/

     /*   result = test.getNonCollides(input);
        assertTrue("Expected send count of 1  "+"but found " + result,result == 1);*/

        test.executeTest();
    }



}
