package com.example.homeworkn6

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homeworkn6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddUser.setOnClickListener {
            userDataCheck()
        }
        binding.btnRemoveUser.setOnClickListener {
            removeUser()
        }
        binding.btnUpdateUser.setOnClickListener {
            updateUser()
        }
    }

    private val email = mutableListOf<String>()
    private val deletedUsers = mutableListOf<String>()
    private val mapOfUsers = mutableMapOf<String, com.example.homeworkn6.UserData>()

    @SuppressLint("SetTextI18n")
    private fun userDataCheck() {
        val mail = binding.edEmail.text.toString()
        val firstName = binding.edEmail.text.toString()
        val lastName = binding.edEmail.text.toString()
        val age = binding.edEmail.text.toString()
        val userUpdate = UserData(firstName, lastName, mail, age)

        if (email.contains(mail)) {
            binding.tvInfo.text = "Error, user $mail already exists"
            binding.tvInfo.setTextColor(Color.RED)
        } else if (mail.isNotEmpty()) {
            email.add(mail)
            mapOfUsers[mail] = userUpdate
            binding.tvInfo.setTextColor(Color.GREEN)
            binding.tvInfo.text = "$mail added"
            binding.tvTotalAcc.text = "Active Users: ${email.size}"
        } else {
            binding.tvInfo.text = "Something went wrong !"
            binding.tvInfo.setTextColor(Color.RED)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun removeUser() {
        val mail = binding.edEmail.text.toString()
        if (email.contains(mail)) {
            email.remove(mail)
            binding.tvInfo.text = "user $mail removed"
            binding.tvInfo.setTextColor(Color.GREEN)
            deletedUsers.add(mail)
            binding.tvDelete.text = "Deleted Users: ${deletedUsers.size}"
            binding.tvTotalAcc.text = "Active Users: ${email.size}"
        } else {
            binding.tvInfo.text = "user not found"
            binding.tvInfo.setTextColor(Color.RED)
        }
    }

    private fun updateUser() {
        val email = binding.edEmail.text.toString()
        val firstName = binding.edFirstname.text.toString()
        val lastName = binding.edLastname.text.toString()
        val age = binding.edAge.text.toString()

        if (mapOfUsers.containsKey(email)) {
            mapOfUsers[email]?.firstName = firstName
            mapOfUsers[email]?.lastName = lastName
            mapOfUsers[email]?.age = age
            Toast.makeText(this, "User Info Updated Successfully !", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "User doesn't exist !", Toast.LENGTH_SHORT).show()
        }
    }

}