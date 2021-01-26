import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;


public class ConveyerBelt extends JPanel
{
    /**
	 * 
	 */
	//Lists
	private LinkGroceryItem first = null;
    public LinkGroceryItem pickedUpItem = null;
    //Draws the picked up item
    public void paintComponent (Graphics g)
    	{
    	super.paintComponent (g);
    	Graphics2D g2 = (Graphics2D) g;
    	if (pickedUpItem != null)
		{
	    pickedUpItem.draw (g2);
		}
    }

    //adds the item and shifts it 	
    public void addItem (GroceryItem gitem)
    {
    	LinkGroceryItem temp = new LinkGroceryItem (gitem, pickedUpItem);
    	temp.next = first;
    	first = temp;
    	pickedUpItem.setLocation (pickedUpItem.box.x, pickedUpItem.box.y+50);
    	repaint ();
    }

    //removes the item from the previous list
    public GroceryItem removeItem ()
    {
    	LinkGroceryItem current = null;  // Initialize current
    	while (first.next != null)
    	{
    	    current = first;
    	    first = first.next;
    	}
    	repaint ();
    	return current.gitem;
    }

    //Sets the next picked up item
    public void setPickedUpItem (GroceryItem gitem)
    {
	LinkGroceryItem temp = new LinkGroceryItem (gitem, null);
	pickedUpItem = temp;
	repaint ();
    }
    //gets the number of items
    private int numItems ()
    {
	int count = 0;  // Initialize count
	LinkGroceryItem current = first;  // Initialize current
	while (current != null)
	{
	    count++;
	    current = current.next;
	}
	return count;
    }


    //Inner class
    private class LinkGroceryItem
    {
	private GroceryItem gitem;
	private LinkGroceryItem next;
	private Rectangle box;
	private Rectangle bottom = new Rectangle(10, 150, 300, 50);
	//Constructor Method
	public LinkGroceryItem (GroceryItem gitem, LinkGroceryItem next)
	{
	    this.gitem = gitem;
	    this.next = next;
	    box = new Rectangle (10, 0, 90, 40);
	}
	//Location setting method
	public void setLocation (int x, int y)
	{
	    box.setLocation (x, y);
	}
	//Checks if the box intersects with the item
	public boolean intersects (LinkGroceryItem item)
	{
	    if (box.intersects (item.box))
	    {
		return true;
	    }
	    return false;
	}
	//Draws the object
	public void draw (Graphics2D g2)
	{
		if(first == null)
			return;
		
	    g2.setPaint (Color.black);
	    box.x = pickedUpItem.box.x;
	    box.y = pickedUpItem.box.y;
	    bottom.x = pickedUpItem.bottom.x;
	    bottom.y = pickedUpItem.bottom.y;
	    g2.draw (box);
	    g2.fill(bottom);
	    g2.setFont(new Font("TimesRoman", Font.PLAIN, 10));
	    g2.setColor(Color.gray);
	    g2.drawString (gitem.getLabel (), pickedUpItem.box.x + 15, pickedUpItem.box.y + 15);
	    pickedUpItem.setLocation (pickedUpItem.box.x, pickedUpItem.box.y);

	}
    }
}