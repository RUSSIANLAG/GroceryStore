import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
   
*/
public class GroceryStoreFrame extends JFrame
{
   private static final int FRAME_WIDTH = 1200;
   private static final int FRAME_HEIGHT = 1000;
   
   private CashRegister cash        = new CashRegister();
   private GroceryCart  cart        = new GroceryCart();
   private NutritionScanner scanner = new NutritionScanner();
   //The conveyer belt
   private ConveyerBelt	belt 		= new ConveyerBelt();
   
   private GroceryItem item = null;
   
   public enum Attribute 
   {
	  CALS, FAT, CARBS, SUGAR 
   }
   //new Panel
   private JPanel       scanPanel;
   
   
   private JTextArea 	cartArea;
   private JPanel       cartPanel;
   private JScrollPane 	scrollPaneCartArea;
   private JLabel       cartLabel;
   private JLabel       nutritionScannerLabel;
   private JButton      scanFoodTypeButton;
   private JButton      resetNutritionScanButton;
   private JButton      carbsSorterButton;
   private JButton      calsSorterButton;
   private JButton      fatSorterButton;
   private JButton      sugarSorterButton;
   private JButton      scanGroceryItemButton;
   private JLabel       cashRegisterLabel;
   private JTextArea 	cashRegisterArea;
   private JScrollPane 	scrollPaneCRArea;
   private JPanel       cashRegisterPanel;
   private JTextArea    nutritionArea;
   private JScrollPane 	scrollPaneNutritionArea;
   private JPanel       nutritionPanel;
   private JLabel       nutritionLabel;
   private ActionListener listener;
   private JButton btnPickUp;
   private JButton btnAdd;
   
   
  

   /**
      Constructs the frame.
   */
   public GroceryStoreFrame()
   { 
	  // Fill up grocery cart
      
	  
      createControlPanels();
         
      setSize(FRAME_WIDTH, FRAME_HEIGHT);
   }

   //New Panel added for the conveyer belt
   private void createControlPanels()
   {
	  createGroceryCartPanel();
	  createCashRegisterControlPanel();
	  createActionPanel();
	  createNutritionControlPanel();
	  createGroceryScanPanel();
   }
   //adds the belt to the new panel
   private void createGroceryScanPanel()
   {
	 scanPanel = new JPanel();
	 scanPanel.setLayout(new BorderLayout());
	 scanPanel.add(belt, BorderLayout.CENTER);
     getContentPane().add(scanPanel, BorderLayout.CENTER);
   }
   
   
   
