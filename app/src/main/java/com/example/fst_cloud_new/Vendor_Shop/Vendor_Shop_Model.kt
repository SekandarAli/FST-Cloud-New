package com.example.fst_cloud_new.Vendor_Shop

class Vendor_Shop_Model
{

    var shop_name : String = ""
    var shop_image : String = ""
    var shop_location : String = ""
    var shop_description : String = ""


    constructor(){}
    constructor(Name : String,image : String, Description : String, location : String)
    {
        this.shop_name = Name
        this.shop_image = image
        this.shop_description = Description
        this.shop_location = location
    }
}