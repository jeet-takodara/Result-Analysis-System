package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClassReport {
	
	@FXML
	Label STATUS;
	@FXML
	BarChart<String,Double> bc;
	
	static String s1,s2,s3,s4,s5,s6,s7,s8,class_name;
	String[] sub_name=new String[8];
	static String sc1,sc2,sc3,sc4,sc5,sc6,sc7,sc8;
	String[] sub_code=new String[8];
	static int strength;
	static int p1,p2,p3,p4,p5,p6,p7,p8;
	int[] pass=new int[8];
	
	public void callBC() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/ClassReport.fxml"));
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Result Analysis");
		}catch(Exception e) {
		}
	}
	
	public void loadBarChart(ActionEvent event) {
		bc.setAnimated(false);
		bc.getData().clear();
		Series<String,Double> series =  new XYChart.Series<String,Double>();
		try{
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
			
			p=con.prepareStatement("select * from subject_info where class_name like ?");
			p.setString(1,class_name);
			rs1=p.executeQuery();
			
			int i=0;
			
			while(rs1.next()) {
				sub_name[i]=rs1.getString("sub_name");
				i++;
			}
			rs1.close();
			
			s1=sub_name[0];
			s2=sub_name[1];
			s3=sub_name[2];
			s4=sub_name[3];
			s5=sub_name[4];
			s6=sub_name[5];
			s7=sub_name[6];
			s8=sub_name[7];
			p=con.prepareStatement("select * from subject_info where class_name like ?");
			p.setString(1,class_name);
			rs1=p.executeQuery();
			
			int j=0;
			
			while(rs1.next()) {
				sub_code[j]=rs1.getString("sub_code");
				j++;
			}
			rs1.close();
			
			sc1=sub_code[0];
			sc2=sub_code[1];
			sc3=sub_code[2];
			sc4=sub_code[3];
			sc5=sub_code[4];
			sc6=sub_code[5];
			sc7=sub_code[6];
			sc8=sub_code[7];
			
			p=con.prepareStatement("select count(*) from student_details where class_name like ?");
			p.setString(1,class_name);
			rs1=p.executeQuery();
			while(rs1.next()) {
				strength=Integer.parseInt(rs1.getString("count(*)"));
				break;
			}
			rs1.close();
			
			p=con.prepareStatement("select count(*) from marks_info where class_name like ? and sub_code like ? and result like ?");
			p.setString(1,class_name);
			p.setString(2,sc1);
			p.setString(3,"P");
			rs1=p.executeQuery();
			
			while(rs1.next()) {
				p1=Integer.parseInt(rs1.getString("count(*)"));
				break;
			}
			rs1.close();
			
			
			p=con.prepareStatement("select count(*) from marks_info where class_name like ? and sub_code like ? and result like ?");
			p.setString(1,class_name);
			p.setString(2,sc2);
			p.setString(3,"P");
			rs1=p.executeQuery();
			
			while(rs1.next()) {
				p2=Integer.parseInt(rs1.getString("count(*)"));
				break;
			}
			rs1.close();
			
			
			p=con.prepareStatement("select count(*) from marks_info where class_name like ? and sub_code like ? and result like ?");
			p.setString(1,class_name);
			p.setString(2,sc3);
			p.setString(3,"P");
			rs1=p.executeQuery();
			
			while(rs1.next()) {
				p3=Integer.parseInt(rs1.getString("count(*)"));
				break;
			}
			rs1.close();
			
			
			p=con.prepareStatement("select count(*) from marks_info where class_name like ? and sub_code like ? and result like ?");
			p.setString(1,class_name);
			p.setString(2,sc4);
			p.setString(3,"P");
			rs1=p.executeQuery();
			
			while(rs1.next()) {
				p4=Integer.parseInt(rs1.getString("count(*)"));
				break;
			}
			rs1.close();
			
			
			
			p=con.prepareStatement("select count(*) from marks_info where class_name like ? and sub_code like ? and result like ?");
			p.setString(1,class_name);
			p.setString(2,sc5);
			p.setString(3,"P");
			rs1=p.executeQuery();
			
			while(rs1.next()) {
				p5=Integer.parseInt(rs1.getString("count(*)"));
				break;
			}
			rs1.close();
			
			
			
			p=con.prepareStatement("select count(*) from marks_info where class_name like ? and sub_code like ? and result like ?");
			p.setString(1,class_name);
			p.setString(2,sc6);
			p.setString(3,"P");
			rs1=p.executeQuery();
			
			while(rs1.next()) {
				p6=Integer.parseInt(rs1.getString("count(*)"));
				break;
			}
			rs1.close();
			
			
			p=con.prepareStatement("select count(*) from marks_info where class_name like ? and sub_code like ? and result like ?");
			p.setString(1,class_name);
			p.setString(2,sc7);
			p.setString(3,"P");
			rs1=p.executeQuery();
			
			while(rs1.next()) {
				p7=Integer.parseInt(rs1.getString("count(*)"));
				break;
			}
			rs1.close();
			
		
			
			p=con.prepareStatement("select count(*) from marks_info where class_name like ? and sub_code like ? and result like ?");
			p.setString(1,class_name);
			p.setString(2,sc8);
			p.setString(3,"P");
			rs1=p.executeQuery();
			
			while(rs1.next()) {
				p8=Integer.parseInt(rs1.getString("count(*)"));
				break;
			}
			rs1.close();
			
			series.getData().add(new XYChart.Data<String,Double>(s1,(double) ((p1/(double)strength)*100)));
			series.getData().add(new XYChart.Data<String,Double>(s2,(double) ((p2/(double)strength)*100)));
			series.getData().add(new XYChart.Data<String,Double>(s3,(double) ((p3/(double)strength)*100)));
			series.getData().add(new XYChart.Data<String,Double>(s4,(double) ((p4/(double)strength)*100)));
			series.getData().add(new XYChart.Data<String, Double>(s5,(double) ((p5/(double)strength)*100)));
			series.getData().add(new XYChart.Data<String, Double>(s6,(double) ((p6/(double)strength)*100)));
			series.getData().add(new XYChart.Data<String, Double>(s7,(double) ((p7/(double)strength)*100)));
			series.getData().add(new XYChart.Data<String, Double>(s8,(double) ((p8/(double)strength)*100)));
			
			bc.getData().add(series);
			
			for(final XYChart.Data<String,Double> data : series.getData()) {
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
}
