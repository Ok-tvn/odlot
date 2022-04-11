package com.example.odlot

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    var textview_start: TextView? = null
    var cal_start = Calendar.getInstance()

    var textview_srodek: TextView? = null
    var cal_srodek = Calendar.getInstance()

    var textview_koniec: TextView? = null
    var cal_koniec = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var Suwak: SeekBar
        lateinit var progres: ProgressBar
        lateinit var edittext_predkosc: EditText
        lateinit var kalendarz: CalendarView
        lateinit var przycisk: Button

        var a:Float

        textview_start = findViewById<TextView>(R.id.start);
        textview_srodek = findViewById<TextView>(R.id.srodek);
        textview_koniec = findViewById<TextView>(R.id.koniec);
        progres = findViewById<ProgressBar>(R.id.progressBar)
        przycisk = findViewById<Button>(R.id.start_btn)

        edittext_predkosc = findViewById<EditText>(R.id.predkosc);

        textview_start!!.text = "--/--/----"
        textview_srodek!!.text = "--/--/----"
        textview_koniec!!.text = "--/--/----"

        val timer = object: CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                progres.setProgress(progres.getProgress()+1);
            }

            override fun onFinish() {

            }
        }
        przycisk.setOnClickListener{
            val dateStr = start
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val date = sdf.parse(dateStr)
            timer.start()
        }

        kalendarz = findViewById<CalendarView>(R.id.kalendarz)
        fun data(){
            val date = SimpleDateFormat("dd/MM/yyyy").parse(textview_start.toString())
            val millionSeconds = date.time - Calendar.getInstance().timeInMillis
            kalendarz.setDate(millionSeconds)
        }


        Suwak = findViewById<SeekBar>(R.id.seekbar_predkosc)

        Suwak.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,fromUser: Boolean) {

                a = (progress.toFloat())
                a=a/10;
                edittext_predkosc!!.setText(a.toString());


            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        val dateSetListener_koniec = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal_srodek.set(Calendar.YEAR, year)
                cal_srodek.set(Calendar.MONTH, monthOfYear)
                cal_srodek.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView_koniec()
            }
        }

        val dateSetListener_srodek = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal_srodek.set(Calendar.YEAR, year)
                cal_srodek.set(Calendar.MONTH, monthOfYear)
                cal_srodek.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView_srodek()
            }
        }

        val dateSetListener_start = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal_start.set(Calendar.YEAR, year)
                cal_start.set(Calendar.MONTH, monthOfYear)
                cal_start.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView_start()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        textview_start!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@MainActivity,
                    dateSetListener_start,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal_start.get(Calendar.YEAR),
                    cal_start.get(Calendar.MONTH),
                    cal_start.get(Calendar.DAY_OF_MONTH)).show()
                    //data();
            }
        })
        textview_srodek!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@MainActivity,
                    dateSetListener_srodek,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal_srodek.get(Calendar.YEAR),
                    cal_srodek.get(Calendar.MONTH),
                    cal_srodek.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
        textview_koniec!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@MainActivity,
                    dateSetListener_koniec,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal_koniec.get(Calendar.YEAR),
                    cal_koniec.get(Calendar.MONTH),
                    cal_koniec.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
    }
    private fun updateDateInView_start() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat  (myFormat, Locale.US)
        textview_start!!.text = sdf.format(cal_start.getTime())
    }
    private fun updateDateInView_srodek() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat  (myFormat, Locale.US)
        textview_srodek!!.text = sdf.format(cal_srodek.getTime())
    }
    private fun updateDateInView_koniec() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat  (myFormat, Locale.US)
        textview_koniec!!.text = sdf.format(cal_koniec.getTime())
    }
}