package modelFinal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphPanel extends JFrame{
   
  //public int height, width, nx, ny;
   JFrame frame;
   DrawPanel dr;
   String [] array;
   String str = "";
   String str_data = "";
   int top1, top2, top3, top4;
   int dd1, dd2, mm1, mm2;
   String kw1, kw2, kw3, kw4, ct1, ct2, ct3, ct4;
   
   GraphPanel(int mm1, int mm2, int dd1, int dd2) {
       
      try {
         BufferedReader in = new BufferedReader(new FileReader("december.txt"));

         while (true) {
            if (str == null) {
               break;
            }
            str_data += str;
            str = in.readLine();
         }
         System.out.println(str_data);
         array = str_data.split(",");
         top1 = Integer.parseInt(array[5]);
         top2 = Integer.parseInt(array[8]);
         top3 = Integer.parseInt(array[11]);
         top4 = Integer.parseInt(array[14]);
        
         kw1 = array[4];
         kw2 = array[7];
         kw3 = array[10];
         kw4 = array[13];
         
         ct1 = array[5];
         ct2 = array[8];
         ct3 = array[11];
         ct4 = array[14];
         in.close();
      
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      dr = new DrawPanel(mm1, mm2, dd1, dd2);
      frame = new JFrame("키워드 그래프");
      frame.setLocation(100, 100);
      frame.add(dr);
      //frame.add(periodForKeyword);
      frame.pack();
      frame.setVisible(true);
      frame.setSize(700,500);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // JPanel j2 = new JPanel();
   }
   
   class DrawPanel extends JPanel {
   
         int m1;
         int m2;
         int d1;
         int d2;
         String period;
         Font f1;
         JLabel periodForKeyword; 
         
         DrawPanel(int m1,int m2,int d1,int d2) {
        	 this.m1 = m1;
        	 this.m2 = m2;
        	 this.d1 = d1;
        	 this.d2 = d2;      
         }
         
         public void paintComponent(Graphics g) {
        	 
        	String mmm1 = Integer.toString(m1);
            String mmm2 = Integer.toString(m2);
            String ddd1 = Integer.toString(d1);
            String ddd2 = Integer.toString(d2);
            
         f1 = new Font("a펜고딕M", Font.PLAIN, 15);
         System.out.println("paintComponent() 호출.");
        
         Color blue1 = new Color(103, 110, 238);
         Color blue2 = new Color(149, 154, 244);
         Color blue3 = new Color(223, 224, 251);
         Color blue4 = new Color(104, 107, 170);
         
         g.drawString(kw1, 45, 315);
         g.drawString(ct1, 46, 330);
         g.drawString(kw2, 115, 315);
         g.drawString(ct2, 116, 330);
         g.drawString(kw3, 185, 315);
         g.drawString(ct3, 186, 330);
         g.drawString(kw4, 255, 315);
         g.drawString(ct4, 256, 330);
         
         g.drawLine(30, 40, 30, 300);
         g.drawLine(30, 300, 330, 300);
         g.setColor(blue1);
         g.fillRect(50, (300 - (top1 / 13)), 32, (top1 / 13));
         g.setColor(blue3);
         g.fillRect(120, (300 - (top2 / 13)), 32, (top2 / 13));
         g.setColor(blue2);
         g.fillRect(190, (300 - (top3 / 13)), 32, (top3 / 13));
         g.setColor(blue4);
         g.fillRect(260, (300 - (top4 / 13)), 32, (top4 / 13));
         g.drawLine(380, 40, 380, 300);
         g.drawLine(380, 300, 640, 300);
         g.setColor(Color.black);
         g.setFont(new Font("a펜고딕M", Font.PLAIN, 13));
         g.drawString(mmm1 + "월" + ddd1 + "일 ~ " + mmm2 + "월"
                   + ddd2 + "일 간 많이 검색된 단어", 80,20);
         

         Graphics2D g2 = (Graphics2D) g;
         g2.setStroke(new BasicStroke(5));
         g2.setColor(blue1);
         int xArray[] = { 400, 470, 540, 610 };
         int yArray[] = { (380 - 190), (380 - 150), (380 - 280), (380 - 200) };
         g2.drawPolyline(xArray, yArray, 4);
         }
   }
}
   