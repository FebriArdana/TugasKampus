package com.example.a2212500199_febri_uas;


import static com.android.volley.VolleyLog.TAG;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TambahDataActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String URLSELECT = "http://192.168.136.116/data/select.php";
    public static final String URLDELETE = "http://192.168.136.116/data/delete.php";
    public static final String URLEDIT = "http://192.168.136.116/data/edit.php";
    public static final String URLINSERT = "http://192.168.136.116/data/insert.php";
    ListView list;
    SwipeRefreshLayout swipe;
    List<Data> itemList = new ArrayList<Data>();
    MhsAdapter adapter;
    LayoutInflater inflater;
    EditText tid,tnama,talamat;
    String vid, vnama, valamat;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list);

        adapter = new MhsAdapter(TambahDataActivity.this, itemList);
        list.setAdapter(adapter);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //statement jika fab diklik
                dialogForm("","","","Tambah");
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getId();
                final CharSequence[] pilihanAksi = {"Hapus", "Ubah"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(TambahDataActivity.this);
                dialog.setItems(pilihanAksi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                hapusData(idx);
                                break;

                            case 1:
                                ubahData(idx);
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });


    }
    public void ubahData(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String idx = jsonObject.getString("id");
                            String namax = jsonObject.getString("nama");
                            String alamatx = jsonObject.getString("alamat");

                            dialogForm(idx, namax, alamatx, "UPDATE");

                            adapter.notifyDataSetChanged();

                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TambahDataActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("id", id);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
    public void dialogForm(String id, String nama, String alamat, String button){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(TambahDataActivity.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_mahasiswa, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);
        dialogForm.setTitle("Form Alamat");

        tid = (EditText) viewDialog.findViewById(R.id.inId);
        tnama = (EditText) viewDialog.findViewById(R.id.inNama);
        talamat = (EditText) viewDialog.findViewById(R.id.inAlamat);

        if (id.isEmpty()){
            tid.setText(null);
            tnama.setText(null);
            talamat.setText(null);
        }else{
            tid.setText(id);
            tnama.setText(nama);
            talamat.setText(alamat);
        }

        dialogForm.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vid = tid.getText().toString();
                vnama = tnama.getText().toString();
                valamat = talamat.getText().toString();

                simpan();
                dialog.dismiss();
            }
        });
        dialogForm.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tid.setText(null);
                tnama.setText(null);
                talamat.setText(null);
            }
        });
        dialogForm.show();

    }
    void simpan(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLINSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(TambahDataActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TambahDataActivity.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                params.put("nama", vnama);
                params.put("alamat", valamat);
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
    public void hapusData(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLDELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(TambahDataActivity.this, response, Toast.LENGTH_LONG).show();
                        callVolley();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TambahDataActivity.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("id", id);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();

    }
    private void callVolley() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(URLSELECT, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        Data item = new Data();

                        item.setId(obj.getString("id"));
                        item.setNama(obj.getString("nama"));
                        item.setAlamat(obj.getString("alamat"));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);

    }
}