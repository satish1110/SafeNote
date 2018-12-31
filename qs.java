  import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class qs extends JFrame implements ActionListener
{
	    JLabel l1, l2,  l4;
	    JTextField  tf2,tf4;
	    JButton btn1, btn2;
	    JPasswordField p1, p2;
	     int j=1;
	   String uname;
	   
	   qs(int i,String name)
	     {
	        uname=name;
             j=i;
             j++;			 
	        l1 = new JLabel("Question"+(j-1));
	        l1.setForeground(Color.blue);
	        l1.setFont(new Font("Serif", Font.BOLD, 20));
	 
	        l2 = new JLabel("Question:");
	        l4 = new JLabel("Answer:");
	        
	        tf2 = new JTextField();
	        tf4 = new JTextField();
			
	        
	        
	 
	        btn1 = new JButton("Next");
	 
	        btn1.addActionListener((ActionListener) this);
	        
	 
	        l1.setBounds(20, 30, 200, 30);
	        l2.setBounds(20, 70, 200, 30);
	        l4.setBounds(20, 110, 200, 30);
	        
	        
	        tf2.setBounds(220, 70, 200, 30);
	        tf4.setBounds(220,110,200,30);
			
	        
	        
	        
	        btn1.setBounds(50, 200, 100, 30);
	        
	 
	        add(l1);
	        add(l2);
	        add(tf2);
	        add(l4);
	        add(tf4);
	        add(btn1);
	        
	    
	     
	        
	        setLocation(new Point(500,200));
	        setSize(500, 300);
	        setResizable(false);
	        setLayout(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("Sign up form");
	        setVisible(true);
	        
		}
	 
	    public void actionPerformed(ActionEvent e) 
	     {
			 int x=0;
	        if (e.getSource() == btn1)
	         {
	            
	            String s2 = tf2.getText();
				String s4 = tf4.getText();
	             
	           
	            if(s2.isEmpty())
                {
                	JOptionPane.showMessageDialog(null,"Enter valid data!", "Invalid!",
                            JOptionPane.ERROR_MESSAGE);
                
				} 
	            
				else
				{
				if(j<5)
				{
				new qs(j,uname);
				setVisible(false);
				}
				
				else
				{
					 JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");
	                
					new safenote();
					setVisible(false);
				  
				}
	            
	            
	           
	        	   PreparedStatement ps;
	               databas db=new databas(); 
	        	   
	          
	               try
	               {
	                    Class.forName("com.mysql.jdbc.Driver");
	                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sat","root","root");
	                    
	                    String sq = "update sano set ques? = ?,ans? = ? where uname=?";
	                    ps = con.prepareStatement(sq);
						ps.setInt(1,j-1);
						ps.setInt(3,j-1);
	                    ps.setString(2, s2);
						ps.setString(4,s4);
						ps.setString(5,uname);
	                             
	                    ps.executeUpdate();
	                    x++;
	                    
	                    
	                }
	          
	                catch (Exception ex) 
	                {
	                    System.out.println(ex);
	                }
	            }
	               
	               
	           
	           
	          
	            }
		 }
}
	          
	         
		 
    
	
	
	


