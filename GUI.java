

import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener {
	
	private static JLabel Nlabel;
	private static JTextField userN;
	private static JLabel BFRlabel;
	private static JTextField userBFR;
	private static JLabel OUTlabel;
	private static JTextField userOUT;
	private static JButton button;
	private static JLabel largestNum;
	private static JLabel counterLab;
	private static JLabel timeLab;
	
	
	
	private Producer prod;
	private Consumer cons;
	private Buffer buf;
	public GUI()
	{}
	public GUI (Producer p,Consumer c,Buffer buff)
	{
		this.prod=p;
		this.cons=c;
		this.buf=buff;
	}
	public static void createFrame(Producer p,Consumer c, Buffer buff)
	{
		JPanel panel = new JPanel();
		JFrame frame = new JFrame("prime");
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		//...............//
		panel.setLayout(null);
		Nlabel = new JLabel("N");
		Nlabel.setBounds(600,100,100,100);
		panel.add(Nlabel);
		
	    userN =new JTextField();
		userN.setBounds(200,135,300,50);
		panel.add(userN);
		
		BFRlabel = new JLabel("Buffer Size");
		BFRlabel.setBounds(570,170,100,100);
		panel.add(BFRlabel);
		frame.setVisible(true);
		
		userBFR =new JTextField();
		userBFR.setBounds(200,205,300,50);
		panel.add(userBFR);
		
		OUTlabel = new JLabel("Output File");
		OUTlabel.setBounds(570,240,100,100);
		panel.add(OUTlabel);
		
		userOUT =new JTextField();
		userOUT.setBounds(200,275,300,50);
		panel.add(userOUT);
		
		button = new JButton("Start Producer");
		button.setBounds(200,335,110,35);
		button.addActionListener(new GUI(p,c,buff));
		panel.add(button);
		//****************//
		
		JLabel LRGlabel = new JLabel("the largest prime number");
		LRGlabel.setBounds(200,400,200,100);
		panel.add(LRGlabel);
		
		largestNum = new JLabel("");
		largestNum.setBounds(500,400,200,100);
		panel.add(largestNum);
		
		counterLab=new JLabel("");
		counterLab.setBounds(500,450,200,100);
		panel.add(counterLab);
		
		timeLab=new JLabel("");
		timeLab.setBounds(500,500,200,100);
		panel.add(timeLab);
		
		JLabel NUM_ELMlabel = new JLabel("#of elements (prime number) generated");
		NUM_ELMlabel.setBounds(200,450,250,100);
		panel.add(NUM_ELMlabel);
		
		JLabel TIMElabel = new JLabel("time elapsed since the start of processing");
		TIMElabel.setBounds(200,500,280,100);
		panel.add(TIMElabel);
		
	
		frame.setVisible(true);
	}
	public void setlargestprimenumber(int n)
	{
		largestNum.setText(Integer.toString(n));
	}
	public void setCounter(int n)
	{
		counterLab.setText(Integer.toString(n));
	}
	public void setTime(long n)
	{
		timeLab.setText(Long.toString(n));
	}
	

	public void actionPerformed(ActionEvent e) {
		String filenam=userOUT.getText();
		int buffer_size=Integer.parseInt(userBFR.getText());
		Object store[] = new Object[buffer_size];
		Semaphore spaces= new Semaphore(buffer_size);
		int number=Integer.parseInt(userN.getText());
		buf.setter(buffer_size, spaces, store);
		prod.setBufferAndNumber(buf,number);
		cons.setBufAndFname(buf, filenam);
		prod.start();
		cons.start();

	}

}

