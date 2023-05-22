package Book;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

public class Book_bus_detail extends JFrame implements ActionListener {
	JButton start,end,search;
	JTextField title,start_tf,end_tf;
	//버스노선테이블 JPanel=(jt(+dtm))
	JScrollPane terminal_data;
	JTable jt;
	DefaultTableModel dtm;
	bus_DB_open db=new bus_DB_open();
	ResultSet rs;
	int change_end_info=0;//경로 클릭시 출발경로 도착경로 텍스트 필드에 경로 입력하기위한 변수

	
	public Book_bus_detail() {
		////이창이 켜질때 자동으로 출발 버튼에 포커스 그리고 버스노선 테이블 보여지기///
		
		
		////큰 설절///
		this.setTitle("버스 경로 설정");
		this.setBackground(Color.gray);
		this.setLayout(null);
		this.setBounds(0, 0, 500, 700);
			
		//버튼 텍스트 필드 설정 //
		title = new JTextField("출발지 검색");
		title.setEditable(false);
		title.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		search=new JButton("검색"); 
		start=new JButton("출발");
		start_tf=new JTextField("출발지를 선택해주세요");
		start_tf.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		start_tf.setEditable(false);
		end= new JButton("도착");
		end_tf=new JTextField("도착지를 선택해주세요");
		end_tf.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		end_tf.setEditable(false);
	
			
		//버스노선테이블//
		String [] Title= {"노선명","코드"};
		Object [] [] row= new Object[0][2];
		dtm= new DefaultTableModel(row,Title);
		
		jt=new JTable(dtm);
		////왜 이 리스너로 적용이 되는지 이유를 모름.!////
		jt.addMouseListener(new MouseAdapter() {//어댑터클래스

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jt.getSelectedRow();
				if (change_end_info==0) {//change_end_info==0 출발경로 텍스트 필드 설정
					int start_terminal_code=Integer.parseInt(jt.getValueAt(row,1).toString());
					start_tf.setText(jt.getValueAt(row,0).toString());
					start.setBackground(null);
					start_tf.setBackground(null);
					end.setBackground(Color.lightGray);
					end_tf.setBackground(Color.lightGray);
					rs=db.select_end_info(start_terminal_code);
					display(rs,1);
					change_end_info=1;
				}
				else {//change_end_info==1 도착경로 텍스트 필드 설정
					end_tf.setText(jt.getValueAt(row,0).toString());
					///날짜조회 버튼 눌리기전까지는 1 그후에 0으로
					change_end_info=0;
					
					
				}
				
				
				
				
			}

			
			
		});
//		jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//		    
//
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				// TODO Auto-generated method stub
//				int row = jt.getSelectedRow();
//				int start_terminal_code=Integer.parseInt(jt.getValueAt(row,1).toString());
//				start_tf.setText(jt.getValueAt(row,0).toString());
//				start.setBackground(null);
//				start_tf.setBackground(null);
//				end.setBackground(Color.lightGray);
//				end_tf.setBackground(Color.lightGray);
//				rs=db.select_end_info(start_terminal_code);
//				display(rs,1);
//				
//				
//			}
//		});
		terminal_data =new JScrollPane(jt);
		
			
		

			
			
		
		
		//사이즈 위치 조절
		title.setBounds(70, 10, 350, 30);
		start.setBounds(40, 70, 200, 30);
		start_tf.setBounds(40,100,200,100);
		end.setBounds(240, 70, 200, 30);
		end_tf.setBounds(240,100,200,100);
		search.setBounds(40,210,400,30);
		terminal_data.setBounds(40, 260, 400,380);
		terminal_data.setBackground(Color.darkGray);
		//버튼 텍스트 필드 추가//
		//버스노선테이블추가///
		
		////////////////
		///그외 추가///////
		this.add(title);
		this.add(start);
		this.add(start_tf);
		this.add(end);
		this.add(end_tf);
		this.add(search);
		this.add(terminal_data);
		this.setResizable(false);
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				start.setBackground(Color.lightGray);
				start_tf.setBackground(Color.lightGray);
				rs=db.select();
				display(rs,0);
				
				
				
			}
			
			
		});
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public void display(ResultSet rs,int num) {
		if (num==0) {
			try {
				
				
				dtm.setRowCount(0);
				Object info[]=new Object[2];
				
				//행개수를 0이되게 설정
	
				while(rs.next()) {
					info[0]=rs.getString(1);
					info[1]=rs.getInt(2);
					dtm.addRow(info);
					
				}
			
				
				
				
			}catch(SQLException e1) {
					e1.printStackTrace();
				}
		}
		else { 
			if (dtm.getRowCount() > 0) {
			    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
			        dtm.removeRow(0);
			    }
			}
			System.out.println("전부지우기는 성공");
			
		
			
			try {
				dtm.setRowCount(0);
				Object newinfo[]=new Object[2];
				//행개수를 0이되게 설정
				rs.beforeFirst();
				while(rs.next()) {
					
					newinfo[0]=rs.getString(1);
					newinfo[1]=rs.getInt(2);
					dtm.addRow(newinfo);
					
				}
				
				rs.beforeFirst();
				
			}catch(SQLException e1) {
					e1.printStackTrace();
				}
			
		}
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Book_bus_detail();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
