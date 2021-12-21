package com.example.fst_cloud_new.TRAVEL

class Travel_Model {

    var travel_name : String  = ""
    var travel_description = ""
    var travel_image = ""
    var travel_location = ""



    constructor(name : String,description : String, rating : String,image : String)
    {
        this.travel_name = name
        this.travel_description = description
        this.travel_location = rating
        this.travel_image = image
    }
    constructor(){

    }
}
