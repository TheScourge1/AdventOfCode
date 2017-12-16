package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class Excercise15 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise14.txt");

        long result = countPairs(699,124);
        logger.debug("Result of excercise 15A: "+result);

        result = countImprovedPairs(699,124);
        logger.debug("Result of excercise 15B: "+result);

    }

    final long FACTOR_A =  16807;
    final long FACTOR_B =  48271;
    final long DEVIDOR = 2147483647;

    final long CYCLES = 40000000;
    final long IMPROVED_CYCLES = 5000000;


    public long countPairs(long a,long b) {

        long result = 0;
        long aValue = a;
        long bValue = b;

        for(int i = 0; i < CYCLES; i++){
            aValue *= FACTOR_A;
            bValue *= FACTOR_B;

            aValue %= DEVIDOR;
            bValue %= DEVIDOR;

            if(lastBinaryEquals(aValue,bValue)) result++;

        }
        return result;
    }

    public long countImprovedPairs(long a,long b){
        long result = 0;
        long aValue = a;
        long bValue = b;

        int count = 0;
        while(count< IMPROVED_CYCLES){
            do{
                aValue *= FACTOR_A;
                aValue %= DEVIDOR;
            }while(aValue % 4 != 0);

            do{
                bValue *= FACTOR_B;
                bValue %= DEVIDOR;
            }while(bValue % 8 != 0);

            if(lastBinaryEquals(aValue,bValue)) result++;
            count++;
        }
        return result;
    }

    private boolean lastBinaryEquals(long a, long b){
        int factor = 1;
        for(int i = 1; i<= 16; i++) {
            factor = factor*2;
            if(a%factor != b%factor) return false;
            a -= factor * a%factor;
            b -= factor * b%factor;
        }
        return true;
    }

}

