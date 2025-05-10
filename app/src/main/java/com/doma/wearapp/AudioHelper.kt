package com.doma.wearapp

import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.provider.Settings
import android.content.Intent

class AudioHelper(private val context: Context) {

    private val audioManager: AudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    // Verifica se o alto-falante embutido está disponível
    fun isSpeakerAvailable(): Boolean {
        return isAudioOutputAvailable(AudioDeviceInfo.TYPE_BUILTIN_SPEAKER)
    }

    // Verifica se há um fone de ouvido Bluetooth conectado
    fun isBluetoothConnected(): Boolean {
        return isAudioOutputAvailable(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP)
    }

    // Função genérica para verificar tipos de saída de áudio
    private fun isAudioOutputAvailable(type: Int): Boolean {
        if (!context.packageManager.hasSystemFeature(PackageManager.FEATURE_AUDIO_OUTPUT)) {
            return false
        }

        val devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS)
        return devices.any { it.type == type }
    }

    // Abre diretamente as configurações de Bluetooth do dispositivo
    fun openBluetoothSettings() {
        val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            putExtra("EXTRA_CONNECTION_ONLY", true)
            putExtra("EXTRA_CLOSE_ON_CONNECT", true)
            putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 1)
        }
        context.startActivity(intent)
    }
}
