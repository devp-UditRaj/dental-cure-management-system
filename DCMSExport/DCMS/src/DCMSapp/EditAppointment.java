package DCMSapp;

import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;




public class EditAppointment extends JFrame {
	static Connection con=null;
	static Statement st=null;
	static ResultSet rs=null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table;
	static DefaultTableModel model;
	@SuppressWarnings("unused")
	private static Image img;
    private Image scaled;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					Image img = null;
					Image img2=null;
					img2 = ImageIO.read(getClass().getResource("/nw.png")); //this is the another image object which is to be resized 
		             
		             img=img2.getScaledInstance(600, 500, Image.SCALE_DEFAULT); //this is for resizing the image
					EditAppointment frame = new EditAppointment(img);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					 frame.setIconImage(new ImageIcon(getClass().getResource("/logo1.jpg")).getImage());
					frame.pack();
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
public static void updatetable()
	
	{
		try {
			
			Class.forName(dbcon.DRIVER);
			con=DriverManager.getConnection(dbcon.JDBC_URL);
			st=con.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		boolean a= SearchInfoReception.rdbtnRegistrationId.isSelected();
		boolean b= SearchInfoReception.rdbtnAppointmentId.isSelected();
		boolean c= SearchInfoReception.rdbtnContactNo.isSelected();
		String getdata= SearchInfoReception.txtSearch.getText();
		long getids= Long.parseLong(getdata);
		if(a==true)
		{
			
				int count=0;
				try {
					String manupstatus = null;
				rs=st.executeQuery("select appointmentid,datetime,isdeleted from appointmenttable where pk_patientid="+getids+" order by datetime desc");
				while(rs.next())
				{
					count++;
					int appointmentid= rs.getInt(1);
					Date date= rs.getDate(2);
					Time time= rs.getTime(2);
					String status= rs.getString(3);
					if(status.equals("f"))
					{
						manupstatus="Not Attended";
					}
					else
					{
						manupstatus="Attended";
					}
				    model.addRow(new Object[]{count,appointmentid,date,time,manupstatus});
				  }
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		}
		else if(b==true)
		{		int count=0;
			try {
				String manupstatus = null;
				ResultSet table1= st.executeQuery("Select pk_patientid from appointmenttable where appointmentid="+getids);
				table1.next();
				int getpatientid= table1.getInt(1);
				rs=st.executeQuery("select appointmentid,datetime,isdeleted from appointmenttable where pk_patientid="+getpatientid+" order by datetime asc");
				while(rs.next())
				{
					count++;
					int appointmentid= rs.getInt(1);
					Date date= rs.getDate(2);
					Time time= rs.getTime(2);
					String status= rs.getString(3);
					if(status.equals("f"))
					{
						manupstatus="Not Attended";
					}
					else
					{
						manupstatus="Attended";
					}
				    model.addRow(new Object[]{count,appointmentid,date,time,manupstatus});
				  }
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(c==true)
		{	int count=0;
			try {
				String manupstatus = null;
				ResultSet table= st.executeQuery("select patientid from patienttable where contact='"+getdata+"'");
				table.next();
				long ihaveid=table.getInt(1);
				rs=st.executeQuery("select appointmentid,datetime,isdeleted from appointmenttable where pk_patientid="+ihaveid+" order by datetime asc");
				while(rs.next())
				{
					count++;
					int appointmentid= rs.getInt(1);
					Date date= rs.getDate(2);
					Time time= rs.getTime(2);
					String status= rs.getString(3);
					if(status.equals("f"))
					{
						manupstatus="Not Attended";
					}
					else
					{
						manupstatus="Attended";
					}
				    model.addRow(new Object[]{count,appointmentid,date,time,manupstatus});
				  }
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
	}
        public EditAppointment(String img) {
               this(new ImageIcon(img).getImage());
         }
 
	/**
	 * @wbp.parser.constructor
	 */

	
	@SuppressWarnings({ "static-access", "serial" })
	public EditAppointment(Image img) {
		  setMinimumSize(new Dimension(600, 500));
			
			this.img = img;
		
		setVisible(true);
		setTitle("Appointment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel()
		{
			
			 @Override
		        public void invalidate() {
		            super.invalidate();
		            int width = getWidth();
		            int height = getHeight();

		            if (width > 0 && height > 0) {
		                scaled = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		            }
		        }

		        @Override
		        public Dimension getPreferredSize() {
		            return img == null ? new Dimension(200, 200) : new Dimension(img.getWidth(this), img.getHeight(this));
		        }

		        @Override
		        public void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            g.drawImage(scaled, 0, 0, null);
		        }
		};

		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAllAppointments = new JLabel("All Appointments:");
		lblAllAppointments.setAlignmentY(Component.TOP_ALIGNMENT);
		lblAllAppointments.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("unused")
				int column = 1;
				int row = table.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null, "There is no row selected or either there is no data present!");
				}
				else
				{
					String valueattended= table.getModel().getValueAt(row, 4).toString();
					if(valueattended.equals("Attended"))
					{
						JOptionPane.showMessageDialog(null,"Sorry the attended appointment cannot be edited! Only the non attended appointments can be changed!!");
					}
					else
					{
						AppointmentMList.main(null);
					}
					
				}
			}
		});
		btnEdit.setBackground(new Color(242,171,42));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 1;
				int row = table.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null, "There is no row selected or either there is no data present!");
				}
				else
				{
				String value = table.getModel().getValueAt(row, column).toString();
				String valueattended= table.getModel().getValueAt(row, 4).toString();
				if(valueattended.equals("Attended"))
				{
					JOptionPane.showMessageDialog(null,"Sorry the appointment is already done and can't be deleted! You can only delete the non attended one!");
				}
				else
				{
				long getappointmentid = Integer.parseInt(value);
				int a=JOptionPane.showConfirmDialog(null, "Are you sure you u want to **PERMANENTLY DELETE** the selected appointment from the list?");
				if(a==0)
				{
					
				try {
					con.createStatement().execute("delete from appointmenttable where appointmentid="+getappointmentid+"");
				}
				catch(Exception e1)
				{
				e1.printStackTrace();	
				}
				
				model.setRowCount(0);
				updatetable();	
					
				}
				}
				}
			}
		});
		btnDelete.setBackground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
							.addGap(5))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAllAppointments)
							.addPreferredGap(ComponentPlacement.RELATED, 313, Short.MAX_VALUE)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(7))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAllAppointments)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable()
		{
			DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();

		    { // initializer block
		        renderCenter.setHorizontalAlignment(SwingConstants.CENTER);
		    }

		    @Override
		    public TableCellRenderer getCellRenderer (int arg0, int arg1) {
		        return renderCenter;
		    }
					private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
		        return false;   //Disallow the editing of any cell
		    }
				};
		table.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setRowHeight(25);
		table.setSelectionBackground(new Color(164,180,189));		
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setBackground(Color.WHITE);
		model = new DefaultTableModel(new String[]{"Sno.", "Appointment ID", "Date","Time","Status"}, 0);
		table.setModel(model);
		contentPane.setLayout(gl_contentPane);
		updatetable();
	}
}
