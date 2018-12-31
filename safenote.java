import java.awt.*;
import java.sql.*;

import javax.swing.*;

import java.awt.event.*;

public class safenote extends JFrame
	{
	    //declaring our swing components
	    JLabel l_name,l_pass;
	    JTextField t_name;
	    JPasswordField t_pass;     //A special JTextField but hides input text
	    JButton button;
	    JButton signu;
	    
	 
	    //a inner class to handling ActionEvents
	    handler handle;
	    JLabel l1;
	    //a separate class for processing database connection and authentication
	    database db;
	 
	    safenote()
	    {
	        super("Login form");
	 
	        
	 
	        //extra classes
	        db=new database();
	        handle =new handler();
	        
	        l1 = new JLabel("Enter details");
	        l1.setForeground(Color.blue);
	        l1.setFont(new Font("Serif", Font.BOLD, 20));

	 
	                //swing components
	        l_name=new JLabel("Username:");
	        
	        t_name=new JTextField();
	        
	        button=new JButton("Login");
	        signu=new JButton("Sign Up");
	        
	        l1.setBounds(20,30,200,30);
	        l_name.setBounds(20, 70, 100, 30);
	        
	        t_name.setBounds(150, 70, 200, 30);
	        
	       button.setBounds(50, 150, 100, 30);
	        signu.setBounds(170, 150, 100, 30);
	        
	        //adding actionlistener to the button
	        button.addActionListener(handle);
	        signu.addActionListener(handle);
	        
	        //add to contaienr
	        add(l1);
	        add(l_name);
	        add(t_name);
	        
	        add(button);
	        add(signu);
	        
	        //visual
	        
	        setLocation(new Point(500,200));
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(400,250);
	        setResizable(false);
	        setLayout(null);
	        setVisible(true);
	    }
	    
	   
	    
	    public static void main(String args[])
	    {
	        safenote sample=new safenote();
	    }
	 
	    //an inner class .You can also write as a separate class
	    class handler implements ActionListener
	    {
	        //must implement method
	        //This is triggered whenever the user clicks the login button
	        
	    	public void actionPerformed(ActionEvent ae)
	        {
	            //checks if the button clicked
	            
	    		if(ae.getSource()==button)
	            {
	                
	                
	                if((t_name.getText()).isEmpty())
	                {
	                	JOptionPane.showMessageDialog(null,"Enter valid data!", "Invalid!",
                                JOptionPane.ERROR_MESSAGE);
	                }
	 
	                //The entered username and password are sent via "checkLogin()" which return boolean
	                else
	                {
					if(db.checkLogin(t_name.getText()))
					{
					    new ql(1,(t_name.getText()));
						setVisible(false);
	                
					}
	                else
	                {
	                    //a pop-up box
	                    
	                	JOptionPane.showMessageDialog(null, "Login failed!","Failed!!",
	                                        JOptionPane.ERROR_MESSAGE);
	                	t_name.setText("");
		                
	                }
	          
			   }
	            }
	        
	            else if(ae.getSource()==signu)
	            {
	            	setVisible(false);
	            	new signup();
	                
	                
	            }
	        }
	 
	    }
	}

	
	
	
	
	
 class database 
	{
	    Connection con;
	    PreparedStatement pst;
	    ResultSet rs;
	    
	    database()
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
	               //this replaces the 2st  "?" in the query for password
	            
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
