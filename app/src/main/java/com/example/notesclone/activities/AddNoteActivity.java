package com.example.notesclone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesclone.R;
import com.example.notesclone.api.ApiClient;
import com.example.notesclone.api.ApiInterface;
import com.example.notesclone.pojo.NotePojo;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setStatusBar();
    }

    public void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = getWindow().getDecorView();
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    public void saveNote(View view) {
        String title = ((EditText) findViewById(R.id.et_title)).getText().toString();
        String note = ((EditText) findViewById(R.id.et_note)).getText().toString();

        JsonObject object = new JsonObject();
        object.addProperty("title", title);
        object.addProperty("note", note);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.createNote(object).enqueue(new Callback<NotePojo>() {
            @Override
            public void onResponse(Call<NotePojo> call, Response<NotePojo> response) {
                if (response.isSuccessful()) {
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotePojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
