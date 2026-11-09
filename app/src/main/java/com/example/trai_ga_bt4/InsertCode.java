package com.example.trai_ga_bt4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InsertCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_code);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editText = findViewById(R.id.editTextNumber);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 6) {
                    Intent intent = new Intent(InsertCode.this, Home.class);
                    startActivity(intent);
                }
            }
        });

        TextView textView2 = findViewById(R.id.textView5);
        TextView textView = findViewById(R.id.textView6);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime());

            textView.setTranslationY(-imeInsets.bottom);

            textView2.setTranslationY(-imeInsets.bottom);

            return insets;
        });

        ImageView imageView = findViewById(R.id.imageView9);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(InsertCode.this, Login.class);
            startActivity(intent);
        });
    }
}