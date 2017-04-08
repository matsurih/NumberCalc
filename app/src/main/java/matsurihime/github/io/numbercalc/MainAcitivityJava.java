package matsurihime.github.io.numbercalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainAcitivityJava extends AppCompatActivity {
    //変数定義
    EditText txtBin;
    EditText txtDec;
    EditText txtHex;

    /**
     * アクティビティ生成時に呼ばれるメソッド
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditTextの取得
        txtBin = (EditText) findViewById(R.id.txtBin);
        txtDec = (EditText) findViewById(R.id.txtDec);
        txtHex = (EditText) findViewById(R.id.txtHex);

        //Buttonの取得
        Button btnBin = (Button) findViewById(R.id.btnBin);
        Button btnDec = (Button) findViewById(R.id.btnDec);
        Button btnHex = (Button) findViewById(R.id.btnHex);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click((Button) v);
            }
        };

        //OnClickListenerのセット
        btnBin.setOnClickListener(listener);
        btnDec.setOnClickListener(listener);
        btnHex.setOnClickListener(listener);
    }

    /**
     * ボタンクリック時に呼ばれるメソッド
     *
     * @param sender クリックされたボタン.
     */
    public void Click(Button sender) {

        //入力値を10進数で取得する
        Long source;
        switch (sender.getId()) {
            case R.id.btnBin:
                source = tryParseLong(txtBin.getText().toString(), 2);
                break;
            case R.id.btnDec:
                source = tryParseLong(txtDec.getText().toString(), 10);
                break;
            case R.id.btnHex:
                source = tryParseLong(txtHex.getText().toString(), 16);
                break;
            default:
                source= null;
        }

        //異常値が入力されていた場合はエラートーストを表示する
        if(source == null) {
            Toast.makeText(this, "正しい値を入力してください", Toast.LENGTH_SHORT).show();
            return;
        }

        //変換して画面へ反映
        txtBin.setText(Long.toBinaryString(source));
        txtDec.setText(String.format(source.toString(), Locale.JAPAN));
        txtHex.setText(Long.toHexString(source));
    }

    /**
     * n進数表記の文字列を10進数の数字に変換します。変換に失敗した場合はnullを返します。
     * @param val
     * @param base
     * @return
     */
    private Long tryParseLong(String val, int base){
        try{
            return Long.parseLong(val, base);
        }catch(NumberFormatException ex) {
            return null;
        }
    }
}
