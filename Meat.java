public class Meat extends GroceryItem 
{
  private int weight;
    
  public Meat(int foodCode, String label, double price, int w)
  {
	  super(foodCode, label, price);
	  weight = w;
  }
  public void setWeight(int w)
  {
	  weight = w;
  }
  
  public int getWeight()
  {
	  return weight;
  }
  
  public double getPrice()
  {
	  return weight*super.getPrice();
  }
}
