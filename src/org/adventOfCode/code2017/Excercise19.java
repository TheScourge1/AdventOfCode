package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;
import java.util.regex.Pattern;

public class Excercise19 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise19.txt");

        String result = findPath(input);
        logger.debug("Result of excercise 19A: "+result);

        int result2 = getStepCount(input);
        logger.debug("Result of excercise 19B: "+result2);

    }

    Pattern isChar = Pattern.compile("[A-Z]");
    Pattern isRoute = Pattern.compile("-|\\|");

    public String findPath(List<String> input) throws Exception{
        String path = walkMaze(input);
        StringBuilder result = new StringBuilder();
        Arrays.asList(path.split("")).stream().filter(str -> isChar.matcher(str).matches()).forEach(str -> result.append(str));

        return result.toString();
    }

    public int getStepCount(List<String> input)throws Exception{
        String path = walkMaze(input);
        return path.length();
    }

    private String walkMaze(List<String> input) throws Exception{
        StringBuilder result = new StringBuilder();

        List<List<String>> inputList = new ArrayList<>();
        input.stream().forEach( s -> inputList.add(new ArrayList(Arrays.asList(s.split("")))));

        int startIndex = -1;
        for(int i = 1; i < inputList.get(0).size();i++) if(inputList.get(0).get(i).equals("|"))startIndex = i;
        if(startIndex == -1) throw new Exception("Start location not found!");

        int maxSize = inputList.stream().mapToInt(l -> l.size()).max().getAsInt();
        for(List<String> l : inputList)
            while(l.size() < maxSize) l.add(" ");

        int[] direction = {0,1};
        int[] loc = new int[2];
        loc[0] = startIndex;
        loc[1] = 0;

        while(direction[0] != 0 || direction[1] != 0){
            String str = inputList.get(loc[1]).get(loc[0]);

            //  logger.debug(""+loc[0]+","+loc[1]+ " -> "+str);
            if(isChar.matcher(str).matches()){
               // result.append(str);
                loc[0]+=direction[0];
                loc[1]+=direction[1];
            }
            else if(isRoute.matcher(str).matches()){
                loc[0]+=direction[0];
                loc[1]+=direction[1];
            }
            else if(str.equals("+")){
                if(direction[0] == 1 || direction[0] == -1){
                    if(loc[1]-1 > 0 && (isChar.matcher(inputList.get(loc[1]-1).get(loc[0])).matches()
                            || isRoute.matcher(inputList.get(loc[1]-1).get(loc[0])).matches())){
                        direction[1] = -1;
                        loc[1]--;
                    }
                    else if(loc[1]+1 < inputList.size() && (isChar.matcher(inputList.get(loc[1]+1).get(loc[0])).matches()
                            || isRoute.matcher(inputList.get(loc[1]+1).get(loc[0])).matches())){
                        direction[1] = 1;
                        loc[1]++;
                    }
                    else direction[1] = 0;
                    direction[0] = 0;
                }
                else{
                    if(loc[0]-1 > 0 && (isChar.matcher(inputList.get(loc[1]).get(loc[0]-1)).matches()
                            ||isRoute.matcher(inputList.get(loc[1]).get(loc[0]-1)).matches())){
                        direction[0] = -1;
                        loc[0]--;
                    }
                    else if(loc[0]+1 < inputList.get(loc[1]).size() && (isChar.matcher(inputList.get(loc[1]).get(loc[0]+1)).matches()
                            || isRoute.matcher(inputList.get(loc[1]).get(loc[0]+1)).matches())){
                        direction[0] = 1;
                        loc[0]++;
                    }
                    else direction[0] = 0;
                    direction[1] = 0;
                }
            }
            else if(str.equals(" ")) break;
            else throw new Exception("Unexpected char found:" + str);

            result.append(str);
        }

        return result.toString();
    }

}

