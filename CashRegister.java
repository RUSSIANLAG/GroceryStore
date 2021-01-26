import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
   A simulated cash register that tracks the grocery items and
   the total amount due.
*/
public class CashRegister
{
   private ArrayList<GroceryItem> items;

   Calendar calendar;
   SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd kk:mm:ss yyyy");
   
   /**
      Constructs a cash register with cleared item totals
   */
   public CashRegister()
   {
      items = new ArrayList<GroceryItem>();
   }

   /**
      Adds an item to this cash register.
      @param GroceryItem instance
   */
   public void addItem(GroceryItem item)
   {
      items.add(item);
   }
   
   public void scanItem(GroceryItem item)
   {
	   items.add(item);
   }

   /**
      Gets the price of all items of the current sale.
      @return the total price
   */
   public double getTotal()
   {
      double total = 0.0;
      for (GroceryItem item : items)
      {
         total += item.getPrice();
      }
      return total;
   }

   /**
      Gets the number of items in the current sale.
      @return the item count
   */
   public int getNumItems()
   {
      return items.size();
   }

   /**
      Clears the items and the total.
   */
   public void clear()
   {
      items = new ArrayList<GroceryItem>();
   }
   
   /**
      Displays the items in the current sale.
   */
   public void displayAll(JTextArea displayArea)
   {
	   displayArea.setText("");
	   
	   for (int i = 0; i < items.size(); i++)
	   {
		 displayArea.append(items.get(i).getLabel() + "   " + String.format("%10.2f", items.get(i).getPrice()) + "\n\n");
	   }
	   displayArea.append("-----------------------\n");
	   displayArea.append("Total: " + String.format("%10.2f", getTotal())+ "\n\n");
	  
	   calendar = new GregorianCalendar();
	   displayArea.append(sdf.format(calendar.getTime())); 
   }
}
