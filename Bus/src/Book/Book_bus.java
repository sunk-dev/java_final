package Book;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Book_bus extends JFrame implements ActionListener {
	JTextField title;
	JButton search_terminal;
	JPanel bus_image_in;
	
	
	
	
	public Book_bus() {
		////큰 설절///
		this.setTitle("버스 예매 경로 검색 첫화면");
		this.setBackground(Color.gray);
		this.setLayout(null);
		this.setBounds(0, 0, 300, 500);
		
		//버튼 텍스트 필드 설정 //
		title = new JTextField("고속.시외 통합예매");
		title.setEditable(false);
		title.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		bus_image_in= new JPanel();
		search_terminal = new JButton("터미널 검색");
		search_terminal.addActionListener(this);
		
		
		//사이즈 위치 조절
		title.setBounds(20, 10, 250, 30);
		bus_image_in.setBounds(50,60,200,80);
		bus_image_in.setBackground(Color.YELLOW);
		search_terminal.setBounds(20, 170, 250, 30);
		
		//버튼 텍스트 필드 추가//
		this.add(title);
		this.add(bus_image_in);
		this.add(search_terminal);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Book_bus();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if (cmd.equals("터미널 검색")) {
			new Book_bus_detail();
		}
		
	}

}
