package model;

public class ClasificadorLetras {
    private ArregloBidimensional arreglo;
    public int variablesHorizontales;
    public int variablesVerticales;
    private final double log2 = Math.log10(2);

    private boolean[][] definicion4;
    private boolean[][] definicion2;


    /** Creates a new instance of ClasificadorLetras */
    public ClasificadorLetras(ArregloBidimensional array) {
        arreglo = array;

        //saca la equivalencia entre el numero de casillas y el numero de variables
        variablesHorizontales = (int)(Math.log10((double)arreglo.dimensionX)/log2);
        variablesVerticales   = (int)(Math.log10((double)arreglo.dimensionY)/log2);    

        definicion4 = new boolean[4][2];        
        definicion2 = new boolean[2][2];        
        definicionDeArreglos();
    }
    
    /** Este metodo me da los nombres a las variables que aparecen
     *  en la parte horizontal, justo arriba del grafico.
     *  @param posicion esta es la posicion del cuadrado a nombrar
     *  @return retorna un string que es la representacion de ese lugar.     
     */
    public String getHorizontalClasificacion(int posicion) {
        char letra = 'a';
        boolean[][] definicion;
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
        String texto = new String();
        
        for(int i=0; i<variablesVerticales; i++) {
            texto = texto.concat(String.valueOf(letra));
            if(!definicion[posicion][i]) {
                texto = texto.concat("'");
            }                          
            letra++;
        }
        return(texto);
    }
    
    /** Este metodo me da los nombres a las variables que aparecen
     *  en la parte vertical, justo al lado izquierdo del grafico.
     *  @param posicion esta es la posicion del cuadrado a nombrar
     *  @return retorna un string que es la representacion de ese lugar.     
     */    
    public String getVerticalClasificacion(int posicion) {
        char letra = 'c';
        boolean[][] definicion;
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
        
        String texto = new String();
        for(int i=0; i<variablesHorizontales; i++) {
            texto = texto.concat(String.valueOf(letra));
            if(!definicion[posicion][i]) {
                texto = texto.concat("'");
            }                          
            letra++;
        }
        return(texto);
    }    
    
    /**
     * En un mapa K, cada casilla tiene un minterm. Regresa el minterm de una casilla especifica sin simplificar.
     * Regresa vacio de no estar marcada la casilla como verdadera
     */
    public String getStringAtPosition(int fila, int columna) {
        String texto = new String();
        boolean[][] definicion;
        char letraVertical   = 'a';
        char letraHorizontal = 'c';
        
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
