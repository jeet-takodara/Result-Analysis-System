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

public class Subjects {
	
	@FXML
	private Label STATUS;
	@FXML
	private TextField S1C;
	@FXML
	private TextField S1N;
	@FXML
	private TextField S1CR;
	@FXML
	private TextField S2C;
	@FXML
	private TextField S2N;
	@FXML
	private TextField S2CR;
	@FXML
	private TextField S3C;
	@FXML
	private TextField S3N;
	@FXML
	private TextField S3CR;
	@FXML
	private TextField S4C;
	@FXML
	private TextField S4N;
	@FXML
	private TextField S4CR;
	@FXML
	private TextField S5C;
	@FXML
	private TextField S5N;
	@FXML
	private TextField S5CR;
	@FXML
	private TextField S6C;
	@FXML
	private TextField S6N;
	@FXML
	private TextField S6CR;
	@FXML
	private TextField S7C;
	@FXML
	private TextField S7N;
	@FXML
	private TextField S7CR;
	@FXML
	private TextField S8C;
	@FXML
	private TextField S8N;
	@FXML
	private TextField S8CR;
	
	String class_name;
	int cr;
	
	public void CalladdSubjects() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/AddSubject.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Result Analysis");
		}catch(Exception e) {
			
		}
	}
	
	public void addSubjects() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			
			PreparedStatement p = con.prepareStatement("select * from class_info where userid like ?");
			p.setString(1,Login.userid);
			ResultSet rs1 = p.executeQuery();
			
			while(rs1.next()) {
				class_name=rs1.getString("class_name");
				break;
			}
			rs1.close();
			
			PreparedStatement ps = con.prepareStatement("select * from subject_info where class_name like ?");
			ps.setString(1,class_name);
			ResultSet R = ps.executeQuery();
			if(R.next()) {
				System.out.println("Subjects already created!");
			}
			
			else{
				PreparedStatement p1;
			
			p1=con.prepareStatement("insert into subject_info values (?,?,?,?)");
			p1.setString(1,class_name);
			p1.setString(2,S1C.getText().toUpperCase());
			p1.setString(3,S1N.getText().toUpperCase());
			cr=Integer.parseInt(S1CR.getText());
			p1.setInt(4,cr);
			p1.executeUpdate();
			
			p1=con.prepareStatement("insert into subject_info values (?,?,?,?)");
			p1.setString(1,class_name);
			p1.setString(2,S2C.getText().toUpperCase());
			p1.setString(3,S2N.getText().toUpperCase());
			cr=Integer.parseInt(S2CR.getText());
			p1.setInt(4,cr);
			p1.executeUpdate();
			
			p1=con.prepareStatement("insert into subject_info values (?,?,?,?)");
			p1.setString(1,class_name);
			p1.setString(2,S3C.getText().toUpperCase());
			p1.setString(3,S3N.getText().toUpperCase());
			cr=Integer.parseInt(S3CR.getText());
			p1.setInt(4,cr);
			p1.executeUpdate();
			
			p1=con.prepareStatement("insert into subject_info values (?,?,?,?)");
			p1.setString(1,class_name);
			p1.setString(2,S4C.getText().toUpperCase());
			p1.setString(3,S4N.getText().toUpperCase());
			cr=Integer.parseInt(S4CR.getText());
			p1.setInt(4,cr);
			p1.executeUpdate();
			
			p1=con.prepareStatement("insert into subject_info values (?,?,?,?)");
			p1.setString(1,class_name);
			p1.setString(2,S5C.getText().toUpperCase());
			p1.setString(3,S5N.getText().toUpperCase());
			cr=Integer.parseInt(S5CR.getText());
			p1.setInt(4,cr);
			p1.executeUpdate();
			
			p1=con.prepareStatement("insert into subject_info values (?,?,?,?)");
			p1.setString(1,class_name);
			p1.setString(2,S6C.getText().toUpperCase());
			p1.setString(3,S6N.getText().toUpperCase());
			cr=Integer.parseInt(S6CR.getText());
			p1.setInt(4,cr);
			p1.executeUpdate();
			
			p1=con.prepareStatement("insert into subject_info values (?,?,?,?)");
			p1.setString(1,class_name);
			p1.setString(2,S7C.getText().toUpperCase());
			p1.setString(3,S7N.getText().toUpperCase());
			cr=Integer.parseInt(S7CR.getText());
			p1.setInt(4,cr);
			p1.executeUpdate();
			
			p1=con.prepareStatement("insert into subject_info values (?,?,?,?)");
			p1.setString(1,class_name);
			p1.setString(2,S8C.getText().toUpperCase());
			p1.setString(3,S8N.getText().toUpperCase());
			cr=Integer.parseInt(S8CR.getText());
			p1.setInt(4,cr);
			p1.executeUpdate();
			
			con.commit();
			
			STATUS.setText("Subjects Added Successfully!");
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
