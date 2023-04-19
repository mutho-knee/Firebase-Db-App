package com.muthoknee.morningfirebasedbapp

class User {
    var name:String = ""
    var email:String = ""
    var idNumber:String = ""
    var id:String = ""
    var itemSelected:String = ""

    constructor(name: String, email: String, idNumber: String, id: String, itemSelected: String) {
        this.name = name
        this.email = email
        this.idNumber = idNumber
        this.id = id
        this.itemSelected = itemSelected
    }

    constructor()

}