   private void createGroceryCartPanel()
   {
	 cartPanel = new JPanel();
	 cartPanel.setLayout(new BorderLayout());
	 cartPanel.setBorder(BorderFactory.createLineBorder(Color.red));
	   
	 cartArea = new JTextArea(30,20);
	 cartArea.setFont(new Font("SansSerif", Font.BOLD, 22));
	 cartArea.setText("");
	 cartArea.setEditable(false);
	 scrollPaneCartArea = new JScrollPane(cartArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	 cartPanel.add(scrollPaneCartArea, BorderLayout.CENTER);
	 cart.display(cartArea);
	  
	 JPanel cartControlPanel = new JPanel(); 
	 cartLabel = new JLabel("Grocery Cart");
	 cartLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
	 JButton refillButton = new JButton("REFILL");
	 refillButton.setFont(new Font("SansSerif", Font.BOLD, 22));
	 cartControlPanel.add(cartLabel);
	 cartControlPanel.add(refillButton);
	 cartPanel.add(cartControlPanel, BorderLayout.NORTH);
	 
	  class CartRefillListener implements ActionListener
	  {  
	      public void actionPerformed(ActionEvent event)
	      {  
	    	  if (cart != null)
	    	  {
                cartArea.setText("");
	    		cart.clear();
	    	    cart.fill();
	    	    cart.display(cartArea);
	    	  }
	      }
	   }
	   listener = new CartRefillListener();
	   refillButton.addActionListener(listener);

      getContentPane().add(cartPanel, BorderLayout.WEST);
      scrollPaneCartArea.repaint();
   }
   
   
   
   private void createCashRegisterControlPanel()
   {
	  cashRegisterLabel = new JLabel("Cash Register");
	  cashRegisterLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
      cashRegisterArea = new JTextArea(30,20);
	  cashRegisterArea.setFont(new Font("SansSerif", Font.BOLD, 22));
	  cashRegisterArea.setText("");
	  cashRegisterArea.setEditable(false);
	  scrollPaneCRArea = new JScrollPane(cashRegisterArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	  
	  cashRegisterPanel = new JPanel();
	  cashRegisterPanel.setLayout(new BorderLayout());
      cashRegisterPanel.setBorder(BorderFactory.createLineBorder(Color.red));
      JPanel CRControlPanel = new JPanel();
	  JButton clearButton = new JButton("CHECKOUT");
	  clearButton.setFont(new Font("SansSerif", Font.BOLD, 22));
	  CRControlPanel.add(cashRegisterLabel);
	  CRControlPanel.add(clearButton);
	   
	  class CheckOutListener implements ActionListener
	  {  
	      public void actionPerformed(ActionEvent event)
	      {  
	    	  cash.clear();
	    	  cashRegisterArea.setText("");
	      }
	   }
	   listener = new CheckOutListener();
	   clearButton.addActionListener(listener);
      
      cashRegisterPanel.add(CRControlPanel, BorderLayout.NORTH);
      cashRegisterPanel.add(scrollPaneCRArea, BorderLayout.CENTER);
      getContentPane().add(cashRegisterPanel, BorderLayout.EAST);
	  scrollPaneCRArea.repaint();
   }
   

   private void createActionPanel()
   {
	   JPanel panel = new JPanel();
	   panel.setFont(new Font("SansSerif", Font.BOLD, 28));
	   panel.setLayout(new BorderLayout());
	   
	   JPanel groceryScannerPanel = new JPanel();
	   
	  //The button for picking up the next Item
	   btnPickUp = new JButton("PICK UP");
	   btnPickUp.setFont(new Font("SansSerif", Font.BOLD, 15));
	   groceryScannerPanel.add(btnPickUp,BorderLayout.NORTH);
	   btnPickUp.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	    	  item = cart.removeTopItem();
	    	  if (item == null) return;
	    	  belt.setPickedUpItem(item);
	    	  belt.addItem(item);
	    	  cart.display(cartArea);

	   	}
	   });
	   //The button that adds the next Item
	   btnAdd = new JButton("ADD");
	   btnAdd.setFont(new Font("SansSerif", Font.BOLD, 15));
	   groceryScannerPanel.add(btnAdd,BorderLayout.NORTH);
	   class Picking implements ActionListener
	   {  
	      public void actionPerformed(ActionEvent event)
	      {  
	    	  if (item == null) return;
	    	  belt.addItem(item); 
	      }
	   }
	   ActionListener adding = new Picking();
	   btnAdd.addActionListener(adding);
	   
	  
	   //The scan button to print on the checkout
	   scanGroceryItemButton = new JButton("SCAN");
	   scanGroceryItemButton.setFont(new Font("SansSerif", Font.BOLD, 15));
	   groceryScannerPanel.add(scanGroceryItemButton,BorderLayout.NORTH);
	   panel.add(groceryScannerPanel);
	   getContentPane().add(panel,BorderLayout.NORTH);
	   class CRScanItemListener implements ActionListener
	   {  
	      public void actionPerformed(ActionEvent event)
	      { 
	    	  if (item == null) return;
	    	  belt.removeItem();
	    	  cash.scanItem(item);
	    	  cash.displayAll(cashRegisterArea);
	      }
	   }
	   ActionListener listener = new CRScanItemListener();
	   scanGroceryItemButton.addActionListener(listener);
   }
   
   private void createNutritionControlPanel()
   {
	  nutritionLabel = new JLabel("CART NUTRITION INFORMATION  ");
	  nutritionLabel.setFont(new Font("SansSerif", Font.BOLD, 28)); 
	  nutritionArea = new JTextArea(100,20);
	  nutritionArea.setFont(new Font("SansSerif", Font.BOLD, 22));
	  nutritionArea.setText("");
	  nutritionArea.setEditable(false);
	  scrollPaneNutritionArea = new JScrollPane(nutritionArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	  nutritionPanel = new JPanel();
	  nutritionPanel.setLayout(new BorderLayout());
      nutritionPanel.setBorder(BorderFactory.createLineBorder(Color.red));
      nutritionPanel.setPreferredSize(new Dimension(400, 400));
      
      nutritionPanel.add(scrollPaneNutritionArea, BorderLayout.CENTER);
      
      cart.startViewing();
      
      class NutritionScanner implements ActionListener
	   {  
	      public void actionPerformed(ActionEvent event)
	      {  
	    	  GroceryItem item = cart.viewNextItem();
	    	  if (item == null)
	    	  {
	    		return;
	    	  }
	    	  scanner.scanFoodCode(item.getFoodCode());
	    	  scanner.displayAll(nutritionArea);
	      }
	   }
	   ActionListener listener = new NutritionScanner();
	             
      scanFoodTypeButton = new JButton("SCAN FOOD ITEM");
	  scanFoodTypeButton.setFont(new Font("SansSerif", Font.BOLD, 22));
	  scanFoodTypeButton.addActionListener(listener);
	  
	  class ResetNutritionScanner implements ActionListener
	   {  
	      public void actionPerformed(ActionEvent event)
	      {  
	    	  cart.startViewing();
	    	  nutritionArea.setText("");
	    	  scanner.clear();
	      }
	   }
	   listener = new ResetNutritionScanner();
	   resetNutritionScanButton = new JButton("CLEAR");
	   resetNutritionScanButton.setFont(new Font("SansSerif", Font.BOLD, 22));
	   resetNutritionScanButton.addActionListener(listener);

	   JPanel scanPanel = new JPanel();
	   scanPanel.add(nutritionLabel);
	   scanPanel.add(scanFoodTypeButton);
	   scanPanel.add(resetNutritionScanButton);
	   nutritionPanel.add(scanPanel,BorderLayout.NORTH);
       
	   // We could use separate Listener classes for each button but 
	   // we can also "share" a listener class and pass in a type to the constructor
	   class Sorter implements ActionListener
	   {  
		   Attribute attribute;
		   
		   public Sorter(Attribute a)
		   {
		     attribute = a;   
		   }
	      public void actionPerformed(ActionEvent event)
	      {  
	    	  switch (attribute)
	    	  {
	    	  case CALS:
	    		  scanner.sortByCalories();
	    		  break;
	    	  case FAT:
	    		  scanner.sortByFat();
	    		  break;
	    	  case CARBS:
	    		  scanner.sortByCarbs();
	    		  break;
	    	  case SUGAR:
	    		  scanner.sortBySugar();
	    		  break;	  
	    	  }
	    	  
	    	  scanner.displayAll(nutritionArea);
	      }
	   }
	   // Cals
	   listener = new Sorter(Attribute.CALS);
	   calsSorterButton = new JButton("Cals");
	   calsSorterButton.setFont(new Font("SansSerif", Font.BOLD, 22));
       calsSorterButton.addActionListener(listener);
       JPanel sorterButtons = new JPanel();
       sorterButtons.add(calsSorterButton);
       // Carbs
	   listener = new Sorter(Attribute.CARBS);
	   carbsSorterButton = new JButton("Carbs");
	   carbsSorterButton.setFont(new Font("SansSerif", Font.BOLD, 22));
       carbsSorterButton.addActionListener(listener);
       sorterButtons.add(carbsSorterButton);
       // Fat       
       listener = new Sorter(Attribute.FAT);
	   fatSorterButton = new JButton("Fat");
	   fatSorterButton.setFont(new Font("SansSerif", Font.BOLD, 22));
       fatSorterButton.addActionListener(listener);
       sorterButtons.add(fatSorterButton);
       // Sugar
       listener = new Sorter(Attribute.SUGAR);
	   sugarSorterButton = new JButton("Sugar");
	   sugarSorterButton.setFont(new Font("SansSerif", Font.BOLD, 22));
       sugarSorterButton.addActionListener(listener);
       sorterButtons.add(sugarSorterButton);
       nutritionPanel.add(sorterButtons,BorderLayout.SOUTH);
	  
	   getContentPane().add(nutritionPanel, BorderLayout.SOUTH);
       scrollPaneNutritionArea.repaint(); 
   }
 }

