package model;

public class LetterFactory {
    private ArregloBidimensional arreglo;
    public int variablesHorizontales;
    public int variablesVerticales;
    private char letraHorizontal;

    private boolean[][] definicion4;
    private boolean[][] definicion2;


    /** Creates a new instance of LetterFactory */
    public LetterFactory(ArregloBidimensional array, char letraHorizontal) {
        arreglo = array;
        this.letraHorizontal = letraHorizontal;

        //saca la equivalencia entre el numero de casillas y el numero de variables
        variablesHorizontales = (int)(Math.log10((double)arreglo.dimensionX)/Math.log10(2));
        variablesVerticales   = (int)(Math.log10((double)arreglo.dimensionY)/Math.log10(2));

        definicion4 = new boolean[4][2];        
        definicion2 = new boolean[2][1];
        definicionDeArreglos();
    }
    
    /**
     * En un mapa K, cada casilla tiene un minterm. Regresa el minterm de una casilla especifica sin simplificar.
     * Regresa vacio de no estar marcada la casilla como verdadera
     */
    public String getStringAtPosition(int fila, int columna) {
        String texto = new String();
        boolean[][] definicion;
        char letraVertical   = 'a';
        char letraHorizontal = this.letraHorizontal;
        
        if(arreglo.getPoint(fila,columna)) {    
            
            switch(arreglo.dimensionY) {
                case 2:
                    definicion = definicion2;
                    break;  
                case 4:
                    definicion = definicion4;
                    break;
                default:
                    definicion = definicion4;

            }              
            
            for(int i=0; i<variablesVerticales; i++) {

                texto = texto.concat(String.valueOf(letraVertical));

                if(!definicion[columna][i]) {
                    texto = texto.concat("'");
                }                          
                letraVertical++;
            }
            
            switch(arreglo.dimensionX) {
                case 2:
                    definicion = definicion2;
                    break;  
                case 4:
                    definicion = definicion4;
                    break;
                default:
                    definicion = definicion4;

            }
            
            for(int i=0; i<variablesHorizontales; i++) {
                texto = texto.concat(String.valueOf(letraHorizontal));
                if(!definicion[fila][i]) {
                    texto = texto.concat("'");
                }                          
                letraHorizontal++;
            }        
        } 
        return texto;
    }    
    
    /**
     * Arreglos que controlan el valor que toma una letra en celdas adyacentes
     */
    private void definicionDeArreglos() {

        definicion4[0][0] = false;
        definicion4[0][1] = false;
        definicion4[1][0] = false;        
        definicion4[1][1] = true;
        definicion4[2][0] = true;
        definicion4[2][1] = true;
        definicion4[3][0] = true;        
        definicion4[3][1] = false;
        
        definicion2[0][0] = false;
        definicion2[1][0] = true; 
    }
}
