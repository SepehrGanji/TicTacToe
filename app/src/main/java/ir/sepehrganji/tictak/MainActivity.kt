package ir.sepehrganji.tictak

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var canplay = true
    var isauto = false
    var CurrentPlayer = 1
    var P1 = ArrayList<Int>()
    var P2 = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        P1.clear()
        P2.clear()
        isauto = intent.getBooleanExtra("automode",false)
    }
    fun btn_Click(view:View){
        val btn = view as Button
        var btnid = 0
        when(btn.id){
            R.id.b1 -> btnid = 1
            R.id.b2 -> btnid = 2
            R.id.b3 -> btnid = 3
            R.id.b4 -> btnid = 4
            R.id.b5 -> btnid = 5
            R.id.b6 -> btnid = 6
            R.id.b7 -> btnid = 7
            R.id.b8 -> btnid = 8
            R.id.b9 -> btnid = 9
        }
        if(canplay){play(btnid,btn)}
    }
    fun play(bid:Int,b:Button){
        if(CurrentPlayer == 1){
            P1.add(bid)
            b.text = "X"
            b.setTextColor(Color.GREEN)
            if (isauto){
                autoplay()
            }else{
                CurrentPlayer = 2
            }
        }else{
            P2.add(bid)
            b.text = "O"
            b.setTextColor(Color.BLUE)
            CurrentPlayer = 1
        }
        b.isEnabled = false
        if (findwinner(P1)){
            Toast.makeText(this,"بازیکن اول برنده شد",Toast.LENGTH_LONG).show()
            canplay = false
        }
        if (findwinner(P2)){
            Toast.makeText(this,"بازیکن دوم برنده شد",Toast.LENGTH_LONG).show()
            canplay = false
        }
        if(P1.size>=5 || P2.size>=5){
            if(canplay) {
                Toast.makeText(this, "تموم شد!", Toast.LENGTH_SHORT).show()
                canplay = false
            }
        }
    }
    fun findwinner(array:ArrayList<Int>):Boolean{
        //row
        for(i in 1..7 step 3){
            if(array.contains(i) && array.contains(i+1) && array.contains(i+2)){return true}
        }
        //column
        for (i in 1..3){
            if(array.contains(i) && array.contains(i+3) && array.contains(i+6)){return true}
        }
        //otherwaystowin
        if(array.contains(1) && array.contains(5) && array.contains(9)){return true}
        if(array.contains(3) && array.contains(5) && array.contains(7)){return true}
        return false
    }
    fun autoplay(){
        val target = findtarget()
        if(target != 0){
            val btn:Button
            when(target){
                1 -> btn = b1
                2 -> btn = b2
                3 -> btn = b3
                4 -> btn = b4
                5 -> btn = b5
                6 -> btn = b6
                7 -> btn = b7
                8 -> btn = b8
                9 -> btn = b9
                else -> {
                    btn = b1
                    Toast.makeText(this, "Err1\n$target",Toast.LENGTH_LONG).show()
                }
            }
            if(btn.isEnabled){
                P2.add(target)
                btn.text = "O"
                btn.setTextColor(Color.BLUE)
            }else{Toast.makeText(this,"Err2\n$target",Toast.LENGTH_LONG).show()}
        }
    }
    fun findtarget():Int{
        if (!P1.contains(5) && !P2.contains(5)){return 5}
        val empty = ArrayList<Int>()
        empty.clear()
        for(a in 1..9){
            if (!P1.contains(a) && !P2.contains(a)){
                empty.add(a)
            }
        }
        if(empty.size==0){
            return 0
        }else if(empty.size == 1){
            return empty[0]
        }else{
            when (P2.size){
                0 -> {
                    return 1
                }
                1 -> {
                    for(j in 0 until empty.size){
                        P1.add(empty[j])
                        if (findwinner(P1)){
                            P1.remove(empty[j])
                            return empty[j]
                        }
                        P1.remove(empty[j])
                    }
                    if(!empty.contains(1)){return 1}
                    if(!empty.contains(7)){return 7}
                    if(!empty.contains(3)){return 3}
                    if(!empty.contains(9)){return 9}
                }
                else -> {
                    for(a in 0 until empty.size){
                        P2.add(empty[a])
                        if (findwinner(P2)){
                            P2.remove(empty[a])
                            return empty[a]
                        }
                        P2.remove(empty[a])
                    }
                    for(j in 0 until empty.size){
                        P1.add(empty[j])
                        if (findwinner(P1)){
                            P1.remove(empty[j])
                            return empty[j]
                        }
                        P1.remove(empty[j])
                    }
                    if(empty.contains(1)){return 1}
                    if(empty.contains(7)){return 7}
                    if(empty.contains(3)){return 3}
                    if(empty.contains(9)){return 9}
                    if(empty.contains(8)){return 8}
                    if(empty.contains(6)){return 6}
                    if(empty.contains(4)){return 4}
                    if(empty.contains(2)){return 2}
                }
            }
        }
        return 0
    }
}
