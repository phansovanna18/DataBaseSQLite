package com.example.praticesdatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

const val DB_Name = "Student"
const val TABLE_NAME = "Staff"
const val column1 = "student_id"
const val column2 = "student_name"
const val column3 = "student_city"

class MyHelper (context: Context):SQLiteOpenHelper(context, DB_Name, null, 1)



{

    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = "create table $TABLE_NAME($column1 INTEGER, $column2 varchar(250), $column3 varchar(250)) "
        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertData(dto:Student):Long{
        val cv = ContentValues()
        cv.put(column1,dto.id)
        cv.put(column2,dto.name)
        cv.put(column3,dto.city)
        val db = this.writableDatabase
        return db.insert(TABLE_NAME,null,cv)
    }

    fun deleteData(id:Int):Int{
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"$column1=$id",null)
    }

    fun view():ArrayList<Student>
    {
        val list = ArrayList<Student>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME",null)
        if(cursor!=null)
        {
            if (cursor.moveToFirst())
            {
                do {
                    val name = cursor.getString(cursor.getColumnIndex(column2))
                    val city = cursor.getString(cursor.getColumnIndex(column3))
                    val id = cursor.getInt(cursor.getColumnIndex(column1))
                    Log.d("id student",id.toString())
                    list.add(Student(id,name,city))
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        return list
    }

    fun updateData(dto:Student):Int{
        val cv = ContentValues()
        cv.put(column1,dto.id)
        cv.put(column2,dto.name)
        cv.put(column3,dto.city)
        val db = this.writableDatabase
        return db.update(TABLE_NAME,cv,"$column1=${dto.id}",null)
    }
}