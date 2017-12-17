package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Excercise17 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise17.txt");

        int result = getSpinlockValue(356);
        logger.debug("Result of excercise 17A: "+result);

        result = getValueAfterZero(356);
        logger.debug("Result of excercise 17B: "+result);

    }

    public int getSpinlockValue(int input) {
        LinkedList<Integer> values = new LinkedList<Integer>();
        values.add(0);
        int index =0;
        for(int i = 1; i < 2018; i++) {
            index = (index +input) % i;
            values.add(index,i);
            index++;
        }
        return values.get(index);
    }

    public int getValueAfterZero(int input){
        int index = 0;
        int result = 0;
        for(int i = 1; i < 50000000; i++) {
            index = (index +input) % i;
            if(index == 0) result = i;
            index++;
        }

        return result;
    }

}

