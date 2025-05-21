package com.arsoft.viewmodelapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.arsoft.viewmodelapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MyViewModel viewModel;
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_main
        );

        viewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);

       /*
       // We can use this onClickListener. But for best practice we use the DataBinding

       mainBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.increaseCounter();
            }
        });*/
        // Link the DataBinding with ViewModel
        mainBinding.setMyViewModel(viewModel);


        // Observing the LiveData
        viewModel.getCounter().observe(
                this,
                new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer counter) {
                        // Update the UI when the LiveData Changes
                        mainBinding.textView2.setText(""+counter);
                    }
                }
        );





    }
}