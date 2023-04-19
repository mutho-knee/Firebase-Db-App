package com.muthoknee.morningfirebasedbapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class UserupdateActivity : AppCompatActivity() {
    lateinit var edtName: EditText
    lateinit var edtEmail: EditText
    lateinit var edtIdNumber: EditText
    lateinit var progressDialog: ProgressDialog
    lateinit var btnUpdate: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userupdate)
        edtName = findViewById(R.id.mEdtName)
        edtEmail = findViewById(R.id.mEdtEmail)
        edtIdNumber = findViewById(R.id.mEdtIdNumber)
        btnUpdate = findViewById(R.id.mBtnUserupdate)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Updating")
        progressDialog.setMessage("Please wait...")
        var receivedName = intent.getStringExtra("name")
        var receivedEmail = intent.getStringExtra("email")
        var receivedIdNumber = intent.getStringExtra("idNumber")
        var receivedId = intent.getStringExtra("id")
        //Set the received data to the edit text
        edtName.setText(receivedName)
        edtEmail.setText(receivedEmail)
        edtIdNumber.setText(receivedIdNumber)
        // Set onClickListener on the button update
        btnUpdate.setOnClickListener {
            var name = edtName.text.toString().trim()
            var email = edtEmail.text.toString().trim()
            var idNumber = edtIdNumber.text.toString().trim()
            var id = receivedId
            if (name.isEmpty()){
                edtName.setError("Please fill this field")
                edtName.requestFocus()
            }else if (email.isEmpty()){
                edtEmail.setError("Please fill this field")
                edtEmail.requestFocus()
            }else if (idNumber.isEmpty()){
                edtIdNumber.setError("Please fill this field")
                edtIdNumber.requestFocus()
            }else{
                //Proceed to save
                //Prepare the user to be saved
                var user = User(name, email, idNumber, id!!)
                //Create a reference in the firebase database
                var ref = FirebaseDatabase.getInstance().getReference().child("Users/"+id)

                //Show the progress to start saving
                progressDialog.show()
                ref.setValue(user).addOnCompleteListener{
                    //Dismiss the progress and check if the task was successful
                        task ->
                    progressDialog.dismiss()
                    if (task.isSuccessful){
                        Toast.makeText(this,"User updated successfully", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@UserupdateActivity,UsersActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "User updating failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}