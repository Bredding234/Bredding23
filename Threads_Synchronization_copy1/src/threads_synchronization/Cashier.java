/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads_synchronization;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author Bredding
 */

public class Cashier extends Thread  
{
    
private Buffer<Thread> buffer;
static int time;
private Semaphore semaphore;
Cashier_Bounded_Buffer<Thread> sleep = new Cashier_Bounded_Buffer<Thread>();

/*
The cashier constructor that initializes the semaphores, the time units between 10 and 20, and the buffer . 

 */
public Cashier(int v, Semaphore semaphore, Buffer<Thread> buffers) {
                 //visNum = v;
		this.semaphore = semaphore;
                time = (int) (Math.random()*((20-10)+1)+10);
                this.buffer = buffers;

}
//Cashier cash = new Cashier();

Queue<Thread> visitors = new LinkedList<Thread>();
public void addVisitor(Vistor new_vistor){
   visitors.add(new_vistor);
}


public void removeVisitor(){
    visitors.remove();
}


 public void run()
   {
   Vistor vistors = new Vistor(semaphore,buffer);
   int count = 1;
    
     
      
         System.out.println("Cashiers are waiting for customers");
	 SleepUtilities.nap();   
         //break;
      
         //if(vistors.items > 1){   
         //visitors.add(vistors);

             //visitors.remove();
             //System.out.println(vistors.items);
         Random ran = new Random();          
         //visNum = {1,2,3,4,5,6};

         /*
         
        for (int i = 0; i<visNum.length; i++){   
         vistors.items = ran.nextInt(6)+1;
        
         System.out.println("The number of items that customer " + (visNum[i]) + " has " + vistors.items + " items. ");
         SleepUtilities.nap(6);
          
         //break;
        }
                 */
             //System.out.println("The Visitor has checked out of the store");
         //}
         //if(vistors.items == vistors.items){
             //        SleepUtilities.nap(10);
               //      vistors
         
         //}
       // if(count == 1){
                //System.out.println("The number of people waiting in the cashier line after the check out " + buffer.Customer_Line());
                //SleepUtilities.nap(20);   
         //}
         // consume an item from the buffer
        // System.out.println("Cashiers want more visitors.");
         //vistors = buffer.Remove_Customer_Line();
         
      
   }

public void removeList(){
        Random rand = new Random();
        int r = rand.nextInt(11)+10;
     for(int i = 0;i < visitors.size(); i++){
         try{
            Thread.sleep(r);
         }catch(Exception e){
             System.out.println("Error!");
         }    
         visitors.remove(i);
        }
   }

    
        
}
