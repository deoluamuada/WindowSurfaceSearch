
package mywindowsearch;
import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;
import javax.swing.event.*;
import javax.swing.JOptionPane;
import javax.swing.text.*;
//Read line input.
import java.io.*; //to handle RandomAccessFile
public class MyWindowSearch extends JFrame implements ListSelectionListener, ActionListener{
	
	protected JButton bLookin, bText, bExit, bHelp, bAbout, bCopy;
	private JTextField tLookin, tText, tStatus;
	private JLabel lLookin, lText, lResults, lStatus;
	private Container c ;
	private JList list;
	private DefaultListModel model;
	private JPanel left, right, south;
	private JTextArea tContent;
	public long count;
	public static final byte BUFFER_SIZE=100;
	private JFileChooser chooser = new JFileChooser();
		
public MyWindowSearch(){
	
	super(" MySearch by E- Badmus - Junior Software Developer");
	int WIDTH = 600;
	int HEIGHT = 300;
	
	setup();
	
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
 	int screenHeight = screenSize.height;
 	int screenWidth = screenSize.width;

 	// center frame in screen
	setSize(WIDTH, HEIGHT);
	setLocation((screenWidth-WIDTH) / 2, (screenHeight-HEIGHT) / 2);
	// set frame icon
	Image img = kit.getImage("icon.jpg");
	setIconImage(img);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	show();
	
	}
	
