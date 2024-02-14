package multichat;

import java.sql.SQLException;

//CRUD 중 입력을 위한 클래스
public class SQLinsert extends MyConnection {
	/*
	 생성자에서 부모클래스의 생성자를 호출하여 DB에 연결한다.
	 연결한 계정의 아이디와 비번을 매개변수로 전달하고 있다. 
	 */
	public SQLinsert() {

	}
	
	//멤버변수
	String query;//쿼리문 저장
	int result;//insert후 결과를 저장(1이 나오면 성공, 0이 나오면 실패로)
	
	@Override
	public void dbExecute(String name, String s) {
		try {
			
			//insert를 위한 동적 쿼리문 작성
			query = " INSERT INTO chat_talking VALUES "
					+ " (chat_talking_seq.nextval, ?, ?, sysdate)";
			//동적쿼리 실행을 위한 prepartedStatement 인스턴스 생성
			psmt = con.prepareStatement(query);
			/*
			 동적쿼리문의 ?부분(인파라미터)을 사용자의 입력값으로 채워준다.
			 DB에서는 인덱스가 1부터 시작이므로 ?의 갯수만큼 순서대로 값을 설정하면
			 된다.
			 */
			psmt.setString(1, name);
			psmt.setString(2, s);
			
			//쿼리문 실행 및 결과 반환
			result = psmt.executeUpdate();
			//insert 쿼리문이므로 성공시 1, 실패시 0이 반환한다.
			System.out.println("[psmt]" + result + "행 입력됨");
		}
		catch(SQLException e) {
			System.out.println("쿼리실행 중 예외발생");
			e.printStackTrace();
		}
	}
}
