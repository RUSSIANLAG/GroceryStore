public class GroceryItem 
{
  private String label;
  private int    foodCode;
  private double price;
  
  
  public GroceryItem()
  {
	  label = "";
	  price = 0.0;
  }
  
  public GroceryItem(int foodCode, String label, double price)
  {
	  this.foodCode = foodCode;
	  this.label = label;
	  this.price = price;
  }
  
  public int getFoodCode()
  {
	  return foodCode;
  }
  
  public String getLabel()
  {
	  return label;
  }
  
  public double getPrice()
  {
	  return price;
  }
  
  public String toString()
  {
	  return this.getClass() + "[" + label + "FoodCode = " + foodCode + " Price " + price + "]";
  }
}