package com.example.fst_cloud_new.FOOD

class food_detail_model {

    var dish_rating : String = ""
    var dish_review : String = ""
    var userName : String = ""


    constructor(){}

    constructor(rating : String, review : String,name:String){

        dish_rating = rating
        dish_review = review
        userName = name
    }
}