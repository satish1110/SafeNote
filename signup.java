import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class signup extends JFrame implements ActionListener
{
	    JLabel l1, l2,  l4, l5;
	    JTextField  tf1;
	    JButton btn1, btn2;
	    JPasswordField p1, p2;
	 
	   signup()
	     {
	       
	 
	        l1 = new JLabel("Enter details");
	        l1.setForeground(Color.blue);
	        l1.setFont(new Font("Serif", Font.BOLD, 20));
	 
	        l2 = new JLabel("Userame:");
	        
	        tf1 = new JTextField();
	        
	        btn1 = new JButton("Next");
	        btn2 = new JButton("Clear");
	 
	        btn1.addActionListener((ActionListener) this);
	        btn2.addActionListener((ActionListener) this);
	 
	        l1.setBounds(20, 30, 200, 30);
	        l2.setBounds(20, 70, 150, 30);
	        
	        
	        tf1.setBounds(200, 70, 150, 30);
	        
	        
	 
	        btn1.setBounds(50, 150, 100, 30);
	        btn2.setBounds(170, 150, 100, 30);
	 
	        add(l1);
	        add(l2);
	        add(tf1);
	        
	        add(btn1);
	        add(btn2);
	    
	     
	        
	        setLocation(new Point(500,200));
	        setSize(400, 250);
	        setResizable(false);
	        setLayout(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("Sign up form");
	        setVisible(true);
	     }
	 
	    public void actionPerformed(ActionEvent e) 
	     {
	        if (e.getSource() == btn1)
	         {
	            int x = 0;
	            String s1 = tf1.getText();
	            
	            if(s1.isEmpty())
                {
                	JOptionPane.showMessageDialog(null,"Enter valid data!", "Invalid!",
                            JOptionPane.ERROR_MESSAGE);
                
				} 
				else
				{
	           
			   
	       
	            
	           
	        	   PreparedStatement ps;
	               databas db=new databas(); 
	        	   
	               if(!db.checkLogin(s1))
	               {
					   setVisible(false);
			   
			            new qs(1,s1);
	               try
	               {
	                    Class.forName("com.mysql.jdbc.Driver");
	                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sat","root","root");
	                    
	                    String sq = "insert into sano(uname) values(?)";
	                    ps = con.prepareStatement(sq);
	                    ps.setString(1, s1);
	                    
	                    
	                    ps.executeUpdate();
	                                
	                }
	          
	                catch (Exception ex) 
	                {
	                    System.out.println(ex);
	                }
	            }
	               
	               else
	               {
	            	   JOptionPane.showMessageDialog(null, "Please enter a unique username!","Try again!",
                               JOptionPane.ERROR_MESSAGE);
	               }
	           }
	           
	           
	         } 
	         

  else
	       {
	            tf1.setText("");
	            
	            
	            
	        }
	    
	    }
	   
	}


class databas 
	{
	    Connection con;
	    PreparedStatement pst;
	    ResultSet rs;
	    
	    databas()
	    {
	        try
	        {
	             
	            //MAKE SURE YOU KEEP THE mysql_connector.jar file in java/lib folder
	            //ALSO SET THE CLASSPATH
	           
	        	Class.forName("com.mysql.jdbc.Driver");
	            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sat","root","root");
	            pst=con.prepareStatement("select * from sano where uname=?");
	             
	           }
	        
	        catch (Exception e) 
	        {
	            System.out.println(e);
	        }
	    }
	        //ip:username,password
	        //return boolean
	    
	    public Boolean checkLogin(String uname)
	    {
	        try 
	        {
	                        
	            pst.setString(1, uname); //this replaces the 1st  "?" in the query for username
	            
	            //executes the prepared statement
	            rs=pst.executeQuery();
	            
	            if(rs.next())
	            {
	                //TRUE iff the query founds any corresponding data
	                return true;
	            }
	            else
	            {
	                return false;
	            }
	        } 
	        
	        catch (Exception e) 
	        {
	            // TODO Auto-generated catch block
	            System.out.println("error while validating"+e);
	            return false;
	        }
	    }

}
