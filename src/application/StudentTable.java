package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentTable implements Initializable {
	@FXML
	TableView<Student> table;
	@FXML
	TableColumn<Student,String> USN;
	@FXML
	TableColumn<Student,String> NAME;
	@FXML
	TableColumn<Student,String> EMAIL;
	@FXML
	TableColumn<Student,String> PHONE;
	@FXML
	private Label Status;
	@FXML
	private Label STRENGTH;

	String fname,lname,class_name;
	private ObservableList<Student> data= FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			table.refresh();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			

			PreparedStatement pstmt1 = con.prepareStatement("select * from class_info where userid like ?");
			pstmt1.setString(1,Login.userid);
			ResultSet rs2 = pstmt1.executeQuery();
			while(rs2.next()) {
			class_name=rs2.getString("class_name");
			break;
			}
			rs2.close();
			
			PreparedStatement pstmt = con.prepareStatement("select * from faculty_register where userid = ?");
			pstmt.setString(1,Login.userid);
			ResultSet rs1 = pstmt.executeQuery();
			while(rs1.next()) {
				fname=rs1.getString("f_name");
				lname=rs1.getString("l_name");
				break;
			}
			rs1.close();
			
			Status.setText("Welcome "+fname+" "+lname+"!");
			

			
		}catch(Exception e) {
			System.out.println(e);
		}
		loadDataFromDatabase();
		setCellValue();
		table.setItems(data);
		totalStrength();
	}	
	
	public void setCellValue() {
		this.USN.setCellValueFactory(new PropertyValueFactory<Student,String>("usn"));
		this.NAME.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
		this.EMAIL.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
		this.PHONE.setCellValueFactory(new PropertyValueFactory<Student,String>("phone"));
	}
	
	public void loadDataFromDatabase() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");

			PreparedStatement pstmt = con.prepareStatement("select * from student_details where class_name like ? order by usn");
			pstmt.setString(1,class_name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
			data.add(new Student(rs.getString("usn"),rs.getString("name"),rs.getString("email"),rs.getString("phone")));
			}
			rs.close();
		}catch(Exception e) {
			
		}
	}
	
	public void refresh() {
		table.getItems().clear();
		initialize(null,null);
	}
	
	public void callcc() {
		CreateClass c = new CreateClass();
		c.CallcreateClass();
	}
	
	public void callis() {
		InsertStudent i = new InsertStudent();
		i.CallInsertStudent();
	}
	
	public void callds() {
		DeleteStudent d = new DeleteStudent();
		d.CallDelete();
	}
	
	public void calls() {
		Subjects s = new Subjects();
		s.CalladdSubjects();
	}
	
	public void callim() {
		InsertMarks i = new InsertMarks();
		i.CallinsertMarks();
	}
	
	public void callcr() {
		ClassReport c = new ClassReport();
		c.callBC();
	}
	
	public void callsr() {
		StudentReport s = new StudentReport();
		s.CallStudentReport();
	}
	
	public void totalStrength() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			
			PreparedStatement p = con.prepareStatement("select count(*) as s from student_details where class_name = ?");
			p.setString(1,class_name);
			ResultSet r = p.executeQuery();
			int strength=0;
			while(r.next()) {
				strength=Integer.parseInt(r.getString("s"));
				break;
			}
			r.close();
			STRENGTH.setText(Integer.toString(strength));
			
		}catch(SQLException e) {
			
		}catch(Exception e) {
			
		}
	}
	
}