package com.example.fst_cloud_new.PROFILE

class Profile_Model {

    var vendor_username : String = ""
    var vendor_image : String = ""
    var vendor_email : String = ""


    constructor()
    {}

    constructor(Name : String,image : String, email : String)
    {
        this.vendor_username = Name
        this.vendor_image = image
        this.vendor_email = email
    }

}