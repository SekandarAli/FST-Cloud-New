package com.example.fst_cloud_new.RESTAURANT

class Restaurant_Model {

     var resturant_name : String  = ""
     var resturant_description = ""
     var resturant_image = ""
     var resturant_location = ""



    constructor(name : String,description : String, rating : String,image : String)
    {
        this.resturant_name = name
        this.resturant_description = description
        this.resturant_location = rating
        this.resturant_image = image
    }
    constructor(){

    }
}
