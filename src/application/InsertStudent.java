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

public class InsertStudent {
	
	@FXML
	private TextField USN;
	@FXML
	private TextField NAME;
	@FXML
	private TextField EMAIL;
	@FXML
	private TextField PHONE;
	@FXML
	private Label STATUS;
	
	static String class_name;
	
	public void CallInsertStudent() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/InsertStudent.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Result Analysis");
		}catch(Exception e) {	
		}
	}
	
	public void insertstudent() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			boolean value=true;
			Statement stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student_details");
			while(rs.next()) {
				if((rs.getString("usn")).equals(USN.getText().toUpperCase())) {
					STATUS.setText("Student already exists!");
					value=false;
					break;
				}
			}
			rs.close();
			PreparedStatement pstmt,pstmt1;
			pstmt1 = con.prepareStatement("select * from class_info where userid like ?");
			pstmt1.setString(1,Login.userid);
			ResultSet rs1 = pstmt1.executeQuery();
			while(rs1.next()) {
			class_name=rs1.getString("class_name");
			break;
			}
			if(value) {
				pstmt=con.prepareStatement("insert into student_details values(?,?,?,?,?)");
				pstmt.setString(1,USN.getText().toUpperCase());
				pstmt.setString(2,NAME.getText());
				pstmt.setString(3,EMAIL.getText());
				pstmt.setString(4,PHONE.getText());
				pstmt.setString(5,class_name);
				pstmt.executeUpdate();
				STATUS.setText("Student added Successfully!");
				con.commit();
				USN.clear();
				NAME.clear();
				EMAIL.clear();
				PHONE.clear();
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
