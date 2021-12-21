package com.example.fst_cloud_new.Vendor_Travel

class Vendor_Travel_Model {

    var travel_name : String = ""
    var travel_image : String = ""
    var travel_location : String = ""
    var travel_description : String = ""


    constructor(){}
    constructor(Name : String,image : String, Description : String, location : String)
    {
        this.travel_name = Name
        this.travel_image = image
        this.travel_description = Description
        this.travel_location = location
    }
}