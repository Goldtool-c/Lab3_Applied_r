package sample;


import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import javax.naming.Context;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable
{

        @FXML
        private TableView<Polynomial> Table1;
        @FXML
        private MenuItem menuItem;
        @FXML
        private MenuItem Polyndrom;
        @FXML
        private MenuItem saveIt;
        @FXML
        private TableColumn<Polynomial, String> ColumnX;

        @FXML
        private TableColumn<Polynomial, String> ColumnValue;

        @FXML
        private TableColumn<Polynomial, String> ColumnFloatValue;

        @FXML
        private TableColumn<Polynomial, String> ColumnDiff;


    @FXML
    private TextField EnterPol;

    @FXML
    private Button Set_X;
    @FXML
    private TextField XField;
    private String temp1;
    private Polynomial temp[]=new Polynomial[10];
    private String tempA;
   /* public void SaveFile(TableView<Polynomial> Table1)
    {
        try(FileWriter writer = new FileWriter("D:\\Install\\С3\\Polynom.csv", true))
        {
            // запись всей строки
            String text = "Hello Gold!";
            Polynomial Pol=new Polynomial();
            List<List<String>> arrList=new ArrayList<>();
            int it=0;

            while(it<Table1.getItems().size())
            {
                Pol=Table1.getItems().get(it);
                System.out.println(Pol.X+", "+Pol.Value+", "+Pol.FloatX+", "+Pol.Result+"\n");
                writer.write(Pol.X+", "+Pol.Value+", "+Pol.FloatX+", "+Pol.Result+"\n");
                it++;
            }
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }*/
   public void writeToCsvFile(List<String[]> thingsToWrite, String separator, String fileName){
       try (FileWriter writer = new FileWriter(fileName)){
           for (String[] strings : thingsToWrite) {
               for (int i = 0; i < strings.length; i++) {
                   writer.append(strings[i]);
                   if(i < (strings.length-1))
                       writer.append(separator);
               }
               writer.append(System.lineSeparator());
           }
           writer.flush();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
        @Override
        public void initialize(URL Ur1, ResourceBundle rb)
        {
            this.XField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {
                    temp1 =newValue;
                }

            });
            menuItem.setOnAction(DialogEvent->
            {
                Stage stag= new Stage();

                Label label=new Label();
                label.setText("Гладышев 2 курс 8ая группа");
                FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10);
                //Image image=new Image("D:\\Install\\С3\\My_Photo.png");
                FileInputStream inputstream1 = null;
                try {
                    inputstream1 = new FileInputStream("D:\\Install\\С3\\My_Photo.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image = new Image(inputstream1);
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                root.getChildren().addAll(label, imageView);
                stag.setScene(new Scene(root,200, 200));
                stag.show();
            });
            saveIt.setOnAction(event ->
            {
                Polynomial pol = new Polynomial();
                int it = 0;List<String[]> arrList=new ArrayList<>();
                while (it < Table1.getItems().size()) {
                    pol = Table1.getItems().get(it);
                    System.out.println(pol.X);
                    int i=0;
                    String temp[]=new String[4];
                    temp[0]=pol.X;
                    temp[1]=pol.Value;
                    temp[2]=pol.FloatX;
                    temp[3]=pol.Result;
                    arrList.add(temp);
                    writeToCsvFile(arrList,", ", "D:\\Install\\С3\\Polynom.csv");
                    it++;
                }

            });
            Polyndrom.setOnAction(DialogEvent->
            {
                Stage stag= new Stage();
                int i=0;
                String Plnd="";
                while(i<4)
                {
                    String s= ColumnX.getCellData(i);
                    Boolean Con=s.equals((new StringBuilder(s)).reverse().toString());

                        if(Con)
                        {
                            Plnd=Plnd+"1ый Столбец "+(i+1)+"ая строка\n";
                        }
                        i++;
                    }
                i=0;
                while(i<4)
                {
                    String s= ColumnValue.getCellData(i);
                    Boolean Con=s.equals((new StringBuilder(s)).reverse().toString());

                    if(Con)
                    {
                        Plnd=Plnd+"2ый Столбец "+(i+1)+"ая строка\n";
                    }
                    i++;
                }
                Label label=new Label();
                label.setText(Plnd);
                FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10);
                root.getChildren().addAll(label);
                stag.setScene(new Scene(root,200, 200));
                stag.show();
            });
            this.EnterPol.textProperty().addListener(new ChangeListener<String>()
            {
              @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
              {
                  tempA=newValue;
              }
            }
            );
            final int[] i = {0};
            this.Set_X.setOnAction(event ->
            {
                temp[i[0]] = new Polynomial(temp1);
                temp[i[0]].Coff(tempA);
                temp[i[0]].setValue();
                temp[i[0]].setFloatX();
                temp[i[0]].setResult();
                this.ColumnX.setCellValueFactory(new PropertyValueFactory<>("X"));
                ColumnX.setStyle("-fx-border-color: black");
                this.ColumnValue.setCellValueFactory(new PropertyValueFactory<>("Value"));
                ColumnValue.setStyle("-fx-border-color: black");
                this.ColumnFloatValue.setCellValueFactory(new PropertyValueFactory<>("FloatX"));
                ColumnFloatValue.setStyle("-fx-border-color: black");
                this.ColumnDiff.setCellValueFactory(new PropertyValueFactory<>("Result"));
                ColumnDiff.setStyle("-fx-border-color: black");
                ObservableList<Polynomial> list= FXCollections.observableArrayList(temp);
                Table1.setStyle("-fx-border-color: black");
                this.Table1.setItems(list);
                i[0]++;
            });
            Table1.setEditable(true);
            Table1.getSelectionModel().setCellSelectionEnabled(true);  // selects cell only, not the whole row
            Table1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent click) {
                    if (click.getClickCount() == 2) {
                        @SuppressWarnings("rawtypes")
                        TablePosition pos = Table1.getSelectionModel().getSelectedCells().get(0);
                        int row = pos.getRow();
                        int col = pos.getColumn();
                        @SuppressWarnings("rawtypes")
                        TableColumn column = pos.getTableColumn();
                        String val = column.getCellData(row).toString(); System.out.println("Selected Value, " + val + ", Column: " + col + ", Row: " + row);

                        if(val.equals(temp[row].Value)) {
                                String t = temp[row].Value;
                                temp[row].Value = temp[row].Value + " eq";
                                ObservableList<Polynomial> list = FXCollections.observableArrayList(temp[row]);
                                System.out.println(temp[row].Value);
                                Table1.setItems(list);
                                int a = 0;
                                int a1 = 0;
                                while (a1 < 15000000) {
                                    a = 0;
                                    while (a < 1000000000) {
                                        a++;
                                    }
                                    a1++;
                                }
                                //temp[row].Value = t;
                                //list = FXCollections.observableArrayList(temp);
                            }

                    }
                }
            });
        }
    }

