/* This program will display a graphical-user interface in which the user of this application
 * will have the ability to select a type of Loan object they would like to create (by clicking the corresponding button)
 * and insert the required characteristics of the Loan object.
 * Assignment: hw10
 * Author: Atman Sage Cooper
 */


package oop22;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Oop22Class {
	
	public interface LoanConstants {
		public double SHORT_TERM = .25; // 1 year
		public double MEDIUM_TERM = .15; // 3 years
		public double LONG_TERM = .075; // 5 years
		public String COMPANYNAME = "High Tech";
		public double MaxLoanAmount = 500000;
	}

	public static abstract class Loan implements LoanConstants {
		int loan_number;
		String customer_last_name;
		double loan_amount;
		double interest_rate;
		int term; // in years
		
		public Loan(int loan_number, String customer_last_name, double loan_amount, int term) {
			this.loan_number = loan_number;
			this.customer_last_name = customer_last_name;
			this.term = term;
			
			// check loan amount
			if (loan_amount <= 500000)
				this.loan_amount = loan_amount;
			if (loan_amount > 500000)
				System.out.println("Loan Amount Is Too High.");
			
			//determine interest rate
			if (term == 3)
				this.interest_rate = LoanConstants.MEDIUM_TERM;
			else if (term == 5)
				this.interest_rate = LoanConstants.LONG_TERM;
			else
				this.interest_rate = LoanConstants.SHORT_TERM;
		}
		
		public double totalPay() {
			return (loan_amount * interest_rate * term) + loan_amount;
		}
		
		public void print() {
			System.out.println("Loan Number: "+loan_number+
					" | Customer Last Name: "+customer_last_name+
					" | Loan Amount: "+loan_amount+
					" | Term: "+term+
					" | Interest Rate: "+interest_rate+
					" | Total Amount Due: "+ totalPay());
		}
	}
	
	public static class BusinessLoan extends Loan {
		double interest_rate;
		public BusinessLoan(int loan_number, String customer_last_name, double loan_amount, int term) {
			super(loan_number, customer_last_name, loan_amount, term);
			//determine interest rate
			if (term == 3)
				this.interest_rate = MEDIUM_TERM + .01;
			else if (term == 5)
				this.interest_rate = LONG_TERM + .01;
			else
				this.interest_rate = SHORT_TERM + .01;
		}	
	}
	
	public static class PersonalLoan extends Loan {
		double interest_rate;
		public PersonalLoan(int loan_number, String customer_last_name, double loan_amount, int term) {
			super(loan_number, customer_last_name, loan_amount, term);
			//determine interest rate
			if (term == 3)
				this.interest_rate = MEDIUM_TERM + .02;
			else if (term == 5)
				this.interest_rate = LONG_TERM + .02;
			else
				this.interest_rate = SHORT_TERM + .02;
		}	
	}
	
	//Create a class to contain the features of the GUI window
	static class ActionEvent implements ActionListener {
		JFrame frame = new JFrame();
		JButton personalButton = new JButton("Personal");
		JButton businessButton = new JButton("Business");
		JLabel label = new JLabel("Select Loan Type:");
		ArrayList<String> list = new ArrayList<>();
		String type = null;
		JLabel label2 = new JLabel("Insert Loan Options");
		JTextField text1 = new JTextField(10);
		JTextField text2 = new JTextField(10);
		JTextField text3 = new JTextField(10);
		JTextField text4 = new JTextField(10);
		JButton submit = new JButton("Submit");
		PersonalLoan p;
		BusinessLoan b;
		
		//When this class is invoked, these methods will execute
		ActionEvent() {
			prepareGUI();
			labelProperties();
			buttonProperties();
			textFieldProperties();
		}
		
		public void prepareGUI() {
			frame.setTitle("Loan GUI");
			frame.getContentPane().setLayout(new FlowLayout());
			frame.setVisible(true);
			frame.setBounds(200,200,400,400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setBackground(Color.pink);
		}
		
		public void buttonProperties() {
			personalButton.setBounds(50,300,100,40);
			frame.add(personalButton);
			personalButton.addActionListener(this);
			businessButton.setBounds(225,300,100,40);
			frame.add(businessButton);
			businessButton.addActionListener(this);
		}
		
		public void textFieldProperties() {
			text1.setText("Loan Number");
			text2.setText("Last Name");
			text3.setText("Loan Amount");
			text4.setText("Term");
			frame.add(text1);
			frame.add(text2);
			frame.add(text3);
			frame.add(text4);
			frame.add(submit);
			submit.addActionListener(this);
		}
		
		public void labelProperties() {
			label.setBounds(135,200,200,60);
			frame.add(label);
		}
		
		//Listener function, when input is detected or button is clicked, this method will handle those actions
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (e.getSource() == personalButton)
				type = "Personal";
			else if (e.getSource() == businessButton)
				type = "Business";
			
			//Once a user submits I create string copies, and corresponding type copies of the input.
			// An object of the users choosing is created with the input supplied
			// Then the properties of the Object are called using methods for the specific class
			else if (e.getSource() == submit) {
				list.clear();
				list.add(text1.getText());
				list.add(text2.getText());
				list.add(text3.getText());
				list.add(text4.getText());
				
				String num = text1.getText();
				String name = text2.getText();
				String amount = text3.getText();
				String term = text4.getText();
				String company = Loan.COMPANYNAME;
				String rate = "";
				String total = "";
				
				int num1 = Integer.valueOf(list.get(0));
				String name1 = list.get(1);
				double amount1 = Double.valueOf(list.get(2));
				int term1 = Integer.valueOf(list.get(3));
				
				if (type == "Personal") {
					p = new PersonalLoan(num1, name1, amount1, term1);
					rate = Double.toString(p.interest_rate);
					total = Double.toString(p.totalPay());
				}
				else if (type == "Business") {
					b = new BusinessLoan(num1, name1, amount1, term1);
					rate = Double.toString(b.interest_rate);
					total = Double.toString(b.totalPay());
				}
				
				new window2(type, num, name, amount, term, company, rate, total);
			}
		}
	}
	
	// A second window to display the object created
	static public class window2 {
		JFrame frame = new JFrame();
		String type = null;
		String num;
		String name;
		String amount;
		String term;
		String company;
		String rate;
		String total;
		ArrayList<String> list = new ArrayList<>();
		JLabel label = new JLabel("Your Object:");
		JLabel label2 = new JLabel();
		
		window2(String type, String num, String name, String amount, String term, String company, String rate, String total) {
			this.type = type;
			this.num = num;
			this.name = name;
			this.amount = amount;
			this.term = term;
			this.company = company;
			this.rate = rate;
			this.total = total;
			prepareGUI();
			
		}
		
		public void prepareGUI() {
			frame.setTitle("Loan GUI - Submitted");
			frame.getContentPane().setLayout(new FlowLayout());
			frame.setVisible(true);
			frame.setBounds(200,200,800,800);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setBackground(Color.pink);
			label.setBounds(135,200,200,60);
			frame.add(label);
			label2.setBounds(200,200,200,200);
			label2.setText(forOutput()); 
			frame.add(label2);
		}
		
		//Utilizes methods and variables unique to the Loan objects to display output
		public String forOutput() {
			String a ="| Company: "+company+"\n"
					+ "| Loan Number: " +num+"\n"
					+ "| Customer Last Name: "+name+"\n"
					+ "| Loan Amount: "+amount+"\n"
					+ "| Term: "+term+"\n"
					+ "| Interest Rate:"+rate+"\n"
					+ "| Total Amount Due: "+total;
			return a;
		}
	}
	
	static public void main(String[] args) {
		new ActionEvent();
	}
}
