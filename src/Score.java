public class Score {
    private int score = 0;

    public void addPoints(int linesCleared) {
        switch (linesCleared) {
            case 1:
                score += 100; // 1 line cleared
                break;
            case 2:
                score += 300; // 2 lines cleared
                break;
            case 3:
                score += 500; // 3 lines cleared
                break;
            case 4:
                score += 800; // 4 lines cleared
                break;
        }
    }
    public int getScore() {
        return score;
    }
}
