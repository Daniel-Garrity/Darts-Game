public class Player {
    private int score;
    final private String name;
    public Player(String name,int startingScore){
        this.name=name;
        this.score= startingScore;
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
    public void applyScore(int points){
        score-=points;
    }
    public void resetScore(int score){
        this.score=score;
    }
}
