package model;

public class ResolvMethod {

    private ArregloBidimensional arreglo;
    private int numCasillasHorizontales, numCasillasVerticales;
    private ClasificadorLetras clasificador;
    private AlgoritmoReductor reductor;

    public ResolvMethod(ArregloBidimensional arreglo, int numCasillasHorizontales, int numCasillasVerticales, char letraHorizontal) {
        this.arreglo = arreglo;
        this.numCasillasHorizontales = numCasillasHorizontales;
        this.numCasillasVerticales = numCasillasVerticales;

        clasificador = new ClasificadorLetras(arreglo, letraHorizontal);
        AlgoritmoReductor.setArregloBidimensional(arreglo);


        reductor = new AlgoritmoReductor(letraHorizontal);
    }

    public String getLongExpresion(){
        String texto = "";
        String tempText;

        for(int i=0; i<numCasillasVerticales; i++) {
            for(int j=0; j<numCasillasHorizontales ; j++) {

                tempText = clasificador.getStringAtPosition(j,i);

                if(!tempText.equals(""))
                    texto = texto.concat( tempText + " + ");

            }
        }

        //quitarle el ultimo signo de +
        texto = texto.substring(0, texto.length()-2);

        return texto;
    }

    public String getShortExpresion(){
        String shortS = reductor.getString();
        shortS = shortS.substring(0, shortS.length()-2);

        if(shortS.equals(" "))
            shortS = "1";

        return shortS;
    }


}
