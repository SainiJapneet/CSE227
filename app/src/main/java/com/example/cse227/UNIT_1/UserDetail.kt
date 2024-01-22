package com.example.cse227.UNIT_1

class UserDetail {
    lateinit var name: String
    lateinit var contact: String
    lateinit var address: String
    @JvmName("SetName")
    fun setName(n: String){
        name = n;
    }

    @JvmName("GetName")
    fun getName(): String{
        return name
    }

    @JvmName("SetContact")
    fun setContact(c: String){
        contact = c
    }

    @JvmName("GetContact")
    fun getContact(): String{
        return contact
    }

    @JvmName("SetAddress")
    fun setAddress(a: String){
        address = a
    }

    @JvmName("GetAddress")
    fun getAddress(): String{
        return address
    }
}