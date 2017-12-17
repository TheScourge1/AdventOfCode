package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Excercise16 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise16.txt");

        String result = executeDance("abcdefghijklmnop",input.get(0),16);
        logger.debug("Result of excercise 16A: "+result);

        result = executeMultipleDanceMoves("abcdefghijklmnop",input.get(0),16,1000000000);
        logger.debug("Result of excercise 15B: "+result);

    }

    public String executeDance(String startPositions,String input,int size){
        String result = startPositions;
        if(size < 16) result = result.substring(0,size);

        String[] inputCommands = input.split(",");

        for(int i = 0; i< inputCommands.length;i++){
            String command = inputCommands[i];
            if(command.startsWith("s")){
                int index = Integer.parseInt(command.substring(1));
                result = result.substring(result.length()-index,result.length()) + result.substring(0,result.length()-index);
            }
            else if(command.startsWith("x")){
                String[] indexes = command.substring(1).split("/");
                int indexA = Integer.parseInt(indexes[0]);
                int indexB = Integer.parseInt(indexes[1]);
                if(indexA > indexB){
                    int temp = indexA;
                    indexA = indexB;
                    indexB = temp;
                }
                result = result.substring(0,indexA)
                        + result.substring(indexB,indexB+1)
                        + result.substring(indexA+1,indexB)
                        + result.substring(indexA,indexA+1)
                        + result.substring(indexB+1,result.length());
            }
            else if(command.startsWith("p")){
                String[] indexes = command.substring(1).split("/");
                result = result.replace(indexes[0],"-");
                result = result.replace(indexes[1],indexes[0]);
                result = result.replace("-",indexes[1]);
            }
        }

        return result;
    }

    public String executeMultipleDanceMoves(String startPositions,String input,int size,int danceCount) {
        String result = executeDance(startPositions,input,size);
        Set<String> newMoves = new HashSet<>();

        while(! newMoves.contains(result)){
            newMoves.add(result);
            if(newMoves.size() == danceCount) return result;
            result = executeDance(result,input,size);
        }
        logger.debug("Duplicate found after "+newMoves.size()+ " loops: "+result);

        int movesToComplete = danceCount % newMoves.size();

        for(int i = 0; i < movesToComplete-1; i++){
            result = executeDance(result,input,size);
            if(i% 10000 == 0 )logger.debug(i);
        }

        return result;
    }

}

