package com.example.android.lab09_include_merge;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import static com.example.android.lab09_include_merge.Activity1.Q1_ANSWER_KEY;
import static com.example.android.lab09_include_merge.Activity3.Q3_ANSWER_KEY;

public class Activity2 extends AppCompatActivity {

    public static final String Q2_ANSWER_KEY = "Q2";

    private TextView m_tv_no;
    private TextView m_tv_question;
    private RadioButton m_radio_a;
    private RadioButton m_radio_b;
    private RadioButton m_radio_c;

    private CharSequence m_q1_answer;
    private CharSequence m_q2_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        init();


    }

    private void init() {
        m_tv_no = (TextView)findViewById(R.id.tv_no);
        m_tv_question = (TextView)findViewById(R.id.tv_question);
        m_radio_a = (RadioButton)findViewById(R.id.radio_a);
        m_radio_b = (RadioButton)findViewById(R.id.radio_b);
        m_radio_c = (RadioButton)findViewById(R.id.radio_c);

        m_q1_answer = getIntent().getExtras().getString(Q1_ANSWER_KEY);
        m_tv_no.setText("2");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Spanned result;
            result = Html.fromHtml(getString(R.string.question_2), Html.FROM_HTML_MODE_LEGACY);
            m_tv_question.setText(result);
            result = Html.fromHtml(getString(R.string.question_2_radio_a), Html.FROM_HTML_MODE_LEGACY);
            m_radio_a.setText(result);
            result = Html.fromHtml(getString(R.string.question_2_radio_b), Html.FROM_HTML_MODE_LEGACY);
            m_radio_b.setText(result);
            result = Html.fromHtml(getString(R.string.question_2_radio_c), Html.FROM_HTML_MODE_LEGACY);
            m_radio_c.setText(result);
        }else {
            m_tv_question.setText(Html.fromHtml(getString(R.string.question_2)));
            m_radio_a.setText(Html.fromHtml(getString(R.string.question_2_radio_a)));
            m_radio_b.setText(Html.fromHtml(getString(R.string.question_2_radio_b)));
            m_radio_c.setText(Html.fromHtml(getString(R.string.question_2_radio_c)));
        }
    }

    public void next(View view) {
        Intent intent = new Intent(this,Activity3.class);
        intent.putExtra(Q1_ANSWER_KEY,m_q1_answer);
        intent.putExtra(Q2_ANSWER_KEY,m_q2_answer);

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        startActivity(intent);

    }

    public void back(View view) {
        Intent intent = new Intent(this,Activity1.class);

        intent.putExtra(Q1_ANSWER_KEY,m_q1_answer);
        intent.putExtra(Q2_ANSWER_KEY,m_q2_answer);

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        startActivity(intent);
    }

    public void click(View view) {
        m_q2_answer = view.getTag().toString();
    }

    @Override
    protected void onResume(){
        super.onResume();
        overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
    }

    @Override
    public void onBackPressed() {
        return;
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        m_q1_answer = getIntent().getExtras().getString(Q1_ANSWER_KEY);
    }
}
