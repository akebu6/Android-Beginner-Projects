package com.example.tippy

import android.animation.ArgbEvaluator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tippy.databinding.ActivityMainBinding


const val TAG= "app"
const val InitaialValue= 15
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        var data= binding.root
        setContentView(data)

        binding.sbBaseAmtPercentR.progress= InitaialValue
            binding.tvTipPercent.text= "$InitaialValue%"
            changecolor(InitaialValue)
            binding.sbBaseAmtPercentR.apply {
            setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                  Log.i(TAG, "onProgressChanged: $p1 ")
                    binding.tvTipPercent.text="$p1%"
                   changecolor(p1)
                   computeTipAndTotal()
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }

            })

        }
        binding.etBaseAmtDispR.apply {
            addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {

                    Log.i(TAG, "afterTextChanged: $p0")
                    computeTipAndTotal()

                } })
        }
    }

    private fun changecolor(p1: Int) {
  var statemnet = when(p1){
      in 0..5->"poor"
      in 6..10->"staisfactory"
      in  11..15 -> "okayish"
      in 16..21 ->"good"
      in 22..25->"nice"
      else ->"Impressed❤"
  }
        binding.tvchangecolorR.text=statemnet
        val col = ArgbEvaluator().evaluate(
            p1.toFloat() / binding.sbBaseAmtPercentR.max , ContextCompat.getColor(this,R.color.worstCase)
        ,ContextCompat.getColor(this,R.color.bestCase)
        )as Int
        binding.tvchangecolorR.setTextColor(col)
    }

    private fun computeTipAndTotal() {
        if(binding.etBaseAmtDispR.text.isEmpty()){
            binding.tvTotalAmtR.text=""
            binding.tvTipAmtR.text=""
            return
        }
     val bill = binding.etBaseAmtDispR.text.toString().toFloat()
     val tipPercent=binding.sbBaseAmtPercentR.progress
     val tip= bill*tipPercent/100
     val total = tip+bill
     binding.tvTipAmtR.text=  "%.2f".format(tip)
     binding.tvTotalAmtR.text="%.2f".format(total)
    }
}