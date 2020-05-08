package model;

import java.util.*;
/**
 *
 * @author Francisco Olivares
 */
public class McCluskey {
    private int[] intBuffer;
    private static ArregloBidimensional arreglo;  
    private ArrayList<SimpleGrup> listaGrupos;
    private int nVariablesHorizontales;
    private int nVariablesVerticales;
    private char letraHorizontal;
    
    /** Creates a new instance of McCluskey */
    public McCluskey(char letraHorizontal) {

        this.letraHorizontal = letraHorizontal;
        
        //saca la equivalencia entre el numero de casillas y el numero de variables
        nVariablesHorizontales = (int)(Math.log10((double)arreglo.dimensionX)/Math.log10(2));
        nVariablesVerticales   = (int)(Math.log10((double)arreglo.dimensionY)/Math.log10(2));  
        
        intBuffer = new int[6];
        
        int[] shortHorizontalBuffer = new int[3];
        int[] shortVerticalBuffer = new int[3];
        
        listaGrupos = new ArrayList<SimpleGrup>();        
        
        for(int i=0; i<arreglo.dimensionY; i++) {//columnas
            for(int j=0; j<arreglo.dimensionX; j++) {//filas
                
                if(arreglo.getPoint(j,i)) {
                    shortVerticalBuffer   = getArrayFromInt(i);
                    shortHorizontalBuffer = getArrayFromInt(j);
                    intBuffer = pegarShortBuffers(shortHorizontalBuffer,shortVerticalBuffer);
                    listaGrupos.add(new SimpleGrup(intBuffer));               
                }
                
            }
        }
        
        ArrayList<SimpleGrup> listaGruposTraspaso = new ArrayList<SimpleGrup>();
        
        for(int j=0; j < listaGrupos.size(); j++) {
            for(int i=0; i < listaGrupos.size(); i++) {
                
                if(listaGrupos.get(j).considerar)// si los 2 son falsos no los considero
                    
                    if(1 == revisaDiferencias6(listaGrupos.get(j).intBuffer, listaGrupos.get(i).intBuffer)) {
                        
                        listaGruposTraspaso.add(new SimpleGrup(postRevisaDiferencias6(listaGrupos.get(j).intBuffer,
                                listaGrupos.get(i).intBuffer)));
                        
                        listaGrupos.get(j).noConsiderar();
                        listaGrupos.get(i).noConsiderar();                                            

                    }
            }
            
            if(listaGrupos.get(j).considerar) {
                listaGruposTraspaso.add(new SimpleGrup(listaGrupos.get(j).intBuffer));
                listaGrupos.get(j).noConsiderar();
            }            
        }        
        
        listaGrupos.clear();
        listaGrupos = listaGruposTraspaso;
        
        for(int i=0; i<listaGrupos.size() ; i++) {
            if(nVariablesHorizontales == 2)
                listaGrupos.get(i).intBuffer[0] = -1;
            if(nVariablesHorizontales == 1) {
                listaGrupos.get(i).intBuffer[1] = -1;
                listaGrupos.get(i).intBuffer[0] = -1;
            }
            if(nVariablesVerticales == 2)
                listaGrupos.get(i).intBuffer[3] = -1;
            if(nVariablesVerticales == 1) {
                listaGrupos.get(i).intBuffer[3] = -1;
                listaGrupos.get(i).intBuffer[4] = -1;
            }
        }
        
        //---------------------------------------------------
        // AQUI COMIENZA LO NUEVO
        //---------------------------------------------------
        for(int runge=0;runge<10;runge++) {  
            listaGruposTraspaso = new ArrayList<SimpleGrup>();
            for(int j=0; j < listaGrupos.size(); j++) {
                for(int i=0; i < listaGrupos.size(); i++) {
                    if(listaGrupos.get(j).considerar)// si los 2 son falsos no los considero
                        if(1 == revisaDiferencias6(listaGrupos.get(j).intBuffer,
                                                   listaGrupos.get(i).intBuffer)) {
                            listaGruposTraspaso.add(new SimpleGrup(
                                    postRevisaDiferencias6(listaGrupos.get(j).intBuffer,
                                                           listaGrupos.get(i).intBuffer)));
                            listaGrupos.get(j).noConsiderar();
                            listaGrupos.get(i).noConsiderar();
                        }
                }
                if(listaGrupos.get(j).considerar) {
                    listaGruposTraspaso.add(new SimpleGrup(listaGrupos.get(j).intBuffer));
                    listaGrupos.get(j).noConsiderar();
                }            
            }
            listaGrupos.clear();
            listaGrupos = listaGruposTraspaso;

            for(int i=0; i<listaGrupos.size() ; i++) {
                if(nVariablesHorizontales == 2)
                    listaGrupos.get(i).intBuffer[0] = -1;
                if(nVariablesHorizontales == 1) {
                    listaGrupos.get(i).intBuffer[1] = -1;
                    listaGrupos.get(i).intBuffer[0] = -1;
                }
                if(nVariablesVerticales == 2)
                    listaGrupos.get(i).intBuffer[3] = -1;
                if(nVariablesVerticales == 1) {
                    listaGrupos.get(i).intBuffer[3] = -1;
                    listaGrupos.get(i).intBuffer[4] = -1;
                }
            }
        }

        for(int runge=0;runge<10;runge++) {              
            listaGruposTraspaso = new ArrayList<SimpleGrup>();
            for(int j=0; j < listaGrupos.size(); j++) {
                for(int i=0; i < listaGrupos.size(); i++) {
                    if(true) //listaGrupos.get(i).considerar)// si los 2 son falsos no los considero
                        if(0 == revisaDiferencias6(listaGrupos.get(j).intBuffer,
                                                   listaGrupos.get(i).intBuffer)) {
                            listaGruposTraspaso.add(new SimpleGrup(listaGrupos.get(j)));
                            listaGrupos.get(j).noConsiderar();
                            listaGrupos.get(i).noConsiderar();
                        }
                }
		if(listaGrupos.get(j).considerar) {
                    listaGruposTraspaso.add(new SimpleGrup(listaGrupos.get(j).intBuffer));
                    listaGrupos.get(j).noConsiderar();
                }         
            }
            listaGrupos.clear();
            listaGrupos = listaGruposTraspaso;

            for(int i=0; i<listaGrupos.size() ; i++) {
                if(nVariablesHorizontales == 2)
                    listaGrupos.get(i).intBuffer[0] = -1;
                if(nVariablesHorizontales == 1) {
                    listaGrupos.get(i).intBuffer[1] = -1;
                    listaGrupos.get(i).intBuffer[0] = -1;
                }
                if(nVariablesVerticales == 2)
                    listaGrupos.get(i).intBuffer[3] = -1;
                if(nVariablesVerticales == 1) {
                    listaGrupos.get(i).intBuffer[3] = -1;
                    listaGrupos.get(i).intBuffer[4] = -1;
                }
            }        
        }       
    }
    public static void setArregloBidimensional(ArregloBidimensional info) {
        arreglo = info;
    }
    
