package org.adventOfCode.code2017;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class Excercise17Test {

    @Test
    public void testResult() throws Exception {
        Excercise17 test = new Excercise17();

      //  List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise13Test.txt");


        int result = test.getSpinlockValue(3);
        assertTrue("Expected order 638   "+"but found " + result,result == 638 );

    /*    result = test.executeMultipleDanceMoves("abcde","s1,x3/4,pe/b",5,2);
        assertTrue("Expected order ceadb  "+"but found " + result,result.equalsIgnoreCase("ceadb"));*/

        test.executeTest();
    }

}
