import java.sql.*;
import java.util.ArrayList;

class item{
	String name;
	int price = 0;
	double tax = 0;
	double total = 0;
}
        
class Multithreading extends Thread 
{   
    
    private String name;
    public ArrayList<item> list;
    
   public Multithreading(String name,ArrayList l) {   // constructor
      this.name = name;
      this.list = l;
   }
    public void run() 
    { 
        try
        { 
        if(name=="sql"){
          try{
      String myDriver = "org.gjt.mm.mysql.Driver";
      String myUrl = "jdbc:mysql://localhost/test";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
      String query = "SELECT * FROM items";
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      
      // iterate through the java resultset
      while (rs.next())
      {
        double tax = 0;
        String name = rs.getString("name");
        String type = rs.getString("type");
        int quantity = rs.getInt("quantity");
        int price = rs.getInt("price");
        
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
                 item x = new item();
                 x.name = name;
                 x.tax = tax;
                 x.price = price;
                 x.total = price+tax;
                 this.list.add(x);
        }
      st.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
        }
    //print
        
        else{
            try{
            if(this.list.size()==0){throw new Exception("fetching in progress");}
            else{
            for(int i =0;i<this.list.size();i++) {
			int price = ((item)this.list.get(i)).price;
			double tax = ((item)this.list.get(i)).tax;
			double total = ((item)this.list.get(i)).total;
			String name = ((item)this.list.get(i)).name;
                        System.out.println("-->"+name+"  "+price+" "+tax+" "+total);
            }
            }
            }
            catch(Exception x){
                System.err.println(x.getMessage());
            }
        }
        } 
        catch (Exception e) 
        { 
            System.out.println ("Exception is caught"); 
        } 
    } 
} 
public class q4 
{ 
    public static void main(String[] args) 
    {       ArrayList<item> al = new ArrayList();
            Multithreading ob1= new Multithreading("sql",al); 
            ob1.start();
            Multithreading ob2= new Multithreading("print",al); 
            ob2.start();
    } 
} 