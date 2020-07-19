package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateClass {
	
	@FXML
	private Label ClassStatus;
	@FXML
	private TextField ClassName;
	
	static String class_name;
	
	public void CallcreateClass() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Class.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Result Analysis");
		}catch(Exception e) {	
		}
	}
	
	public void createclass() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			Statement stmt= con.createStatement();
			class_name=ClassName.getText().toUpperCase();
			ResultSet rs = stmt.executeQuery("select * from class_info");
			boolean value=true;
			while(rs.next()) {
				if((rs.getString(2)).equals(class_name)) {
					ClassStatus.setText("Class Already Exists!");
					value=false;
					break;
				}
			}
			PreparedStatement pstmt;
			if(value) {
			String sql = "insert into class_info values(?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,Login.userid);
			pstmt.setString(2,ClassName.getText().toUpperCase());
			pstmt.executeUpdate();
			ClassStatus.setText("Class "+ClassName.getText()+" created successfully!");
			con.commit();
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
