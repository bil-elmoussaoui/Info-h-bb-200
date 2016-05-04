package Model;


public class Counter {
    private int counter = 0;
    private int counterMax;

    public Counter(int counterMax) {
        this.counterMax = counterMax;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        if (counter > getCounterMax()) {
            counter = 0;
        }
        this.counter = counter;
    }

    public int getCounterMax() {
        return counterMax;
    }

    public void setCounterMax(int counterMax) {
        try {
            if (counterMax <= 0) {
                throw new Exception("Canno't have a counter max value as 0 or null");
            }
            this.counterMax = counterMax;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void up() {
        setCounter(counter + 1);
    }

    public void init() {
        setCounter(0);
    }

    public boolean isMax() {
        return (getCounter() == getCounterMax());
    }

}