    /**
     * Convierte el valor numerico en un array que representa el valor en binario
     */
    private int[] getArrayFromInt(int num) {
        int[] numero = new int[]{0,0,0};
        
        for(int i=0; i<num; i++) {
            numero = sumadorUnitario(numero);
        }
        
        return(numero);
    }
    
    /**
     * Regresa un array con los valores de los dos arreglos en uno solo
     */
    private int[] pegarShortBuffers(int[] shortHorizontalBuffer, int[] shortVerticalBuffer) {
        int[] suma = new int[6];
        for(int i=0; i<6;i++) {
            if(i < 3 )
                suma[i] = shortHorizontalBuffer[i];
            else
                suma[i] = shortVerticalBuffer[i-3];
        }        
        return(suma);
    }
    
    /**
     * Regresa el valor equivalente (indice) de un mintermino  
     */
    private int valorDelArreglo(int[] array3) {
        int suma = 0;
        for(int i=0; i<3;i++) {
            
            if(array3[2-i] == 1)
                suma += (int)Math.pow(2,i);
        }
        
        return(suma);
    }   


    /**
     * Devuelve cuantas diferencias hay entre los arreglos como parametros
     */
    private int revisaDiferencias6(int[] array6a,int[] array6b) {
        int revision =0;
        for(int i=0; i<6;i++) {
            if(array6a[i] != array6b[i])
                revision++;
        }        
        return(revision);
    }

    /**
     * Marcamos las diferencias entre dos arreglos con un asterisco (en este caso un -1)
     */
    private int[] postRevisaDiferencias6(int[] array6a,int[] array6b) {
        int[] suma = new int[6];
        for(int i=0; i<6;i++) {
            if(array6a[i] != array6b[i])
                suma[i] = -1;
            else 
                suma[i] = array6a[i];
        }        
        return(suma);
    }
    
    /**
     * Convierte el valor de la fila en un array que representa al mismo
     */
    private int[] sumadorUnitario(int[] anterior) {
        int[] suma = new int[3];
        
        //for(int i=0;i<7;i++) {
            
            switch(valorDelArreglo(anterior)) {
                case 0:
                    suma = new int[]{0,0,1};
                    break;
                case 1:
                    suma = new int[]{0,1,1};
                    break;
                case 2:
                    suma = new int[]{1,1,0};
                    break;
                case 3:
                    suma = new int[]{0,1,0};
                    break;
                case 4:
                    suma = new int[]{1,0,1};
                    break;
                case 5:
                    suma = new int[]{1,1,1};
                    break;
                case 6:
                    suma = new int[]{1,0,0};
                    break;
                default:
                    suma = new int[]{0,0,0};                  
            }        
        //}
        
        return(suma);
    }

    
    public String getString() {
        String texto = new String();
        char letraVertical   = 'a';
        char letraHorizontal = this.letraHorizontal;
        /*listaGrupos*/
        for(int z=0; z<listaGrupos.size();z++) {
            for(int i=3-nVariablesVerticales; i <3 ; i++) {
                if(listaGrupos.get(z).intBuffer[i+3] == -1) {
                }
                else {
                    texto = texto.concat(String.valueOf(letraVertical));
                    
                    if(listaGrupos.get(z).intBuffer[i+3] == 0) {
                        texto = texto.concat("'");
                    }                    
                }
                letraVertical++;    
            }
            for(int i=3-nVariablesHorizontales; i <3; i++) {
                if(listaGrupos.get(z).intBuffer[i] == -1) {
                }
                else {
                    texto = texto.concat(String.valueOf(letraHorizontal));
                    if(listaGrupos.get(z).intBuffer[i] == 0) {
                        texto = texto.concat("'");
                    } 
                }
                letraHorizontal++;                     
            }
            texto = texto.concat(" + ");
            letraVertical   = 'a';
            letraHorizontal = this.letraHorizontal;
        }         
        return(texto);
    }    
}
