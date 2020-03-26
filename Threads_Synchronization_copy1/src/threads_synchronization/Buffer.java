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
public interface Buffer <E>
{
    // Vistors call this method
	public void Customer_Line(E item);

	// Cashiers call this method
	public E Remove_Customer_Line();

    public void sleep(int i);

}
