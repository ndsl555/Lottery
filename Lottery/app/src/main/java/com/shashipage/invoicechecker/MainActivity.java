package com.shashipage.invoicechecker;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //ui variables
    private Button[] buttons = new Button[12];
    private TextView[] numTextViews = new TextView[8];
    private TextView alertTextView;
    TextView topic;
    String str="",great,good,a,b,c;
    private String numberEntered = ""; //輸入值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewComponent();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try{
            topic=findViewById(R.id.head);
            //從一個URL載入一個Document物件。
            Document doc = Jsoup.connect("https://invoice.etax.nat.gov.tw/index.html").get();
            Elements elements = doc.getElementsByClass("etw-web");
            Elements top=elements.select("a.etw-on");
            topic.setText(top.text()+"查詢");
            System.out.println(top.text());
            for (Element element : elements) {
                String s=element.getElementsByClass("font-weight-bold").text();
                System.out.println(s+'\n');
                str+=s;
            }
            String[] tokens = str.split(" ");

            great=tokens[0];
            good=tokens[1];
            a=tokens[2]+tokens[3];
            b=tokens[4]+tokens[5];
            c=tokens[6]+tokens[7];
        }
        catch(Exception e) {
            System.out.println( e.toString());
        }
    }



    private void setupViewComponent() {
        //ui變數定義與監聽
        for (int i = 0; i < 12; i++) {
            String buttonID = "btn" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = ((Button) findViewById(resID));
            buttons[i].setOnClickListener(this);
        }

        for (int i = 0; i < 8; i++) {
            String textViewID = "numTextView" + i;
            int resID = getResources().getIdentifier(textViewID, "id", getPackageName());
            numTextViews[i] = ((TextView) findViewById(resID));
            numTextViews[i].setOnClickListener(this);
        }

        alertTextView = (TextView) findViewById(R.id.alertTextView);

    }


    private void prizeCheck() {
        if(numberEntered.equals(great)){
            alertTextView.setText("中了特別獎:一千萬元!");
        }
        else if(numberEntered.equals(good)){
            alertTextView.setText("中了特獎:兩百萬元!");
        }
        else if(numberEntered.equals(a)||numberEntered.equals(b)||numberEntered.equals(c)){
            alertTextView.setText("中了頭獎:二十萬元!");
        }
        else{
            if(numberEntered.substring(5).equals(a.substring(5))
                    ||numberEntered.substring(5).equals(b.substring(5))
                    ||numberEntered.substring(5).equals(c.substring(5)))
            {
                alertTextView.setText("中了200元");
            }
            else if(numberEntered.substring(4).equals(a.substring(4))
                    ||numberEntered.substring(4).equals(b.substring(4))
                    ||numberEntered.substring(4).equals(c.substring(4)))
            {
                alertTextView.setText("中了1000元");
            }
            else if(numberEntered.substring(3).equals(a.substring(3))
                    ||numberEntered.substring(3).equals(b.substring(3))
                    ||numberEntered.substring(3).equals(c.substring(3)))
            {
                alertTextView.setText("中了4000元");
            }
            else if(numberEntered.substring(2).equals(a.substring(2))
                    ||numberEntered.substring(2).equals(b.substring(2))
                    ||numberEntered.substring(2).equals(c.substring(2)))
            {
                alertTextView.setText("中了一萬元");
            }
            else if(numberEntered.substring(1).equals(a.substring(1))
                    ||numberEntered.substring(1).equals(b.substring(1))
                    ||numberEntered.substring(1).equals(c.substring(1)))
            {
                alertTextView.setText("中了四萬元");
            }
            else{
                alertTextView.setText("槓龜,再接再厲...");
            }
        }
    }

    private void numInput(int num) {
        //若第一格有數字則填地二格以此類推，三格都有數字時下一個輸入值會先觸發clearAll()清空
        String number = Integer.toString(num);
        if (!numTextViews[7].getText().toString().equals("")) {
            clearAll();
        }
        if (numTextViews[0].getText().toString().equals("")) {
            numberEntered = numberEntered + number;
            numTextViews[0].setText(number);
        }
        else if (numTextViews[1].getText().toString().equals("")) {
            numberEntered = numberEntered + number;
            numTextViews[1].setText(number);
        }
        else if (numTextViews[2].getText().toString().equals("")) {
            numberEntered = numberEntered + number;
            numTextViews[2].setText(number);
        }
        else if (numTextViews[3].getText().toString().equals("")) {
            numberEntered = numberEntered + number;
            numTextViews[3].setText(number);
        }
        else if (numTextViews[4].getText().toString().equals("")) {
            numberEntered = numberEntered + number;
            numTextViews[4].setText(number);
        }
        else if (numTextViews[5].getText().toString().equals("")) {
            numberEntered = numberEntered + number;
            numTextViews[5].setText(number);
        }
        else if (numTextViews[6].getText().toString().equals("")) {
            numberEntered = numberEntered + number;
            numTextViews[6].setText(number);
        }
        else if (numTextViews[7].getText().toString().equals("")) {
            numberEntered = numberEntered + number;
            numTextViews[7].setText(number);
            prizeCheck();
        }
    }

    private void clearAll() {
        for (int i = 0; i < 8; i++) {
            numTextViews[i].setText("");
        }
        numberEntered = "";
        alertTextView.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                numInput(0);
                break;

            case R.id.btn1:
                numInput(1);
                break;

            case R.id.btn2:
                numInput(2);
                break;

            case R.id.btn3:
                numInput(3);
                break;

            case R.id.btn4:
                numInput(4);
                break;

            case R.id.btn5:
                numInput(5);
                break;

            case R.id.btn6:
                numInput(6);
                break;

            case R.id.btn7:
                numInput(7);
                break;

            case R.id.btn8:
                numInput(8);
                break;

            case R.id.btn9:
                numInput(9);
                break;

            case R.id.btn10:
                clearAll();
                break;

            case R.id.btn11:
                if(!numberEntered.isEmpty())
                numberEntered=numberEntered.substring(0, numberEntered.length()-1);
                numTextViews[numberEntered.length()].setText("");
                alertTextView.setText("");
                System.out.println(numberEntered);
                break;
        }
    }

}