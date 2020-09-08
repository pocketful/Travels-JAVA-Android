package com.byethost17.pocketful.travels;

import android.app.ProgressDialog;
import android.content.Intent;
// import android.provider.MediaStore;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewEntryActivity extends Activity {

    private static final String REGISTER_URL = "http://pocketful.byethost17.com/mobile/newEntry.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        final EditText pavadinimas_new = (EditText) findViewById(R.id.pavadinimas);

        final Spinner zemynas_new = (Spinner) findViewById(R.id.zemynaiSelect);
        List<String> zemynai = new ArrayList<String>();
        zemynai.add(getResources().getString(R.string.zemynas_Afrika));
        zemynai.add(getResources().getString(R.string.zemynas_Australija));
        zemynai.add(getResources().getString(R.string.zemynas_Azija));
        zemynai.add(getResources().getString(R.string.zemynas_Europa));
        zemynai.add(getResources().getString(R.string.zemynas_PietuAmerika));
        zemynai.add(getResources().getString(R.string.zemynas_SiauresAmerika));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, zemynai);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zemynas_new.setAdapter(dataAdapter);

        final EditText trukme_new = (EditText) findViewById(R.id.trukme);

        final CheckBox metulaikasPavasaris = (CheckBox) findViewById(R.id.metulaikasPavasaris);
        final CheckBox metulaikasRuduo = (CheckBox) findViewById(R.id.metulaikasRuduo);
        final CheckBox metulaikasVasara = (CheckBox) findViewById(R.id.metulaikasVasara);
        final CheckBox metulaikasZiema = (CheckBox) findViewById(R.id.metulaikasZiema);

        final RadioGroup kelionestipai_new = (RadioGroup) findViewById(R.id.kelionestipaiRadioGroup);
        final RadioButton[] kelionestipas = new RadioButton[1];

        Button buttonNewEntryCreate = (Button) findViewById(R.id.prideti);

        pavadinimas_new.setError(null);
        trukme_new.setError(null);

        buttonNewEntryCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(pavadinimas_new.getText().toString())) {
                    pavadinimas_new.setError(getResources().getString(R.string.new_entry_pavadinimas_error));
                }
                else if(TextUtils.isEmpty(trukme_new.getText().toString())) {
                    trukme_new.setError(getResources().getString(R.string.new_entry_trukme_error));
                }
                else {
                    int selectedId = kelionestipai_new.getCheckedRadioButtonId();
                    kelionestipas[0] = (RadioButton) findViewById(selectedId);

                    StringBuffer metulaikas_new = new StringBuffer();
                    if (metulaikasPavasaris.isChecked()) {
                        metulaikas_new.append(getResources().getString(R.string.metulaikas_pavasaris) + " ");
                    }
                    if (metulaikasRuduo.isChecked()) {
                        metulaikas_new.append(getResources().getString(R.string.metulaikas_ruduo) + " ");
                    }
                    if (metulaikasVasara.isChecked()) {
                        metulaikas_new.append(getResources().getString(R.string.metulaikas_vasara) + " ");
                    }
                    if (metulaikasZiema.isChecked()) {
                        metulaikas_new.append(getResources().getString(R.string.metulaikas_ziema) + " ");
                    }

                    Kelione kelione = new Kelione(pavadinimas_new.getText().toString(),
                            String.valueOf(zemynas_new.getSelectedItem()),
                            Integer.parseInt(trukme_new.getText().toString()),
                            metulaikas_new.toString(),
                            kelionestipas[0].getText().toString());

                    register(kelione.getName(), kelione.getContinent(), kelione.getDuration(), kelione.getSeasons(), kelione.getTraveltype());

                    Toast.makeText(NewEntryActivity.this,
                            kelione.getName() + "\n" +
                                    kelione.getContinent() + "\n" +
                                    kelione.getDuration() + "\n" +
                                    kelione.getSeasons() + "\n" +
                                    kelione.getTraveltype(), Toast.LENGTH_SHORT).show();

                    Intent GoToMenuActivity = new Intent(NewEntryActivity.this, MenuActivity.class);
                    startActivity(GoToMenuActivity);
                }
            }
        });
    }

    private void register(String name, String continent, int duration, String seasons, String traveltype) {
        class NewEntry extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            DB database = new DB();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(NewEntryActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("pavadinimas",params[0]);
                data.put("zemynas",params[1]);
                data.put("trukme",params[2]);
                data.put("metulaikas",params[3]);
                data.put("kelionestipas",params[4]);


                String result = database.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        NewEntry insertNewEntryDB = new NewEntry();
        String durationString = Integer.toString(duration);
        insertNewEntryDB.execute(name, continent, durationString, seasons, traveltype);
    }
}