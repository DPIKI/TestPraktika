package dpiki.testpraktika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;

    EditText editLogin;
    EditText editPassword;
    TextView textResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editLogin = (EditText)findViewById(R.id.et_login);
        editPassword = (EditText)findViewById(R.id.et_password);
        textResponse = (TextView)findViewById(R.id.tv_response);

        queue = Volley.newRequestQueue(this);
    }

    public void onClickLogin(View view) {
        String url = "http://82.146.52.50/taskserver/?login=" +
                editLogin.getText().toString() +
                "&pass=" +
                editPassword.getText().toString();
        Log.d("TAG", url);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                listenerResponse, listenerError);
        queue.add(request);
    }

    Response.Listener<String> listenerResponse = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            textResponse.setText(response.replace("<br>", " "));
        }
    };

    Response.ErrorListener listenerError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    };
}
