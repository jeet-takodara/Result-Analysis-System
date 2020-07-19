package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;

public class Login {
	@FXML
	private Label labelStatus;
	@FXML
	public TextField LoginUsername;
	@FXML
	private TextField LoginPassword;
	@FXML
	private AnchorPane ap;
	
	static String userid;
	
	public void doLogin(ActionEvent event) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select userid,password from faculty_register");
			boolean val=false;
			while(rs.next()) {
				if((rs.getString("userid").equals(LoginUsername.getText())) && (rs.getString("password").equals(LoginPassword.getText()))) {
					userid=LoginUsername.getText();
					labelStatus.setText("Login Successfull!");
					LoginUsername.clear();
					LoginPassword.clear();
					val=true;
					StudentTable st = new StudentTable();
					callREGISTERfxml();
					st.initialize(null,null);
					break;
				}
			}
			if(!val) {
				labelStatus.setText("Invalid userID or Password!");
			}
		}catch(Exception e) {
			
		}
	}
	
	public void callREGISTERfxml() {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("/application/FacultyHomePage.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Result Analysis");
	}catch(Exception e) {	
	}
}

	
	@FXML
	private Label Registerlabelstatus;
	@FXML
	private TextField RegisterFN;
	@FXML
	private TextField RegisterLN;
	@FXML
	private TextField RegisterUID;
	@FXML
	private TextField RegisterPWD;
	@FXML
	private TextField RegisterDEPT;
	@FXML
	private TextField RegisterDESIG;
	
	public void callRegister(ActionEvent event) {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Register.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage=new Stage();
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		}catch(Exception e) {
		}
	}
	
	public void doRegister(ActionEvent event) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select userid from faculty_register");
			again:{
			String fn = RegisterFN.getText();
			String ln=RegisterLN.getText();
			String uid=RegisterUID.getText();
			String pwd=RegisterPWD.getText();
			String dept=RegisterDEPT.getText();
			String desig=RegisterDESIG.getText();
			boolean value=true;
			
			while(rs.next()) {
				if((rs.getString("userId")).equals(uid)) {
					Registerlabelstatus.setText("userID already exixts!");
					value=false;
					break again;
				}
			}
			if(value) {
				PreparedStatement pstmt = con.prepareStatement("insert into faculty_register values(?,?,?,?,?,?)");
				pstmt.setString(1,uid);
				pstmt.setString(2,pwd);
				pstmt.setString(3,fn);
				pstmt.setString(4,ln);
				pstmt.setString(5,dept);
				pstmt.setString(6,desig);
				pstmt.executeUpdate();
				Registerlabelstatus.setText("Registered Successfully!");
				con.commit();
				RegisterFN.clear();
				RegisterLN.clear();
				RegisterUID.clear();
				RegisterPWD.clear();
				RegisterDEPT.clear();
				RegisterDESIG.clear();
				}
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println(e);
		}
   }	
}