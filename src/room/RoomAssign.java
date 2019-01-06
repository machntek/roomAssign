package room;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Choice;
import javax.swing.JLabel;
import java.awt.Label; 

public class RoomAssign extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	String filePath;
	Choice choice;
	List<Choice> choiceList = new ArrayList<>();

	int i=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomAssign frame = new RoomAssign();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RoomAssign() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton button = new JButton("\uC0DD\uC131");
		button.setBounds(5, 233, 424, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Integer> opt = new ArrayList<>();
				for(i=0; i<choiceList.size(); i++) {
					opt.add(choiceList.get(i).getSelectedIndex()+1);
				}
//				Iterator it = opt.iterator();
//				while(it.hasNext()) {
//					System.out.println(it.next());
//				}
				int weeks = choice.getSelectedIndex()+1;
				
				ExcReader er = new ExcReader();
				List<Employee> list = er.xlsxToEmployeeList(filePath);
				
				Assigner ass = new Assigner();
				try {
					ass.assignEntireTime(list, weeks, opt);
					er.employeeListToXlsx(list);
					JOptionPane.showMessageDialog(null, "성공 .c:\\users\\Documents\\out.xlsx\\확인");

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "실패 .");
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(button);
		
		JButton open = new JButton("open file..");
		open.setBounds(5, 5, 109, 23);
		contentPane.add(open);
		
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(); //객체 생성
				   int ret = chooser.showOpenDialog(null);  //열기창 정의
				   if (ret != JFileChooser.APPROVE_OPTION) {
				    JOptionPane.showMessageDialog(null, "경로를 선택하지않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				    return;
				   }
				   filePath = chooser.getSelectedFile().getPath();  //파일경로를 가져옴
				   textField.setText(filePath);
				   System.out.println(filePath);  //출력
			  }
			
		});
		
		textField = new JTextField();
		textField.setBounds(114, 6, 315, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		choiceList.add(new Choice());
		choiceList.get(0).setBounds(54, 75, 55, 23);
		setChoice(choiceList.get(0));
		contentPane.add(choiceList.get(0));
		
		choiceList.add(new Choice());
		choiceList.get(1).setBounds(218, 75, 55, 21);
		setChoice(choiceList.get(1));
		contentPane.add(choiceList.get(1));
		
		JButton btnNewButton = new JButton("추가");
		btnNewButton.setBounds(325, 73, 97, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i++;
				if(i>=3) {
					System.out.println("ㄴㄴ");
					return;
				}
				Label label = new Label("\uC870\uBCC4");
				label.setBounds(22, i*20 + 73, 25, i*20 + 23);
				contentPane.add(label);
				
				Label label_1 = new Label("\uBA85");
				label_1.setBounds(114, i*20 + 75, 17, i*20 + 23);
				contentPane.add(label_1);
				
				Label label_2 = new Label("\uCD1D");
				label_2.setBounds(191, i*20 + 75, 17, i*20 + 23);
				contentPane.add(label_2);
				
				Label label_3 = new Label("\uC870");
				label_3.setBounds(279, i*20 + 75, 17, i*20 + 23);
				contentPane.add(label_3);
				
				choiceList.add(new Choice());
				choiceList.get(2*i).setBounds(54, i*30 + 75, 55, i*30 + 23);
				setChoice(choiceList.get(2*i));
				contentPane.add(choiceList.get(2*i));
				
				choiceList.add(new Choice());
				choiceList.get(2*i+1).setBounds(218, i*30 + 75, 55, i*30 + 21);
				setChoice(choiceList.get(2*i+1));
				contentPane.add(choiceList.get(2*i+1));
			}
		});
		
		Label label = new Label("\uC870\uBCC4");
		label.setBounds(22, 73, 25, 23);
		contentPane.add(label);
		
		Label label_1 = new Label("\uBA85");
		label_1.setBounds(114, 75, 17, 23);
		contentPane.add(label_1);
		
		Label label_2 = new Label("\uCD1D");
		label_2.setBounds(191, 75, 17, 23);
		contentPane.add(label_2);
		
		Label label_3 = new Label("\uC870");
		label_3.setBounds(279, 75, 17, 23);
		contentPane.add(label_3);
		
		choice = new Choice();
		choice.setBounds(340, 34, 63, 23);
		setChoice(choice);
		contentPane.add(choice);
		
		Label label_4 = new Label("\uC8FC");
		label_4.setBounds(407, 34, 17, 23);
		contentPane.add(label_4);
		
		Label label_5 = new Label("\uC5F0\uC218 \uAE30\uAC04");
		label_5.setBounds(279, 34, 55, 23);
		contentPane.add(label_5);
		
		
	}
	public void setChoice(Choice choice) {
		for(int i=0; i<10; i++) {
			choice.add(i+1 +" ");
		}
	}
	
}
