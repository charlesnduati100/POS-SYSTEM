package data



import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.possystem.navigation.ROUTE_DASHBOARD
import com.example.possystem.navigation.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import model.UserModel

class AuthViewModel:ViewModel() {

    fun signup(username:String,email:String,password:String,confirmpassword:String,phoneNumber:String,navController: NavController,context:Context){
        if (username.isBlank() || email.isBlank() || password.isBlank() || confirmpassword.isBlank() || phoneNumber.isBlank()){
            Toast.makeText(context,"Please fill all the fields",Toast.LENGTH_LONG).show()
            return
        }
        if (password != confirmpassword){
            Toast.makeText(context,"Password do not match",Toast.LENGTH_LONG).show()
            return
        }
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                task ->
            if (task.isSuccessful){
                val userId = auth.currentUser?.uid ?: ""
                val user = UserModel(username=username,email=email,userId=userId,phoneNumber=phoneNumber)

                saveUserToDatabase(user,navController,context)
            }else{
                Toast.makeText(context,task.exception?.message ?:
                "Registration failed",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun saveUserToDatabase(user: UserModel, navController: NavController, context: Context){
        val dbRef = FirebaseDatabase.getInstance().getReference("User/${user.userId}")
        dbRef.setValue(user).addOnCompleteListener{
                task ->
            if (task.isSuccessful){
                Toast.makeText(context,"User Registered successfully",
                    Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN){
                    popUpTo(0)
                }
            }else{
                Toast.makeText(context,task.exception?.message ?: "Failed to save user",
                    Toast.LENGTH_LONG).show()
            }
        }


    }
    fun login(email: String,password: String,navController: NavController,context: Context){
        if (email.isBlank() || password.isBlank()){
            Toast.makeText(context,"Email and Password required",Toast.LENGTH_LONG).show()
            return
        }
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                task ->
            if (task.isSuccessful){
                Toast.makeText(context,"Login Successful",Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_DASHBOARD){
                    popUpTo(0)
                }
            }else{
                Toast.makeText(context,task.exception?.message ?: "Login failed",
                    Toast.LENGTH_LONG).show()
            }}}

    fun logout(navController: NavController, context: Context) {
        FirebaseAuth.getInstance().signOut()
        Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
        navController.navigate(ROUTE_LOGIN) {
            popUpTo(0) { inclusive = true }
        }
    }
}