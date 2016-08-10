import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;


@SuppressWarnings("serial")
public class AddressBookGUI extends JFrame {
	
	AddressBookManager bookManager;
	JTextField nameTF;
	JTextField phoneTF;
	DefaultTableModel dtm;
	JTable contactTable;
	JButton printButton;
	JTextArea textArea;
	JButton tableViewButton;
	JLabel noteLabel;
	JLabel nameLabel;
	JLabel phoneLabel;
	JButton addButton;
	
	AddressBookGUI(AddressBookManager bookManager){
		
		this.bookManager = bookManager;
		    	
    	JPanel contentPane = new JPanel(new BorderLayout());  
    	
    	this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                    System.exit(0);
                } // Closes windowClosing method
            });
    	
    	
    	textArea = new JTextArea(3, 4);
    	textArea.setBounds(1, 1, 600, 200);
    	textArea.setVisible(false);
    	textArea.setEditable(false);
    	contentPane.add(textArea,BorderLayout.PAGE_START);
    	
    	dtm = new DefaultTableModel();
    	dtm.addColumn("ID");
        dtm.addColumn("Name");
        dtm.addColumn("Phone Number");
        
                
        //create table with data
        contactTable = new JTable(dtm);        
        contactTable.setRowSelectionAllowed(true);
        //contactTable.setVisible(false);
        
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(5, 395, 50, 20);
        contentPane.add(nameLabel,BorderLayout.WEST);
        nameLabel.setAlignmentX(RIGHT_ALIGNMENT);
        
        nameTF = new JTextField();
        nameTF.setBounds(55, 395, 200, 20);  
        contentPane.add(nameTF,BorderLayout.WEST);
        
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(255, 395, 50, 20);
        contentPane.add(phoneLabel,BorderLayout.WEST);
        nameLabel.setAlignmentX(RIGHT_ALIGNMENT);
        
        phoneTF = new JTextField();
        phoneTF.setBounds(305, 395, 140, 20);  
        contentPane.add(phoneTF,BorderLayout.WEST);
        
        addButton = new JButton("ADD");        
        addButton.setBounds(445, 395, 70, 20);           
        contentPane.add(addButton,BorderLayout.WEST);
        
        noteLabel = new JLabel("*Select + RIGHT CLICK table row to delete contact.");
        noteLabel.setBounds(5, 420, 200, 20);
        noteLabel.setVisible(false);
        contentPane.add(noteLabel,BorderLayout.WEST);
        noteLabel.setAlignmentX(LEFT_ALIGNMENT);
        
        
        printButton = new JButton("PRINT");        
        printButton.setBounds(220, 420, 70, 10);           
        contentPane.add(printButton,BorderLayout.EAST);
        printButton.setVisible(false);
        
            
        tableViewButton = new JButton("Table View");        
        tableViewButton.setBounds(290, 420, 100, 10);           
        contentPane.add(tableViewButton,BorderLayout.EAST);
        tableViewButton.setVisible(false);
        
        //add the table to the frame
        contentPane.add(new JScrollPane(contactTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.PAGE_START);
      
        this.getContentPane().add(contentPane, BorderLayout.CENTER );
    	
    	this.pack();
    	this.setSize(600, 500);
    	this.setBackground(Color.white);
    	this.setVisible(true);
    	this.setLocation(500,50);
    	this.setResizable(true);        	
    	
    	addAddListener(addButton);    	
    	
    	addTableListener(dtm);
    	addRowSelectionListener(contactTable);
    	
    	addPrintListener(printButton);
    	addTableViewListener(tableViewButton);
	}
	
	private void addTableViewListener(AbstractButton button) {
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				contactTable.setVisible(true);
				textArea.setVisible(false);
				tableViewButton.setVisible(false);
				printButton.setVisible(true);
				noteLabel.setVisible(true);
				nameLabel.setVisible(true);
				phoneLabel.setVisible(true);
				nameTF.setVisible(true);
				phoneTF.setVisible(true);
				addButton.setVisible(true);
			}
			
		});
		
	}

	private void addPrintListener(AbstractButton button) {
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {				
				contactTable.setVisible(false);
				textArea.setText("");
				textArea.setVisible(true);
				List<Contact> contacts = new ArrayList<Contact>(bookManager.getContacts());
				Collections.sort(contacts, new Comparator<Contact>() {
					@Override
					public int compare(Contact contact1, Contact contact2) {
						// TODO Auto-generated method stub
						return contact1.getName().compareTo(contact2.getName());
					}
				});
				for(Contact contact:contacts){
					/*try {
						textArea.getDocument().insertString(0, contact.getName() + ", " + contact.getPhone() + "\n", null);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					textArea.append(contact.getName() + ", " + contact.getPhone() + "\n" );
				}
				printButton.setVisible(false);
				tableViewButton.setVisible(true);
				noteLabel.setVisible(false);
				nameLabel.setVisible(false);
				phoneLabel.setVisible(false);
				nameTF.setVisible(false);
				phoneTF.setVisible(false);
				addButton.setVisible(false);
			}
			
		});
		
	}

	private void addRowSelectionListener(final JTable table) {
		// TODO Auto-generated method stub
		table.addMouseListener(new MouseListener() {
			
			
			@Override
			public void mouseClicked(MouseEvent event) {
				// TODO Auto-generated method stub
				if(SwingUtilities.isRightMouseButton(event)){
										
					//int id =  Integer.parseInt(String.valueOf(dtm.getValueAt(table.getSelectedRow(), 0)));
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(table, "Delete Selected Row?", "Contact Deletion", dialogButton);
					if(dialogResult == JOptionPane.YES_OPTION){						
						dtm.removeRow(table.getSelectedRow());
						dtm.fireTableDataChanged();
					}
					
				}
			}

			@Override
			public void mousePressed(MouseEvent paramMouseEvent) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent paramMouseEvent) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent paramMouseEvent) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent paramMouseEvent) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public void addTableListener(final DefaultTableModel model) {
		// TODO Auto-generated method stub
		
		model.addTableModelListener(new TableModelListener(){

			@Override
			public void tableChanged(TableModelEvent event) {
				// TODO Auto-generated method stub
								
				//clean bookManager--
				if(bookManager.getContacts().size()>0){
					for(Contact contact:bookManager.getContacts()){
						bookManager.removeContact(contact.getId());
					}
				}else{
					printButton.setVisible(false);
					noteLabel.setVisible(false);
				}
								
				Contact contact;           	   
         	   //bookManager.addContact(contact);
				for(int i = 0; i<dtm.getRowCount();i++){
					
					if(!printButton.isVisible()){printButton.setVisible(true);}
					if(!noteLabel.isVisible()){noteLabel.setVisible(true);}
					
					int id =  Integer.parseInt(String.valueOf(dtm.getValueAt(i, 0)));
					String name =  String.valueOf(dtm.getValueAt(i, 1));
					String phone =  String.valueOf(dtm.getValueAt(i, 2));
					//System.out.println(" value @ 0-1 :" + dtm.getValueAt(0, 1));
					contact = new Contact(id,name,phone); 
					bookManager.addContact(contact);
				}				
			}		
			
		});
		
		
	}

	public void addAddListener(AbstractButton button){
        button.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {            	   
            	   
            	   if(null==nameTF.getText() || "".equalsIgnoreCase(nameTF.getText())){                		  
            		   nameTF.grabFocus();
            		   JOptionPane.showMessageDialog(null, "Name is required.", "Address Book", JOptionPane.ERROR_MESSAGE);
       	  			   return;
            	   }
            	   if(null==phoneTF.getText() || "".equalsIgnoreCase(phoneTF.getText())){                		  
            		   phoneTF.grabFocus();
            		   JOptionPane.showMessageDialog(null, "Phone is required.", "Address Book", JOptionPane.ERROR_MESSAGE);
       	  			   return;
            	   }            	   
            	   
            	   String name = nameTF.getText();
            	   String phone = phoneTF.getText();            	   
            	   
            	   if(exist(name,phone)){
            		   nameTF.grabFocus();
            		   JOptionPane.showMessageDialog(null, "Name already exist.", "Address Book", JOptionPane.ERROR_MESSAGE);
       	  			   return;
            	   }
            	   
            	   Contact contact = new Contact(name, phone);            	   
            	   //bookManager.addContact(contact);
            	   
            	   dtm.addRow(new Object[]{contact.getId(),contact.getName(),contact.getPhone()});
            	  
            	   dtm.fireTableDataChanged();
            	   
            	   //dtm.addRow(new Object[]{name,phone});
               }
        });
	}
	
	
	public boolean exist(String name, String phone){
		boolean exist = false;
		if(bookManager.getContacts().size()>0){
			for(Contact contact:bookManager.getContacts()){
				if(name.equalsIgnoreCase(contact.getName())){
					exist = true;
					break;
				}
			}
		}
		
		return exist;
	}
	
	
	public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddressBookGUI(new AddressBookManagerImpl());
            }
        });
    } 

}
