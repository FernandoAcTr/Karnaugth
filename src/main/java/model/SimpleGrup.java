package model;

public class SimpleGrup {
    
    public int[] intBuffer;
    public boolean considerar;
    
    /** Creates a new instance of SimpleGrup */
    public SimpleGrup(int[] group) {
        intBuffer = group.clone();
        considerar = true;
    }
    
    public SimpleGrup(SimpleGrup x) {
        intBuffer = x.intBuffer.clone();
        considerar = x.considerar;
    }
    
    public void noConsiderar() {
        considerar = false;
    }
}