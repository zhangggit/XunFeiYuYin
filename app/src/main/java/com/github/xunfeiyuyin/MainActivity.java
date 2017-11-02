package com.github.xunfeiyuyin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edittext);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but1:  //语音识别  （语音转文字）
                voiceToText();
                break;
            case R.id.but2:  //语音合成 （文字转语音）
                textToVoice("淼淼，你真帅！");
                break;
        }
    }

    //语音转为文字
    private void voiceToText() {
        //1.创建 RecognizerDialog 对象
        RecognizerDialog mDialog = new RecognizerDialog(this, null);

        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //若要将 RecognizerDialog 用于语义理解，必须添加以下参数设置，设置之后 onResult 回调返回将是语义理解的结果
        //mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "3.0");

        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                String resultString = recognizerResult.getResultString();
                Log.e(TAG, "讯飞语音识别的结果是：" + resultString.toString());

            }

            @Override
            public void onError(SpeechError speechError) {
                Log.e(TAG, "onError: " + speechError);
            }
        });

        //4.显示 dialog，接收语音输入
        mDialog.show();
    }
    //文字转语音
    private void textToVoice(String speak) {
        //1.创建 SpeechSynthesizer 对象, 第二个参数：本地合成时传 InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(this, null);
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan"); //设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        mTts.startSpeaking(speak, null);

    }

}

