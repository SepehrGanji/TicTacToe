package ir.sepehrganji.tictak

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.mainlayout.*

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainlayout)
        one.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("automode",true)
            startActivity(intent)
        }
        two.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("automode",false)
            startActivity(intent)
        }
    }
}
