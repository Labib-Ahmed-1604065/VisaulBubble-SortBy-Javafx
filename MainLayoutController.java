package bubblesort;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MD Rakibul Islam;
 */
public class MainLayoutController implements Initializable {
// FXML initialize...

    @FXML
    public Label counterInput, inputmis;
    @FXML
    TextArea inputText;

    @FXML
    ComboBox<Integer> comboBox;
    @FXML
    public AnchorPane pane;
    @FXML
    public Circle circle;

    //Initialize the variable for programm
    Button[] button = new Button[15];
    int index1 = 0, index2 = 1, counter = 0;
    int[] inputArray = new int[15];

    ObservableList<Integer> options = FXCollections.observableArrayList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pane.getChildren().remove(inputmis);
        comboBox.setItems(options);
        comboBox.setValue(5);
        DestroyButton(comboBox.getValue());

    }

    public void onPauseButton(ActionEvent event) {
         
        inputText.setText("10 25 35 69 58 25 35 36 24 25 15 55 2 452 75");
        
    }

    void DestroyButton(int n) {

        for (int i = 0; i < n; i++) {
            pane.getChildren().remove(button[i]);
        }
    }

    public void OnStartButton(ActionEvent event) {
        String input;

        int count = 0, j = 0;
        int c = 1, sum = 0, i;
        input = inputText.getText();
        String[] arra = input.split(" ");

        for (i = 0; i < input.length(); i++) {

            if (input.charAt(i) == ' ') {

                count++;
            };

        }

        for (i = 0; i < arra.length; i++) {
            inputArray[i] = Integer.parseInt(arra[i]);

        }

        count++;
        counterInput.setText(count + "");
        if (comboBox.getValue() == count) {
            setButtonlebel(comboBox.getValue());
        } else {
            pane.getChildren().add(inputmis);
        }

        bubbleSort(button[index1], button[index2]);

    }

    public void OnComboChange(ActionEvent event) {
        DestroyButton(comboBox.getValue());
//       for(int i=0;i<comboBox.getValue();i++){
//           pane.getChildren().add(button[i]);
//       }
        initbuttonArray(comboBox.getValue());
        setCircleOnButton(comboBox.getValue());
        counter = comboBox.getValue();

    }

    public void initbuttonArray(int n) {

        for (int i = 0; i < n; i++) {
            button[i] = new Button();
            button[i].setLayoutX(36 + (i * 51));
            button[i].setLayoutY(211);
            button[i].setMinWidth(40);
            button[i].setMinHeight(43);
            pane.getChildren().add(button[i]);
        }

    }

    void setCircleOnButton(int N) {

        circle.setLayoutX(button[N - 1].getLayoutX() + 20);
    }

    void setButtonlebel(int n) {
        for (int i = 0; i < n; i++) {
            button[i].setText(inputArray[i] + "");
        }
    }

    void bubbleSort(Button btn1, Button btn2) {

 

        if (index2 < counter) {

            if (inputArray[index1] > inputArray[index2]) {
                LeftToRight(btn1);
                righttoleft(btn2);
            } else {
                UpAndDown(btn1);
                UpAndDownonly(btn2);
            }

        } else if (counter == 2) {
            inputmis.setText(" Sorting Completed!!!");
            pane.getChildren().add(inputmis);
        } else if (index2 == counter) {

            if (index2 != 1) {
                index1 = 0;
                index2 = 1;
                counter--;
                setCircleOnButton(counter);

                bubbleSort(button[index1], button[index2]);
            }
        }

    }

    void LeftToRight(Button btn) {

        PathElement[] exchange = {
            new MoveTo(12, 12),
            new VLineTo(120),
            new HLineTo(70),
            new VLineTo(18),};

        PathTransition anim = new PathTransition();
        Path path = new Path();
        path.getElements().addAll(exchange);
        anim.setPath(path);
        anim.setNode(btn);

        anim.setDuration(new Duration(2000));
        anim.setCycleCount(1);
        
        anim.play();

        anim.setOnFinished((event) -> {
            if (index2 < counter - 1) {
               changeButton();

                bubbleSort(button[++index1], button[++index2]);
            } else if (index2 == counter - 1 && counter != 2) {
                setCircleOnButton(counter);
               changeButton();
                //changeButton();
                index1 = 0;
                index2 = 1;
                counter--;

                bubbleSort(button[index1], button[index2]);
            }

        });

    }

    void righttoleft(Button btn) {

        PathElement[] exchange = {
            new MoveTo(12, 12),
            new VLineTo(120),
            new HLineTo(-36),
            new VLineTo(19),};

        PathTransition anim1 = new PathTransition();

        Path path1 = new Path();
        anim1.setNode(btn);
        path1.getElements().addAll(exchange);
        anim1.setPath(path1);

        // anim1.setInterpolator(Interpolator.LINEAR);
        anim1.setDuration(new Duration(2000));
        anim1.setCycleCount(1);
        anim1.play();

    }

    void UpAndDown(Button btn) {

        PathElement[] exchange = {
            new MoveTo(12, 12),
            new VLineTo(120),
            new VLineTo(19),};

        Path pathh = new Path();

        pathh.getElements().addAll(exchange);
        PathTransition transition = new PathTransition();
        transition.setPath(pathh);
        transition.setNode(btn);

        // anim1.setInterpolator(Interpolator.LINEAR);
        transition.setDuration(new Duration(1000));
        transition.setCycleCount(1);
        transition.play();
        transition.setOnFinished((event) -> {
            if (index2 < counter - 1) {
               
                bubbleSort(button[++index1], button[++index2]);
            } else if (index2 == counter - 1 && counter != 2) {
                index1 = 0;
                index2 = 1;
                counter--;
                setCircleOnButton(counter);
              
                bubbleSort(button[index1], button[index2]);
            }

        });

    }

    void UpAndDownonly(Button btn) {

        PathElement[] exchange = {
            new MoveTo(12, 12),
            new VLineTo(120),
            new VLineTo(19),};

        Path pathh = new Path();

        pathh.getElements().addAll(exchange);
        PathTransition transition = new PathTransition();
        transition.setPath(pathh);
        transition.setNode(btn);

        // anim1.setInterpolator(Interpolator.LINEAR);
        transition.setDuration(new Duration(1000));
        transition.setCycleCount(1);
        transition.play();
    }

    void changeButton() {
       
        
        System.out.println(getLayoutx(index1));
        System.out.println(getLayoutx(index2));
        int l;
        l = inputArray[index1];
        inputArray[index1] = inputArray[index2];
        inputArray[index2] = l;

      

        Button temp = new Button();
        temp=button[index1];
        button[index1]=button[index2];
        button[index2]=temp;

       
       
        button[index1].setLayoutX(getLayoutx(index1));
        button[index2].setLayoutX(getLayoutx(index2));
        
    
        setButtonlebel(comboBox.getValue());
       

    }
    int getLayoutx(int n){
       return n*51+36;
    }
  
}
