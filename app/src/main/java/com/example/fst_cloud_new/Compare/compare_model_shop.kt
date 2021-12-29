package com.example.fst_cloud_new.Compare

class compare_model_shop {

    var shop_main_name : String = ""
    var shop_main_image : String = ""
    var shop_main_price : String = ""
    var shop_main_description : String = ""



    constructor(){}

    constructor(Name : String,image : String, Description : String, price : String)
    {
        this.shop_main_name = Name
        this.shop_main_image = image
        this.shop_main_description = Description
        this.shop_main_price = price
    }


}