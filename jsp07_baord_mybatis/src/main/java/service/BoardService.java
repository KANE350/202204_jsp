package service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.BoardDAO;
import dao.BoardfileDAO;
import dao.ReplyDAO;
import dto.Board;
import dto.Boardfile;

public class BoardService {
	private BoardDAO boardDAO= new BoardDAO();
	private BoardfileDAO boardfileDAO = new BoardfileDAO();
	private ReplyDAO replyDAO = new ReplyDAO(); 
	
	public void insert(Board board, List<String> filenames) {	//리턴값이 없기 때문에 void 설정
		//게시물 저장
		int cnt = boardDAO.insert(board);	
		//System.out.println("여기는 서비스"+board); //여기서 출력했을때는 bnum에 값이 담긴 상태여야 한다.
		System.out.println(cnt + "건 board 추가");
		//게시물 파일 저장
		//bnum은 board저장시 시퀀스 생성이 되고, 그걸 가져다가 사용할 수 있다(boardMapper에서 selectKey로 세팅)
		for(String filename:filenames) {	//filenames를 filename에 하나씩 풀어헤친다. filenamse의 갯수만큼 반복
			Boardfile boardfile = new Boardfile();
			boardfile.setBnum(board.getBnum());		//boardfilemapper.xml참고
			//보드는 그냥 주소이다. 컨트롤로에서 최초로 만들어진 boarddto주소값을 받은상태 그렇기 때문에 dao에서 접근하는 보드나
			//매퍼에서 접근하는 보드나 다 같은 상태이다. 때문에 한군데서 세팅하면 다른곳에 있는 보드들도 모두 변경이 된다. 
			//때문에 서비스의 보드넘버는 보드매퍼에서 세팅을 해준다.
			boardfile.setFilename(filename);
			cnt += boardfileDAO.insert(boardfile); //insert는 건수를 돌려준다. cnt에 돌려주는 값을 누적해서 찍어볼 수 있다. 
		}
		System.out.println(cnt+ "건 boardfile 추가");
		
	}
	
	public List<Board> selectList(Map<String, Object> findmap) {

//		//조회조건 Map만들기	 컨트롤러에 삽입했기때문에 필요없어진다.
//		Map<String, Object> findmap = new HashMap<>();
//		if (findkey==null) findkey = "userid";
//		if (findvalue == null) findvalue = "";
//		findmap.put("findkey", findkey);
//		findmap.put("findvalue", findvalue);
		
		//1)페이징처리 //보드매퍼에서 작성한 셀렉트리스트 페이징처리 수식
		//페이징 처리하기 위해서는 한 페이지의 게시물수와 현재페이지 두 가지의 값을 알아야 한다, 현재페이지는 위에서 매개변수로 받았기 때문에 perPage만 작성하면 된다. 
		int perPage = 10;			//한 페이지의 게시물 수 :
		int curPage = (int) findmap.get("curPage");//현재페이지
		int startnum = (curPage-1)*perPage +1;//시작번호				
		int endnum = startnum + perPage -1;	//끝번호
		findmap.put("startnum", startnum);	
		findmap.put("endnum", endnum);
		
		//2)페이징 블럭 처리
		
		//2-1)전체 게시물수
		int totCnt = boardDAO.select_totalcnt(findmap);
		int totPage = totCnt/perPage;			//전체페이지수=전체게시물수/한페이지의 게시물 수  
		if(totCnt % perPage >0) totPage++;		//나머지 값이 나온다면 +1을 해라
		findmap.put("totPage", totPage);		//뷰에 출력한다.
		//2-1)시작페이지, 끝페이지 구하기
		int perBlock = 10;
		int startPage =  curPage - ((curPage -1)%perBlock);
		int endPage = startPage + perBlock -1;
		
		//endPage수정		//endpage를 totalpage와 같게 하기 위해
		if (endPage>totPage) endPage = totPage;
		findmap.put("startPage", startPage);	//블럭의 시작페이지	
		findmap.put("endPage", endPage);	//블럭의 끝페이지		
		
		System.out.println(findmap + "여기가 파인드맵");
		return boardDAO.selectList(findmap);
	}
	
	public Board selectOne(int bnum) {
		//한건조회
		return boardDAO.selectOne(bnum);
	}

	public void delete(int bnum) {
		//1)댓글 삭제(자식): 반드시 자식부터 삭제
		int cnt = replyDAO.delete_bnum(bnum);
		System.out.println(cnt+"건 reply 삭제");
		
		//2)게시물 파일(자식) 삭제(오라클에서 포린키로 연결이 되어있는 상태이기 때문에, 자식을 먼저 삭제해야 부모도 삭제가 가능해진다. 생성시에는 반대)
		cnt =  boardfileDAO.delete_bnum(bnum);
		System.out.println(cnt + "건 파일 boardfile 삭제");
		
		//3)게시물(부모) 삭제
		cnt = boardDAO.delete(bnum);
		System.out.println(cnt + "건 board 삭제");
		
	}

	public void update(Board board, List<String> filenames, String[] removefiles) {
		//1)게시물 수정
		boardDAO.update(board);
		
		//2)추가할 파일들 추가
		for(String filename:filenames) {
			Boardfile boardfile = new Boardfile();
			boardfile.setBnum(board.getBnum());
			boardfile.setFilename(filename);
			boardfileDAO.insert(boardfile);
			
		}
		
		//3)파일들 삭제
		if (removefiles == null) return;	//널이면 메소드를 끝내겠다
		for(String bfnum:removefiles) {
			boardfileDAO.delete(Integer.parseInt(bfnum));
		}

	}
		
		//조회수 +1
		public void update_readcnt(int bnum) {
			boardDAO.update_readcnt(bnum);
		}	//컨트롤러로 넘어가서 코드 작성
}
