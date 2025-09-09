package com.example.playlistmaker
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import com.example.playlistmaker.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private var _binding: ActivitySettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val sharedPref = getSharedPreferences("settings", MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean("dark_mode", false)

        // Устанавливаем Switch и тему при старте
        binding.switchBottomTheme.isChecked = isDarkMode
        if (isDarkMode) {
            setDefaultNightMode(MODE_NIGHT_YES)
        } else {
            setDefaultNightMode(MODE_NIGHT_NO)
        }

        // Слушатель Switch
        binding.switchBottomTheme.setOnCheckedChangeListener { _, isChecked ->
            with(sharedPref.edit()) {
                putBoolean("dark_mode", isChecked)
                apply()
            }

            if (isChecked) {
                setDefaultNightMode(MODE_NIGHT_YES)
            } else {
                setDefaultNightMode(MODE_NIGHT_NO)
            }

            // Перезапуск Activity для применения темы
            recreate()
        }

        binding.shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
            }
            val chooser = Intent.createChooser(shareIntent, "Поделиться через")
            startActivity(chooser)
        }
        binding.supportButton.setOnClickListener {
            val recipient = arrayOf("Dr.Gepard0205@yandex.ru") // мой адрес
            val subject = "Сообщение разработчикам и разработчицам приложения Playlist Maker"
            val body = "Спасибо разработчикам и разработчицам за крутое приложение!"

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, recipient)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }

            startActivity(Intent.createChooser(intent, "Выберите почтовое приложение"))
        }
        binding.termsButton.setOnClickListener {
            val termsUrl = "https://yandex.ru/legal/practicum_offer/ru/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(termsUrl))

            // Запускаем браузер
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}