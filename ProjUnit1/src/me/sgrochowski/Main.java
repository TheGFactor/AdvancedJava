package me.sgrochowski;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private final static FileInput places = new FileInput("places.csv");
    private final static FileInput stuff = new FileInput("stuff.csv");
    private static Scanner keyboard = new Scanner(System.in);

    static ArrayList<String> coun = new ArrayList<>();
    static ArrayList<Integer> citi = new ArrayList<>();
    static ArrayList<Integer> prod = new ArrayList<>();

    public static void main(String[] args){
        System.out.format("%-22s %6s %5s\n","Country","Cities", "Stuff");
        System.out.format("%7s  %20s %-7s\n","=======","======", "=====");
        ArrayList countries = new ArrayList();


        getCountries(); //Calls the getCountries method of the main class- will count countries/cities
        getItems(); //Calls get getItems method in the main class- counts items per country
        for(int x = 0; x<(coun.size()); x++){
            String a = (coun.get(x));
            int b = (citi.get(x));
            int c = (prod.get(x));
            //System.out.println(a + " " + b + " " + c);
            System.out.format("%-22s %4s %5s\n", a, b, c);
        }

    }

    /**
     * This class reads each line in the country CSV, adds all countries and cities together
     */
    public static void getCountries()
    {

        String eval;
        String[] fields;
        String prev = "";
        int citycount = 0; //This is the counter for the number of cities
        while(((eval = places.readFile()) != null)){
            fields = eval.split(",");

            if(prev.equals("")){
                citycount++;
                coun.add(fields[0]);
                prev = fields[0];
            }
            else if(fields[0].equals(prev)){
                citycount++;
                prev = fields[0];
            }
            else{
                coun.add(fields[0]);
                citi.add(citycount);
                citycount = 1;
                prev = fields[0];

            }
        }
        citi.add(citycount);
        places.closeFile();
    }

    public static void getItems(){
        int countries = coun.size();
        String[] fields;
        int inc = 0; //This is what country in the og list is being searched
        int count = 1;
        String line;
        line = stuff.readFile(); //This was the only was I was able to get the code to work- split seemed to skip my first line no matter what so I had to disregard and throw away the result from the
        while((inc < (countries) && (line = stuff.readFile()) != null)){
            fields = line.split(",");
            String currentCountry = coun.get(inc);
            if(currentCountry.equals(fields[0])){
                count++; //Increases the counter
            }
            else{
                prod.add(count);
                inc++; //Advances to the next country
                count = 1; //Resets the counter to one
            }
        }
        prod.add(count);
        stuff.closeFile();

    }

    /**
     * Will return the X-th country- for unit testing
     * @param x = country number that user would like to search
     * @return The country name indexed at X
     */
    public String getCountry(int x)
    {
        return coun.get(x);
    }

    /**
     * Will return the number of cities at X country indexed.
     * @param x = the country number that the user would like to search for
     * @return The number of cities in country x
     */
    public int getCities(int x)
    {
        return citi.get(x);
    }

    /**
     * Will return the number of products from X country indexed.
     * @param x = the country number that the user would like to search for
     * @return The number of products from country x
     */
    public int getProducts(int x)
    {
        return prod.get(x);
    }
    }

