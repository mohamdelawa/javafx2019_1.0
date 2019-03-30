
package File_View;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;



public class file_View extends Application {
    
     // يمثل شريط القوائم الذي سنضعه في أعلا النافذة MenuBar هنا قمنا بإنشاء كائن من الكلاس
    MenuBar menuBar = new MenuBar();
    // تمثل القوائم الثلاث الرئيسية التي سنضعها في شريط القوائم Menu هنا قمنا بإنشاء 3 كائنات من الكلاس
    Menu menuFile = new Menu("File");
    Menu menuEdit = new Menu("Edit");
   
    // تمثل العناصر التي سنضعها في القوائم MenuItem هنا قمنا بإنشاء 11 كائن من الكلاس
    MenuItem itemOpen = new MenuItem("_Open");
    MenuItem itemClose = new MenuItem("_Close");
    MenuItem itemExit = new MenuItem("E_xit");
    MenuItem itemFont = new MenuItem("_Font");
    MenuItem itemColor = new MenuItem("Co_lor");
     File  selectedFile;
     
    @Override
    public void start(Stage stage) throws Exception {
        
        BorderPane theBorderPane = new BorderPane();

        //create and add textAria
        TextArea text =new TextArea();
        text.setPrefSize(350,280);
        text.setEditable(false);
       text.setFont(Font.font("Verdana", FontWeight.BOLD,14));
      
        
     
       //mnuebar ****************************************************************************************
                 // هنا قمنا بوضع القوائم في شريط القوائم
        menuBar.getMenus().addAll(menuFile, menuEdit);
        // menuFile في القائمة itemExit و itemSave ,ItemOpen ,itemNewFile هنا قمنا بوضع العناصر
        menuFile.getItems().add(itemOpen);
        menuFile.getItems().add(itemClose);
        menuFile.getItems().add(itemExit);
        // menuEdit في القائمة itemSelect و itemPaste ,itemCopy ,itemCut ,itemUndo هنا قمنا بوضع العناصر
        menuEdit.getItems().add(itemFont);
        menuEdit.getItems().add(itemColor);
        
       
        // هنا قمنا بجعل عرض شريط القوائم يساوي عرض النافذة حتى يظهر مطابق لها
        menuBar.setPrefWidth(400);
        theBorderPane.setTop(menuBar);

        ScrollPane scrollPane = new ScrollPane(text);
        scrollPane.setPrefSize(300, 300);
        scrollPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-focus-color: transparent;");

       //*************************************************************************************************
        //add flowpane in  scene
         theBorderPane.setCenter(scrollPane);
       Scene scene=new Scene(theBorderPane,350,320);
          
       //add scene in stage
       stage.setScene(scene);
       stage.setTitle("File View");
       stage.show();
       //actin menu Item
       // itemOpen هنا نضع الأوامر التي نريد أن يتم تنفيذها عند النقر على العنصر
        itemOpen.setOnAction((ActionEvent e) -> {
            
                  FileChooser fileChooser = new FileChooser(); 
                  ExtensionFilter filter = new ExtensionFilter(".txt", "txt");
                  fileChooser.setSelectedExtensionFilter(filter);
                  selectedFile  =fileChooser.showOpenDialog(stage);
                 
              
              try {
                   if (selectedFile!=null) {
                      
                       Scanner Scanner;
                      
                          Scanner = new Scanner(selectedFile);
                     
                       text.setText("");
                       while(Scanner.hasNextLine()){
                            text.appendText(Scanner.nextLine()+"\n");
                         
                        }
                       System.out.println("Open File and write True.");
                    }
		    text.setEditable(true);
                } catch (FileNotFoundException ex) {
                          Logger.getLogger(file_View.class.getName()).log(Level.SEVERE, null, ex);
                      }
        });
        
        // itemSave هنا نضع الأوامر التي نريد أن يتم تنفيذها عند النقر على العنصر
        itemClose.setOnAction((ActionEvent e) -> {
           //(selectedFile);
           text.setText("");
           text.setEditable(false);
            System.out.println("Close True.");
        });
        
        // itemExit هنا نضع الأوامر التي نريد أن يتم تنفيذها عند النقر على العنصر
        itemExit.setOnAction((ActionEvent e) -> {
            
            System.out.println("Exit True");
		System.exit(0);
        });
        
        // itemUndo هنا نضع الأوامر التي نريد أن يتم تنفيذها عند النقر على العنصر
        itemFont.setOnAction((ActionEvent e) -> {
            
                  int x=fontSize();
                    text.setFont(Font.font("Verdana", FontWeight.BOLD,x));
              System.out.println("Font Size Text :"+x+ " \n in true ");
        });
        
        // itemCut هنا نضع الأوامر التي نريد أن يتم تنفيذها عند النقر على العنصر
        itemColor.setOnAction((ActionEvent e) -> {
            String x=color();
            text.setStyle("-fx-text-fill:"+x+";");
        });
        
       
    }
       
    public static void main(String[] args) {
        launch(args);
    }
    public static  int fontSize(){
       String [] fontsize={"8", "14", "18", "24", "30", "37", "48", "60", "68","75", "82","90"};
       ChoiceDialog d = new ChoiceDialog(fontsize[1], fontsize); 
      
       Optional<String> Auswahl = d.showAndWait();
       if (Auswahl.isPresent()) {
           int x=Integer.parseInt(Auswahl.get());
           return x;
       }
       return 14; 
       }
    
    
    public static  String color(){
       String [] fontsize={"red", "blue", "green", "black","Cyan","DarkGray","Gray"};
       ChoiceDialog d = new ChoiceDialog(fontsize[1], fontsize); 
      
       Optional<String> Auswahl = d.showAndWait();
       if (Auswahl.isPresent()) {
           String x=Auswahl.get();
           return x;
       }
       return "Black"; 
       }
}
