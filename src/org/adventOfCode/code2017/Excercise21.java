package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Excercise21 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise21.txt");

        int result = getPixelCount(input,5);
        logger.debug("Result of excercise 21A: "+result);

        result = getPixelCount(input,18);
        logger.debug("Result of excercise 21B: "+result);

    }

    public int getPixelCount(List<String> input,int itterations) throws Exception{
        int result = 0;
        List<String> matrix = Arrays.asList(".#.","..#","###");
        Map<List<String>,List<String>> rulesMap = loadRules(input);
        addRotations(rulesMap);
        addFlips(rulesMap);
        addRotations(rulesMap);
        addFlips(rulesMap);

        for(int itt = 0; itt < itterations;itt++){
            int size = (matrix.size()%2 ==0)?2:3;
            List<List<List<String>>> subMatrixes = new ArrayList<>();
            for(int i = 0; i< matrix.size()/size;i++){
                List<List<String>> row = new ArrayList<>();
                subMatrixes.add(row);
                for(int j = 0; j < matrix.size()/size;j++){
                    List<String> subMatrix = new ArrayList<>();
                    for(int ii = 0; ii < size; ii++)
                        subMatrix.add(matrix.get(i*size+ii).substring(j*size,j*size+size));
                    List<String> convertedMatrix = convert(subMatrix,rulesMap);
                    row.add(convertedMatrix);
                }
            }

            // converting back to one matrix

            List<String> newMatrix = new ArrayList<>();
            for(int i = 0; i < subMatrixes.size();i++) {
                for(int ii = 0;ii<subMatrixes.get(i).get(0).size(); ii++) {
                    // also itterate over the submatrix rows to fetch all rows
                    StringBuilder b = new StringBuilder();
                    for (int j = 0; j < subMatrixes.size(); j++) {
                        b.append(subMatrixes.get(i).get(j).get(ii));
                    }
                    newMatrix.add(b.toString());
                }
            }
            matrix = newMatrix;
          //  logger.debug("");
          //  for(String s : matrix) logger.debug(s);
        }

        for(String s : matrix) {
            result +=s.chars().filter( c -> c == '#').count();
        }

        return result;
    }

    public Map<List<String>,List<String>> loadRules(List<String> input){
        Map<List<String>,List<String>> result = new HashMap<>();
        Pattern p = Pattern.compile("(.*?) => (.*)");
        for(String s : input){
            List<Integer> rule = new ArrayList<>();
            Matcher m = p.matcher(s);
            if(m.find());
            String startRule = m.group(1);
            String endRule = m.group(2);
            result.put(Arrays.asList(startRule.split("/")),Arrays.asList(endRule.split("/")));
        }

        return result;
    }

    public void addRotations(Map<List<String>,List<String>> rules){
        Map<List<String>,List<String>> rotations = new HashMap<>();
        for(List<String> rule: rules.keySet()){
            List<String> oriRule = rule;
            for(int rCount = 0; rCount < 4; rCount++) { // rotate three times
                List<String> newRule = new ArrayList<>();
                for(int i = 0;i <rule.size(); i++){
                    StringBuilder row = new StringBuilder();
                    for(int j = 0; j < rule.size();j++){
                        row.append(rule.get(j).substring(i,i+1));
                    }
                    newRule.add(row.toString());
                }
                rotations.put(newRule,rules.get(oriRule));
                rule = newRule;
            }
        }

        for(List<String> rule : rotations.keySet()) rules.put(rule,rotations.get(rule));
    }

    public void addFlips(Map<List<String>,List<String>> rules) {
        Map<List<String>, List<String>> flips = new HashMap<>();
        for (List<String> rule : rules.keySet()) {
            List<String> newRule = new ArrayList<>();
            for (int i = 1; i <= rule.size(); i++) { // vertical flip
                newRule.add(rule.get(rule.size() - i));
            }
            flips.put(newRule, rules.get(rule));

            newRule = new ArrayList<>(); // horizontal flip
            for (int i = 0; i < rule.size(); i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 1; j <= rule.size(); j++) {
                    int size = rule.get(i).length();
                    row.append(rule.get(i).substring(size - j, size - j + 1));
                }
                newRule.add(row.toString());
            }
            flips.put(newRule, rules.get(rule));
        }

        for (List<String> rule : flips.keySet()) rules.put(rule, flips.get(rule));
    }

    public List<String> convert(List<String> inputArray, Map<List<String>,List<String>> rules)throws Exception{
        List<String> ruleFound = null;
        for(List<String> rule : rules.keySet()){
            ruleFound = rule;
            for(int i = 0; i < inputArray.size();i++){
                if(!inputArray.get(i).equals(rule.get(i))) {
                    ruleFound = null;
                    break;
                }
            }
            if(ruleFound != null) return rules.get(ruleFound);
        }

        StringBuilder b = new StringBuilder();
        inputArray.stream().forEach(s -> b.append(s+"/"));
        throw new Exception("No rule found for: "+b.toString());
    }
}

