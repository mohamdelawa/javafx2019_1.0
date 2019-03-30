
package multiple;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class MultipleSelection extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
      
      //create flowpane
       FlowPane pane=new FlowPane();
        
        pane.setPadding(new Insets(10,10,20,10));
         pane.setHgap(10);
         pane.setVgap(10);
        //create listview
        ObservableList<String> List = FXCollections.<String>observableArrayList("Black","Blue","Cyan","Dark Gray","Gray","Green");
       
        ListView<String> list=new ListView<>();
        //add item in listview
        list.setItems(List);
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //create and add scrollPane
        ScrollPane scroll=new ScrollPane();
        scroll.setContent(list);
        scroll.setPrefSize(130, 150);
        scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
       //add scrollPane in flowPane
       
       pane.getChildren().add(scroll);
        //create button 
       Button but=new Button("Copy>>>");
             //add button in stackpane
      
        pane.getChildren().add(but);
        //create and add textAria
       TextArea text =new TextArea();
       text.setPrefSize(130, 150);
       pane.getChildren().add(text);
        //action in button
       but.setOnAction(new EventHandler<ActionEvent>() {
          @Override
           public void handle(ActionEvent e) {
               String lister="";
                for (int i = 0; i < list.getSelectionModel().getSelectedItems().size(); i++) {
                  lister=lister+list.getSelectionModel().getSelectedItems().get(i)+"\n";
              }
              
              
              text.setText(lister);
           }
       });

       //add flowpane in  scene
       Scene scene=new Scene(pane,380,180);
       //add scene in stage
       stage.setScene(scene);
       stage.setTitle("Multiple Selection Lists");
       stage.show();
       
    }
       
    public static void main(String[] args) {
        launch(args);
    }
    
}
