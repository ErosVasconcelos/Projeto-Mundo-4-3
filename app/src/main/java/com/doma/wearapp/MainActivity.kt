package com.doma.wearapp

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val helper = AudioHelper(this)

        if (helper.isBluetoothConnected()) {
            // Fone de ouvido Bluetooth está conectado
            Toast.makeText(this, "Fone Bluetooth conectado!", Toast.LENGTH_SHORT).show()
            playAudio()
        } else if (helper.isSpeakerAvailable()) {
            // Usando alto-falante embutido
            Toast.makeText(this, "Usando alto-falante do relógio", Toast.LENGTH_SHORT).show()
            playAudio()
        } else {
            // Nenhum dispositivo de áudio disponível
            Toast.makeText(this, "Conecte um fone Bluetooth", Toast.LENGTH_LONG).show()
            helper.openBluetoothSettings() // Redireciona para as configurações do Bluetooth
        }
    }

    // Função para tocar áudio do arquivo 'sample_audio.mp3' presente em res/raw/
    private fun playAudio() {
        val mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio) // Acesse o áudio em res/raw/sample_audio.mp3
        mediaPlayer?.start()
    }
}
