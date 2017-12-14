package org.adventOfCode.code2017;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class Excercise10Test {

    @Test
    public void testResult() throws Exception {
        Excercise10 test = new Excercise10();

       // List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise9Test.txt");


        int result = test.getMultiplyValue("3,4,1,5",4);
        assertTrue("Expected value 12 "+"but found " + result,result == 12);

        String hashValue = test.getDenseHash("");
        assertTrue("Empy hash: " + hashValue,hashValue.equals("a2582a3a0e66e6e86e3812dcb672a272"));
        hashValue = test.getDenseHash("AoC 2017");
        assertTrue("AoC 2017 hash: " + hashValue,hashValue.equals("33efeb34ea91902bb2f59c9920caa6cd"));
        hashValue = test.getDenseHash("1,2,3");
        assertTrue("1,2,3 hash: " + hashValue,hashValue.equals("3efbe78a8d82f29979031a4aa0b16a9d"));

        test.executeTest();
    }

}
