package com.example.zengyong.studydemo.voice;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zengyong.studydemo.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoiceActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {


    @BindView(R.id.etOne)
    EditText etOne;
    @BindView(R.id.etThree)
    EditText etThree;
    private TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        ButterKnife.bind(this);

        //语音播报
        textToSpeech = new TextToSpeech(this, this);
        initSpeech();
    }

    private void initSpeech() {
        if (textToSpeech != null) {
            // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            textToSpeech.setPitch(0.5f);
            //设定语速 ，默认1.0正常语速
            textToSpeech.setSpeechRate(1.0f);
        }
    }

    @Override
    public void onInit(int status) {
        Log.d("VoiceActivity", status + "");
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void voice(String str) {
        if (textToSpeech != null && !textToSpeech.isSpeaking()) {
        }
        //朗读，注意这里三个参数的added in API level 4   四个参数的added in API level 21
        //QUEUE_FLUSH方式表示清除当前队列中的内容而直接播放新的内容，
        // QUEUE_ADD方式表示将新的内容添加到队列尾部进行播放
        textToSpeech.speak(str, TextToSpeech.QUEUE_ADD, null);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        textToSpeech.stop();
        textToSpeech.shutdown();
    }


    @OnClick(R.id.btRead)
    public void onBtReadClicked() {
        String readStr = etOne.getText().toString();
        voice(readStr);
    }

    @OnClick(R.id.btReadThree)
    public void onBtReadThreeClicked() {
        String readStr = etThree.getText().toString();
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            sBuffer.append(readStr);
            sBuffer.append("。");
        }
        voice(sBuffer.toString());
    }
}
