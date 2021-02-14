package modelFinal;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

class ControlPanel {
	CrawlEngine craw;
	CrawDAO dao;

	
	
	ControlPanel(){
		craw = new CrawlEngine(3519712, 3534905);			// 이 CrawlEngine 에 네이버뉴스 크롤링할 시작주소와
		dao = new CrawDAO();											// 종료주소가 들어갑니다. ControlPanel 가 Main에서
																					// 인스턴스 되는 동시에 CrawlEngine 또한 인스턴스 됩니다.
	}																				
	
	
	
	
	public void collectData(int i) {
		craw.engineStart(i);
	}
	
	
	
	public ArrayList<UIDTO> displayRefiendData(String keyword) {						// 이 키워드는 NewsForYou에서 입력이 된다.
		String NewsTitle, Sliced_Words = null; int Word_cnt = 0;
		ArrayList<SelectDTO> list = dao.refinedSelect();
		ArrayList<UIDTO> list1 = new ArrayList<UIDTO>();

		
		System.out.println("결과 출력 완료 : 패널 확인--");
//		System.out.println("제목\t\t 잘라진 단어 수 \t\t 단어 빈도");
		
		System.out.println("size : "+list.size());
			for(SelectDTO dto : list) {		
					//System.out.println(dto.getSliced_Words());
				
						
					if (keyword.equals(dto.getSliced_Words())) {
						NewsTitle = dto.getNewsTitle();
						Sliced_Words = dto.getSliced_Words();
						Word_cnt = dto.getWord_cnt();
						list1.add(new UIDTO(NewsTitle, Sliced_Words, Word_cnt));
//						System.out.printf("%s, %s, %s \n", NewsTitle, Sliced_Words, Word_cnt);
					 }

//			System.out.printf("%s\t%s\t\t%d\n", NewsTitle, Sliced_Words, Word_cnt);
//			System.out.println("==================================\n");
			
			}
			
			return list1;

		
	}
	
	
	public ArrayList<CrawDTO> displayData() {
		String title=null, date=null, contents=null, url = null;
		ArrayList<CrawDTO> list = dao.select();
		ArrayList<CrawDTO> list1 = new ArrayList<CrawDTO>();
		
//		System.out.println("제목\t\t날짜\t\t본문\t\t\t\tURL");
//		System.out.println("---------------------------------------\n");
		
		for(CrawDTO dto : list) {
			title = dto.getTitle();
			date = dto.getDate();
			contents = dto.getContents();
			url = dto.getUrl();
			list1.add(new CrawDTO(title, date, contents, url));
//			System.out.printf("%s\t%s\t\t%s\n%s\n", title, date, contents, url);
//			System.out.println("=========================\n");

		}
		return list1;
	}
	
	public void deleteData() {
		dao.delete();
	}
	
}
