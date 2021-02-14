package modelFinal;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

class CrawlEngine {
	int startAid;
	int endAid;
	CrawDAO dao;
	
	CrawlEngine(int startAid, int endAid){													
		dao = new CrawDAO();	
		this.startAid = startAid;
		this.endAid = endAid;

	}
	

	public void engineStart(int toggle) {													// 이 engineStart() 메소드또한 UI에 연동 되어야 할까?
		switch(toggle) {
		case 1:
			for (int i = startAid; i <= endAid; i++) {
				String seed = formatAid(i);
				crawling(seed);
			}
			
		case 2:
			System.out.println("중지!");
			return;
		}
		
	}

	public String formatAid(int castNumber) {							// 이 코드는 startAid, endAid 숫자들 앞에 0을 3개 추가해준다.
		String aid = String.format("%010d", castNumber);			// 반환된 값은 seed 값이 된다.
		return aid;																	// ** seed 값은 위의 start 메소드에 의해 계속 변동된다.
	}
	
	
	public void crawling(String seed) {
		String title, date, contents;
		String url = "https://news.naver.com/main/read.nhn?mode=LSD&mid=sec&sid1=sec&oid=023&aid=" + seed;
		
		try {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
				// 연결 완료, 1byte 받음
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "EUC-KR"));
			
			String input;
			StringBuilder sb= new StringBuilder();
			
			while((input = in.readLine()) != null) {
				sb.append(input);
			}
			in.close();
			
			// System.out.println(sb.toString());
			String html = sb.toString();
			
			Document doc = Jsoup.parse(html); // html 코드가 담겨있다. 
			Elements el_title = doc.select("#articleTitle");
			Elements el_date = doc.select("span.t11");
			Elements el_contents = doc.select("#articleBody");
					
			
			
			
			if (el_title.size() != 0) {				// 크롤링된 데이터가 0개 이상이면 if 아래 코드들이 실행 됩니다:
															// 이 코드는 기존 CrawDTO 자료형 ArrayList 셋으로 관리하는 지난 코드와 달리
															// 크롤링 된 즉시 DB에 저장되는 시퀸스임으로 컴퓨터 성능에 따라 퍼포먼스가
															// 떨어질 가능성이 있음을 유의하시고 프로그램을 실행해 주세요.
				
															// 프로그램이 종료되면 DB에는 데이터가 저장이 되어지나,
															// 동일 seed 넘버 (생성자 안의 startAid 값이 같을 경우)를 사용하여 
															// 다시 프로그램을 실행할 경우, 같은 데이터들이 누적되어 저장됨을 유의하세요.
				
															// 프로그램 종료 후 이어서 DB에 데이터를 저장하고 싶으면
															// 몇번째 seed 넘버에서 프로그램이 종료되었는지 확인하고, 그 번호부터 다시
															// 크롤링을 진행해 주세요.
				
				
				
				title = el_title.get(0).text();
				title = title.replaceAll("\"", " ");					// 이부분은 기사 제목의 따옴표 (" ")들을 공백으로 치환해 줍니다.
				
				date = el_date.get(0).text();
				date = date.replaceAll("\"", " ");				// 이 부분은 날짜의 따옴표들을 공백으로 치환합니다.
				
				contents = el_contents.get(0).text();
				contents = contents.replaceAll("\"", " ");		// 마찬가지로 본문의 따옴표들을 공백으로 치환합니다.
					
				
				
				
				
				
				
				
				
				if(contents.length() > 1000) {
						contents = contents.substring(1000);
				}																	// 오라클 SQL 11g EX 버전에서는 테이블에 저장될 수 있는
																					// 메모리의 용량이 한정되어 있습니다. 대다수 뉴스 기사 본문들은
																					// 내용이 길고, 테이블에 저장이 되지 않아 스택 오버플로우 에러를 호출합니다.
																					// 그것을 방지하기 위해 해당 문은 본문을 1000자로 줄여줍니다.
																					
			
			
				
				dao.insert(new CrawDTO(title, date, contents, url));	
																							
																			// 위 문은 CrawDTO에 저장된 내용을 DB로 저장합니다.
																			// CrawDAO.java를 참조하세요
				
//				 System.out.println(title);
//				 System.out.println(date);
//				 System.out.println(contents);
//				 System.out.println(url);
//				 System.out.println("");
				
			}	
			
			
		} catch (IOException e) {
						e.printStackTrace();
						System.out.println("에러 발생");
		}
	}
		
}
		
		

	





