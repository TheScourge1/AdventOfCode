package org.adventOfCode.code2017;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class Excercise16Test {

    @Test
    public void testResult() throws Exception {
        Excercise16 test = new Excercise16();

      //  List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise13Test.txt");


        String result = test.executeDance("abcde","s1,x3/4,pe/b",5);
        assertTrue("Expected order baedc "+"but found " + result,result.equals("baedc"));

        result = test.executeMultipleDanceMoves("abcde","s1,x3/4,pe/b",5,2);
        assertTrue("Expected order ceadb  "+"but found " + result,result.equalsIgnoreCase("ceadb"));

        test.executeTest();
    }

}
