package calculator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Calci {

	private JFrame frmHu;
	private JTextField textField;
	
	double first;
	double second;
	double result;
	String operation;
	String answer;
	
	private JTextField btnEqual;
	private JTextField btnSub;
	private JTextField btnMul;
	private JTextField btnDivide;
	private JTextField btnPercent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calci window = new Calci();
					window.frmHu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calci() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHu = new JFrame();
		frmHu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHu.setAlwaysOnTop(true);
		
		
		frmHu.setForeground(new Color(255, 128, 0));
		frmHu.setFont(new Font("Arial Black", Font.ITALIC, 14));
		frmHu.setTitle("Calculator");
		frmHu.setBounds(50, 50, 374, 490);
		frmHu.setResizable(true);
		frmHu.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setBounds(29, 10, 286, 71);
		frmHu.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBackspace = new JButton("\uF0E7");
		btnBackspace.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String backSpace = null;
		        if (textField.getText().length() > 0) {
		            StringBuilder str = new StringBuilder(textField.getText());
		            str.deleteCharAt(textField.getText().length() - 1);
		            backSpace = str.toString();
		            textField.setText(backSpace);
		        }
		    }
		});
		btnBackspace.setBounds(20, 97, 72, 56);
		btnBackspace.setHorizontalAlignment(SwingConstants.CENTER);
		btnBackspace.setFont(new Font("Wingdings", Font.PLAIN, 17));
		frmHu.getContentPane().add(btnBackspace);
		
		
		JButton btn7 = new JButton("7");
		btn7.setBounds(20, 163, 72, 56);
		btn7.setText("7");
		btn7.setHorizontalAlignment(SwingConstants.CENTER);
		btn7.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btn7);
		btn7.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btn7.getText();
		        textField.setText(number);
		    }
		});
		
		JButton btn4 = new JButton("4");
		btn4.setBounds(20, 228, 72, 56);
		btn4.setText("4");
		btn4.setHorizontalAlignment(SwingConstants.CENTER);
		btn4.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btn4);
		
		btn4.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btn4.getText();
		        textField.setText(number);
		    }
		});
		
		JButton btn1 = new JButton("1");
		btn1.setBounds(20, 294, 72, 56);
		btn1.setHorizontalAlignment(SwingConstants.CENTER);
		btn1.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btn1);

		btn1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btn1.getText();
		        textField.setText(number);
		    }
		    
		});
		
		JButton btn0 = new JButton("0");
		btn0.setBounds(20, 359, 72, 56);
		btn0.setHorizontalAlignment(SwingConstants.CENTER);
		btn0.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btn0);

		btn0.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btn0.getText();
		        textField.setText(number);
		    }
		});
		JButton btnEqual = new JButton("=");
		btnEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String answer;
				second=Double.parseDouble(textField.getText());
				
				if (operation=="+")
				{
					result=first+second;
					answer=String.format("%.2f", result);
					textField.setText(answer);
				}
				else if(operation=="-")
				{
					result=first-second;
					answer=String.format("%.2f", result);
					textField.setText(answer);
				}
				else if(operation=="*")
				{
					result=first*second;
					answer=String.format("%.2f", result);
					textField.setText(answer);
				}
				else if(operation=="/")
				{
					result=first/second;
					answer=String.format("%.2f", result);
					textField.setText(answer);
				}
				else if (operation.equals("%"))
				{
				    result = second * (first / 100);
				    answer = String.format("%.2f", result);
				    textField.setText(answer);
				}
			}
		});
		
				
		btnEqual.setBounds(184, 359, 72, 56);
		btnEqual.setText("=");
		btnEqual.setHorizontalAlignment(SwingConstants.CENTER);
		btnEqual.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btnEqual);
		
		JButton btn00 = new JButton("00");
		btn00.setBounds(184, 97, 72, 56);
		btn00.setText("00");
		btn00.setHorizontalAlignment(SwingConstants.CENTER);
		btn00.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btn00);
		
		btn00.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btn00.getText();
		        textField.setText(number);
		    }
		});
		
		JButton btn9 = new JButton("9");
		btn9.setBounds(184, 163, 72, 56);
		btn9.setText("9");
		btn9.setHorizontalAlignment(SwingConstants.CENTER);
		btn9.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btn9);
		
		btn9.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btn9.getText();
		        textField.setText(number);
		    }
		});
		
		JButton btn6 = new JButton("6");
		btn6.setBounds(184, 228, 72, 56);
		btn6.setText("6");
		btn6.setHorizontalAlignment(SwingConstants.CENTER);
		btn6.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btn6);
		
		btn6.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btn6.getText();
		        textField.setText(number);
		    }
		});
		
		JButton btnClear = new JButton("C");
		btnClear.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
				 textField.setText(null);
			 }
		});
		
		btnClear.setBounds(102, 97, 72, 56);
		btnClear.setHorizontalAlignment(SwingConstants.CENTER);
		btnClear.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btnClear);
		
		JButton btn8 = new JButton("8");
		btn8.setBounds(102, 163, 72, 56);
		btn8.setText("8");
		btn8.setHorizontalAlignment(SwingConstants.CENTER);
		btn8.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btn8);
		
		btn8.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btn8.getText();
		        textField.setText(number);
		    }
		});
		
		JButton btn5 = new JButton("5");
		btn5.setBounds(102, 228, 72, 56);
		btn5.setText("5");
		btn5.setHorizontalAlignment(SwingConstants.CENTER);
		btn5.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btn5);
		
		btn5.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btn5.getText();
		        textField.setText(number);
		    }
		});
		
		JButton btn2 = new JButton("2");
		btn2.setBounds(102, 294, 72, 56);
		btn2.setText("2");
		btn2.setHorizontalAlignment(SwingConstants.CENTER);
		btn2.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btn2);
		
		btn2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btn2.getText();
		        textField.setText(number);
		    }
		});
		
		
		JButton btnDot = new JButton(".");
		btnDot.setBounds(102, 359, 72, 56);
		btnDot.setText(".");
		btnDot.setHorizontalAlignment(SwingConstants.CENTER);
		btnDot.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btnDot);
		
		btnDot.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btnDot.getText();
		        textField.setText(number);
		    }
		});
		
		JButton btn3 = new JButton("3");
		btn3.setBounds(184, 293, 72, 56);
		btn3.setText("3");
		btn3.setHorizontalAlignment(SwingConstants.CENTER);
		btn3.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btn3);
		
		btn3.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        String number = textField.getText() + btn3.getText();
		        textField.setText(number);
		    }
		});
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				first=Double.parseDouble(textField.getText());
				textField.setText("");
				operation="+";
			}
			
		});
		
		btnPlus.setBounds(266, 97, 72, 56);
		btnPlus.setText("+");
		btnPlus.setHorizontalAlignment(SwingConstants.CENTER);
		btnPlus.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btnPlus);
		
		JButton btnSub = new JButton("-");
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				first=Double.parseDouble(textField.getText());
				textField.setText("");
				operation="-";
			}
			
		});
		
		btnSub.setBounds(266, 163, 72, 56);
		btnSub.setText("-");
		btnSub.setHorizontalAlignment(SwingConstants.CENTER);
		btnSub.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btnSub);
		
		JButton btnMul = new JButton("*");
		btnMul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				first=Double.parseDouble(textField.getText());
				textField.setText("");
				operation="*";
			}
			
		});
		
		btnMul.setBounds(266, 228, 72, 56);
		btnMul.setText("*");
		btnMul.setHorizontalAlignment(SwingConstants.CENTER);
		btnMul.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btnMul);
		
		JButton btnDivide = new JButton("/");
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				first=Double.parseDouble(textField.getText());
				textField.setText("");
				operation="/";
			}
			
		});
		
		
		btnDivide.setBounds(266, 294, 72, 56);
		btnDivide.setText("/");
		btnDivide.setHorizontalAlignment(SwingConstants.CENTER);
		btnDivide.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btnDivide);
		
		JButton btnPercent = new JButton("%");
		btnPercent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				first=Double.parseDouble(textField.getText());
				textField.setText("");
				operation="%";
			}
			
		});
		
		
		btnPercent.setBounds(266, 359, 72, 56);
		btnPercent.setText("%");
		btnPercent.setHorizontalAlignment(SwingConstants.CENTER);
		btnPercent.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmHu.getContentPane().add(btnPercent);
	}

}
