package com.example.fst_cloud_new.Profile_Data

class Profile_Model {

    var vendor_username : String = ""
    var vendor_image : String = ""



    constructor()
    {}

    constructor(Name : String,image : String, email : String)
    {
        this.vendor_username = Name
        this.vendor_image = image

    }

}