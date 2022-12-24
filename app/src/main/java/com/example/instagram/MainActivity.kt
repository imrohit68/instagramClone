package com.example.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram.databinding.ActivityMainBinding
import com.example.instagram.databinding.ActivitySignupBinding
import com.example.instagram.models.posts
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_main.*


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var fireStoreDb: FirebaseFirestore
    private lateinit var Post: MutableList<posts>
    private lateinit var adapter: PostAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        fireStoreDb = FirebaseFirestore.getInstance()

        Post = mutableListOf()
        adapter = PostAdapter(this,Post)
        rvPost.adapter = adapter
        rvPost.layoutManager = LinearLayoutManager(this)


        var postsReference = fireStoreDb.collection("posts")
            .limit(20)
            .orderBy("creation_time_milli",Query.Direction.DESCENDING)
        postsReference.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                Log.e(TAG, "Exception when querying posts", exception)
                return@addSnapshotListener
            }
            else{
                val postList = snapshot.toObjects(posts::class.java)
                Post.clear()
                Post.addAll(postList)
                adapter.notifyDataSetChanged()
                for (post in postList){
                    Log.i(TAG, "Post ${post}")
                }
            }
        }

    }
}