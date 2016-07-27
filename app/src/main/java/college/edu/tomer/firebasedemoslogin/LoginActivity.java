package college.edu.tomer.firebasedemoslogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void login(View view) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword
                        (
                                getEmail(),
                                getPassword()
                        ).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        gotoMain();
                    }
                }).
                addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showError(e);
                    }
                });
    }

    public void register(View view) {
        FirebaseAuth.
                getInstance().
                createUserWithEmailAndPassword(
                        getEmail(),
                        getPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                gotoMain();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showError(e);
            }
        });
    }

    private void showError(Exception e) {
        Snackbar.make(etEmail, e.getLocalizedMessage(), Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void gotoMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public String getEmail() {
        return etEmail.getText().toString();
    }

    public String getPassword() {
        return etPassword.getText().toString();
    }
}
