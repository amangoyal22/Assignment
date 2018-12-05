import java.util.ArrayList;
import java.util.Scanner;

import jdk.nashorn.internal.ir.ContinueNode;

class item{
	String name;
	int price = 0;
	int quantity = 0;
	String type = "";
}
public class q1 {
	public static void display(ArrayList a){
		System.out.print("\n\n");
		for(int i =0;i<a.size();i++) {
			int price = ((item)a.get(i)).price;
			String type = ((item)a.get(i)).type;
			double tax = 0;
			if("raw".equals(type)) {
				tax=12.5*price/100;	
			}
			else if("manufactured".equals(type)){
				tax=(12.5*price/100);
				tax+=0.02*(price+tax);
			}
			else {
				tax=0.1*price;
				if(tax+price<=100)tax+=5;
				else if(tax+price<=200)tax+=10;
				else tax+=0.5*(tax+price);
			}
			double total = price+tax;
			System.out.println(((item)a.get(i)).name+"  "+price+"  "+tax+"   "+total+"\n");
		}
	}
	public static ArrayList again(ArrayList al,String[] args){
	     try {
	     	if(args[0].equals("-name")){
	     		item i1 = new item();
	     		i1.name=args[1];
	     		//System.out.print();
	     		for(int i=0;i<args.length - 2;i+=2) {
	     			if(args[i+2].equals("-price")) {
	     				i1.price=Integer.parseInt(args[i+3]);
	     			}else if(args[i+2].equals("-quantity")) {
	     				i1.quantity=Integer.parseInt(args[i+3]);
	     			}else if(args[i+2].equals("-type")) {
	     				if("raw".equals(args[i+3])|| "manufactured".equals(args[i+3])||"imported".equals(args[i+3]))
	     				{i1.type=args[i+3];}
	     				else {throw new Exception("Item type can have 3 possible values raw, manufactured and imported.\n");}
	     			}
	     		}
	     		if(i1.type.equals("")){throw new Exception("please enter item type\n");}
	     		else {
	     			al.add(i1);
	     			return al;
	     		}
	     	}
	     	else {
	     	throw new Exception("First argument should be name\n");
	     	}
	     	}
	     	catch(Exception ex) {
	     		System.out.print(ex.getMessage());
	     	}
	     
	     return al;
	}
    
	public static void main(String[] args) {
        ArrayList<item> al = new ArrayList();
        Scanner sc = new Scanner(System.in);
        String answer = "y";
        while(answer.equals("y")) {
        al = again(al,args);
        System.out.print("Do you want to enter details of any other item (y/n): ");
		answer = sc.nextLine();
		if(answer.equals("y")) {	
		String in1 = sc.nextLine();
		args=in1.split(" "); 
		}
        }
        display(al);
     }

}