package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Excercise7 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise7.txt");

        Program result = getBottomProgram(input);
        logger.debug("Result of excercise 7A: "+result.getName());

        int balanceWeight = getBalanceWeight(input);
        logger.debug("Result of excercise 7B: "+balanceWeight);

    }

    public Program getBottomProgram(List<String> input)
    {
        List<Program> programs = getPrograms(input);

        Program program = programs.get(0);
        while (program.getParent() != null) program = program.getParent();

        return program;
    }

    public int getBalanceWeight(List<String> input)
    {
        int result = 0;
        Program subTower =  getBottomProgram(input);
        Program unbalancedTower = subTower;
        while(unbalancedTower != null) {
            Map<Integer, List<Program>> subTowerWeights = new HashMap<Integer, List<Program>>();
            for (Program p : subTower.getChilds()) {
                int totalWeight = p.getTotalWeight();
                if (subTowerWeights.containsKey(totalWeight))
                    subTowerWeights.get(totalWeight).add(p);
                else {
                    List newPList = new ArrayList<Program>();
                    newPList.add(p);
                    subTowerWeights.put(totalWeight, newPList);
                }
            }

            unbalancedTower = null;
            for (List<Program> subTowers : subTowerWeights.values())
                if (subTowers.size() == 1) unbalancedTower = subTowers.get(0);
            if(unbalancedTower != null) subTower = unbalancedTower;
            else
            {
                List<Program> siblings = subTower.getParent().getChilds();
                int i = 0;
                while(siblings.get(i).getTotalWeight() == subTower.getTotalWeight()) i++;
                int targetWeight = siblings.get(i).getTotalWeight();
                return subTower.getWeight() +targetWeight - subTower.getTotalWeight();
            }
        }


        return result;
    }


    private List<Program> getPrograms(List<String> input)
    {
        Pattern p1 = Pattern.compile("(\\p{Alpha}+)");
        Pattern p2 = Pattern.compile("(\\d+)");
        List<Program> programs = new ArrayList<Program>();
        for(String s : input) {
            Matcher m = p1.matcher(s);
            List<String> pList = new ArrayList<String>();
            while(m.find()) pList.add(m.group(1));
            Program program = new Program(pList.get(0));
            m = p2.matcher(s);
            if(!programs.contains(program)) programs.add(program);
            else program = programs.get(programs.indexOf(program));
            if(m.find()) program.setWeight(Integer.parseInt((m.group(1))));

            for(int i = 1; i < pList.size(); i++) {
                Program childProgram = new Program(pList.get(i));
                if(!programs.contains(childProgram)) programs.add(childProgram);
                else childProgram = programs.get(programs.indexOf(childProgram));

                childProgram.setParent(program);
                program.getChilds().add(childProgram);
            }

        }
        return programs;
    }


    public class Program
    {
        List<Program> childs = new ArrayList<Program>();
        String name;
        Program parent;
        int weight;

        public Program(String name)
        {
            this.name = name;
        }

        public List<Program> getChilds() {
            return childs;
        }

        public void setChilds(List<Program> childs) {
            this.childs = childs;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Program getParent() {
            return parent;
        }

        public void setParent(Program parent) {
            this.parent = parent;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getTotalWeight()
        {
            Integer result = 0;
            result = childs.stream().mapToInt( p -> p.getTotalWeight()).sum();
            result += getWeight();
            return result;
        }

        @Override
        public boolean equals(Object o) {
            return ((Program)o).getName().equals(getName());
        }

        @Override
        public int hashCode() {
            return getName().hashCode();
        }

        @Override
        public String toString() {
            return this.getName();
        }
    }

}

