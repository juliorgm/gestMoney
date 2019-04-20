package br.com.juliorgm.gestmoney;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        TextView t2, t3;
        ImageView img1;

        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView3);
        img1 = findViewById(R.id.imageView2);

        t2.setText(user.getEmail());
        t3.setText(user.getDisplayName());
        Picasso.get().load(user.getPhotoUrl()).into(img1);
    }
}
