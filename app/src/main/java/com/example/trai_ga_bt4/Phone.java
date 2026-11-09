package com.example.trai_ga_bt4;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Phone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phone);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imageView = findViewById(R.id.imageView12);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(Phone.this, VerifyAcount.class);
            startActivity(intent);
        });


        Button button = findViewById(R.id.button4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime());

            button.setTranslationY(-imeInsets.bottom);

            return insets;
        });

        EditText editText = findViewById(R.id.editTextPhone);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 10) {
                    button.setBackgroundTintList(
                            ColorStateList.valueOf(Color.parseColor("#58BC6B"))
                    );

                    button.setOnClickListener(v -> showPinDialog());
                }
                else {
                    button.setBackgroundTintList(
                            ColorStateList.valueOf(Color.parseColor("#C5C5C5"))
                    );
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void showPinDialog() {

        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.activity_verfication_code);

        dialog.setCancelable(true);

        EditText edtOtpHidden = dialog.findViewById(R.id.edtOtpHidden);

        TextView[] otpViews = {
                dialog.findViewById(R.id.etPin1),
                dialog.findViewById(R.id.etPin2),
                dialog.findViewById(R.id.etPin3),
                dialog.findViewById(R.id.etPin4),
                dialog.findViewById(R.id.etPin5),
                dialog.findViewById(R.id.etPin6)
        };

        edtOtpHidden.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                for (int i = 0; i < 6; i++) {
                    if (i < s.length()) {
                        otpViews[i].setText(String.valueOf(s.charAt(i)));
                    } else {
                        otpViews[i].setText("-");
                    }
                }

                if (s.length() == 6) {
                    Intent intent = new Intent(Phone.this, VerifyAcount.class);
                    startActivity(intent);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        for (TextView otpView : otpViews) {
            otpView.setOnClickListener(v -> {
                edtOtpHidden.requestFocus();

                InputMethodManager imm =
                        (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

                imm.showSoftInput(edtOtpHidden, InputMethodManager.SHOW_IMPLICIT);
            });
        }

        dialog.show();

        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.95);

        dialog.getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);

        edtOtpHidden.requestFocus();
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        );

        edtOtpHidden.post(() -> {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtOtpHidden, InputMethodManager.SHOW_IMPLICIT);
        });
    }
}