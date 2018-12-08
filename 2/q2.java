import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class user 
{ 
    int age; 
    int roll; 
    String name, address;
    String courses[];
  
    // Constructor 
    public String writeString(){
       String l = "";int  j;
        for(j =0;j<this.courses.length-1;j++){
            l+=this.courses[j]+",";
       }
        l+=this.courses[j++];
       return this.age+"%"+this.roll+"%"+this.name+"%"+l+"%"+this.address; 
    }
    public user(int age,int roll, String name, 
                               String address,String courses[]) 
    { 
        this.age = age; 
        this.roll = roll; 
        this.name = name;
        this.courses = courses;
        this.address = address; 
    } 
}

class Sortbyrolla implements Comparator<user> 
{ 
    public int compare(user a, user b) 
    { 
        return a.roll - b.roll; 
    } 
}
class Sortbyagea implements Comparator<user> 
{ 
    public int compare(user a, user b) 
    { 
        return a.age - b.age; 
    } 
}
class Sortbyrolld implements Comparator<user> 
{ 
    public int compare(user a, user b) 
    { 
        if((a.roll - b.roll)>0){return -1;}
        else return 1; 
    } 
}
class Sortbyaged implements Comparator<user> 
{ 
    public int compare(user a, user b) 
    { 
        if((a.age - b.age)>0){return -1;}
        else return 1; 
    } 
}
class Sortbynamea implements Comparator<user> 
{ 
    public int compare(user a, user b) 
    { 
        int x = a.name.compareTo(b.name) ;
        if(x<0)return -1;
        if(x==0){
        return a.roll - b.roll;
        }
        else return 1;
    } 
}
class Sortbyadda implements Comparator<user> 
{ 
    public int compare(user a, user b) 
    { 
        int x = a.address.compareTo(b.address) ;
        if(x==0){
        return a.roll - b.roll;
        }
        else return x;
    } 
}
class Sortbynamed implements Comparator<user> 
{ 
    public int compare(user a, user b) 
    { 
        int x = a.name.compareTo(b.name) ;
        if(x<0)return 1;
        if(x==0){
        if((a.roll - b.roll)>0){return -1;}
        else return 1;
        }
        else return -1;
    } 
}
class Sortbyaddd implements Comparator<user> 
{ 
    public int compare(user a, user b) 
    { 
        int x = a.address.compareTo(b.address) ;
        if(x==0){
        if((a.roll - b.roll)>0){return -1;}
        else return 1;
        }
        else return -1*x;
    } 
}
public class q2 {
   
    public static void Display(ArrayList a){
        ArrayList ax = a;
    	System.out.println("\nAscending(a) / Descending(d): ");
        Scanner sc = new Scanner(System.in);
       String x = sc.next();
       System.out.println("Column(name/roll number/age/address): ");
       String y = sc.next();
       if(ax.equals("a")){
       if(y.equals("name")){Collections.sort(ax, new Sortbynamea());}
       else if(y.equals("roll number")){Collections.sort(ax, new Sortbyrolla());}
       else if(y.equals("age")){Collections.sort(ax, new Sortbyagea());}
       else if(y.equals("address")){Collections.sort(ax, new Sortbyadda());}
       }
       else{
       if(y.equals("name")){Collections.sort(ax, new Sortbynamed());}
       else if(y.equals("roll number")){Collections.sort(ax, new Sortbyrolld());}
       else if(y.equals("age")){Collections.sort(ax, new Sortbyaged());}
       else if(y.equals("address")){Collections.sort(ax, new Sortbyaddd());}
       
       }
//      Collections.sort(ar, new Sortbynamea()); 
        for (int i=0; i<a.size(); i++) 
            System.out.println(((user)ax.get(i)).roll+" "+((user)ax.get(i)).name+" "+((user)ax.get(i)).age+" "+((user)ax.get(i)).address); 
    }
    
