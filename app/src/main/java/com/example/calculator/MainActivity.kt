package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.TextureView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private val expressionTextView: TextView by lazy {
        findViewById<TextView>(R.id.expressionTextView)
    }

    private val resultTextView: TextView by lazy {
        findViewById(R.id.resultTextView)
    }

    //operator값을 입력하다 왔는가?
    private var isOperator = false

    private var hasOperator = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun buttonClicked(v: View) {
        when (v.id) {
            R.id.button0 -> numberButtonClicked("0")
            R.id.button1 -> numberButtonClicked("1")
            R.id.button2 -> numberButtonClicked("2")
            R.id.button3 -> numberButtonClicked("3")
            R.id.button4 -> numberButtonClicked("4")
            R.id.button5 -> numberButtonClicked("5")
            R.id.button6 -> numberButtonClicked("6")
            R.id.button7 -> numberButtonClicked("7")
            R.id.button8 -> numberButtonClicked("8")
            R.id.button9 -> numberButtonClicked("9")
            R.id.buttonPlus -> operatorButtonClicked("+")
            R.id.buttonSubtract -> operatorButtonClicked("-")
            R.id.buttonDivider -> operatorButtonClicked("/")
            R.id.buttonModulo -> operatorButtonClicked("%")
            R.id.buttonMultiple -> operatorButtonClicked("*")
        }

    }

    private fun numberButtonClicked(number: String) {
        if (isOperator) {
            //숫자로 입력이 변경되었음.
            expressionTextView.append(" ")
        }
        isOperator = false

        val expressionText = expressionTextView.text.split(" ")
        //첫 수를 입력받고 연산자가 나온 후 last는 두 번째 숫자열의 첫 수임.
        if (expressionText.isNotEmpty() && expressionText.last().length >= 15) {
            Toast.makeText(this, "15자리 까지만 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
            return
        } else if (expressionText.last().isEmpty() && number == "0") {
            Toast.makeText(this, "0은 제일 앞에 올 수 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        expressionTextView.append(number)
        // TODO resultTextView 실시간으로 연산결과를 넣어야하는 기

    }

    private fun operatorButtonClicked(operator: String) {
        //숫자보다 연산자가 먼저 눌리면 무시하는 예외를 처리해줘야함.
        if (expressionTextView.text.isEmpty()) {
            return
        }
        //이미 연산자가 존재하지만 다른 연산자를 누른 경우, 마지막으로 누른 연산자로 바꿔주는 식
        when {
            isOperator -> {
                val text = expressionTextView.text.toString() //현재 존재하는 텍스트값의 상수
                expressionTextView.text =
                    text.dropLast(1) + operator // 그 값의 마지막값(중복입력된 연산자 1자리)을 지워주고, 연산자를 넣어줌.
            }
            hasOperator -> {
                Toast.makeText(this, "연산자는 한 번만 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return
            }
            else -> {
                expressionTextView.append(" $operator")
            }
        }
        //색을 연산자일 경우에는 초록색으로 칠해주는 기능.
        //what, start, end, flag 인자가 들어가야하므로 연산자는 한 번만 나오므로 무조건 마지막에 나옴.
        val ssb = SpannableStringBuilder(expressionTextView.text)
        ssb.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            expressionTextView.text.length - 1,
            expressionTextView.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        //최정적으로 ssb로 색을 재설정한 값을 expressionTextView에 부여함.
        expressionTextView.text = ssb

        //연산자가 입력이 되었음.
        isOperator = true
        hasOperator = true


    }

    fun clearButtonClicked(v: View) {



    }

    fun historyButtonClicked(v: View) {

    }

    fun resultButtonClicked(v: View) {

    }
    //expressionTextView에서 가져온 결과를 받아 result로 가기 직전에 String으로 반환하는 역할
    private fun calculateExpression() : String {
        val expressionTexts = expressionTextView.text.split(" ")
        //연산자가 아예 눌린적이 없다 or 숫자 + 연산자 + 숫자 형태로 계산이 불가능할 때,
        if(hasOperator.not() || expressionTexts.size != 3){
            return ""
        } else if(expressionTexts[0].isNumber().not() || expressionTexts[2].isNumber().not()){
            return ""
        }
        //isNumber은 존재하지 않으므로 확장함수로 만듦.

        //operator을 제외하고 이미 예외처리를 한 문자열들(숫자들)을 BigInteger로 바꿔줌 ex)1234 + 5678 ->  [0] = 1234, [1] = +, [2] = 5678
        val exp1 = expressionTexts[0].toBigInteger()
        val exp2 = expressionTexts[2].toBigInteger()

    }


}
// 확장함수
fun String.isNumber(): Boolean {
    return try {
        this.toBigInteger()
        true //BigInteger의 범위에 들어오면 true를 반환
    } catch(e: NumberFormatException) {
        false
    }
}