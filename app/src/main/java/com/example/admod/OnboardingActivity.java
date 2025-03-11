package com.example.admod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.admod.R;

public class OnboardingActivity extends BaseActivity {
    private ImageView imageView;
    private TextView description;
    private View dotActive, dotInactive1, dotInactive2;
    private Button btnNext;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        imageView = findViewById(R.id.imageView);
        description = findViewById(R.id.description);
        dotActive = findViewById(R.id.dot_active);
        dotInactive1 = findViewById(R.id.dot_inactive_1);
        dotInactive2 = findViewById(R.id.dot_inactive_2);
        btnNext = findViewById(R.id.btnnext);

        btnNext.setOnClickListener(v -> {
            count++;
            updateOnboardingUI();
        });
    }

    private void updateOnboardingUI() {
        switch (count) {
            case 1:
                imageView.setImageResource(R.drawable.pic2);
                description.setText("Connecting NGOs, Social \nEnterprises with Communities");

                // Swap dot_active và dotInactive1
                dotActive.setBackgroundResource(R.drawable.dot_inactive);
                dotInactive1.setBackgroundResource(R.drawable.dot_active);
                break;

            case 2:
                imageView.setImageResource(R.drawable.pic3);
                description.setText("Donate, Invest & Support \ninfrastructure projects");

                // Swap dotInactive1 và dotInactive2
                dotInactive1.setBackgroundResource(R.drawable.dot_inactive);
                dotInactive2.setBackgroundResource(R.drawable.dot_active);
                btnNext.setText("Finish");
                break;

            case 3:
                startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
                finish();
                break;
        }
    }
}
