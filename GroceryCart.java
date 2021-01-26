import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JTextArea;

public class GroceryCart 
{
	//Stack replaced the arrayList
	private Stack<GroceryItem> stackItems;
	int currentIndex;
	
	public GroceryCart()
	{
		stackItems = new Stack<GroceryItem>();
		currentIndex = -1;
	}
	//Pops the next item into stack
	public GroceryItem removeTopItem()
	{
		if (stackItems.size() > 0)
		{
		  currentIndex--;
		  if (currentIndex < 0) currentIndex = 0;
		  return stackItems.pop();
		}
		return null;
	}
	
	public void startViewing()
	{
		currentIndex = 0;
	}
	
	public GroceryItem viewNextItem()
	{
		GroceryItem next = null;
		ListIterator<GroceryItem> iterator = stackItems.listIterator(stackItems.size());
		
		if (iterator.hasPrevious())
		{
		  next = iterator.previous();
		  iterator.remove();
		}
		return next;
	}
	
	public void display(JTextArea displayArea)
	{
	  displayArea.setText("");
	  
	  for (int i = stackItems.size() - 1; i >= 0; i--)
	  {
	    displayArea.append(stackItems.get(i).getLabel() + "\n\n");
	  }
	}
	
	public void clear()
	{
		stackItems = new Stack<GroceryItem>();
		currentIndex = -1;
	}
	//Fill now uses stack push
	public void fill() 
	{	
		int code = 0;
		String lable = "";
		double price = 0.00;
		int amount = 0;
		GroceryItem item;
		//reads from a txt file
		try
			{
			Scanner x = new Scanner(new File("groceryItems.txt"));
				//uses a string array an split to find the code and variables and throws IOexception
			while ((x.hasNextLine()))
			{
				String line = x.nextLine();
                String[] d = line.split("\t");
              
                if(d[1].equals("Meat"))
                {
                	code = Integer.parseInt(d[0]);
    				lable = d[2];
    				price = Double.parseDouble(d[3]);
    				amount = Integer.parseInt(d[4]);
    				item = new Meat(code, lable, price, amount);
    				stackItems.push((item));
                }
                else if (d[1].equals("Dairy"))
                {
                 	code = Integer.parseInt(d[0]);
    				lable = d[2];
    				price = Double.parseDouble(d[3]);
    				amount = Integer.parseInt(d[4]);
    				item = new Dairy(code, lable, price, amount);
    				stackItems.push((item));
                }
                else
                {
                	code = Integer.parseInt(d[0]);
                	lable = d[1];
                	price = Double.parseDouble(d[2]);
                	item = new GroceryItem(code, lable, price);
                	stackItems.push((item));
                }
                
			}
			x.close();
			}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
