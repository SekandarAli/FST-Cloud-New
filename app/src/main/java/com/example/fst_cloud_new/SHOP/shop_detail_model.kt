package com.example.fst_cloud_new.SHOP

class shop_detail_model {

    var shop_rating : String = ""
    var shop_review : String = ""
    var userName : String = ""


    constructor(){}

    constructor(rating : String, review : String,name:String){

        shop_rating = rating
        shop_review = review
        userName = name
    }
}