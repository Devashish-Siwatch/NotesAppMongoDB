package com.example.notesappmongodb

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults

class NotesAdapter(private val context: Context?, private val notesList: RealmResults<Notes>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.findViewById<TextView>(R.id.titleTV).text = notesList[position]!!.title
        holder.itemView.findViewById<TextView>(R.id.descTV).text = notesList[position]!!.description
        holder.itemView.findViewById<TextView>(R.id.IDTV).text=  notesList[position]!!.id.toString()

        notesList[position]!!.title?.let { Log.d("Adapter", it) }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
    class ViewHolder(v: View?):RecyclerView.ViewHolder(v!!){
        val title = itemView.findViewById<TextView>(R.id.titleTV)
        val desc = itemView.findViewById<TextView>(R.id.descTV)
        val id = itemView.findViewById<TextView>(R.id.IDTV)
    }


}