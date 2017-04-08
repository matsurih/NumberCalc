package matsurihime.github.io.numbercalc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

/**
 * MainActivityクラス
 */
class MainActivity : AppCompatActivity() {
    //変数定義
    var txtBin : EditText? = null
    var txtDec : EditText? = null
    var txtHex : EditText? = null

    /**
     * アクティビティ生成時に呼ばれるメソッド
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //EditTextの取得
        txtBin = findViewById(R.id.txtBin) as EditText
        txtDec = findViewById(R.id.txtDec) as EditText
        txtHex = findViewById(R.id.txtHex) as EditText

        //Buttonの取得
        val btnBin: Button = findViewById(R.id.btnBin) as Button
        val btnDec: Button = findViewById(R.id.btnDec) as Button
        val btnHex: Button = findViewById(R.id.btnHex) as Button

        //OnClickListenerのセット
        btnBin.setOnClickListener{onClick(btnBin)}
        btnDec.setOnClickListener{onClick(btnDec)}
        btnHex.setOnClickListener{onClick(btnHex)}
    }

    /**
     *
     * ボタンクリック時に呼ばれるメソッド
     *
     * @param sender クリックされたボタン.
     */
    private fun onClick (sender: Button){

        //入力値を10進数で取得する
        val source = when(sender.id){
            R.id.btnBin -> txtBin?.text.toString().toLongOrNull(2)
            R.id.btnDec -> txtDec?.text.toString().toLongOrNull(10)
            R.id.btnHex -> txtHex?.text.toString().toLongOrNull(16)
            else -> null
        }

        //異常値が入力されていた場合はエラートーストを表示する
        source ?: Toast.makeText(this, "正しい値を入力してください", Toast.LENGTH_SHORT).show();

        //変換して画面へ反映
        txtBin?.setText(source?.toString(2) ?: txtBin?.text.toString())
        txtDec?.setText(source?.toString(10) ?: txtDec?.text.toString())
        txtHex?.setText(source?.toString(16)?.toUpperCase() ?: txtHex?.text.toString().toUpperCase())
    }
}
