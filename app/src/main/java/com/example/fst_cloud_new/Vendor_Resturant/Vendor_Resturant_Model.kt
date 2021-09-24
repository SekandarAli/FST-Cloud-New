package com.example.fst_cloud_new.Vendor_Resturant

class Vendor_Resturant_Model
{


    var resturant_name : String = ""
    var resturant_image : String = ""
    var resturant_location : String = ""
    var resturant_description : String = ""


    constructor(){}
    constructor(Name : String,image : String, Description : String, location : String)
    {
        this.resturant_name = Name
        this.resturant_image = image
        this.resturant_description = Description
        this.resturant_location = location
    }
}