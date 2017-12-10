package org.adventOfCode.code2017;

import org.adventOfCode.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class Excercise7Test {

    @Test
    public void testResult() throws Exception {
        Excercise7 test = new Excercise7();

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise7Test.txt");

        String result = test.getBottomProgram(input).getName();
        assertTrue("Top of tower tknk expected "+"but found " + result,result.equals("tknk"));

        int balanceWeight = test.getBalanceWeight(input);
        assertTrue("expected a weight of 60 "+"but found " + balanceWeight,balanceWeight == 60);

        test.executeTest();
    }


}
