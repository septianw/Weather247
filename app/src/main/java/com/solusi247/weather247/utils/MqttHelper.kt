package com.solusi247.weather247.utils

import android.content.Context
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MqttHelper(context: Context) {

    var mqttAndroidClient: MqttAndroidClient

    // Server URI Cloud MQTT
    // Format: tcp://server.uri:port
    val serverUri = "tcp://m14.cloudmqtt.com:13021"

    // Client ID which this connection should be identified to the server
    val clientId = "Weather247Client"

    // Topic MQTT
    val subscriptionTopic = "sensor/+"

    // Username instances cloud MQTT
    val uname = "tebbodbu"
    // Password instances cloud MQTT
    val pword = "pAFFxtjpA9dt"

    init {
        mqttAndroidClient = MqttAndroidClient(context, serverUri, clientId)

        val mqttConnectOptions = MqttConnectOptions().apply {
            isAutomaticReconnect = true
            isCleanSession = false
            userName = uname
            password = pword.toCharArray()
        }

        mqttAndroidClient.connect(mqttConnectOptions, null, object : IMqttActionListener {

            override fun onSuccess(asyncActionToken: IMqttToken) {
                mqttAndroidClient.setBufferOpts(DisconnectedBufferOptions().apply {
                    isBufferEnabled = true
                    bufferSize = 100
                    isPersistBuffer = false
                    isDeleteOldestMessages = false
                })
                mqttAndroidClient.subscribe(subscriptionTopic, 0, null, object : IMqttActionListener {
                    override fun onSuccess(asyncActionToken: IMqttToken?) {}
                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                        exception?.printStackTrace()
                    }
                })
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                exception?.printStackTrace()
            }
        })
    }

    fun setCallBack(mqttCallbackExtended: MqttCallbackExtended) = mqttAndroidClient.setCallback(mqttCallbackExtended)

}
