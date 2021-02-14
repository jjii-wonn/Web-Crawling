package modelFinal;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class Name extends JFrame implements ActionListener{
   
   JButton click;
   JTextField name;
   Container container;
   Font f1,f2,f3;
 
   String userName;
   NewsPanel news;
   JLabel nm;  

   
   Name(){
	   Color blue1 = new Color(103,110,238);
	   Color blue2 = new Color(149, 154, 244);
	   Color blue3 = new Color(223,224,251);
	   Color blue4 = new Color(104,107,170);
	   
      f1 = new Font("HY궁서B", Font.BOLD, 18);
      f2 = new Font("HY궁서B", Font.BOLD, 28);
      f3 = new Font("HY궁서B", Font.BOLD, 28);
      
      container = getContentPane();
      container.setLayout(null);
  
      
      JLabel title = new JLabel("당신을 위한 맞춤형 뉴우 - 스");
      //JLabel deco = new JLabel("😺😸😹😻😼😽🙀😿😾");
      title.setFont(f2);
      title.setBounds(86, 100, 500, 100);
      title.setBackground(blue2);
      //deco.setFont(f3);
      //deco.setBounds(115,120,500,100);
      JLabel greeting = new JLabel("당신의 이름은?");
      greeting.setFont(f1);
      greeting.setBounds(220, 170, 200, 50);
      nm = new JLabel();
      
      name = new JTextField(10);
      name.setBounds(230, 220, 100, 30);
      
      click = new JButton("입   력");
      click.setFont(f1);
      click.setBounds(230, 260, 100, 30);
      
      name.addActionListener(this);
      click.addActionListener(this);
      
      //container.add(deco);
      container.add(title);
      container.add(greeting);
      container.add(name);
      container.add(click);
      
       setTitle("맞춤형 뉴스 메인");
       setSize(600, 600);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(true);
   } 
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(click == e.getSource()) {
         userName = name.getText();
         nm.setText(name.getText());
         //news.j1.add(nm);
         System.out.println(userName);
         //SaveName();
         news = new NewsPanel(userName);
         }//System.out.println(userName);
   }
  
}

public class MainName {
   public static void main(String[] args) {
      Name n = new Name();
      n.getContentPane().setBackground(new Color(223, 224, 248));
      System.out.println();
      //n.display();
      
      
   }
}