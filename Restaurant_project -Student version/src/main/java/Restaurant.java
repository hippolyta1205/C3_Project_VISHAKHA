import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        //<Vishakha> Checking if current time is in the range of restaurant open timings
        if ((getCurrentTime().equals(openingTime) || getCurrentTime().isAfter(openingTime)) &&
                (getCurrentTime().isBefore(closingTime)||getCurrentTime().equals(closingTime))) {
            return true;
        }
        else {
            return false;
        }
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        //<Vishakha> Implemented getter method for menu
        return this.menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    //<Vishakha> Implementing the method for calculating total value of itmes
    public int getItemTotal (String... input) {
        int totalValue = 0;
        for (String itemName : input) {
            Item convertingStringToItem = findItemByName(itemName);// getting Item object for String entered
            totalValue = totalValue + convertingStringToItem.getPrice();// returning sum of all items
        }
        return totalValue;
    }

    public String getName() {
        return name;
    }
}
