package app.example.siddharthbyale.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {

    private EditText mNewEmailField;
    private EditText mNewPasswordField;
    private Button signup;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mNewEmailField=findViewById(R.id.newemail);
        mNewPasswordField=findViewById(R.id.newpassword);
        signup=findViewById(R.id.signup);
        mAuth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewSignUp();
            }
        });

    }

    private void startNewSignUp() {
        String email1=mNewEmailField.getText().toString();
        String password2=mNewPasswordField.getText().toString();

        if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(password2))
            Toast.makeText(this,"Fields are Empty",Toast.LENGTH_LONG).show();
        else {
            mAuth.createUserWithEmailAndPassword(email1,password2).addOnCompleteListener(this,
                    new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user=mAuth.getCurrentUser();
                        Toast.makeText(Main2Activity.this,"Successful",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Main2Activity.this,MainActivity.class));
                        finish();
                    }

                    else {
                        Toast.makeText(Main2Activity.this,"Fail",Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
}
