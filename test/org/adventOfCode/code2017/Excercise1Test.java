package org.adventOfCode.code2017;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class Excercise1Test  {

    @Test
    public void testResult() throws Exception {
        Excercise1 test = new Excercise1();


        long result = test.calculateCaptcha("1122");
        assertTrue("Captha of 1122  expect 3 "+"but found " + result,result == 3);
        result = test.calculateCaptcha("1111");
        assertTrue("Captha of 1111  expect 4 "+"but found " + result,result == 4);
        result = test.calculateCaptcha("1234");
        assertTrue("Captha of 1234  expect 0 "+"but found " + result,result == 0);
        result = test.calculateCaptcha("91212129");
        assertTrue("Captha of 91212129  expect 9"+"but found " + result,result == 9);

        result = test.calculateCircularCaptcha("1212");
        assertTrue("Captha of 1212  expect 6 "+"but found " + result,result == 6);
        result = test.calculateCircularCaptcha("1221");
        assertTrue("Captha of 1221  expect 0 "+"but found " + result,result == 0);
        result = test.calculateCircularCaptcha("123425");
        assertTrue("Captha of 123425  expect 4 "+"but found " + result,result == 4);
        result = test.calculateCircularCaptcha("123123");
        assertTrue("Captha of 123123  expect 12"+"but found " + result,result == 12);


        test.executeTest();
    }


}
