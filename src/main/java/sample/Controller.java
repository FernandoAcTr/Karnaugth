package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.ArregloBidimensional;
import model.ResolvMethod;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Spinner<Integer> spinnerNumTerms;

    @FXML
    private HBox paneMinterms;

    @FXML
    private Button btnShowMap;

    @FXML
    private AnchorPane paneTwoVar, paneThreeVar, paneFourVar;

    @FXML private Label lbl_d_0;
    @FXML private Label lbl_d_1;
    @FXML private Label lbl_d_2;
    @FXML private Label lbl_d_3;

    @FXML private Label lbl_t_0;
    @FXML private Label lbl_t_1;
    @FXML private Label lbl_t_2;
    @FXML private Label lbl_t_3;
    @FXML private Label lbl_t_4;
    @FXML private Label lbl_t_5;
    @FXML private Label lbl_t_6;
    @FXML private Label lbl_t_7;

    @FXML private Label lbl_c_0;
    @FXML private Label lbl_c_1;
    @FXML private Label lbl_c_2;
    @FXML private Label lbl_c_3;
    @FXML private Label lbl_c_4;
    @FXML private Label lbl_c_5;
    @FXML private Label lbl_c_6;
    @FXML private Label lbl_c_7;
    @FXML private Label lbl_c_8;
    @FXML private Label lbl_c_9;
    @FXML private Label lbl_c_10;
    @FXML private Label lbl_c_11;
    @FXML private Label lbl_c_12;
    @FXML private Label lbl_c_13;
    @FXML private Label lbl_c_14;
    @FXML private Label lbl_c_15;

    @FXML
    private Label lblResult;

    @FXML private MenuItem mnuAbout;

    Label termsTwoVar[];
    Label termsThreeVar[];
    Label termsFourVar[];

    boolean matTwoVar[][];
    boolean matThreeVar[][];
    boolean matFourVar[][];

    ResolvMethod simplificador;

    public void initialize(URL location, ResourceBundle resources) {
        initData();
        initGUI();
    }

    private void initGUI(){
        generateCheckBox(2);
        paneTwoVar.setVisible(false);
        paneThreeVar.setVisible(false);
        paneFourVar.setVisible(false);

        spinnerNumTerms.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2,4));

        spinnerNumTerms.valueProperty().addListener(new ChangeListener<Integer>() {
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                generateCheckBox(newValue);
            }
        });

        btnShowMap.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                int var = spinnerNumTerms.getValue();
                showMap(var);
                showExpresion(var);
            }
        });

        mnuAbout.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/layout_about.fxml"));
                    Scene scene = new Scene(root, 420, 270);
                    scene.getStylesheets().add("/org/kordamp/bootstrapfx/bootstrapfx.css");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        for (Label l : termsTwoVar)
            l.getStyleClass().add("minterm");

        for (Label l : termsThreeVar)
            l.getStyleClass().add("minterm");

        for (Label l : termsFourVar)
            l.getStyleClass().add("minterm");
    }

    /**
     * Acomodar las label en arreglos para poder tratarlas por indices
     */
    private void initData(){
       Label two[] = {
               lbl_d_0,
               lbl_d_1,
               lbl_d_2,
               lbl_d_3,
       };

       Label three[] = {
               lbl_t_0,
               lbl_t_1,
               lbl_t_2,
               lbl_t_3,
               lbl_t_4,
               lbl_t_5,
               lbl_t_6,
               lbl_t_7,
       };

       Label four[] = {
               lbl_c_0,
               lbl_c_1,
               lbl_c_2,
               lbl_c_3,
               lbl_c_4,
               lbl_c_5,
               lbl_c_6,
               lbl_c_7,
               lbl_c_8,
               lbl_c_9,
               lbl_c_10,
               lbl_c_11,
               lbl_c_12,
               lbl_c_13,
               lbl_c_14,
               lbl_c_15,
       };

       termsTwoVar = two;
       termsThreeVar = three;
       termsFourVar = four;

       matTwoVar = new boolean[2][2];
       matThreeVar = new boolean[2][4];
       matFourVar = new boolean[4][4];
    }

    private void generateCheckBox(int numVar){
        paneMinterms.getChildren().clear();
        for (int i = 0; i < Math.pow(2, numVar) ; i++) {
            CheckBox check = new CheckBox(""+i);
            check.setPrefWidth(50);
            paneMinterms.getChildren().add(check);
        }
    }

    private void showMap(int numVar){
        if (numVar == 2){
            paneTwoVar.setVisible(true);
            paneThreeVar.setVisible(false);
            paneFourVar.setVisible(false);
            showMinTerms(numVar);
        }else if (numVar == 3){
            paneTwoVar.setVisible(false);
            paneThreeVar.setVisible(true);
            paneFourVar.setVisible(false);
            showMinTerms(numVar);
        }else if (numVar == 4){
            paneTwoVar.setVisible(false);
            paneThreeVar.setVisible(false);
            paneFourVar.setVisible(true);
            showMinTerms(numVar);
        }

        refreshMats(numVar);
    }

    private void showMinTerms(int map){
        ObservableList<Node> checkBoxes = paneMinterms.getChildren();
        Label terms[];

        if (map == 2)
            terms = termsTwoVar;
        else if (map == 3)
            terms = termsThreeVar;
        else
            terms = termsFourVar;
        /*
        Si el checkbox de indice i esta seleccionado, la label de indice i, que ya esta en la celda que representa el mintermino
        desde la construccion del layout, debe ser 1
         */
        for (int i = 0; i < terms.length; i++) {
            CheckBox c = (CheckBox) checkBoxes.get(i);
            if(c.isSelected())
                terms[i].setText("1");
            else
                terms[i].setText("");
        }
    }

    private void showExpresion(int numVar){
        int vVerticales, vHorizontales; //las casillas verticales y horix¿zontales dependiendo el numero de variables
        boolean[][] arrayTemp;
        char letraHorizontal;

        if(numVar == 2){
            vVerticales = 2;
            vHorizontales = 2;
            arrayTemp = matTwoVar;
            letraHorizontal = 'b';
        }else if(numVar == 3){
            vVerticales = 4;
            vHorizontales = 2;
            arrayTemp = matThreeVar;
            letraHorizontal = 'c';
        }else{
            vVerticales = 4;
            vHorizontales = 4;
            arrayTemp = matFourVar;
            letraHorizontal = 'c';
        }

        ArregloBidimensional arreglo = new ArregloBidimensional(vHorizontales,vVerticales);
        arreglo.setArreglo(arrayTemp);

        simplificador = new ResolvMethod(arreglo, vHorizontales, vVerticales, letraHorizontal);

        String result = "Expresión larga: "+simplificador.getLongExpresion();
        result += "\nExpresión simplificada: "+simplificador.getShortExpresion();

        lblResult.setText("\n"+result);

    }

    /**
     * obtiene una matriz booleana que representa el mapa K, en cuanto a posiciones.
     */
    private void refreshMats(int numMap){
        if(numMap == 2) {
            matTwoVar[0][0] = lbl_d_0.getText().equals("1");
            matTwoVar[0][1] = lbl_d_2.getText().equals("1");
            matTwoVar[1][0] = lbl_d_1.getText().equals("1");
            matTwoVar[1][1] = lbl_d_3.getText().equals("1");
        }

        if(numMap == 3) {
            matThreeVar[0][0] = lbl_t_0.getText().equals("1");
            matThreeVar[0][1] = lbl_t_2.getText().equals("1");
            matThreeVar[0][2] = lbl_t_6.getText().equals("1");
            matThreeVar[0][3] = lbl_t_4.getText().equals("1");
            matThreeVar[1][0] = lbl_t_1.getText().equals("1");
            matThreeVar[1][1] = lbl_t_3.getText().equals("1");
            matThreeVar[1][2] = lbl_t_7.getText().equals("1");
            matThreeVar[1][3] = lbl_t_5.getText().equals("1");
        }

        if(numMap == 4) {
            matFourVar[0][0] = lbl_c_0.getText().equals("1");
            matFourVar[0][1] = lbl_c_4.getText().equals("1");
            matFourVar[0][2] = lbl_c_12.getText().equals("1");
            matFourVar[0][3] = lbl_c_8.getText().equals("1");
            matFourVar[1][0] = lbl_c_1.getText().equals("1");
            matFourVar[1][1] = lbl_c_5.getText().equals("1");
            matFourVar[1][2] = lbl_c_13.getText().equals("1");
            matFourVar[1][3] = lbl_c_9.getText().equals("1");
            matFourVar[2][0] = lbl_c_3.getText().equals("1");
            matFourVar[2][1] = lbl_c_7.getText().equals("1");
            matFourVar[2][2] = lbl_c_15.getText().equals("1");
            matFourVar[2][3] = lbl_c_11.getText().equals("1");
            matFourVar[3][0] = lbl_c_2.getText().equals("1");
            matFourVar[3][1] = lbl_c_6.getText().equals("1");
            matFourVar[3][2] = lbl_c_14.getText().equals("1");
            matFourVar[3][3] = lbl_c_10.getText().equals("1");
        }
    }

    void printMatriz(boolean[][] array, int nrow, int ncol){

        for (int row = 0; row < nrow; row++) {

            for (int col = 0; col < ncol; col++) {

                System.out.print(array[row][col] + " ");
            }

            System.out.println();
        }
    }

}
