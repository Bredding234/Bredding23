/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads_synchronization;

/**
 *
 * @author Bredding
 */
import java.util.*;

/**
 *
 * @author Brian
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class Cashier_Bounded_Buffer<E> implements Buffer<E>
{
    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;
    private E[] buffer;
    private int in, out;
    private int count;
    private int buffer_SIZE = 50;
    
    public Cashier_Bounded_Buffer(){
        this.mutex = new Semaphore(1); //the mutex value initialize to 1
        this.empty = new Semaphore(buffer_SIZE);
        this.full = new Semaphore(0);
        
        this.buffer = (E[]) new Object[buffer_SIZE];
        this.in = 0;
        this.out = 0;
    }
    
    public void Customer_Line(E item){
        empty.acquire(); // The empty process is being acquire.
        //buffer[in] = item;
        //in = (in + 1 )% buffer_SIZE;
        
        
        // add an item to the buffer
		++count;
		buffer[in] = item;
		in = (in + 1) % buffer_SIZE; 
		
		if (count == buffer_SIZE) {
			System.out.println("Visitors has Entered " + item + " Buffer FULL");
                } else {
                        System.out.println("Visitors Entered " + item + " Buffer Size = " +  count);
                }
        
     
        full.release(); 
        mutex.release();
    } 
    
    public E Remove_Customer_Line(){
        full.acquire();
        //E item = buffer[out];
        //out = (out - 1) % buffer_SIZE;  
    
        
        // remove an item from the buffer
		--count;
		E item = buffer[out];
		out = (out + 1) % buffer_SIZE;
		
		if (count == 0)
			System.out.println("Cashiers Consumed " + item + " Buffer EMPTY");
        else
			System.out.println("Cashiers Consumed " + item + " Buffer Size = " + count);
		
		mutex.release();
		empty.release();
                
        empty.release();
        return item;
        
    }

    @Override
    public void sleep(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
	
    
}
