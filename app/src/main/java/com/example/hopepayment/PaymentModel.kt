package com.example.hopepayment

data class PaymentModel(
    var payId : String? = null,
    var name : String? = null,
    var address : String? = null,
    var mobile : String? = null,
    var price : String? = null,
    var type : String? = null,
    var cardNum : String? = null,
    var cardName : String? = null,
    var cvc : String? = null,
    var expireDate : String? = null,
)