	/**
    * The setup() method is used to allocate the panel and
    * button objects using the new function.
    */
   public void setup()   {
      c = getContentPane() ;


      /** setup text field and panel */
      lLookin  = new JLabel("Look in: ", JLabel.RIGHT) ;
      lLookin.setDisplayedMnemonic('L');
      tLookin = new JTextField(15);
      tLookin.setToolTipText("Enter path to search.");
      bLookin = new JButton("Browse");
      bLookin.setMnemonic('B');
      bLookin.addActionListener(this);
      
      lText = new JLabel("Containing text: ", JLabel.RIGHT);
      lText.setDisplayedMnemonic('t');
      tText = new JTextField(13);
      tText.setToolTipText("Enter word to search.");
      bText = new JButton("Search Now");
      bText.setMnemonic('S');
      bText.addActionListener(this);
      
      lResults = new JLabel("Results:");
      lStatus = new JLabel("mySearch:");
      
      tStatus = new JTextField(22);
      tStatus.setEditable(false);
      tStatus.setText("Ready");
      
      
      model = new DefaultListModel();
      
      list = new JList(model);
      // Monitor all list selections
      list.addListSelectionListener(this);
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
      JScrollPane listpane = new JScrollPane(list);
            
      left = new JPanel() ;
      
      left.add(lLookin);
      left.add(tLookin);
      left.add(bLookin);
      
      left.add(lText);
      left.add(tText);
      left.add(bText);
      
      left.add(lResults);
      left.add(listpane);
      
      left.add(lStatus);
      left.add(tStatus);

      c.add(left , BorderLayout.CENTER) ;
      // right panel
      tContent = new JTextArea(12,20);
                
      //tContent.setLineWrap(true); //uncomment this line if u want to line wrap in ur file's content
	  
	  JScrollPane contentpane = new JScrollPane(tContent);
      
      right = new JPanel();
      right.add(contentpane);
      c.add(right, BorderLayout.EAST);
      //Exit panel
      bExit = new JButton("Exit");
      bExit.setMnemonic('x');
      bExit.addActionListener(this);
      
      bCopy = new JButton("Copy all to");
      bCopy.setMnemonic('C');
      bCopy.addActionListener(this);
      
      bHelp = new JButton("Help");
      bHelp.setMnemonic('H');
      bHelp.addActionListener(this);
      
      bAbout = new JButton("About");
      bAbout.setMnemonic('A');
      bAbout.addActionListener(this);
      
      south = new JPanel();
      south.add(bExit);
      south.add(bCopy);
      south.add(bHelp);
      south.add(bAbout);
      
           
      c.add(south, BorderLayout.SOUTH);
      
      // set up file chooser
	  chooser.setCurrentDirectory(new File("."));
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
           
}

//** actionPerformed() **/
public void actionPerformed( ActionEvent e ){
	if (e.getSource() == bLookin){
		// show file chooser dialog
		int result = chooser.showOpenDialog(MyWindowSearch.this);
 		if(result == JFileChooser.APPROVE_OPTION)
 		{
 			tLookin.setText(chooser.getSelectedFile().getPath());
		}
			
		
	}
	else if (e.getSource() == bText){
		if (tLookin.getText().length()<=0){
			JOptionPane.showMessageDialog(null,"Enter a correct path to search","mySearch",2);
			bLookin.requestFocus();
			return;
		}
		if (tText.getText().length()<=0){
			JOptionPane.showMessageDialog(null,"Enter a text to search","mySearch",2);
			tText.requestFocus();
			return;
		}
		
                
		File strPath = new File(tLookin.getText());
		if (strPath.isDirectory()==true)
		{
			// clear old list, old content
			count = 0;
			model.removeAllElements();
			tContent.setText("");
					
			String path = tLookin.getText();
			File dir = new File(path);
			itmanvn(dir);
			
			if (count==0) 
				tStatus.setText("Search complete. No results to display");
			else
				tStatus.setText(count+" file(s) found. Click on file to view content");

		}
		else
			{
				JOptionPane.showMessageDialog(null,"Wrong path","mySearch",2);
				tLookin.selectAll();
				tLookin.requestFocus();
			}
	}
	else if (e.getSource() == bExit){
		if (JOptionPane.showConfirmDialog(null,"Are you sure you want to Your DeepWindowSearchexit?","mySearch",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
		{	
			System.out.println();
			System.out.println("Thank You For Using myDeepWindowSearch Developed by E-Badmus.");
			System.out.println();
			System.exit(0);
		}
	}
	
	else if (e.getSource() == bHelp){
		JOptionPane.showMessageDialog(null,"MyWindowSearch v1.0 Developed by E- Badmus." + "\n" + "Easy to make A Deep Search Through Your Windows . For more developer@oysec.com"+ "\n" + "or Email me at - badmusamuda@gmail.com"+ "\n" + "My Homepage - www.9jadeveloper.com");	
	}
	else if (e.getSource() == bAbout){
		Runtime rt = Runtime.getRuntime();
            String[] callAndArgs = { "c:\\Program Files\\Internet Explorer\\IExplore.exe" ,
                           "http://9jadeveloper.com" };
            try {
               Process child = rt.exec(callAndArgs);
               child.waitFor();
            }
            catch(IOException e2) {
               System.out.println("Found error: "+e2);
            }
            catch(InterruptedException e3) {
               System.out.println("Found error: "+e3);
            }	
	}
	else if (e.getSource() == bCopy){
		if (count==0)
			JOptionPane.showMessageDialog(null,"Nothing to copy","mySearch",2);
		else
		{
			// show file chooser dialog
			int result = chooser.showOpenDialog(MyWindowSearch.this);
 			if(result == JFileChooser.APPROVE_OPTION)
 			{
 				try
	 			{
	 				String todir=chooser.getSelectedFile().getPath();
	 				for (int i=0;i<count;i++)//duyet file trong list
					{
							list.setSelectedIndex(i);
							File from = new File(list.getSelectedValue().toString()); // source file
							
							String newFile=todir+from.separator + from.getName();
							
							File temp = new File(newFile);
							if (temp.exists()==true)
							{
								if (JOptionPane.showConfirmDialog(null,"The file "+temp.getName()+" is already in existence.\nChoose Yes if you want overwrite,\nor No to auto rename it.","myWindnowSearch",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
								{
									File to = new File(newFile);// destination file
									copyFile(from,to);	
								}
								else
									{
										newFile=todir+from.separator+i+from.getName();//doi ten file neu exist
										File to = new File(newFile);// destination file
										copyFile(from,to);	
									}
							}
							else
							{
								File to = new File(newFile);// destination file
								copyFile(from,to);
							}
							
					}
				tStatus.setText(count+" file(s) copied");
 				}
 				catch (IOException err) {
               	System.out.println(err);}
 			}
				
		}
	}
}
/************ End of actionPerformed() **************/

//** itmanvn() **/
public void itmanvn(File dir){
	
	String where,what=tText.getText().trim();
	
	File[] list = dir.listFiles();
	
	for (int i=0; i<list.length; i++) {
			//long id = -1;
			if (list[i].isFile() && valid_text_file(list[i])){
				try
				{	
					RandomAccessFile in = new RandomAccessFile(list[i].getPath(),"r");
					long length = in.length();
					long Pointer = 0;
					
					while (Pointer<length)
					{
						where = in.readLine();
						if(where.trim().compareTo("")!=0 && where.length()>0 && where!=null)
							if (searchString(what,where)!=-1)
								{
									model.addElement(list[i].getPath());
									count +=1;
									in.close();
									break;
								}
						Pointer=in.getFilePointer();
					
					}
				in.close();
										
				}
				catch(Exception e1)
				{
					System.out.println("Error reading file: "+list[i].getPath().toString());
					System.out.println(e1);
					break;
				}
			}
			else if (list[i].isDirectory()){
				itmanvn(list[i]);
				}
	}
	
}
/************ End of itmanvn() **************/


//** searching in searchstring **/
public static int searchString(String what,String where)
	{
		what=what.toUpperCase();
		where=where.toUpperCase();
		
		int i=0;
		int j; 
		int l1=what.length();
		int l2=where.length();
		byte flag=0;
		
		do
		{
			if (what.charAt(0)==where.charAt(i))
			{
				flag=1;
				j=1;
				while (j<l1)
				{
					if (i+j>=l2)
						flag=0;
					else
						if (what.charAt(j)!=where.charAt(i+j))
							flag=0;
					j++;
				}
			}
			i++;
		}
		while (i<l2 && flag==0);
		if (flag==1)
			return (--i); //vi tri dau tien tim duoc

		return (-1);

	}
/************ end of searchString() **************/

//** copy file **/

public static void copyFile(File from, File to)
										throws IOException {
	
	BufferedInputStream in = new BufferedInputStream(new FileInputStream(from));
	BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(to));
	byte buffer[] = new byte[BUFFER_SIZE];
	int dem;
	
	while ((dem = in.read(buffer)) != -1) {
		out.write(buffer, 0, dem);
	}
	in.close();
	out.close();
}

/************ end of copyFile() **************/



//** list changed && showContent() **//
//******** this show content is the fastest in my class do NOT an cap ban quyen of Bui Duc Long CDTH3A

public void valueChanged(ListSelectionEvent e) {
		// If either of these are true, the event can be ignored.
		if ((e.getValueIsAdjusting() == false) || (e.getFirstIndex() == -1))
			return;
		tContent.setText("");
		File file = new File(list.getSelectedValue().toString());
		try
			{
				
				BufferedReader content = new BufferedReader(new FileReader(file));
				
				String line=""; 
                
                while ((line = content.readLine()) != null) 
                	tContent.append(line+"\n");
				
				content.close();
    		}
			catch(Exception e1)
			{
				System.out.println("Found error: "+e1);
			}	
}
/************ End of list changed() && show content **************/


//**  valid_text_file() **/

public boolean valid_text_file(File what){
	String file = what.toString();
	
	if (file.endsWith(".txt")  	||
	   	file.endsWith(".TXT"))	
	
		return true;
	else
		return false;
}


/************ End of valid_text_file() **************/

//**  main() **/
	public static void main( String args[] ) {
            
            JOptionPane.showMessageDialog(null, "Welcome To My First Window Search Application In GUI","Make A Deep Search In Your "
                    + "Windows\n Search Throught Windows Any Text isnde your filelocation  \n DevelopedBy E- Badmus ",JOptionPane.INFORMATION_MESSAGE);

      final MyWindowSearch app = new MyWindowSearch () ; /** creates an instance of MySearch  */ 
      app.setResizable(false);
   }
/************ End of main() **************/

}
