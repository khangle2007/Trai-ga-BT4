package com.example.trai_ga_bt4;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HowPay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_how_pay);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button6);

        button.setOnClickListener(v -> showPinDialog());

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(HowPay.this, Home.class);
            startActivity(intent);
        });
    }

    public void showPinDialog() {

        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.activity_qrcode);

        dialog.setCancelable(true);

        ImageView imageView = dialog.findViewById(R.id.imageView16);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(HowPay.this, EnterAmount.class);
            startActivity(intent);
        });

        ImageView imageView2 = dialog.findViewById(R.id.imageView40);
        imageView2.setOnClickListener(v -> {
            dialog.dismiss();
        });


        dialog.show();

        Window window = dialog.getWindow();

        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);


            int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.95);

            dialog.getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }
}