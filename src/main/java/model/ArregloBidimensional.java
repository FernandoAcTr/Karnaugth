package model;

public class ArregloBidimensional {
    private boolean[][] arreglo;
    public int dimensionX;
    public int dimensionY;
    
    /** Creates a new instance of ArregloBidimensional */
    public ArregloBidimensional(int casillasHorizontales, int casillasVerticales) {
        dimensionX = casillasHorizontales;
        dimensionY = casillasVerticales;
        arreglo = new boolean[casillasHorizontales][casillasVerticales];
        
        for(int i=0;i<casillasVerticales; i++) {
            for(int j=0;j<casillasHorizontales ; j++) {
                arreglo[j][i] = false;            
            }
        }
    }

    public void setPoint(int varHorizontal, int varVertical) {
        arreglo[varHorizontal][varVertical] = true; 
    }

    public void resetPoint(int varHorizontal, int varVertical) {
        arreglo[varHorizontal][varVertical] = false; 
    }

    public boolean getPoint(int varHorizontal, int varVertical) {
        return(arreglo[varHorizontal][varVertical]);
    }

    public void setArreglo(boolean[][] arreglo) {
        this.arreglo = arreglo;
    }
}
