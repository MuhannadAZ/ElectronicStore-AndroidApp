package com.example.proj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutFragment extends Fragment {

    private EditText cardNumber, expiryDate, cvv;
    private Button submitPaymentButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        cardNumber = view.findViewById(R.id.cardNumber);
        expiryDate = view.findViewById(R.id.expiryDate);
        cvv = view.findViewById(R.id.cvv);
        submitPaymentButton = view.findViewById(R.id.submitPaymentButton);

        submitPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simulate payment process (this is fake, no real payment processing)
                    // Go to Confirmation Screen
                    ConfirmationFragment confirmationFragment = new ConfirmationFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, confirmationFragment)
                            .addToBackStack(null)
                            .commit();
            }
        });

        return view;
    }
}

