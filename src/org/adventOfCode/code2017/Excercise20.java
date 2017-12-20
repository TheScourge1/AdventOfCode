package org.adventOfCode.code2017;

import org.adventOfCode.GenericExcercise;
import org.adventOfCode.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Excercise20 extends GenericExcercise {

    @Override
    public void executeTest() throws Exception {

        List<String> input = FileUtil.readStringsFromFile("resource/2017/Excercise20.txt");

        int result = getClosestParticle(input);
        logger.debug("Result of excercise 20A: "+result);

        result = getNonCollides(input);
        logger.debug("Result of excercise 20B: "+result);

    }

    public int getClosestParticle(List<String> input) throws Exception{
        List<Particle> particleList = getParticleList(input);
        List<Particle> sortedList = particleList.stream()
                .sorted( (p1,p2) -> p1.compareMinDistance(p2)).collect(Collectors.toList());
        return particleList.indexOf(sortedList.get(0));
    }

    public int getNonCollides(List<String> input) throws Exception{
        List<Particle> particleList = getParticleList(input);

        for(int i = 1; i< 1000; i++){
            particleList.stream() //updating velocities
                    .forEach( p -> {
                        p.v[0]+=p.a[0];
                        p.v[1]+=p.a[1];
                        p.v[2]+=p.a[2];});


            particleList.stream().forEach(p -> p.p[0]+=p.v[0]); //updating xPositions
            removeDuplicates(particleList);
            particleList.stream().forEach(p -> p.p[1]+=p.v[1]); //updating yPositions
            removeDuplicates(particleList);
            particleList.stream().forEach(p -> p.p[2]+=p.v[2]); //updating zPositions
            removeDuplicates(particleList);
        }

        return particleList.size();
    }

    private List<Particle> getParticleList(List<String> input) throws Exception{
        Pattern p = Pattern.compile("[pva]..(-?\\d+),(-?\\d+),(-?\\d+).");
        List<Particle> particleList = new ArrayList<Particle>();
        for(String s:input){
            String[] parts = s.split(", ");
            Particle particle = new Particle();
            Matcher m = p.matcher(parts[0]);
            if(m.find()) for (int i = 0; i < 3; i++) particle.p[i] = Integer.parseInt(m.group(i+1));
            else throw new Exception("Invalid input pattern found: " + parts[0]);

            m = p.matcher(parts[1]);
            if(m.find()) for (int i = 0; i < 3; i++) particle.v[i] = Integer.parseInt(m.group(i+1));
            else throw new Exception("Invalid input pattern found: " + parts[1]);

            m = p.matcher(parts[2]);
            if(m.find()) for (int i = 0; i < 3; i++) particle.a[i] = Integer.parseInt(m.group(i+1));
            else throw new Exception("Invalid input pattern found: " + parts[2]);

            particleList.add(particle);
        }
        return particleList;
    }

    private void removeDuplicates(List<Particle> particleList){
        List<Particle> removeList = new ArrayList<>();
        for(int i = 0; i < particleList.size(); i++){
            for(int j = i+1; j < particleList.size();j++)
                if(particleList.get(i).equalLocation(particleList.get(j))){
                    removeList.add(particleList.get(i));
                    removeList.add(particleList.get(j));
                }
        }

        particleList.removeAll(removeList);
    }


    private class Particle{
        int[] p = new int[3];
        int[] v = new int[3];
        int[] a = new int[3];

        public int getAbsoluteA(){
            return Math.abs(a[0]) + Math.abs(a[1]) + Math.abs(a[2]);
        }

        public int getAbsoluteV(){
            return Math.abs(v[0]) + Math.abs(v[1]) + Math.abs(v[2]);
        }

        public int getAbsoluteP(){
            return Math.abs(p[0]) + Math.abs(p[1]) + Math.abs(p[2]);
        }

        public int compareMinDistance(Particle p1){
            if(getAbsoluteA() != p1.getAbsoluteA()) return Integer.compare(this.getAbsoluteA() , p1.getAbsoluteA());
            else if (getAbsoluteV() != p1.getAbsoluteV()) return Integer.compare(this.getAbsoluteV() , p1.getAbsoluteV());
            else  return Integer.compare(this.getAbsoluteP() , p1.getAbsoluteP());
        }

        public boolean equalLocation(Particle p1){
            return p1.p[0] == this.p[0] && p1.p[1] == this.p[1] && p1.p[2] == this.p[2];
        }
    }
}

