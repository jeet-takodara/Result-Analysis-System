package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StudentReport{
	
	@FXML
	LineChart <String,Number> lc;
	@FXML
	Label NAME;
	@FXML
	Label CLASS;
	@FXML
	Label S1;
	@FXML
	Label S2;
	@FXML
	Label S3;
	@FXML
	Label S4;
	@FXML
	Label S5;
	@FXML
	Label S6;
	@FXML
	Label S7;
	@FXML
	Label S8;
	@FXML
	Label R1;
	@FXML
	Label R2;
	@FXML
	Label R3;
	@FXML
	Label R4;
	@FXML
	Label R5;
	@FXML
	Label R6;
	@FXML
	Label R7;
	@FXML
	Label R8;
	@FXML
	Label STATUS;
	@FXML
	ComboBox <String> usn = new ComboBox<String>();
	
	String class_name;
	static String SN;
	private ObservableList<String> data= FXCollections.observableArrayList();
	
	public void CallStudentReport() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/StudentReport.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Result Analysis");
		}catch(Exception e) {
		}
	}
	
	public void loadComboBox() {
			usn.getItems().clear();
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
			ResultSet r1 = p1.executeQuery();
		
			while(r1.next()) {
				data.add(r1.getString("usn"));
			}
			r1.close();
			
			usn.setItems(data);
			con.close();
		}catch(SQLException e) {
			System.out.println(e);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	String[] sub_name = new String[8];
	String[] sub_code = new String[8];
	
	public void loadDetails() {
		lc.setAnimated(false);
		R1.setText("");
		R2.setText("");
		R3.setText("");
		R4.setText("");
		R5.setText("");
		R6.setText("");
		R7.setText("");
		R8.setText("");
		lc.getData().clear();
		Series<String,Number> series =  new XYChart.Series<String,Number>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			
			PreparedStatement p = con.prepareStatement("select * from student_details where usn like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());
			SN=usn.getSelectionModel().getSelectedItem().toString();
			ResultSet r = p.executeQuery();
			while(r.next()) {
				NAME.setText(r.getString("name"));
				break;
			}
			r.close();
			
			
			CLASS.setText(class_name);
			
			p=con.prepareStatement("select * from subject_info where class_name like ?");
			p.setString(1,class_name);
			r=p.executeQuery();
			
			int i=0;
			
			while(r.next()) {
				sub_name[i]=r.getString("sub_name");
				i++;
			}
			r.close();
			
			
			S1.setText(sub_name[0]);
			S2.setText(sub_name[1]);
			S3.setText(sub_name[2]);
			S4.setText(sub_name[3]);
			S5.setText(sub_name[4]);
			S6.setText(sub_name[5]);
			S7.setText(sub_name[6]);
			S8.setText(sub_name[7]);
			
			int j=0;
			p=con.prepareStatement("select * from subject_info where class_name like ?");
			p.setString(1,class_name);
			r=p.executeQuery();
			while(r.next()) {
				sub_code[j]=r.getString("sub_code");
				j++;
			}
			r.close();
			
			
			String res="";
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());
			p.setString(2,sub_code[0]);
			r=p.executeQuery();
			while(r.next()) {
				res=r.getString("result");
				break;
			}
			r.close();
	
			R1.setText(res);
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());
			p.setString(2,sub_code[1]);
			r=p.executeQuery();
			while(r.next()) {
				R2.setText(r.getString("result"));
				break;
			}
			r.close();
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());
			p.setString(2,sub_code[2]);
			r=p.executeQuery();
			while(r.next()) {
				R3.setText(r.getString("result"));
				break;
			}r.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());
			p.setString(2,sub_code[3]);
			r=p.executeQuery();
			while(r.next()) {
				R4.setText(r.getString("result"));
				break;
			}r.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());
			p.setString(2,sub_code[4]);
			r=p.executeQuery();
			while(r.next()) {
				R5.setText(r.getString("result"));
				break;
			}r.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());
			p.setString(2,sub_code[5]);
			r=p.executeQuery();
			while(r.next()) {
				R6.setText(r.getString("result"));
				break;
			}r.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());
			p.setString(2,sub_code[6]);
			r=p.executeQuery();
			while(r.next()) {
				R7.setText(r.getString("result"));
				break;
			}r.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());
			p.setString(2,sub_code[7]);
			r=p.executeQuery();
			while(r.next()) {
				R8.setText(r.getString("result"));
				break;
			}
			r.close();
			
			int m1=0,m2=0,m3=0,m4=0,m5=0,m6=0,m7=0,m8=0;
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());	
			p.setString(2,sub_code[0]);
			r=p.executeQuery();
			while(r.next()) {
				m1=Integer.parseInt(r.getString("total"));
				break;
			}r.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());	
			p.setString(2,sub_code[1]);
			r=p.executeQuery();
			while(r.next()) {
				m2=Integer.parseInt(r.getString("total"));
				break;
			}r.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());	
			p.setString(2,sub_code[2]);
			r=p.executeQuery();
			while(r.next()) {
			    m3=Integer.parseInt(r.getString("total"));
			    break;
			}r.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());	
			p.setString(2,sub_code[3]);
			r=p.executeQuery();
			while(r.next()) {
			    m4=Integer.parseInt(r.getString("total"));
			    break;
			}r.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());	
			p.setString(2,sub_code[4]);
			r=p.executeQuery();
			while(r.next()) {
			    m5=Integer.parseInt(r.getString("total"));
			    break;
			}r.close();
		
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());	
			p.setString(2,sub_code[5]);
			r=p.executeQuery();
			while(r.next()) {
				m6=Integer.parseInt(r.getString("total"));
				break;
			}r.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());	
			p.setString(2,sub_code[6]);
			r=p.executeQuery();
			while(r.next()) {
			    m7=Integer.parseInt(r.getString("total"));
			    break;
			}r.close();


			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,usn.getSelectionModel().getSelectedItem().toString());	
			p.setString(2,sub_code[7]);
			r=p.executeQuery();
			while(r.next()) {
			    m8=Integer.parseInt(r.getString("total"));
			    break;
			}
			r.close();
			
			series.getData().add(new XYChart.Data<String,Number>(sub_code[0],m1));
			series.getData().add(new XYChart.Data<String,Number>(sub_code[1],m2));
			series.getData().add(new XYChart.Data<String,Number>(sub_code[2],m3));
			series.getData().add(new XYChart.Data<String,Number>(sub_code[3],m4));
			series.getData().add(new XYChart.Data<String,Number>(sub_code[4],m5));
			series.getData().add(new XYChart.Data<String,Number>(sub_code[5],m6));
			series.getData().add(new XYChart.Data<String,Number>(sub_code[6],m7));
			series.getData().add(new XYChart.Data<String,Number>(sub_code[7],m8));
			
			lc.getData().add(series);
			
			for(final XYChart.Data<String,Number> data : series.getData()) {
				data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						STATUS.setText(data.getYValue().toString());
					}
					
				});
			}		
			
		}catch(SQLException e) {
			System.out.println(e);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void callReport() {
		PDFReport p = new PDFReport();
		p.createReport();
	}
}
