package modelFinal;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelFinal.GraphPanel.DrawPanel;

class NewsPanel extends JFrame implements ActionListener, ItemListener{
   Integer[] year1 = { 2020 };
   Integer[] month1 = { 4, 5};
   Integer[] date1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
         15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
         27, 28, 29, 30, 31 };
   
   Integer[] year2 = { 2020 };
   Integer[] month2 = { 4, 5 };
   Integer[] date2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
         15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
         27, 28, 29, 30, 31 };
   
   //String[] searchCounts = {"1","2","3","4","5","6","7","8","9","10"};
   Integer[] searchCounts = {1,2,3,4,5,6,7,8,9,10};
   
   JButton goGraph;
   JButton input;
   JButton Crawl;
   JButton Select;
   JButton Delete;
//   JTextArea result;
   JButton exit;
   String keyword = "";
   JLabel nm;
   JTextField searchInput;
   JComboBox<Integer> sCounts;
   JComboBox<Integer> Month1;
   JComboBox<Integer> Date1;
   JComboBox<Integer> Month2;
   JComboBox<Integer> Date2;
   int m1=0;
   int m2=0;
   int d1=0;
   int d2=0;
   int newsNumber = 0;
   
   Container container;
   GraphPanel grp;
   DrawPanel drp;
   JPanel j1;
   JPanel j2;  /// 이것은 왜 변수로 빠져있지 않았을까?
   Font f1,f2,f3;
   //File userName;
   Name name = new Name();
   
   
	DefaultTableModel tm;
	DefaultTableModel tm1;
   
	JTable table;
	JTable table1;
	JScrollPane sp;
	Vector<String> fieldname;
	Vector<String> fieldname1;
   
   NewsPanel(String user) {
	   
	   fieldname = new Vector<>();
	   fieldname.addElement("기사제목\t\t");
	   fieldname.addElement("키워드\t\t");
	   fieldname.addElement("빈도수");
	   
	   fieldname1 = new Vector<>();
	   fieldname1.addElement("기사제목\t\t");
	   fieldname1.addElement("날짜\t\t");
	   fieldname1.addElement("내용\t\t");
	   fieldname1.addElement("주소");
	   
	   tm = new DefaultTableModel(fieldname1, 0);
	   tm1 = new DefaultTableModel(fieldname, 0);
	  
	   sp = new JScrollPane();
	   
      container = getContentPane();
      container.setLayout(new GridLayout(1,2));
      
      f1 = new Font("HY궁서B", Font.PLAIN, 15);
      f2 = new Font("HY궁서B", Font.BOLD, 18);
      f3 = new Font("HY궁서B", Font.ITALIC, 18);

////////////////////////////////////////패널1
      j1 = new JPanel();
      j1.setLayout(null);
      JComboBox<Integer> Year1 = new JComboBox<Integer>(year1);
      Month1 = new JComboBox<Integer>(month1);
      Date1 = new JComboBox<Integer>(date1);
      
      JComboBox<Integer> Year2 = new JComboBox<Integer>(year2);
      Month2 = new JComboBox<Integer>(month2);
      Date2 = new JComboBox<Integer>(date2);
      
      JScrollPane yy1 = new JScrollPane(Year1); 
      JScrollPane yy2 = new JScrollPane(Year2); 
      yy1.setBounds(130,100,80,50);
      yy2.setBounds(130,155,80,50);
      JScrollPane mm1 = new JScrollPane(Month1); 
      JScrollPane mm2 = new JScrollPane(Month2); 
      mm1.setBounds(220,100,80,50);
      mm2.setBounds(220,155,80,50);
      JScrollPane dd1 = new JScrollPane(Date1);
      JScrollPane dd2 = new JScrollPane(Date2); 
      dd1.setBounds(310,100,80,50);
      dd2.setBounds(310,155,80,50);
       
      //name.actionPerformed(userName);
      
      JLabel greeting = new JLabel(user+"님, 원하는 정보 입력하세요.");
      greeting.setFont(f2);
      greeting.setBounds(130,0,500,100); //x가로위치 y세로위치 , w길이, h길이
      
      JLabel period = new JLabel("기간: 연도 - 월 - 일 ~ 연도 - 월 -일");
      period.setFont(f1);
      period.setBounds(130,30,500,100); 
      JLabel search = new JLabel("검색어");
      search.setFont(f1);
      search.setBounds(130,185,500,100);
      
      searchInput = new JTextField(10);
      searchInput.setBounds(195,222,150,30);
 
      JLabel counts = new JLabel("갯   수");
      counts.setFont(f1);
      counts.setBounds(130,227,500,100);
      sCounts = new JComboBox<Integer>(searchCounts);
      JScrollPane countsInput = new JScrollPane(sCounts); 
      countsInput.setBounds(195,265,150,30);
      
      input = new JButton("입   력");
      input.setFont(f1);
      input.setBounds(137,310,120,30);
      
      exit = new JButton("종   료");
      exit.setFont(f1);
      exit.setBounds(267,310,120,30);
      
      goGraph = new JButton("트렌드 보기");
      goGraph.setFont(f1);
      goGraph.setBounds(195,355,130,30);
      
      Crawl = new JButton("크로-링 하기");
      Crawl.setFont(f1);
      Crawl.setBounds(118,400,140,30);
      
      Select = new JButton("전체 데이타");
      Select.setFont(f1);
      Select.setBounds(268, 400, 140, 30);
      
      Delete = new JButton("데이타 삭제");
      Delete.setFont(f1);
      Delete.setBounds(195, 445, 130, 30);
      
///////////////////////////////////////////////////
      input.addActionListener(this);
      searchInput.addActionListener(this);
      exit.addActionListener(this);
      sCounts.addItemListener(this);
      Month1.addItemListener(this);
      Month2.addItemListener(this);
      Date1.addItemListener(this);
      Date2.addItemListener(this);
      goGraph.addActionListener(this);
      
      Crawl.addActionListener(this);
      Select.addActionListener(this);
      Delete.addActionListener(this);
/////////////////////////////////////////////////
      
      j1.add(name.nm);
      j1.add(greeting);
      j1.add(period);
      j1.add(yy1);
      j1.add(mm1);
      j1.add(dd1);
      j1.add(yy2);
      j1.add(mm2);
      j1.add(dd2);
      j1.add(search);
      j1.add(searchInput);
      j1.add(counts);
      j1.add(countsInput);
      j1.add(input);
      j1.add(exit);
      j1.add(goGraph);
      j1.add(Crawl);
      j1.add(Select);
      j1.add(Delete);
      
////////////////////////////////////////// 패널2
      
		/* JPanel */ j2 = new JPanel();
      j2.setLayout(null);
//      result = new JTextArea(10,20);
      JLabel customized = new JLabel("▶▶▶▶기사 제목 - 키워드 - 빈도");
      customized.setFont(f2);
      customized.setBounds(55,0,400,100);
      sp.setBounds(50, 60, 450, 450);
//	  Color blue1 = new Color(209,211,250);
//      result.setBackground(blue1);
      j2.add(customized);
      j2.add(sp);
      
/////////////////////////////////////////////
      
      container.add(j1);
      container.add(j2);
      setTitle("맞춤형 뉴스 검색");
      setSize(1150, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }
   
//////////////////////////////////////////////
   //날짜 선택 시 그 날짜 값을 받는다 
   @Override
   public void itemStateChanged(ItemEvent e) {
      if(e.getStateChange() == 1) {
         if (sCounts == e.getSource()) {
            newsNumber = (Integer) e.getItem();  
            System.out.println(newsNumber);
         } else if (Month1 == e.getSource()) {
            m1 = (Integer) e.getItem();
            System.out.println(m1);
         } else if (Month2 == e.getSource()) {
            m2 = (Integer) e.getItem();
            System.out.println(m2);
         } else if (Date1 == e.getSource()) {
            d1 = (Integer) e.getItem();
            System.out.println(d1);
         } else if (Date2 == e.getSource()) {
            d2 = (Integer) e.getItem();
            System.out.println(d2);
         } 
      }
}
     
    //입력, 종료, 트렌드 보기 버튼 누를 때
	@Override
	public void actionPerformed(ActionEvent e) {
		String NewsTitle = null, Sliced_Words =null; int Word_cnt=0;
		ControlPanel cp = new ControlPanel();
		
		if (input == e.getSource()) {
			keyword = searchInput.getText();
			ArrayList<UIDTO>list = cp.displayRefiendData(keyword);
			
			for(UIDTO dto : list) {
				NewsTitle = dto.getNewsTitle();
				Sliced_Words =  dto.getSliced_Words();
				Word_cnt = dto.getWord_cnt();
				
				Object[] record = {NewsTitle, Sliced_Words, Word_cnt};
				tm1.addRow(record);
			}
			
			   table = new JTable(tm1);
			   sp.setViewportView(table);

//			SaveKeyword();
			
//		}else if(! keyword.equals(Sliced_Words))  {
//			tm.addRow(new Object[]{"검색결과가 없는뎁쇼...", "입력된 검색어 : " + searchInput.getText() , 0});
		
		}else if(Select == e.getSource()) {
			
			String title =null, date = null, contents = null, url = null;
			ArrayList<CrawDTO> list = cp.displayData();
			System.out.println("입력...");
				for(CrawDTO dto : list) {
					title = dto.getTitle();
					date = dto.getDate();
					contents = dto.getContents().substring(0, 10);
					url = dto.getUrl();
					
					Object[] record = {title, date, contents, url};
					tm.addRow(record);			
				}
				
				 table1 = new JTable(tm);
				 sp.setViewportView(table1);
			
			
		}if (exit == e.getSource()) {
			cp.collectData(2);
			System.out.println("작동을 정지합니다...");
		}if (goGraph == e.getSource()) {
			//System.out.println("e.getSource() 호출...");
//			grp = new GraphPanel(m1,m2,d1,d2);
//			 drp = new DrawPanel();
//			 container.add(grp);
		}if (Crawl == e.getSource()) {
			cp.collectData(1);
			System.out.println("oh");
		}if (Delete == e.getSource()) {
			cp.deleteData();
			System.out.println("yeah");
		}
		
		searchInput.setText("");
	}
   
	//키워드를 저장해서 뉴스를 찾고자.
   public void SaveKeyword() {
      try {
         Writer out = new FileWriter("keyword.txt");
               out.write(keyword);
               out.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
   }
}
