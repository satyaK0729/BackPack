
package com.example.packyourbag.Data;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.packyourbag.CheckList;
import com.example.packyourbag.Constants.MyConstants;
import com.example.packyourbag.Database.RoomDB;
import com.example.packyourbag.Models.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppData extends Application {  //it will contain the raw data. before entering the data into the database, it will be kept over here
    RoomDB database;    //creating a object of rooom database
    String category;
    Context context;

    public static final String LAST_VERSION = "LAST_VERSION";
    public static final int NEW_VERSION = 1;

    //Constructor
    public AppData(RoomDB database) {
        this.database = database;
    }   //sDoubt

    //Another constructor
    public AppData(RoomDB database, Context context) {  //sDoubt
        this.database = database;
        this.context = context;
    }

    //adding the raw default data in form of a list
    public List<Items> getBasicData() {
        category = "Basic Needs";
        List<Items> basicItem = new ArrayList<Items>(); //creating a araryList
        basicItem.clear();
        //we are adding a new object of Items in the arrayList, and we are sending 3 arguments, so the constructor with 3 arguments is getting called. Here the addedBy variable in items is set to "System"
        basicItem.add(new Items("Visa", category, false));
        basicItem.add(new Items("Passport", category, false));
        basicItem.add(new Items("Tickets", category, false));
        basicItem.add(new Items("Wallet", category, false));
        basicItem.add(new Items("Driving License", category, false));
        basicItem.add(new Items("Currency", category, false));
        basicItem.add(new Items("House Key", category, false));
        basicItem.add(new Items("Book", category, false));
        basicItem.add(new Items("Travel Pillow", category, false));
        basicItem.add(new Items("Eye Patch", category, false));
        basicItem.add(new Items("Umbrella", category, false));
        basicItem.add(new Items("Note Book", category, false));
        return basicItem;
    }

    public List<Items> getPersonalCareData() {
        String[] data = {"Tooth-brush", "Tooth-paste", "Floss", "Mouthwash", "Shaving Cream", "Razor Blade",
                "Soap", "Fiber", "Shampoo", "Hair Conditioner", "Brush", "Comb", "Hair Dryer", "Curling Iron",
                "Hair Moulder", "Hair Clip", "Moisturizer", "Lip Cream", "Contact Lens", "Perfume", "Deodorant",
                "Makeup Materials", "Makeup Remover", "Wet Wipes", "Pad", "Ear Stick", "Cotton", "Nail Polish",
                "Nail Polish Remover", "Tweezers", "Nail Scissors", "Nail Files", "Suntan Cream"};
        return prepareItemsList(MyConstants.PERSONAL_CARE_CAMEL_CASE, data);
    }

    public List<Items> getClothingData() {
        String[] data = {"Stockings", "Underwear", "Pajamas", "T-Shirts", "Casual Dress", "Evening Dress",
                "Shirt", "Cardigan", "Vest", "Jacket", "Skirt", "Trousers", "Jeans", "Shorts", "Suit",
                "Coat", "Rain Coat", "Glove", "Hat", "Scarf", "Bikini", "Belt", "Slipper", "Sneakers", "Casual Shoes",
                "Heeled Shoes", "Sports Wear"};
        return prepareItemsList(MyConstants.CLOTHING_CAMEL_CASE, data);
    }

    public List<Items> getBabyNeedsData() {
        String[] data = {"Snapsuit", "Outfit", "Jumpsuit", "Baby Socks", "Baby Hat", "Baby Pyjamas",
                "Baby Bath Towel", "Muslin", "Blanket", "Dribble Bibs", "Baby Laundry Detergent",
                "Baby Bottles", "Baby Food Thermos", " Bottle Brushes", " Cover",
                "Breast Pump", "Water Bottle", "Storage Container", "Baby Food Spoon",
                "Highchairs", "Diaper", "Wet Wipes", "Baby Cotton",
                "Baby Care Cover", "Baby Shampoo", "Baby soap", "Baby Nail Scissors",
                "Body Moisturizer",  "Serum Physiological",
                "Nasal Aspirator", " Lotion", "Pyrometer", "Antipyretic Syrup", "Probiotic Power", " Backpack", "Stroller", "Baby Carrier", "Toys", "Teether", "Playpen", "Playpen", "Baby Radio",
                "Non-slip Sea Shoes", "Baby Sunglasses"};
        return prepareItemsList(MyConstants.BABY_NEEDS_CAMEL_CASE, data);
    }

    public List<Items> getHealthData() {
        String[] data = {"Aspirin", "Drugs Used", "Vitamins Used", "Lens Solutions",
                "Hot Water Bag", "Tincture Of Lodine", "Adhesive Plaster",
                "First Aid Kit", "Replacement Lens", "Pain Reliever", "Fever Reducer", "Diarrhea Stopper", "Pain Relieve Spray"};
        return prepareItemsList(MyConstants.HEALTH_CAMEL_CASE, data);
    }


    public List<Items> getTechnologyData() {
        String[] data = {"Mobile Phone", "Phone Cover", "E-Book Reader", "Camera", "Camera Charger",
                "Portable Speaker", "IPad (Tab) ", "Headphone"
                , "Laptop", "Laptop Charger", "Mouse", "Extension Cable", "Data Transfer Cable",
                "Battery", "Power Bank", "DVD Player", "Flash-Light", "MP3 Player", "MP3 Player Charger"
                , "Voltage Adapter", "SD Card"};
        return prepareItemsList(MyConstants.TECHNOLOGY_CAMEL_CASE, data);
    }

    public List<Items> getFoodData() {
        String[] data = {"Snack", "Sandwich", "Juice", "Tea Bags", "Coffee", "Water", "Thermos", "Chips", "Baby Food"};
        return prepareItemsList(MyConstants.FOOD_CAMEL_CASE, data);
    }

    public List<Items> getBeachSuppliesData() {
        String[] data = {"Sea Glasses", "Sea Bed", "Suntan Cream", "Beach Umbrella", "Swim Fins",
                "Beach Bag", "Beach Towel", "Beach Slippers"
                , "Sunbed", "Snorkel", "Waterproof Clock"};
        return prepareItemsList(MyConstants.BEACH_SUPPLIES_CAMEL_CASE, data);
    }

    public List<Items> getCarSuppliesData() {
        String[] data = {"Pump", "Car Jack", "Spare Car Key", "Accident Record Set", "Auto Refrigerator",
                "Car Cover", "Car Charger", "Window Sun Shades"};
        return prepareItemsList(MyConstants.CAR_SUPPLIES_CAMEL_CASE, data);
    }

    public List<Items> getNeedsData() {
        String[] data = {"Backpack", "Daily Bags", "Laundry Bag", "Sewing Kit",
                "Travel Lock", "Luggage Tag", "Magazine",
                "Sports Equipment", "Important Numbers"};
        return prepareItemsList(MyConstants.NEEDS_CAMEL_CASE, data);
    }


    //This method is used to create a list from the string
    public List<Items> prepareItemsList(String category, String[] data) {   //this method is to make the list of data
        List<String> list = Arrays.asList(data);
        List<Items> dataList = new ArrayList<>();
        dataList.clear();

        for (int i = 0; i < list.size(); i++) {
            dataList.add(new Items(list.get(i), category, false));
        }
        return dataList;
    }

    //This method will create a list of list which will be added into the database
    public List<List<Items>> getAllData() {
        List<List<Items>> listOfAllItems = new ArrayList<>();
        listOfAllItems.clear();
        listOfAllItems.add(getBasicData());
        listOfAllItems.add(getClothingData());
        listOfAllItems.add(getPersonalCareData());
        listOfAllItems.add(getBabyNeedsData());
        listOfAllItems.add(getHealthData());
        listOfAllItems.add(getTechnologyData());
        listOfAllItems.add(getFoodData());
        listOfAllItems.add(getBeachSuppliesData());
        listOfAllItems.add(getCarSuppliesData());
        listOfAllItems.add(getNeedsData());
        return listOfAllItems;
    }

    //we have to persist the data into the database, this method does that
    //This method will add the arraylist of list of items into the database
    public void persistAllData() {  //sDoubt
        List<List<Items>> listOfAllItems = getAllData();
        for (List<Items> list : listOfAllItems) {
            for (Items items :
                    list) {
                database.mainDao().saveItem(items); //This will add the data into the database
            }
        }

        System.out.println("Data added");
    }


    public void persistDataByCategory(String category,Boolean onlyDelete){  //reset data.
        try{
            List<Items> list=deleteAndGetListByCategory(category,onlyDelete);   //we are using the function which is defined below
            if (!onlyDelete){
                for(Items items : list){
                    database.mainDao().saveItem(items);
                }
                Toast.makeText(context, category + " Reset Successfully.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, category + " Reset Successfully.", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }
    private List<Items> deleteAndGetListByCategory(String category,Boolean onlyDelete){
        if(onlyDelete){
            database.mainDao().deleteAllByCategoryAndAddedBy(category,MyConstants.SYSTEM_SMALL);
        }else{
            database.mainDao().deleteAllByCategory(category);
        }
        switch(category){
            case MyConstants.BASIC_NEEDS_CAMEL_CASE:_NEEDS_CAMEL_CASE:
            return getBasicData();
            case MyConstants.CLOTHING_CAMEL_CASE:
                return getClothingData();
            case MyConstants.PERSONAL_CARE_CAMEL_CASE:
                return getPersonalCareData();
            case MyConstants.BABY_NEEDS_CAMEL_CASE:
                return getBabyNeedsData();
            case MyConstants.HEALTH_CAMEL_CASE:
                return getHealthData();
            case MyConstants.TECHNOLOGY_CAMEL_CASE:
                return getTechnologyData();
            case MyConstants.FOOD_CAMEL_CASE:
                return getFoodData();
            case MyConstants.BEACH_SUPPLIES_CAMEL_CASE:
                return getBeachSuppliesData();
            case MyConstants.CAR_SUPPLIES_CAMEL_CASE:
                return getCarSuppliesData();
            case MyConstants.NEEDS_CAMEL_CASE:
                return getNeedsData();

            default:
                return new ArrayList<>();
        }
    }
}