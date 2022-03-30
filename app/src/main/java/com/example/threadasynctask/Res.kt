package com.example.threadasynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class Res : AppCompatActivity() {
    lateinit var progressBar: ProgressBar
    lateinit var tvInfo: TextView
    lateinit var tvCorrent: TextView
    lateinit var tvP: TextView
    lateinit var tvRNum: TextView
    lateinit var btnAdd: Button
    lateinit var btnRed: Button
    lateinit var btnReset: Button
    lateinit var imgReversh: ImageView
    lateinit var img1: ImageView
    lateinit var img2: ImageView
    lateinit var img3: ImageView
    var timerAdd: CountDownTimer? = null
    var timerRed: CountDownTimer? = null
    var maxNum = 0
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        maxNum = intent.getIntExtra("maxNum", 0)
        var num = intent.getIntExtra("num", 0)
        progressBar = findViewById(R.id.progressBar)


        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        imgReversh = findViewById(R.id.imgReversh)

        tvRNum = findViewById(R.id.tvRNum)
        tvCorrent = findViewById(R.id.tvCurrent)
        tvP = findViewById(R.id.tvP)
        tvCorrent.text = num.toString()
        tvRNum.text = num.toString()
        tvInfo = findViewById(R.id.tvInfo)

        btnAdd = findViewById(R.id.btnAdd)
        btnRed = findViewById(R.id.btnRed)
        btnReset = findViewById(R.id.btnReset)

            btnAdd.setOnClickListener {
                tvInfo.text = "Adds apples . . ."
                num++
                MyAsyncTask().execute(num)
                timerAdd?.cancel()
                timerAdd = object : CountDownTimer(5000, 300) {
                    override fun onTick(p0: Long) {
                        i++
                        if (i== 8 ) i = 0
                        if (i == 2) img3.visibility = View.VISIBLE else img3.visibility = View.INVISIBLE
                        if (i == 4) img2.visibility = View.VISIBLE else img2.visibility = View.INVISIBLE
                        if (i == 6) img1.visibility = View.VISIBLE else img1.visibility = View.INVISIBLE
                    }
                    override fun onFinish() {
                        timerAdd?.cancel()
                    }
                }.start()
            }

            btnRed.setOnClickListener {
                tvInfo.text = "Reduces apples . . ."
                num--
                MyAsyncTask().execute(num)
                timerRed?.cancel()
                timerRed = object : CountDownTimer(5000, 300) {
                    override fun onTick(p0: Long) {
                        i++
                        if (i == 8 ) i = 0
                        if (i == 2) img1.visibility = View.VISIBLE else img1.visibility = View.INVISIBLE
                        if (i == 4) img2.visibility = View.VISIBLE else img2.visibility = View.INVISIBLE
                        if (i == 6) img3.visibility = View.VISIBLE else img3.visibility = View.INVISIBLE
                    }
                    override fun onFinish() {
                        timerRed?.cancel()
                    }
                }.start()
            }

            btnReset.setOnClickListener {
                tvInfo.text = "Refreshes the box . . ."
                num =  intent.getIntExtra("num", 0)
                MyAsyncTask().execute(num)
            }

    }

    inner class MyAsyncTask: AsyncTask<Int, Int, Int>() {
        var progresBarValue = 0
        override fun onPreExecute() {
            super.onPreExecute()
            btnAdd.isClickable = false
            btnRed.isClickable = false
            imgReversh.visibility = View.VISIBLE
            tvCorrent.visibility = View.INVISIBLE
        }

        override fun onPostExecute(result: Int?) {
            super.onPostExecute(result)
            if (result != null) {
                if (result == 0 || result >= maxNum) {
                    btnReset.visibility = View.VISIBLE
                    btnAdd.visibility = View.GONE
                    btnRed.visibility = View.GONE
                } else {
                    btnReset.visibility = View.GONE
                    btnAdd.visibility = View.VISIBLE
                    btnRed.visibility = View.VISIBLE
                }
            }
            btnAdd.isClickable = true
            btnRed.isClickable = true
            imgReversh.visibility = View.INVISIBLE
            tvCorrent.visibility = View.VISIBLE
            tvCorrent.text =  result.toString()
            img1.visibility = View.INVISIBLE
            img2.visibility = View.INVISIBLE
            img3.visibility = View.INVISIBLE
            tvInfo.visibility = View.INVISIBLE
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            progressBar.progress = values[0]!!
            tvP.text = "${values[0]} %"
            tvInfo.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p0: Int?): Int? {
            while (progresBarValue < 100) {
                progresBarValue++
                publishProgress(progresBarValue)
                SystemClock.sleep(50)

            }
            return p0[0]
        }
    }

    fun timerAdd() {

    }
}