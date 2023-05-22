package Book;
//import java.nio.channels.SelectableChannel;
import java.sql.*;
public class bus_DB_open {
	PreparedStatement ps =null;
	ResultSet rs = null;//select한  결과를 리턴받는 객체
	Connection con=null;
	public ResultSet select() {
		con=makeConnection();
		if(con != null) {
			String sql;
			try {
				//distinct 중복데이터가 있으면 1개만 가져오는것.
				sql="select* from bus_terminal_info";
				
				ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				
				rs= ps.executeQuery();//select실행시 ->테이블 변화 없음
				
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ps 객체생성 오류");
				e.printStackTrace();//추적정보출력
			}
			
			
		}
		return rs;
		
	}
	public ResultSet select_end_info(int start_terminal_code) {
		con=makeConnection();
		if(con != null) {
			String sql;
			try {
				//distinct 중복데이터가 있으면 1개만 가져오는것.
				sql="select name,start_name_code from bus_terminal_info_end where start_name_code=?";
				
				ps = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ps.setInt(1, start_terminal_code);
				rs= ps.executeQuery();//select실행시 ->테이블 변화 없음
				
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ps 객체생성 오류");
				e.printStackTrace();//추적정보출력
			}
			
			
		}
		return rs;
		
	}


	public Connection makeConnection() {
		String url=
				"jdbc:mysql://localhost:3306/myapp_home?serverTimezone=Asia/Seoul";
		String id="root";
		String pw="1234";
		Connection con=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//드라이버로딩
			System.out.println("데이터베이스연결중");
			con=DriverManager.getConnection(url,id,pw);//db연결명령
			System.out.println("데이터베이스 연결성공");
		}catch(ClassNotFoundException e) {//com.mysql.cj.jdbc.Driver 로딩시 예외가 발생할때(위치에 없거나..) 실행할 구문
			System.out.println("jdbc 드라이버를 찾지 못했습니다");
			e.printStackTrace();
			
		}catch(SQLException e){//SQLException e ->SQLException를 줄여서 e로 쓰기
			System.out.println("데이터베이스 연결실패");
			System.out.println(e.getMessage());
		}
		return con;
	}
	



}
