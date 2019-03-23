package model;

public class SimpleGrup {
    
    public int[] intBuffer;
    public boolean considerar;
    
    /** Creates a new instance of SimpleGrup */
    public SimpleGrup(int[] group) {
        intBuffer = group.clone();
        considerar = true;
    }    
    
    public SimpleGrup(int[] group, boolean estado) {
        intBuffer = group.clone();
        considerar = estado;
    }  
    
    public SimpleGrup(SimpleGrup x) {
        intBuffer = x.intBuffer.clone();
        considerar = x.considerar;
    }
    
    public void noConsiderar() {
        considerar = false;
    }
}