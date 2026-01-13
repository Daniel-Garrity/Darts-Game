public class Player {
    private int score;//tracks score
    private String name;//players name
    private int turnsTaken;//used to get three dart average
    private int dartsThrown;//used to how many darts were used to win the leg
    private int totalScored;//also used for three dart average
    private int highestScore;//tracks players highest score
    public Player(String name,int startingScore){
        this.name=name;
        this.score= startingScore;
        this.turnsTaken=0;
        this.dartsThrown=0;
        this.highestScore=0;
        this.totalScored = 0;

    }
    public String getName() {
        return name;
    }
    //Gets the score
    public int getScore(){
        return score;
    }
    //Sets the score
    public void setScore(int score){
        this.score=score;
    }
    public void resetScore(int previousScore){
        this.score=previousScore;
    }

    /*
    Methods below for game statistics
    */

    //helper method to help track stats
    public void applyTurn(int turnScore){
    turnsTaken++;
    dartsThrown += 3;
    totalScored += turnScore;
    highestScore = Math.max(highestScore, turnScore);
    score -= turnScore;
}


    //method to get three dart average
    public double getThreeDartAverage() {
    if (turnsTaken == 0) return 0;
    return (double) totalScored / turnsTaken;
}

public int getTurnsTaken() {
        return turnsTaken;
    }

    public int getDartsThrown() {
        return dartsThrown;
    }

    public int getHighestScore() {
        return highestScore;
    }
}
