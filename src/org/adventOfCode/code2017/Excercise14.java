package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;

public class Excercise14 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise14.txt");

        int result = countUsedSquares("amgozmfv");
        logger.debug("Result of excercise 14A: "+result);

        result = countRegions("amgozmfv");
        logger.debug("Result of excercise 14B: "+result);

    }

    public int countUsedSquares(String input){
        int result = 0;
        List<String> hashes = generateHashes(input);

        result = (int)hashes.stream().mapToLong(
                s -> s.chars().filter( c -> c == '1').count()).sum();

        return result;
    }

    public int countRegions(String input)
    {
        List<String> hashes = generateHashes(input);
        int result = 0;

        for(int i = 0; i < hashes.size(); i++) {
            for (int j = 0; j < hashes.get(0).length(); j++) {
                if(hashes.get(i).substring(j,j+1).equals("1")) {
                    setAdjacentToZero(hashes, i, j);
                    result++;
                }
            }
        }

        return result;
    }

    private void setAdjacentToZero(List<String> hashes,int x,int y){
        String currentString = hashes.get(x);
        StringBuilder newString = new StringBuilder();
        if(y > 0) newString.append(currentString.substring(0,y));
        newString.append("0") ;
        if(y < currentString.length()) newString.append(currentString.substring(y+1,currentString.length()));
        hashes.set(x,newString.toString());

        if(x > 0 && hashes.get(x-1).substring(y,y+1).equals("1")) setAdjacentToZero(hashes,x-1,y);
        if(x < hashes.size()-1 && hashes.get(x+1).substring(y,y+1).equals("1")) setAdjacentToZero(hashes,x+1,y);
        if(y > 0 && hashes.get(x).substring(y-1,y).equals("1")) setAdjacentToZero(hashes,x,y-1);
        if(y < currentString.length()-1 && hashes.get(x).substring(y+1,y+2).equals("1")) setAdjacentToZero(hashes,x,y+1);
    }

    private List<String> generateHashes(String input)
    {
        Excercise10 ex10 = new Excercise10();
        List<String> hashes = new ArrayList<String>();

        for(int i = 0;i<128;i++)
        {
            String hash = ex10.getDenseHash(input+"-"+i);
            StringBuilder binaryHash = new StringBuilder();
            hash.chars().forEach( c -> {
                int intval = Integer.parseInt(Character.toString((char)c),16);
                if(intval >= 8) {binaryHash.append(1);intval%=8;} else  binaryHash.append(0);
                if(intval >= 4) {binaryHash.append(1);intval%=4;} else  binaryHash.append(0);
                if(intval >= 2) {binaryHash.append(1);intval%=2;} else  binaryHash.append(0);
                if(intval >= 1) {binaryHash.append(1);} else  binaryHash.append(0);
            });
            hashes.add(binaryHash.toString());
        }

        return hashes;
    }

}

