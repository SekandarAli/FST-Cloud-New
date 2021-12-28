package com.example.fst_cloud_new.Vendor_Shop_Category

class Vendor_Shop_Category_Model {

    var shop_main_name : String = ""
    var shop_main_image : String = ""
    var shop_main_price : String = ""
    var shop_main_description : String = ""
    var shop_name : String = ""



    constructor(){}
    constructor(Name : String,image : String, Description : String, price : String,shop : String)
    {
        this.shop_main_name = Name
        this.shop_main_image = image
        this.shop_main_description = Description
        this.shop_main_price = price
        this.shop_name = shop

    }
}