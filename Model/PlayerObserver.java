package Model;

interface PlayerObserver {
    public void attach(Observer o);

    public void notifyObservers();

}
