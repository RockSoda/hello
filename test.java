package test;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import static javafx.application.Application.launch;

public class test {

    public String FileIn(String path) throws IOException {
        String content = null;
        File file = new File(path);
        FileReader reader = null;
        try{
            reader = new FileReader(file);
            char[] chars = new char[(int)file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        }catch (Exception e){
            System.out.print("");
        }finally {
            if(reader!=null){
                reader.close();
            }

        }
        return content;
    }

    public void FileOut(String content, String path, String fileName){
        try{
            File newFile = new File(path + "/" + fileName);
            FileWriter fw = new FileWriter(newFile);
            fw.write(content);
            fw.close();
        }catch (Exception iox){
            iox.getStackTrace();
        }
    }

    public void SaveToDatabase(int tmp1, int tmp2) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/MopedRental", "sqluser", "sqluserpw");

            PreparedStatement preparedStmt = con.prepareStatement("INSERT INTO user " +
                    " VALUES (?, ?, default)");

            preparedStmt.setInt(1, tmp1);
            preparedStmt.setInt(2, tmp2);
            preparedStmt.execute();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void get(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/feedback","sqluser","sqluserpw");

            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * " +
                    "from comments");

            while(rs.next()){
                for(int i = 1; i <= 7; i++){
                    System.out.print(rs.getString(i)+"  ");
                }
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Button tmp = new Button("");
        GridPane.setConstraints(tmp, 0, 0);
        grid.getChildren().add(tmp);

        tmp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 //String str = String.valueOf(tmp.getText());
            }

        });

        grid.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(grid, 600, 600));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
