package com.example.cse227.UNIT_1

class UserDetail {
    lateinit var name: String
    lateinit var contact: String
    lateinit var address: String
    @JvmName("setName1")
    fun setName(n: String){
        name = n;
    }

    @JvmName("getName1")
    fun getName(): String{
        return name
    }

    @JvmName("setContact1")
    fun setContact(c: String){
        contact = c
    }

    @JvmName("getContact1")
    fun getContact(): String{
        return contact
    }

    @JvmName("setAddress1")
    fun setAddress(a: String){
        address = a
    }

    @JvmName("getAddress1")
    fun getAddress(): String{
        return address
    }
}