    public static ArrayList Delete(ArrayList a){
    	try {
    	Scanner sc = new Scanner(System.in);
        System.out.println("Roll number: ");
    	int r = sc.nextInt();
    	if(x.contains(r)) {
    		throw new Exception("Does not exist");
    	}
        for (int i=0; i<a.size(); i++){ 
    		if(((user)a.get(i)).roll==r) {
    			a.remove(i);break;
    		}}
        return a;
    	}
    	catch(Exception ex) {
      	   System.out.print("\n\n"+ex.getMessage());
         }
        return a;
    }
    
    public static void Exit(ArrayList a){
        System.out.println("Want to save last changes???(y/n): ");
        Scanner sc = new Scanner(System.in);
        String x = sc.next();
        if(x.equals("y")){
        Save(a);
        }
        flag=0;
        
    }
    
    public static void Save(ArrayList a){
        System.out.print("working.....\n");
    try
        { 
        //String tmp = a.toString();
        PrintWriter pw = new PrintWriter(new FileOutputStream("t.txt"));
        for(int i = 0;i<a.size();i++){
        pw.write(((user)a.get(i)).writeString()+"\n");
          }
        pw.close();
        }
        catch (FileNotFoundException fe)
        {
            System.out.println("File not found");
        }
    
    }
    public static ArrayList upd(ArrayList a){
        BufferedReader reader;
       // ArrayList<user> a = new ArrayList();    
               // BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("t.txt"));
			String line = reader.readLine();
			while (line != null) {
				String x[] = line.split("%");
                                user n;
                                int xx = Integer.parseInt(x[0]);
                                int xxx = Integer.parseInt(x[1]);
                            n = new user(xx,xxx,x[2],x[4],x[3].split(","));
                                a.add(n);
                                //System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return a;
    }
    public static ArrayList Add(ArrayList a){
    	try {
        a=upd(a);
        Scanner sc = new Scanner(System.in);
        System.out.println("Roll number: ");
    	int r = sc.nextInt();
    	if(x.contains(r)) {
    		throw new Exception("Already exists");
    	}
    	System.out.println("Age: ");
    	int age = sc.nextInt();
    	
    	System.out.println("Name: ");
    	String name = sc.next();
    	
    	System.out.println("Address: ");
    	String add = sc.next();

    	System.out.println("Courses: ");
    	String course[] = (sc.next()).split(",");
    	
    	if(course.length<4)throw new Exception("Minimum 4 courses required");
    	else {
    		for(int i = 0;i<course.length;i++) {
    			if(course[i].equals("A")||course[i].equals("B")||course[i].equals("C")||course[i].equals("D")||course[i].equals("E")||course[i].equals("F")){}
    			else {throw new Exception("total 6 courses (Course A, B, C, D, E and F)");}
    		}
    	}
        a.add(new user(age,r,name,add,course)); Collections.sort(a, new Sortbynamea());
        return a;
    }
    	catch(Exception ex) {
     	   System.out.print("\n\n"+ex.getMessage());
        }
        return a;
    	}
    static ArrayList x = new ArrayList();
    static  int flag =  1;
    // Driver Code
    public static void main(String args[]) 
    { 	ArrayList<user> ar = new ArrayList<user>(); 
        x.add(0);
        while(flag==1){
        System.out.print("1.	Add User details.\r\n" + 
        		"2.	Display User details.\r\n" + 
        		"3.	Delete User details\r\n" + 
        		"4.	Save User details.\r\n" + 
        		"5.	Exit\r\n" + 
        		" \r\n");
        Scanner sc = new Scanner(System.in);
        try {
        	int i = sc.nextInt();
        	if(i>5 || i<=0) {
        		throw new Exception("Please select correct option");
        	}
        	else {
        		switch(i) {
        		case 1: ar = Add(ar);//System.out.println(ar.size());
        			break;
        		case 2: Display(ar);
        			break;
        		case 3: ar = Delete(ar);
            		break;
        		case 4:Save(ar);
        			break;
        		case 5:Exit(ar);
        			break;
        		}
        	}
        }
       catch(Exception ex) {
    	   System.out.print("\n\n"+ex.getMessage());
       }
    }
    }
}