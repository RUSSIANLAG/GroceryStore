import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class NutritionChart 
{
   Map<FoodType, Integer> map; 
   
   public NutritionChart()
   {
	   //uses a hashmap now
	   	map = new HashMap<FoodType, Integer>();
	   	int code = 000;
		String label = "";
		int measure = 0;
		int calories = 000; 
		int sugar = 000; 
		int fat = 000;
		int carbs = 000;
		FoodType item;
		//fills from a txt file
		try
			{
			Scanner x = new Scanner(new File("nutrition.txt"));
				//Goes through the file separated by a comma space and reading variables
			while (x.hasNextLine())
			{
				String line = x.nextLine();
				String[] spl = line.split(", ");
				
				code = Integer.parseInt(spl[0]);
				label = spl[1];
				measure = Integer.parseInt(spl[2]);
				calories = Integer.parseInt(spl[3]); 
				sugar = Integer.parseInt(spl[4]); 
				fat = Integer.parseInt(spl[5]);
				carbs = Integer.parseInt(spl[6]);
				item = new FoodType (code, label, measure, calories, sugar, fat, carbs);
				map.put(item, code);
				}
			x.close();
			}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
   }
   //Gets the Foodcode and uses it to return the correct item
   public FoodType getFoodType(int foodCode)
   {
	   Set<FoodType> keySet = map.keySet();
	   for (FoodType key: keySet)
	   {
		   if (foodCode == key.code)
		   {
			   return new FoodType(key.code, key.label,key.measure,key.kcal,key.sugar,key.fat,key.carbs);
		   }
	   }
	   return null;
   }
}
