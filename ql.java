import java.awt.*;
import java.sql.*;

import javax.swing.*;

import java.awt.event.*;

public class ql extends JFrame implements ActionListener
	{
	    //declaring our swing components
	    JLabel l_name,l_pass;
	    JTextField t_pass;
	         //A special JTextField but hides input text
	    JButton button;
	    JButton signu;
	   static  String st="";
		String sk;
	 
	    //a inner class to handling ActionEvents
	    
	    JLabel l1;
	    //a separate class for processing databa connection and authentication
	    databa db;
	 int j;
	    String uname;
		ql(int i,String name)
	    {
	        
			super("Login form");
	        uname=name;
			j=i;
			
	        
	 
	        //extra classes
	        db=new databa();
	        
	        
	        String q= db.getques(uname,j);
	        
	       
	                //swing components
	        
			l_name=new JLabel("Question "+i+":    "+q);
	        
			l_pass=new JLabel("Answer:");
	        t_pass=new JTextField();
	        
	        button=new JButton("Next");
	        
	        
	        l_name.setBounds(20, 20, 110, 30);
	        l_pass.setBounds(20,60,100,30);
	        t_pass.setBounds(130, 60, 200, 30);
	        
	       button.setBounds(150, 150, 100, 30);
	        
	        
	        //adding actionlistener to the button
	        button.addActionListener((ActionListener)this);
	      
	        
	        //add to contaienr
	        
	        add(l_name);
	        add(t_pass);
			add(l_pass);
	        
	        add(button);
	               //visual
	        
	        setLocation(new Point(500,200));
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(400,250);
	        setResizable(false);
	        setLayout(null);
	        setVisible(true);
	    }
	    
	    
	    //an inner class .You can also write as a separate class
	  
			
	    	public void actionPerformed(ActionEvent ae)
	        {
	            //checks if the button clicked
	            
	    		if(ae.getSource()==button)
	            {
	               String pwd=t_pass.getText();
	                if(pwd.isEmpty())
	                {
	                	JOptionPane.showMessageDialog(null,"Enter valid data!", "Invalid!",
                                JOptionPane.ERROR_MESSAGE);
	                }
	 
	                //The entered username and password are sent via "checkLogin()" which return boolean
	                else
	                {
	                    j++;        
					
					
						
				if(pwd.equals(db.checkLogin(uname, j-1)))
	                {
						String substring1 = pwd.length() > 4 ? pwd.substring(pwd.length() - 4) : pwd;
				
				st+=substring1;
				
						if(j<5)
						{
	                    //a pop-up box
	                    new ql(j,uname);
					setVisible(false);
					
						}
						else
	                   {  
                           				
	              
						JOptionPane.showMessageDialog(null, "You have logged in successfully","Success",
	                                        JOptionPane.INFORMATION_MESSAGE);
						
						new Steganography_Controller(
									new Steganography_View("Steganography"),
									new Steganography());
									//System.out.println(st);
						setVisible(false);
					}}
	           
	                else
	                {
	                    //a pop-up box
	                    
	                	JOptionPane.showMessageDialog(null, "Wrong Answer!","Try Again!",
	                                        JOptionPane.ERROR_MESSAGE);
	                	j--;
					
					new ql(j,uname);
		                t_pass.setText("");
	                
					}
						
						
					
					}
					
				
					
	                }
	            }
	        
	            
	        
			
	

	
			
			String gen()
			{
 System.out.println(st);
			return st;
			}
			}
	 
	    
	

	
	
	
	
	
 class databa 
	{
	    Connection con;
	    PreparedStatement pst,pst1;
	    ResultSet rs;
	    
	    databa()
		
	    {  
	        try
	        {
	             
	            //MAKE SURE YOU KEEP THE mysql_connector.jar file in java/lib folder
	            //ALSO SET THE CLASSPATH
	           
	        	Class.forName("com.mysql.jdbc.Driver");
	            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sat","root","root");
	            pst=con.prepareStatement("select ans? from sano where uname=?");
	            pst1=con.prepareStatement("select ques? from sano where uname=?");
	           }
	        
	        catch (Exception e) 
	        {
	            System.out.println(e);
	        }
	    }
	        //ip:username,password
	        //return boolean
	    
		String getques(String uname,int x)
		{
		try{
			pst1.setInt(1,x);
			pst1.setString(2,uname);
			rs=pst1.executeQuery();
			rs.next();
			String s=rs.getString(1);
			return s;
		}
        catch(Exception e)
		{
			System.out.println(e);
			return("error");
		}		
		}
	       String checkLogin(String name , int l)
	       {
                   String uname=name;int k=l;
		   try 
	        {
	                        
	            pst.setInt(1, k);
                pst.setString(2,uname);	
					
					
				//this replaces the 1st  "?" in the query for username
	                //this replaces the 2st  "?" in the query for password
	            
	            //executes the prepared statement
					 rs=pst.executeQuery();
					 rs.next();
					 
					 return (rs.getString(1));
					 
	            
	        } 
	        
	        catch (Exception e) 
	        {
	            // TODO Auto-generated catch block
	            System.out.println("error while validating"+e);
	           return ("error");
	        }
			
	    }

}
