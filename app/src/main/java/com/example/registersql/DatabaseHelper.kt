package com.example.registersql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, dbname, null, dbversion)
{
    private val createSignUpTable=" create table $tablename($colId Integer Primary Key AutoIncrement, $colName Text,$colEmail Text,$colPassword Text)"

    private val dropSignupTable="drop table if exists $tablename"
    companion object{
        private val dbname="firstdb.db"
        private val dbversion=1
        private val tablename="SignUp"
        private val colId="ID"
        private val colName="Name"
        private val colEmail="Email"
        private val colPassword="Password"
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(createSignUpTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL(dropSignupTable)
        onCreate(p0)
    }

    fun addUser(user: userdata){
        val db=this.writableDatabase

        val values=ContentValues()
        values.put(colName, user.name)
        values.put(colEmail,user.email)
        values.put(colPassword,user.pass)

        db.insert(tablename, null, values)
        db.close()
    }

    fun loginAuthentication(email:String, password:String): Boolean {
        val columns= arrayOf(colId, colEmail, colPassword)
        val db=this.readableDatabase

        val where="$colEmail=? and $colPassword=?"

        val selectionArgs= arrayOf(email,password)

        val cursor=db.query(tablename, columns, where, selectionArgs,null, null, null, null)
        val countRows=cursor.count
        cursor.close()
        db.close()

        return countRows>0

    }
}