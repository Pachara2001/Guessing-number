import java.util.concurrent.ThreadLocalRandom;

// Pachara Srisomboonchote 6210401295
// Peerawich Tantavachkij 6210400167

public class GuessNum {
    private int target,count;


    public GuessNum() {
        this.target = ThreadLocalRandom.current().nextInt(0, 100 + 1);;
    }

    public int guess(int input){
        count++;
        if(input<0||input>100) return 101;
        if(input < target){
            return -1;
        }
        if(input > target){
            return 1;
        }
        else return 0;
    }

    public int getCount() {
        return count;
    }

    public int getTarget() {
        return target;
    }
}
