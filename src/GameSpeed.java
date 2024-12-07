public class GameSpeed {
    private final int  baseSpeed; //base speed
    private final int scoreThreshold; //how much to increase speed
    private final double scalingFactor;
    private final int maxSpeed;
    private int currentSpeed; //current speed

    public GameSpeed() {
        this.baseSpeed = 500; //the starting speed -> lower = faster
        this.currentSpeed = baseSpeed;
        this.scoreThreshold = 150; // every 150 points, increase speed
        this.scalingFactor = 100;
        this.maxSpeed = 100;
    }

    // a logarithmic function for speed to make the game more playable
    public void updateSpeed(int score) {
        int thresholdsCrossed = score / scoreThreshold;
        // cap speed at maxSpeed
        this.currentSpeed = (int) Math.max(baseSpeed - (scalingFactor * Math.log(thresholdsCrossed + 1)), maxSpeed);
    }

    public int getCurrentSpeed(){
        return currentSpeed;
    }

}