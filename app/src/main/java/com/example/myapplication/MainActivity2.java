package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    EditText etKeyInput;
    Button btnEncrypt;
    TextView tvEncrypted;
    TextView tvGeneratedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize views
        etKeyInput = findViewById(R.id.etKeyInput);
        btnEncrypt = findViewById(R.id.btnEncrypt);
        tvEncrypted = findViewById(R.id.tvEncrypted);
        tvGeneratedPassword = findViewById(R.id.tvGeneratedPassword);

        // Retrieve generated password from MainActivity
        String generatedPassword = getIntent().getStringExtra("PASSWORD_EXTRA");
        tvGeneratedPassword.setText("Generated Password: " + generatedPassword);

        // Set click listener for the "Encrypt" button
        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the key and generated password
                String keyString = etKeyInput.getText().toString();
                int key = Integer.parseInt(keyString);
                String password = generatedPassword;

                // Perform encryption using the provided algorithm
                String encryptedMessage = encrypt(password, key);

                // Display the encrypted message
                tvEncrypted.setText("Encrypted Message: " + encryptedMessage);
            }
        });
    }

    // Encryption algorithm
    public String encrypt(String message, int shiftKey) {
        // Define the alphabet
        String alpha = "abcdefghijklmnopqrstuvwxyz";

        // Convert message to lowercase
        message = message.toLowerCase();

        // Initialize cipher text
        StringBuilder cipherText = new StringBuilder();

        // Encrypt each character in the message
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            // Find the position of the character in the alphabet
            int charPosition = alpha.indexOf(c);
            // Apply the shift key and modulus to wrap around the alphabet
            int keyVal = (shiftKey + charPosition) % 26;
            // Replace the character with the encrypted character
            char replaceVal = alpha.charAt(keyVal);
            cipherText.append(replaceVal);
        }

        // Return the encrypted message
        return cipherText.toString();
    }
}
