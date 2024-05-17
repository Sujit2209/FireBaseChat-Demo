package com.example.myapplication.myapplication.firebasechatdemo.ViewModel

import android.util.Log
import android.view.View
import android.webkit.ConsoleMessage
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.work.impl.utils.isDefaultProcess
import com.example.myapplication.myapplication.firebasechatdemo.Data.ChatData
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.myapplication.myapplication.firebasechatdemo.Data.Events
import com.example.myapplication.myapplication.firebasechatdemo.Data.USER_NODE
import com.example.myapplication.myapplication.firebasechatdemo.Data.UserData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

@HiltViewModel
class MainViewModel @Inject constructor(
    val auth : FirebaseAuth,
    var db : FirebaseFirestore
) : ViewModel()
{


    var inProcess = mutableStateOf(false)
    val eventMutableState = mutableStateOf<Events<String>?>(null)
    var inProcessChat = mutableStateOf(false)
    var signIn = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    val chats = mutableStateOf<List<ChatData>>(listOf())

    init {

        val currentUser = auth.currentUser
        signIn.value = currentUser !=null

        if (currentUser != null) {
            currentUser.uid?.let{
                getUserData(it)
            }
        }
    }

    fun signUp(name:String, number:String, email:String, password:String)
    {
        if(name.isEmpty() or number.isEmpty() or email.isEmpty() or password.isEmpty())
        {
            handleException(customMessage = "Please Fill All fields")
            return
        }
        inProcess.value = true
        db.collection(USER_NODE).whereEqualTo("number",number).get().addOnSuccessListener {

            if(it.isEmpty){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{


                    inProcess.value = true
                    if(it.isSuccessful)
                    {

                        signIn.value = true
                        createOrUpdateProfile(name,number)

                        Log.d("TAG","Authentication Sucessfull")
                    }else{
                        handleException(it.exception, customMessage = "Sign UP Failed")
                    }
                }

            }else{

                handleException(customMessage = "number Already Exists")
                inProcess.value=false
            }

        }

    }

    fun LogIn(email:String, password:String)
    {
        if(email.isEmpty() or password.isEmpty()) {
            handleException(customMessage = "Please Fill the Fields")
            return
        }else{
            inProcess.value = true
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful)
                {
                    signIn.value = true
                    inProcess.value = false
                    auth.currentUser?.uid?.let {
                        getUserData(it)
                    }
                }else{
                    handleException(exception = it.exception, customMessage = "Login Failed")
                }
            }


        }
    }

    fun createOrUpdateProfile(name: String?=null, number: String?= null, imageurl: String?=null)
    {

        var uid = auth.currentUser?.uid

        val userData = UserData(
            userId = uid,
            name = name?: userData.value?.name,
            number = number?: userData.value?.number,
            imageUrl = imageurl?: userData.value?.imageUrl
        )

        uid?.let {

            inProcess.value = true
            db.collection(USER_NODE).document(uid).get().addOnSuccessListener {
                if(it.exists())
                {

                }else{
                    db.collection(USER_NODE).document(uid).set(userData)
                    inProcess.value = false
                    getUserData(uid)
                }
            }.addOnFailureListener{

                handleException(it, "Cannot Retrieve USER")
            }
        }

    }

    fun getUserData(uid:String)
    {
        inProcess.value = true
        db.collection(USER_NODE).document(uid).addSnapshotListener{

            value, error->

            if(error != null)
            {
                handleException(error, "Cannot Retrieve User")
            }

            if(value != null)
            {
                var user = value.toObject<UserData>()
                userData.value = user
                inProcess.value = false
            }
        }
    }

    fun handleException(exception: Exception?=null, customMessage: String="")
    {
        Log.e("SignUpError","Exception Occured",exception)
        exception?.printStackTrace()

        val errorMessage = exception?.localizedMessage?:""

        val message = if(customMessage.isNullOrEmpty()) errorMessage else customMessage

        eventMutableState.value = Events(message)
        inProcess.value = false

    }

    fun onAddChat(it: String) {

    }
}

