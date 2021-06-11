package com.example.notesappmongodb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm

class AddNotesActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var titleED: EditText
    private lateinit var descED: EditText
    private lateinit var saveButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        realm = Realm.getDefaultInstance()
        titleED = findViewById(R.id.title_EditText)
        descED = findViewById(R.id.desc_EditText)
        saveButton = findViewById(R.id.Save)
        saveButton.setOnClickListener{
            addNotesToDB()
        }


    }
    private fun addNotesToDB(){
        //id Auto Increment
        try {
            realm.beginTransaction()
            val currentIdNumber: Number? = realm.where(Notes::class.java).max("id")
            val nextId: Int
            nextId = if(currentIdNumber==null){
                1
            }else{
                currentIdNumber.toInt() + 1
            }

            val notes = Notes()
            notes.title = titleED.text.toString()
            notes.description = descED.text.toString()
            notes.id = nextId

            realm.copyToRealmOrUpdate(notes)
            realm.commitTransaction()
            Toast.makeText(this,"Note Added Successfully",Toast.LENGTH_SHORT).show()

            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        catch (e:Exception){
            Toast.makeText(this,"Failed $e",Toast.LENGTH_SHORT).show()
        }
    }
}