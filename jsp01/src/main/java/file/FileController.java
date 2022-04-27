package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//확장자 패턴
@WebServlet("*.file")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//파일을 db에 저장하지 않고 뷰로 바로 보내기
		if (uri.contains("upload")){
			//파일 업로드 처리 
			String savedir = "D:\\kgr\\savedir"; //파일 저장 경로
			int size = 1024 * 1024 * 10; //10메가정도 된다.
			//파일 저장하기
			MultipartRequest multi =	//해당 주소를 담았다.
					new MultipartRequest(request, savedir, size, "utf-8", new DefaultFileRenamePolicy());
			//저장된 파일 이름 가져오기
			String filename = multi.getFilesystemName("file1");			//실제 올라간 파일 이름을 꺼낸다	//file.jsp의 인풋네임과 일치해야 한다.
			System.out.println(filename);
			
			//파일 이름을 view로 전달하기(이름을 알아야 다운받을 수 있기 떄문)
			//forward방식으로 이동
			request.setAttribute("filename", filename);
			request.getRequestDispatcher("/view/file/20220426_file.jsp")	//어떤 정보를 포워딩을 시킬 것인가
				.forward(request, response);
		
		}else if (uri.contains("download")) {//해당 url이 컨트롤러로 넘어왔을때 다운로드를 실행하겠다.
			//파일 다운로드
			String filename = request.getParameter("filename");			//파일을 올렸을 때 url에 찍힌 파일 name을 가져온다.
			
			System.out.println("다운로드할 파일 이름:" +filename); 
			String savadir = "D:\\kgr\\savedir"; //파일 저장 경로 설정
			String fileurl = savadir + "/" + filename;	//다운로드할 파일 url
			
			System.out.println(fileurl);
			//마임타입:파일의 종류
			String mimeType = getServletContext().getMimeType(filename);	//맨 상단의 HttpServlet으로부터 상속받았기 때문에 메소드 사용이 가능하다.
			System.out.println(mimeType);		//다운로드를 눌렀을 때 image/png(마임타입)이 뜨는것을 볼 수 있다.
			if (mimeType == null)
				mimeType = "application/octet-stream;charset=utf-8";
			response.setContentType(mimeType);
			
			//첨부파일로 보내기
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));	//이걸 추가하면 클라이언트가 파일을 다운받을 수 있다. filename을 추가해야 올린 파일과 동일한 이름으로 받아진다.
																														//한글파일의 경우 깨지지 않게 인코딩을 해준다.
			
			//서버의 파일을 읽어서 전송
			//파일 입력 스트림 생성
			FileInputStream fis = new FileInputStream(fileurl); //파일을 읽어서 리스폰스에 생성
			//응답 출력스트림 얻기(때문에 리스펀스에게 요청하면 된다)
			ServletOutputStream out =response.getOutputStream();	//멀리 있는 걸 리스펀스 객체를 통해 받으면 사용자가 파일을 클릭하고 확인할 수 있다.(클라이언트로 응답하고 클라이언트가 해석)	
			
			//반복문을 이용해서 파일을 읽어서 (한꺼번에 보내면 안되기 떄문에)
			byte[] b = new byte[4096];//파일을 읽어서 저장할 배열
			int numRead = 0;//반환값: 읽어들일 바이트 수)
			while((numRead = fis.read(b, 0, b.length)) != -1) { //몇바이트인지 모르기 때문에 for문이 아니라 while문 이용
				//(읽어서 어디에 담을지, 현재 위치한 포커스, 몇개를 읽을 것인지설정)	//읽어들인 바이트가 -1이면 종료(break대신 !=을 사용해서 false가 돼서 종료처리하도록 했다.)
				out.write(b, 0, numRead);			//b에다가 0부터 numRead만큼(읽어들인 바이트만큼만) 담는다.
			}
			out.flush();			//내보내기, 기존의 것들은 알아서 쌓인것들이 밀어 밀려나가지만 마지막거는 수동으로 작성해줘야 한다.
			out.close();			//아웃풋 스트림 닫기
			fis.close();			//인풋 스트림 닫기
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
