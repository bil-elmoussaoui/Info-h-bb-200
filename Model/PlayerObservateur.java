package Model;

/**
 * Created by Bejamin on 05-05-16.
 */
interface PlayerObservateur {
    public void attach(IObservateur o);
    public void notifyObservers();

}
