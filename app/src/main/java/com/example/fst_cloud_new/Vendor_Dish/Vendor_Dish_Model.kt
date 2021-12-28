package com.example.fst_cloud_new.Vendor_Dish


class Vendor_Dish_Model {

    var dish_name : String = ""
    var dish_image : String = ""
    var dish_price : String = ""
    var dish_description : String = ""
    var restaurant_name : String = ""


    constructor(){}
    constructor(Name : String,image : String, Description : String, price : String,restaurant : String)
    {
        this.dish_name = Name
        this.dish_image = image
        this.dish_description = Description
        this.dish_price = price
        this.restaurant_name = restaurant
    }
}