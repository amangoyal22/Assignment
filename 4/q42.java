import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList; 
  
class item{
	String name;
	int price = 0;
	double tax = 0;
	double total = 0;
}
     


public class q42 
{ 
    public static void main(String[] args) 
                        throws InterruptedException 
    { 
        
        
        final PD pc = new PD(); 
        Thread t1 = new Thread(new Runnable() 
        { 
            @Override
            public void run() 
            { 
                try
                { 
                    pc.sqldata(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
  
        Thread t2 = new Thread(new Runnable() 
        { 
            @Override
            public void run() 
            { 
                try
                { 
                    pc.print(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
        t1.start(); 
        t2.start(); 
        t1.join(); 
        t2.join(); 
    } 
  
    
    public static class PD 
    { 
        LinkedList<item> list = new LinkedList<>(); 
      
        public void sqldata() throws InterruptedException 
        { 
            int value = 0; 
            while (true) 
            { 
  try{
      String myDriver = "org.gjt.mm.mysql.Driver";
      String myUrl = "jdbc:mysql://localhost/test";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
      String query = "SELECT * FROM items";
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      synchronized (this) { 
        rs.next();
      // iterate through the java resultset
      if(rs != null)
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
                 list.add(x);
         notify(); 
         Thread.sleep(1000); 
           
      }
                }
      st.close();
      
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());Thread.sleep(1000000);
      wait();
    }       } 
        } 
  
        public void print() throws InterruptedException 
        { 
            while (true) 
            { 
                synchronized (this) 
                { 
                    
                    
                    while (list.size()==0) 
                        wait(); 
  
                    item val = list.removeFirst(); 
                    System.out.println("-->"+val.name+"  "+val.price+" "+val.tax+" "+val.total); 
                    notify(); 
                    Thread.sleep(1000); 
                } 
            } 
        } 
    } 
} 