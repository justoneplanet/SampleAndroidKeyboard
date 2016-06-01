package info.justoneplanet.sample.inputmethod.sampleandroidkeyboard;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class KeyboardService extends InputMethodService {
    Handler handler = new Handler();
    @Override
    public View onCreateInputView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.keyboard, null);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request.Builder().url("https://google.com").get().build();
                OkHttpClient client = new OkHttpClient();
                client.newCall(request).enqueue(new Callback(){
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(KeyboardService.this, e.getClass().getCanonicalName() + ":" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(KeyboardService.this, "success", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });
        return view;
    }
}
