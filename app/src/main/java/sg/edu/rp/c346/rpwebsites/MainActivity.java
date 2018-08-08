package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Spinner spin1, spin2;
    Button btnGo;
    ArrayList<String> alChoice;
    ArrayAdapter<String> aaChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spin1 = findViewById(R.id.spinner);
        spin2 = findViewById(R.id.spinner2);
        btnGo = findViewById(R.id.buttonGo);

        alChoice = new ArrayList<>();

        final String[] strChoice = getResources().getStringArray(R.array.rpsubcat);
        alChoice.addAll(Arrays.asList(strChoice));

        aaChoice = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alChoice);
        spin2.setAdapter(aaChoice);


        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                alChoice.clear();
                if (position == 0){
                    String[] strChoice = getResources().getStringArray(R.array.rpsubcat);
                    alChoice.addAll(Arrays.asList(strChoice));
                    spin2.setSelection(0);
                }
                else if (position == 1){
                    String[] strChoice = getResources().getStringArray(R.array.soisub);
                    alChoice.addAll(Arrays.asList(strChoice));
                    spin2.setSelection(0);
                }
                aaChoice.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),WebsiteActivity.class);
                String url="";
                if (spin1.getSelectedItemPosition() == 0){
                    if (spin2.getSelectedItemPosition()==0){
                        url = "https://www.rp.edu.sg/";
                    }
                    else if (spin2.getSelectedItemPosition()==1){
                        url = "https://www.rp.edu.sg/student-life";
                    }
                }
                else if (spin1.getSelectedItemPosition()==1){
                    if (spin2.getSelectedItemPosition()==0){
                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
                    }
                    else if (spin2.getSelectedItemPosition()==1){
                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
                    }
                }
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Integer spin1op = spin1.getSelectedItemPosition();
        Integer spin2op = spin2.getSelectedItemPosition();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        //Step 1c: Obtain an instance of the SharedPreferences Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        //Step 1d: Add the key value pair
        prefEdit.putInt("opt1", spin1op);
        prefEdit.putInt("opt2", spin2op);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 2b: Retrieve the saved data with the key "greeting" from the SharedPreference object
        Integer spin1op = prefs.getInt("opt1", 0);
        Integer spin2op = prefs.getInt("opt2", 0);
        spin1.setSelection(spin1op);
        spin2.setSelection(spin2op);
    }
}
