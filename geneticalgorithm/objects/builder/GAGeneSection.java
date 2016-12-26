
package lucaspellegrinelli.ai.geneticalgorithm.objects.builder;

public class GAGeneSection {
    private String sectionName;
    private double minValue;
    private double maxValue;
    
    /**
     * 
     * @param sectionName The name of the gene section
     * @param minValue The minimum value this section can assume
     * @param maxValue The maximum value this sections can assume
     */
    public GAGeneSection(String sectionName, double minValue, double maxValue){
        this.sectionName = sectionName;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }
}
