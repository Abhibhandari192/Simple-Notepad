import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.URL;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Notepad extends JFrame implements ActionListener{
	
	final JFrame jf;
	JTextArea ta;
	JMenuBar menubar;
	JMenu file,edit,help;
	JMenuItem open,save,save_as,paste,copy,cut,about,exit;
	byte check=0;	
	File f1 = null;
	
	Notepad()
	{
		jf=new JFrame();
		jf.setResizable(false);
		jf.setTitle("Simple Notepad");
		
		ta=new JTextArea();
		ta.setBounds(0, 30, 350, 300);
		
		open=new JMenuItem("Open");
		save=new JMenuItem("Save");
		save_as=new JMenuItem("Save as");
		paste=new JMenuItem("Paste");
		copy=new JMenuItem("Copy");
		cut=new JMenuItem("Cut");
		about=new JMenuItem("About Notepad");
		exit=new JMenuItem("Exit");
		
		open.addActionListener(this);
		save.addActionListener(this);
		save_as.addActionListener(this);
		paste.addActionListener(this);
		copy.addActionListener(this);
		cut.addActionListener(this);
		about.addActionListener(this);
		exit.addActionListener(this);
		
		file=new JMenu("File");
		edit=new JMenu("Edit");
		help=new JMenu("Help");
		
		file.add(open);
		file.add(save);
		file.add(save_as);
		file.add(exit);
		edit.add(copy);
		edit.add(paste);
		edit.add(cut);
		help.add(about);
				
		menubar=new JMenuBar();
		menubar.setBounds(0, 0, 400, 30);
		
		menubar.add(file);
		menubar.add(edit);
		menubar.add(help);
		
		jf.add(ta);
		jf.add(menubar);
		jf.setSize(350,300);
		jf.setLayout(null);
		jf.setVisible(true);
		
		
		
		jf.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	if(!(ta.getText().isEmpty()))
		    	{
		    		JDialog.setDefaultLookAndFeelDecorated(true);
				    int response = JOptionPane.showConfirmDialog(jf, "Do you want to exit without saving?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      
				    } else if (response == JOptionPane.YES_OPTION) {
				      System.exit(0);
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				      System.out.println("JOptionPane closed");
				    }
		    	}
		    }
		});
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getSource()==copy)
		{
			ta.copy();
		}
		
		if(e.getSource()==paste)
		{
			ta.paste();
		}
		
		if(e.getSource()==cut)
		{
			ta.cut();			
		}
		
		if(e.getSource()==open)
		{
			ta.setText("");
			try
			{
				
				JFileChooser open = new JFileChooser();
				int option = open.showOpenDialog(this);
				f1 = new File(open.getSelectedFile().getPath());
				FileReader fr = new FileReader(f1);
				BufferedReader br = new BufferedReader(fr);
				String s;
				check=1;
				
				System.out.println("Open "+f1);
				
			while((s=br.readLine())!=null)
			{
				ta.append(s + "\n");
			}
			
				fr.close();
			}catch(Exception ae)
			 {
				System.out.println(ae);
			 }
			
		}
		
		
		
		if(e.getSource()==save)
		{
			if(check==1)
			{				
				String save_path=f1.toString();
				String all_data=ta.getText();
				int len=all_data.length();
		        byte buf[]=all_data.getBytes();
		        
		        File file_saveas=new File(save_path);
		        FileOutputStream fobj1 = null;
				try {
					fobj1 = new FileOutputStream(file_saveas);
					for(int k=0;k<len;k++)
			        {
						fobj1.write(buf[k]);
			        }
					fobj1.close();
			        
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			else if(ta.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "No content to save.");
			}
			
			else
			{
				FileDialog dialog1=new FileDialog(this,"Save As",FileDialog.SAVE);
		        dialog1.setVisible(true);

		        String dir=dialog1.getDirectory();
		        String file_name=dialog1.getFile();
		        String file_path=dir+file_name+".txt";


		        String str6=ta.getText();
		        int len1=str6.length();
		        byte buf[]=str6.getBytes();

		        File file_saveas=new File(file_path);
		        FileOutputStream fobj1 = null;
				try {
					fobj1 = new FileOutputStream(file_saveas);
					for(int k=0;k<len1;k++)
			        {
						fobj1.write(buf[k]);
			        }
					fobj1.close();
			        
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
		
		
		
		if(e.getSource()==save_as)
		{
			FileDialog dialog1=new FileDialog(this,"Save As",FileDialog.SAVE);
	        dialog1.setVisible(true);

	        String dir=dialog1.getDirectory();
	        String file_name=dialog1.getFile();
	        String file_path=dir+file_name+".txt";


	        String str6=ta.getText();
	        int len1=str6.length();
	        byte buf[]=str6.getBytes();

	        File file_saveas=new File(file_path);
	        FileOutputStream fobj1 = null;
			try {
				fobj1 = new FileOutputStream(file_saveas);
				for(int k=0;k<len1;k++)
		        {
					fobj1.write(buf[k]);
		        }
				fobj1.close();
		        
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        
	        
	        }

	        
		if(e.getSource()==about)
		{
			int i = 0;
			ta.setText("");
			final String s="The Simple Notepad is coded by Abhishek Bhandari :-)";
		
						
			new Thread(){     
			      public void run(){
			         for(int i = 0; i < s.length(); i++)
			          {
			            // ta is the JTextArea
			            ta.append(""+s.charAt(i));
			            try{Thread.sleep(100);}catch(Exception e){}
			          }
			      }
			   }.start();
		}   
					
		
		if(e.getSource()==exit)
		{
			if(!(ta.getText().isEmpty()))
			{
				JDialog.setDefaultLookAndFeelDecorated(true);
			    int response = JOptionPane.showConfirmDialog(jf, "Do you want to exit without saving?", "Confirm",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			    if (response == JOptionPane.NO_OPTION) {
			      
			    } else if (response == JOptionPane.YES_OPTION) {
			      System.exit(0);
			    } else if (response == JOptionPane.CLOSED_OPTION) {
			      System.out.println("JOptionPane closed");
			    }
			}
			
			else
			{
				System.exit(0);
			}
		}
	}

	
	public static void main(String a[])
	{
		Notepad note=new Notepad();
	}

	
}
