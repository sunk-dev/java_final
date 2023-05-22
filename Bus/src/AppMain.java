import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Book.Book_bus;

public class AppMain extends JFrame implements ActionListener{
	JTextField Login;
	JButton Booking_bus,businfo,mybooked;
	public AppMain() {
		//타이틀 설정//
		this.setTitle("버스 예약 시스템");
		this.setBackground(Color.gray);
		this.setLayout(null);
		this.setBounds(0, 0, 700, 650);
		//버튼 텍스트 필드 설정 //
		Login = new JTextField("장선경님");
		Booking_bus= new JButton("고속/시외버스 예약");
		Booking_bus.addActionListener(this);
		businfo=new JButton("버스정보");
		mybooked= new JButton("내 예약 정보");
		//사이즈 위치 조절
		Login.setBounds(20, 10, 650, 30);
		Booking_bus.setBounds(20, 70, 300, 300);
		businfo.setBounds(370, 70, 300, 300);
		mybooked.setBounds(20, 400,650,100);
		
		
		//버튼 텍스트 필드 추가//
		this.add(Login);
		this.add(Booking_bus);
		this.add(businfo);
		this.add(mybooked);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AppMain();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if (cmd.equals("고속/시외버스 예약")) {
			new Book_bus();
		}
		
	}

}
