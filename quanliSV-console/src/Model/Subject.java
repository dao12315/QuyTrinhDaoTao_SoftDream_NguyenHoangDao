package Model;


public class Subject {
    private String subjectId;
    private String subjectName;
    private int numberOfLessons;
    private double processRate;
    private double finalRate;

    public Subject(String subjectId, String subjectName, int numberOfLessons,
                   double processRate, double finalRate) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.numberOfLessons = numberOfLessons;
        this.processRate = processRate;
        this.finalRate = finalRate;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getNumberOfLessons() {
        return numberOfLessons;
    }

    public double getProcessRate() {
        return processRate;
    }

    public double getFinalRate() {
        return finalRate;
    }
}