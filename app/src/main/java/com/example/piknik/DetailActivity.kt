package com.example.piknik
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.piknik.R

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var shareButton: Button


    companion object{
        const val KEY_PIKNIK = "key_piknik"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        shareButton = findViewById(R.id.btn_share)
        shareButton.setOnClickListener(this)

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)



        val dataPiknik = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Piknik>(KEY_PIKNIK, Piknik::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Piknik>(KEY_PIKNIK)
        }

        dataPiknik?.let { ivDetailPhoto.setImageResource(it.photo) }
        tvDetailName.text = dataPiknik?.name
        tvDetailDescription.text = dataPiknik?.description
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                val intentToAbout = Intent(this@DetailActivity, AboutActivity::class.java)
                startActivity(intentToAbout)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_share -> {
                // Membuat Intent untuk berbagi melalui email
                val emailIntent = Intent(Intent.ACTION_SEND)
                emailIntent.type = "message/rfc822" // Tipe pesan email
                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("tujuan@email.com")) // Alamat email penerima
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subjek Email") // Subjek email (opsional)
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Isi pesan email") // Isi pesan email (opsional)

                // Memulai aktivitas berbagi melalui email
                startActivity(Intent.createChooser(emailIntent, "Pilih Aplikasi Email"))
            }
        }
    }
}