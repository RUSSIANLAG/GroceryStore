import java.util.Comparator;

public class FoodType implements Comparable<FoodType>
{
   String label;
   int code;
   int measure;
   int kcal;
   int sugar;
   int fat;
   int carbs;
     
   public FoodType()
   {
	  label = "";
	  code = kcal = sugar = fat = carbs = 0;
	  measure = 1;
   }
   
   public FoodType(int code, String label, int measure, int kcal, int sugar, int fat, int carbs)
   {
	   this.code = code;
	   this.label = label;
	   this.measure = measure;
	   this.kcal = kcal;
	   this.sugar = sugar;
	   this.fat = fat;
	   this.carbs = carbs;
   }
   
   public int compareTo(FoodType otherFood)
   {
	   // compare measure * calories
	   if (this.measure*this.kcal < otherFood.measure*otherFood.kcal) return  1;
	   if (this.measure*this.kcal > otherFood.measure*otherFood.kcal) return -1;
	   return 0;
   }
   
   public String display()
   {
	   return label + ":" + " Cals " + kcal*measure + 
			                " Sugar " + sugar*measure + 
			                " Fat " + fat*measure + 
			                " Carbs "+ carbs*measure;
   }
}


