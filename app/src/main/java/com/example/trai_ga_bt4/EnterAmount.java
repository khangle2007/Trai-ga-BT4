package com.example.trai_ga_bt4;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EnterAmount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_amount);
        Button button = findViewById(R.id.button7);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime());

            button.setTranslationY(-imeInsets.bottom);

            return insets;
        });

        EditText editText = findViewById(R.id.editTextNumber2);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    button.setBackgroundTintList(
                            ColorStateList.valueOf(Color.parseColor("#58BC6B"))
                    );
                } else {
                    button.setBackgroundTintList(
                            ColorStateList.valueOf(Color.parseColor("#C5C5C5"))
                    );
                }
            }
            @Override
            public void afterTextChanged(android.text.Editable s) {
            }
        });

        button.setOnClickListener(v -> {
            String amountText = editText.getText().toString();

            double amount = Double.parseDouble(amountText);

            if(amount > 10.00) {
                showTopupFailedDialog();
            } else {
                showReviewDialog();
            }
        });

        ImageView imageView = findViewById(R.id.imageView41);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(EnterAmount.this, HowPay.class);
            startActivity(intent);
        });
    }

    private void showTopupFailedDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_pop_up_unsucessful);
        dialog.setCancelable(true);

        TextView textView = dialog.findViewById(R.id.textView58);
        textView.setOnClickListener(view -> {
            dialog.dismiss();
        });

        dialog.show();

        Window window = dialog.getWindow();

        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);

            window.setGravity(Gravity.BOTTOM);

            window.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT
            );
        }

    }

    private void showReviewDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_pop_up);
        dialog.setCancelable(true);

        Button button = dialog.findViewById(R.id.button8);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(EnterAmount.this, Home.class);
            Toast.makeText(this, "Top Up Successful", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        ImageView imageView = dialog.findViewById(R.id.imageView46);
        imageView.setOnClickListener(view -> {
            dialog.dismiss();
        });


        dialog.show();

        Window window = dialog.getWindow();

        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setGravity(Gravity.BOTTOM);

            window.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT
            );
        }
    }
}