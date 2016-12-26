package lucaspellegrinelli.ai.geneticalgorithm.objects.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GAGeneSection;

public class GeneSet {
    private final HashMap<String, Double> geneMap = new HashMap<>();
    private static List<GAGeneSection> geneSections;
    
    public GeneSet(){       
        for(GAGeneSection section : geneSections){
            geneMap.put(section.getSectionName(), randomInRange(section.getMinValue(), section.getMaxValue()));
        }
    }
    
    public static void setGeneSections(List<GAGeneSection> geneSection){
        geneSections = geneSection;
    }
    
    private double randomInRange(double min, double max){
    	return min + (Math.random() * (max - min));
    }
    
    public void setSectionValue(String sectionName, double value){
        geneMap.replace(sectionName, value);
    }
    
    public double getSectionValue(String sectionName){
        return geneMap.get(sectionName);
    }
    
    int getSectionCount(){
        return geneMap.size();
    }
    
    public List<String> getSectionNames(){
        List<String> names = new ArrayList<>();
        names.addAll(geneMap.keySet());
        return names;
    }
    
    public void replaceGeneWithRandomValue(String sectionName){
        for(GAGeneSection section : geneSections){
            if(section.getSectionName().equals(sectionName)){
                geneMap.replace(sectionName, randomInRange(section.getMinValue(), section.getMaxValue()));
                break;
            }
        }
    }
}
