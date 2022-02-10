package com.example.userdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    var id: String = ""
    val profileList = arrayListOf<User>()
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // realtime database로 유저 정보 전송
        id = "soo69311@gmail.com"
        insertDataToDatabase()
        updateItem()

        //stringList.add("Java")  //  추가


//       val profileList = user
//
//        rv_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        rv_profile.setHasFixedSize(true)
//        rv_profile.adapter = UserAdapter(profileList)
    }
    private fun insertDataToDatabase() {
        // 여기에 구글 이메일 받아오면 될듯?
        val email = id
        val dog = 0
        // Create User Object
        val user = User(email, dog)
        // Add Data to Database
        mUserViewModel.addUser(user)
        profileList.add(user)

        rv_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_profile.setHasFixedSize(true)
        rv_profile.adapter = UserAdapter(profileList)
        //Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
    }
    private fun updateItem() {
        val email = id
        val dog = 1
        // Create User Object
        val updatedUser = User(email, dog)
        mUserViewModel.updateUser(updatedUser)
        // Recyclerview

        // UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            UserAdapter.setData(user)
        })

    }
}