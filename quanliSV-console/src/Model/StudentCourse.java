package Model;


public class StudentCourse {
    private Subject subject;
    private Double processScore;
    private Double finalScore;

    public StudentCourse(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public Double getProcessScore() {
        return processScore;
    }

    public void setProcessScore(Double processScore) {
        this.processScore = processScore;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public boolean hasScore() {
        return processScore != null && finalScore != null;
    }

    public double calculateTotalScore() {
        if (!hasScore()) {
            return 0;
        }

        return processScore * subject.getProcessRate()
                + finalScore * subject.getFinalRate();
    }

    public String getResult() {
        if (!hasScore()) {
            return "Chua co diem";
        }

        return calculateTotalScore() >= 4 ? "Qua mon" : "Truot mon";
    }
}