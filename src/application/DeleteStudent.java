package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteStudent {
	
	@FXML
	private Label STATUS;
	@FXML
	private TextField USN;
	
	String class_name;
	
	public void CallDelete() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/DeleteStudent.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Result Analysis");
		}catch(Exception e) {	
		}
	}
	
	public void delete() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			
			boolean value=false;
			String usn = USN.getText().toUpperCase();
			
			PreparedStatement p = con.prepareStatement("select * from class_info where userid like ?");
			p.setString(1,Login.userid);
			ResultSet rs1 = p.executeQuery();
			
			while(rs1.next()) {
				class_name=rs1.getString("class_name");
				break;
			}
			rs1.close();
			
			PreparedStatement pstmt = con.prepareStatement("select usn from student_details where usn like ? and class_name like ?");
			pstmt.setString(1,usn);
			pstmt.setString(2,class_name);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if((rs.getString("usn")).equals(usn)) {
					PreparedStatement pstmt1 = con.prepareStatement("delete from student_details where usn like ?");
					pstmt1.setString(1,usn);
					pstmt1.executeUpdate();
					con.commit();
					STATUS.setText(usn+" deleted successfully!");
					value=true;
					USN.clear();
					break;
				}
			}
			rs.close();
			
			if(!value) {
				STATUS.setText("Student does not exist in this class!");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}catch(Exception e) {
			
		}
	}
}
