package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFReport {
	
	String USN,CLASS,NAME;
	int m1,m2,m3,m4,m5,m6,m7,m8;
	String [] sub_code = new String[8];
	String [] sub_name = new String[8];
	StudentReport sr = new StudentReport(); 
	
	
	public static void main(String[] args) {
		PDFReport p =new PDFReport();
		p.createReport();
	}
	
	public void createReport() {
		Document document = new Document();

        try {
        	
        	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
        	
			PreparedStatement p = con.prepareStatement("select * from class_info where userid like ?");
			p.setString(1,Login.userid);
			ResultSet rs1 = p.executeQuery();
			
			while(rs1.next()) {
				CLASS=rs1.getString("class_name");
				break;
			}
			rs1.close();
			
			USN=StudentReport.SN;
			
			p=con.prepareStatement("select * from student_details where class_name like ?");
			p.setString(1,CLASS);
			rs1=p.executeQuery();
			while(rs1.next()) {
				NAME=rs1.getString("NAME");
				break;
			}
			rs1.close();
			
			p=con.prepareStatement("select * from subject_info where class_name like ?");
			p.setString(1,CLASS);
			rs1=p.executeQuery();
			
			int i=0;
			
			while(rs1.next()) {
				sub_name[i]=rs1.getString("sub_name");
				i++;
			}
			rs1.close();
			
			int j=0;
			p=con.prepareStatement("select * from subject_info where class_name like ?");
			p.setString(1,CLASS);
			rs1=p.executeQuery();
			while(rs1.next()) {
				sub_code[j]=rs1.getString("sub_code");
				j++;
			}
			rs1.close();
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,USN);	
			p.setString(2,sub_code[0]);
			rs1=p.executeQuery();
			while(rs1.next()) {
				m1=Integer.parseInt(rs1.getString("total"));
				break;
			}rs1.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,USN);	
			p.setString(2,sub_code[1]);
			rs1=p.executeQuery();
			while(rs1.next()) {
				m2=Integer.parseInt(rs1.getString("total"));
				break;
			}rs1.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,USN);	
			p.setString(2,sub_code[2]);
			rs1=p.executeQuery();
			while(rs1.next()) {
			    m3=Integer.parseInt(rs1.getString("total"));
			    break;
			}rs1.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,USN);	
			p.setString(2,sub_code[3]);
			rs1=p.executeQuery();
			while(rs1.next()) {
			    m4=Integer.parseInt(rs1.getString("total"));
			    break;
			}rs1.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,USN);	
			p.setString(2,sub_code[4]);
			rs1=p.executeQuery();
			while(rs1.next()) {
			    m5=Integer.parseInt(rs1.getString("total"));
			    break;
			}rs1.close();
		
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,USN);	
			p.setString(2,sub_code[5]);
			rs1=p.executeQuery();
			while(rs1.next()) {
				m6=Integer.parseInt(rs1.getString("total"));
				break;
			}rs1.close();
			
			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,USN);	
			p.setString(2,sub_code[6]);
			rs1=p.executeQuery();
			while(rs1.next()) {
			    m7=Integer.parseInt(rs1.getString("total"));
			    break;
			}rs1.close();


			
			p=con.prepareStatement("select * from marks_info where usn like ? and sub_code like ?");
			p.setString(1,USN);	
			p.setString(2,sub_code[7]);
			rs1=p.executeQuery();
			while(rs1.next()) {
			    m8=Integer.parseInt(rs1.getString("total"));
			    break;
			}
			rs1.close();
			
        	
        	PdfWriter.getInstance(document,
                    new FileOutputStream(USN+".pdf"));
            document.open();

            document.add(new Paragraph("USN : "+USN));
            document.add(new Paragraph("NAME : "+NAME));
            document.add(new Paragraph("CLASS : "+CLASS));
            document.add(new Paragraph(sub_code[0]+" "+sub_name[0]+"    "+m1));
            document.add(new Paragraph(sub_code[1]+" "+sub_name[1]+"    "+m2));
            document.add(new Paragraph(sub_code[2]+" "+sub_name[2]+"    "+m3));
            document.add(new Paragraph(sub_code[3]+" "+sub_name[3]+"    "+m4));
            document.add(new Paragraph(sub_code[4]+" "+sub_name[4]+"    "+m5));
            document.add(new Paragraph(sub_code[5]+" "+sub_name[5]+"    "+m6));
            document.add(new Paragraph(sub_code[6]+" "+sub_name[6]+"    "+m7));
            document.add(new Paragraph(sub_code[7]+" "+sub_name[7]+"    "+m8));

            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
