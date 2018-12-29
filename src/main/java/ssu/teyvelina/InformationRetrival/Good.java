package ssu.teyvelina.InformationRetrival;

/*public class Good {
}
package com.daria.programmingTechnology;

/**
 * Created by Daria on 14.12.2017.
 */
/*System.out.println("url " + good_url);
                        System.out.println("id " + good_id);
                        System.out.println("name " + good_name);
                        System.out.println("price " + good_price);
                        System.out.println("brand " + good_brand);
                        System.out.println("type " + good_type);*/
public class Good {
    private String good_url;
    private String good_id;
    private String good_name;
    private String good_price;
    private String good_brand;
    private String good_type;
    private String good_description;

    public Good(){}

    public Good(String url, String id, String name, String price, String brand, String type, String description){
        this.good_url = url;
        this.good_id = id;
        this.good_name = name;
        this.good_price = price;
        this.good_brand = brand;
        this.good_type = type;
        this.good_description = description;
    }

    public String getUrl() {
        return good_url;
    }
    public String getId() {
        return good_id;
    }
    public String getName() {
        return good_name;
    }
    public String getPrice() {
        return good_price;
    }
    public String getBrand() {
        return good_brand;
    }
    public String getType() {
        return good_type;
    }
    public String getDescription() {
        return good_description;
    }

    @Override
    public String toString() {
        return "url = " + good_url + "\n"
                + "id = " + good_id + "\n"
                + "name = " + good_name + "\n"
                + "price = " + good_price + "\n"
                + "brand = " + good_brand + "\n"
                + "type = " + good_type + "\n"
                + "description " + good_description + "\n";
    }

    public String toSimpleString() {
        return good_url + "\n" + good_id + "\n" + good_name + "\n" + good_price + "\n" + good_brand + "\n" + good_type + "\n" + good_description + "\n";
    }
}