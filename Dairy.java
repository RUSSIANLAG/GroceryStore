
public class Dairy extends GroceryItem 
{
   private int volume; // milliLiters
   
   public Dairy()
   {
 	  super();
	  volume = 0;
   }
   
   public Dairy(int foodCode, String label, double price, int vol)
   {
 	  super(foodCode,label,price);
 	  this.volume = vol;
   }
   
   public int getVolume()
   {
	   return volume;
   }
   
   public double getPrice()
   {
 	  return volume*super.getPrice();
   }
   
   public String toString()
   {
 	  return super.toString() + "[" + "Volume = " + volume + "]";
   }
}
