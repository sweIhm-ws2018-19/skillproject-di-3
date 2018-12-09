package verkocht.model;

/*
 * 
 */
public class Timer {
    long startTime;
    
    
    public Timer() {
        startTime = System.currentTimeMillis();
    }
    
    int getCurrentTimeRunning() {
        return (int) (System.currentTimeMillis() - startTime);
    }
    
    
}
