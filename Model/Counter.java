package Model;


public class Counter {
    private int counter = 0;
    private int counterMax;

    public Counter(int counterMax){
        this.counterMax = counterMax;
    }

    public int getCounter(){
        return this.counter;
    }

    public int getCounterMax(){
        return this.counterMax;
    }

    public void setCounter(int counter){
        if(counter > this.getCounterMax()){
            counter = 0;
        }
        this.counter = counter;
    }

    public void up(){
        this.setCounter(this.counter + 1);
    }

    public void init(){
        this.setCounter(0);
    }

    public void setCounterMax(int counterMax){
        try {
            if (counterMax <= 0) {
                throw new Exception("Canno't have a counter max value as 0 or null");
            }
            this.counterMax = counterMax;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}


