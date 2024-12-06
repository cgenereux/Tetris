public class GameSpeed {
    private int baseSpeed; //base speed
    private int currentSpeed; //current speed
    private int scoreThreshold; //how much to increase speed
    private double scalingFactor;

    public GameSpeed(){
        this.baseSpeed = 500; //the starting speed -> lower = faster
        this.currentSpeed = baseSpeed;
        this.scoreThreshold = 150; //every 150 points, increase speed
        this.scalingFactor = 100;
    }

    public void updateSpeed(int score) {
        int thresholdsCrossed = score / scoreThreshold;
        this.currentSpeed = (int) Math.max(baseSpeed - (scalingFactor * Math.log(thresholdsCrossed + 1)), 100);
    }

    public int getCurrentSpeed(){
        return currentSpeed;
    }

}
