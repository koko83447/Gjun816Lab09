package com.example.android.lab09_include_merge;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import static com.example.android.lab09_include_merge.Activity1.Q1_ANSWER_KEY;
import static com.example.android.lab09_include_merge.Activity2.Q2_ANSWER_KEY;

public class Activity3 extends AppCompatActivity {

    public static final String Q3_ANSWER_KEY = "Q3";

    private TextView m_tv_no;
    private TextView m_tv_question;
    private RadioButton m_radio_a;
    private RadioButton m_radio_b;
    private RadioButton m_radio_c;

    private CharSequence m_q1_answer;
    private CharSequence m_q2_answer;
    private CharSequence m_q3_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        init();

        m_q1_answer = getIntent().getExtras().getString(Q1_ANSWER_KEY);
        m_q2_answer = getIntent().getExtras().getString(Q2_ANSWER_KEY);
    }

    private void init() {
        m_tv_no = (TextView)findViewById(R.id.tv_no);
        m_tv_question = (TextView)findViewById(R.id.tv_question);
        m_radio_a = (RadioButton)findViewById(R.id.radio_a);
        m_radio_b = (RadioButton)findViewById(R.id.radio_b);
        m_radio_c = (RadioButton)findViewById(R.id.radio_c);

        m_tv_no.setText("3");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Spanned result;
            result = Html.fromHtml(getString(R.string.question_3), Html.FROM_HTML_MODE_LEGACY);
            m_tv_question.setText(result);
            result = Html.fromHtml(getString(R.string.question_3_radio_a), Html.FROM_HTML_MODE_LEGACY);
            m_radio_a.setText(result);
            result = Html.fromHtml(getString(R.string.question_3_radio_b), Html.FROM_HTML_MODE_LEGACY);
            m_radio_b.setText(result);
            result = Html.fromHtml(getString(R.string.question_3_radio_c), Html.FROM_HTML_MODE_LEGACY);
            m_radio_c.setText(result);
        }else {
            m_tv_question.setText(Html.fromHtml(getString(R.string.question_3)));
            m_radio_a.setText(Html.fromHtml(getString(R.string.question_3_radio_a)));
            m_radio_b.setText(Html.fromHtml(getString(R.string.question_3_radio_b)));
            m_radio_c.setText(Html.fromHtml(getString(R.string.question_3_radio_c)));
        }
    }

    public void back(View view) {
        Intent intent = new Intent(this,Activity2.class);
        intent.putExtra(Q1_ANSWER_KEY,m_q1_answer);
        intent.putExtra(Q2_ANSWER_KEY,m_q2_answer);
        intent.putExtra(Q3_ANSWER_KEY,m_q3_answer);
        startActivity(intent);
    }
    public void click(View view){
        m_q3_answer = view.getTag().toString();
    }

    public void main(View view) {
        StringBuilder message = new StringBuilder(100);
        message.append("您的作答如下\n")
                .append(m_q1_answer+"\n")
                .append(m_q2_answer+"\n")
                .append(m_q3_answer)
                .append("\n確定要結束嗎？");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("YES",new PositiveButton小幫手())
                .setNegativeButton("NO",new NegativeButton小幫手())
                .show();
    }

    private class PositiveButton小幫手 implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which){
            Intent intent = new Intent(Activity3.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private class NegativeButton小幫手 implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which){

        }
    }
}
