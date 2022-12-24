package com.example.instagram

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram.models.posts
import kotlinx.android.synthetic.main.post.view.*


class PostAdapter (val context: Context,val posts: List<posts>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: posts) {
            itemView.username.text = post.User?.username
            itemView.publisher.text = post.User?.username
            itemView.description.text = post.description
            Glide.with(context).load(post.image_url).centerCrop().into(itemView.postImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.post,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

}



