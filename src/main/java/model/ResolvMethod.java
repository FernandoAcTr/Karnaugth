package model;

public class ResolvMethod {

    private ArregloBidimensional arreglo;
    private int numCasillasHorizontales, numCasillasVerticales;
    private LetterFactory factory;
    private McCluskey reductor;

    public ResolvMethod(ArregloBidimensional arreglo, int numCasillasHorizontales, int numCasillasVerticales, char letraHorizontal) {
        this.arreglo = arreglo;
        this.numCasillasHorizontales = numCasillasHorizontales;
        this.numCasillasVerticales = numCasillasVerticales;

        factory = new LetterFactory(arreglo, letraHorizontal);
        McCluskey.setArregloBidimensional(arreglo);


        reductor = new McCluskey(letraHorizontal);
    }

    public String getLongExpresion(){
        String texto = "";
        String tempText;

        for(int i=0; i<numCasillasVerticales; i++) {
            for(int j=0; j<numCasillasHorizontales ; j++) {

                tempText = factory.getStringAtPosition(j,i);

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
