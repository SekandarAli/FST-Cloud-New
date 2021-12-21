package com.example.fst_cloud_new.SHOP


class Shop_Model {

    var shop_name : String  = ""
    var shop_description = ""
    var shop_image = ""
    var shop_location = ""



    constructor(name : String,description : String, rating : String,image : String)
    {
        this.shop_name = name
        this.shop_description = description
        this.shop_location = rating
        this.shop_image = image
    }
    constructor(){

    }
}
