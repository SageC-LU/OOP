package oop22;

import java.util.*;
import java.io.*;

class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String phoneNumber;
	String DOB;
	String email;
	
	
	public Person(String name, String phoneNumber, String DOB, String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.DOB = DOB;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person [name=" + name +", phonenumber="+phoneNumber+", dob="+DOB+", email="+email+"]";
	}
	
}



public class Oop22Class {
	public static void writeToFile(Person p, String fn) throws FileNotFoundException, IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fn));
		output.writeObject(p);
	}
	
	public static void readFile(String fn) throws IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(fn));
		Person read = (Person) input.readObject();
		System.out.println(read);
	}
	
	static public void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----MENU-----\n"
				+ "1. Add Information into a File\n"
				+ "2. Retrieve Information From a File and Display Information\n"
				+ "3. Delete Information\n"
				+ "4. Update Information\n"
				+ "5. Exit Program");
		int in = sc.nextInt();
		
		if (in == 1) {
			String[] list = new String[6];
			System.out.println("Insert File Name:");
			System.out.println("Enter Person Name: ");
			System.out.println("Enter Person Phone Number: ");
			System.out.println("Enter Person Date Of Birth: ");
			System.out.println("Enter Person E-Mail: ");
			for (int i = 0; i < list.length; i++) {
				list[i] = sc.nextLine();
			}
			//sc.close();
			String file = list[1];
			Person p = new Person(list[2], list[3], list[4], list[5]);
			try {
				writeToFile(p,file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main(new String[] {"call"});
		}
		
		else if (in == 2) {
			System.out.println("Enter a file to display from:");
			String choice = sc.next();
			try {
				readFile(choice);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main(new String[] {"call"});
		}
		
		else if (in == 3) {
			System.out.println("Choose file to delete");
			String choice = sc.next();
			File file = new File(choice);
			file.delete();
			System.out.println("File has been deleted");
			main(new String[] {"call"});
		}
		
		else if (in == 4) {
			String[] list = new String[6];
			System.out.println("Enter file to update:");
			System.out.println("Enter Person Name: ");
			System.out.println("Enter Person Phone Number: ");
			System.out.println("Enter Person Date Of Birth: ");
			System.out.println("Enter Person E-Mail: ");
			for (int i = 0; i < list.length; i++) {
				list[i] = sc.nextLine();
			}
			//sc.close();
			String file = list[1];
			Person p = new Person(list[2], list[3], list[4], list[5]);
			try {
				writeToFile(p,file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("File has been updated.");
			main(new String[] {"call"});
		}
		
		else if (in == 5) {
			System.out.println("Exitting Application.");
		}
		
		else {
			System.out.println("Unrecognized Input"); 
		}
	}
}
