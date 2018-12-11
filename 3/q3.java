import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import jdk.nashorn.internal.ir.ContinueNode;

class node{
	int id;
	String name;
}

public class q3 {
        public static void searchparent(ArrayList<Integer>p,ArrayList<Integer>c,int s){
            for(int i = 0;i<p.size();i++){
                if(s==c.get(i)){
                    System.out.print(p.get(i)+"  ");
                }
            }
        }
        
        public static void searchchild(ArrayList<Integer>p,ArrayList<Integer>c,int s){
            for(int i = 0;i<p.size();i++){
                if(s==p.get(i)){
                    System.out.print(c.get(i)+"  ");
                }
            }
        }
        
        public static void searchancestors(ArrayList<Integer>p,ArrayList<Integer>c,int s){
            ArrayList<Integer> a = new ArrayList<Integer>(s);
            for(int i = 0;i<a.size();i++){
                if( c.contains(a.get(i)) ){
                    a.remove(c.indexOf(a.get(i)));
                    a.add(p.get(i));
                    System.out.print(p.get(i)+"  ");
                    i=0;
                }
            }
        }
        public static void searchdescendants(ArrayList<Integer>p,ArrayList<Integer>c,int s){
            ArrayList<Integer> a = new ArrayList<Integer>(s);
            for(int i = 0;i<a.size();i++){
                if( p.contains(a.get(i)) ){
                    a.remove(p.indexOf(a.get(i)));
                    a.add(c.get(i));
                    System.out.print(c.get(i)+"  ");
                    i=0;
                }
            }
        }
        //doing
        public static void deletenode(ArrayList<Integer>p,ArrayList<Integer>c,int s){
            ArrayList<Integer> a = new ArrayList<Integer>(s);
            for(int i = 0;i<a.size();i++){
                int x = a.get(i);
                if( p.contains(x) ){
                    a.remove(p.indexOf(x));
                    p.remove(p.indexOf(x));
                    a.add(c.get(i));
                    c.remove(c.indexOf(c.get(i)));
                    System.out.print(c.get(i)+"  ");
                    i=0;
                }
            }
        }
        
        public static void drelationnodes(ArrayList<Integer>p,ArrayList<Integer>c,String r[]){
         //   ArrayList<Integer> a = new ArrayList<Integer>(s);
         int i;   
         for(i = 0;i<p.size();i++){
                if( p.get(i)==Integer.parseInt(r[0]) && c.get(i)==Integer.parseInt(r[1]) ){
                    p.remove(i);
                    c.remove(i);
                    System.out.println("Deleted");break;
                }
                
            }
            if(i==p.size()){System.out.println("Not found");}
        }
         public static void arelationnodes(ArrayList<Integer>p,ArrayList<Integer>c,String r[]){
         //   ArrayList<Integer> a = new ArrayList<Integer>(s);
         int i;   
         for(i = 0;i<p.size();i++){
                if( p.get(i)==Integer.parseInt(r[0]) && c.get(i)==Integer.parseInt(r[1]) ){
                    System.out.println("Already Exist");break;
                }
                
            }
            if(i==p.size()){
            p.add(Integer.parseInt(r[0]));
            c.add(Integer.parseInt(r[1]));
                System.out.println("Added");
            }
        }
         public static void newnode(Hashtable<Integer,String> r){
         try{
               Scanner sc = new Scanner(System.in);
               node n = new node();
               System.out.println("Node ID:");
               n.id=sc.nextInt();
               System.out.println("Node Name:");
               n.name=sc.next();
               if(r.containsKey(n.id)){
               throw new Exception("Already in use");
               }
               else{
               r.put(n.id,n.name);
               }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
         }
	public static void main(String[] args) {
            Hashtable<Integer,String> record  = new Hashtable<Integer,String>();
            ArrayList<Integer>parent = new ArrayList<Integer>();
            ArrayList<Integer>child = new ArrayList<Integer>();
            //System.out.println("Enter node Info....");
            
            try{
              Scanner sc = new Scanner(System.in);String flag = "y";
              /*  while(flag.equals("y")){
               node n = new node();
               System.out.println("Node ID:");
               n.id=sc.nextInt();
               System.out.println("Node Name:");
               n.name=sc.next();
               if(record.containsKey(n.id)){
               throw new Exception("Already in use");
               }
               else{
               record.put(n.id,n.name);
               System.out.println("Want to add more nodes..(y/n)");
               flag = sc.next();
               }
            }
            System.out.println("Enter number of relationships you want to add:");
            int n = sc.nextInt();
            System.out.println("Add relationships like 1,2 if 1 is parent of 2");
            for(int i=0;i<n;i++){
              String read[] = sc.next().split(",");
              parent.add(Integer.parseInt(read[0]));
              child.add(Integer.parseInt(read[1]));
            }   */ 
              
                while(flag.equals("y")){
                    System.out.println("1.the immediate parents\n2.the immediate children\n3.the ancestors\n4.the descendants\n"+
                                "5.Delete a whole dependecies from node\n6.delete a dependency\n7.Add new dependency\n8.AddNode");
                 int j=sc.nextInt();   
                 switch(j){
                case 1: System.out.println("the immediate parents of node id: ");
                int search  = sc.nextInt();
                searchparent(parent,child,search);
                break;
               case 2: System.out.println("the immediate children of  node id: ");
                search  = sc.nextInt();
                searchchild(parent,child,search);
                break;
                case 3: System.out.println("the ancestors of node id: ");
                search  = sc.nextInt();
                searchancestors(parent,child,search);
                break;
                case 4:
                System.out.println("the descendants of node id: ");
                search  = sc.nextInt();
                searchdescendants(parent,child,search);
                break;
                case 5:
                System.out.println("Delete a whole dependecies from a tree of node id: ");
                search  = sc.nextInt();
                deletenode(parent,child,search);
                break;
                case 6:
                System.out.println("delete a dependency to a tree of node ids(eg 1,2):  ");
                String read[] = sc.next().split(",");
                drelationnodes(parent,child,read);
                break;case 7:
                System.out.println("Add new dependency to a tree of node ids(eg 1,2):  ");
                search  = sc.nextInt();
                String rad[] = sc.next().split(",");
                arelationnodes(parent,child,rad);
                
                break;case 8:
                System.out.println("a new node to tree:  ");
                //search  = sc.nextInt();
                newnode(record);
                break;
               default:
                   System.out.println("Choose Correct Options!!!");
             }
            System.out.println("Want To continue(y/n)?");
             flag = sc.next();
            } 
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
     }

}