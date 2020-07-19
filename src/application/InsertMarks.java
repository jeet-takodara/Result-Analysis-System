package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertMarks {
	
	@FXML
	private Label STATUS;
	@FXML
	private TextField IA1;
	@FXML
	private TextField IA2;
	@FXML
	private TextField IA3;
	@FXML
	private TextField A1;
	@FXML
	private TextField A2;
	@FXML
	private TextField A3;
	@FXML
	private TextField EXTERNAL;
	@FXML
	private ComboBox<String> USN = new ComboBox<String>();
	@FXML
	private ComboBox<String> SUBCODE=new ComboBox<String>();
	
	String class_name;
	String[] subjects = new String[8];
	private ObservableList<String> data= FXCollections.observableArrayList();
	private ObservableList<String> subjectcode= FXCollections.observableArrayList();
	public void CallinsertMarks() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/InsertMarks.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Result Analysis");
		}catch(Exception e) {
		}
	}
	
	
	
	public void loadUSNandSUB() {
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
			
			PreparedStatement p1 = con.prepareStatement("select * from student_details where class_name like ? order by usn");
			p1.setString(1,class_name);
			ResultSet r = p1.executeQuery();
		
			while(r.next()) {
				data.add(r.getString("usn"));
			}
			r.close();
			
			p1=con.prepareStatement("select * from subject_info where class_name like ?");
			p1.setString(1,class_name);
			r=p1.executeQuery();
			
			int i=0;
			
			while(r.next()) {
				subjects[i]=r.getString("sub_code");
				i++;
			}
			
			String s1,s2,s3,s4,s5,s6,s7,s8;
			s1=subjects[0];
			s2=subjects[1];
			s3=subjects[2];
			s4=subjects[3];
			s5=subjects[4];
			s6=subjects[5];
			s7=subjects[6];
			s8=subjects[7];

			subjectcode.addAll(s1,s2,s3,s4,s5,s6,s7,s8);
			
			USN.setItems(data);
			SUBCODE.setItems(subjectcode);
			
		}catch(SQLException e) {
			
		}catch(Exception e) {
			
		}
	}
	
	public void insertmarks() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			
			PreparedStatement p = con.prepareStatement("insert into marks_info(class_name,usn,sub_code,ia1,ia2,ia3,a1,a2,a3,external) values(?,?,?,?,?,?,?,?,?,?)");
			p.setString(1,class_name);
			p.setString(2,USN.getSelectionModel().getSelectedItem().toString());
			p.setString(3,SUBCODE.getSelectionModel().getSelectedItem().toString());
			p.setInt(4,Integer.parseInt(IA1.getText()));
			p.setInt(5,Integer.parseInt(IA2.getText()));
			p.setInt(6,Integer.parseInt(IA3.getText()));
			p.setInt(7,Integer.parseInt(A1.getText()));
			p.setInt(8,Integer.parseInt(A2.getText()));
			p.setInt(9,Integer.parseInt(A3.getText()));
			p.setInt(10,Integer.parseInt(EXTERNAL.getText()));
			p.executeUpdate();
			
			STATUS.setText("Marks Added for "+USN.getSelectionModel().getSelectedItem().toString());
			
			CallableStatement c = con.prepareCall("{call assign_grade}");
			c.executeUpdate();
			con.commit();
			IA1.clear();
			IA2.clear();
			IA3.clear();
			A1.clear();
			A2.clear();
			A3.clear();
			EXTERNAL.clear();
			
		}catch(SQLException e) {
			System.out.println(e);
		}catch(Exception e) {
			
		}
	}
}
