package com.example.praticesdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


data class Student(val id:Int,val name:String,val city:String)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(v: View){
        when(v.id)
        {
            R.id.btnInsert ->
            {
                val id = txtId.text.toString().toInt()
                val city = txtCity.text.toString()
                val name = txtName.text.toString()

                val dto1 = Student(id,name,city)

                val help = MyHelper(this)
                val result = help.insertData(dto1)

                if(result == (-1).toLong())
                {
                    Toast.makeText(this,"Registration Failed", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Registration success $result", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btnView ->
            {
                val db = MyHelper(this)
                val list = db.view()
                var str = ""
                for(i in list)
                {
                    str += "id = ${i.id} name = ${i.name} and city = ${i.city} \n"
                }
                txtOutput.text = str
            }

            R.id.btnDelete ->{
                val id = txtId.text.toString().toInt()
                val db = MyHelper(this)
                val result = db.deleteData(id)
                if(result == -1)
                {
                    Toast.makeText(this,"Delete Failed", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Delete success $result", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btnUpdate ->{
                val city = txtCity.text.toString()
                val name = txtName.text.toString()
                val id = txtId.text.toString().toInt()
                val dto1 = Student(id,name,city)

                val help = MyHelper(this)
                val result = help.updateData(dto1)

                if(result == -1)
                {
                    Toast.makeText(this,"Update Failed", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Update success $result", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
