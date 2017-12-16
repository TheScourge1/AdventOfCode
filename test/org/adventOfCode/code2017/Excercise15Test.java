package org.adventOfCode.code2017;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class Excercise15Test {

    @Test
    public void testResult() throws Exception {
        Excercise15 test = new Excercise15();

      //  List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise13Test.txt");


        long result = test.countPairs(65,8921);
        assertTrue("Expected number of pairs 588 "+"but found " + result,result == 588);

        result = test.countImprovedPairs(65,8921);
        assertTrue("Expected number of pairs 309 "+"but found " + result,result == 309);

   /*     result = test.countRegions("flqrgnkx");
        assertTrue("Expected region count 1242 "+"but found " + result,result == 1242);*/

        test.executeTest();
    }

}
