public class GameSpeed {
    private int baseSpeed; //base speed
    private int currentSpeed; //current speed
    private int scoreThreshold; //how much to increase speed
    private int speedIncrement; //decrease delay

    public GameSpeed(){
        this.baseSpeed = 500; //the starting speed -> lower = faster
        this.currentSpeed = baseSpeed;
        this.scoreThreshold = 150; //every 150 points, increase speed
        this.speedIncrement = 50; //reduce the delay by this much
    }

    public void updateSpeed(int score){
        int thresholdsCrossed = score / scoreThreshold;
        //make sure it won't go down 100 ms
        this.currentSpeed = Math.max(baseSpeed - (thresholdsCrossed * speedIncrement), 100);
    }

    public int getCurrentSpeed(){
        return currentSpeed;
    }



}
