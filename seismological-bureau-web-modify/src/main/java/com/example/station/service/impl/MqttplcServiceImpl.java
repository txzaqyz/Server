package com.example.station.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.station.entity.PowerPlc;
import com.example.station.mapper.PlcMapper;
import com.example.station.utils.PlcMsgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Configuration
@IntegrationComponentScan
public class MqttplcServiceImpl {
    //Infect object cannot be 'new'!!!
    private String hostUrl = "tcp://127.0.0.1:1883";
    private String username = "admin";
    private String password = "public";
    private String clientId = "serverPlc";
    private String recvTopic = "plc/+/up";
    private String pubTopic = "plc/+/down";

    @Resource
    private PlcMapper plcMapper;//用于向数据库写入数据


    @Bean
    public MqttConnectOptions getMqttConnectOptions(){
        MqttConnectOptions mqttConnectOptions=new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(90);
//        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        return mqttConnectOptions;
    }
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(clientId, mqttClientFactory(), recvTopic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String msg = (String)message.getPayload();
                String plcTopic = message.getHeaders().get("mqtt_receivedTopic").toString();
                PowerPlc powerPlc = PlcMsgUtils.creatPlcEntity(plcTopic,msg);
                if(powerPlc == null || !PlcMsgUtils.addPlcMsg(plcMapper, powerPlc)) {
                    System.out.println("add PlcMessage failed!");
                }
            }
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =  new MqttPahoMessageHandler(clientId, mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(pubTopic);
        return messageHandler;
    }
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

}

/*
class PlcMsgParser {
    //private PlcDataMapper plcDataMapper;
    public static boolean ParseMsg(PlcDataMapper plcDataMapper, String topic, String plcMsg ){
        int msgType = -1;
        Timestamp ts;
        PlcCollectorData data = new PlcCollectorData();
        JSONObject jsonObj = JSON.parseObject(plcMsg);

        String[] topicLayer = topic.split("\\.|/");
//        System.out.println(plcDataMapper);
//        System.out.println(topic);
//        System.out.println(topicLayer[0]);
//        System.out.println(topicLayer[1]);
//        System.out.println(topicLayer[2]);
        if (topicLayer.length < 3 || topicLayer[1].length() != 12) {
            System.out.println("MQTT topic is not right");
            return false;
        }
        if (false == jsonObj.containsKey(PlcMsgConst.JSON_KEY_UP_TYPE)
                || false == jsonObj.containsKey(PlcMsgConst.JSON_KEY_UP_TIMESTAMP)) {
            System.out.println("Json key missed!");
            return false;
        }
        ts = Timestamp.valueOf(jsonObj.getString(PlcMsgConst.JSON_KEY_UP_TIMESTAMP));
        data.SetBaseInfo(topicLayer[1], ts);
        msgType = jsonObj.getIntValue(PlcMsgConst.JSON_KEY_UP_TYPE);
        //The following {} is 'if..elseif..else' parser code.
        {
            if (msgType == PlcMsgConst.PLC_DATA_TYPE_UP_DI) {
                if (false == jsonObj.containsKey(PlcMsgConst.JSON_KEY_UP_VAL1)) {
                    return false;
                }
                data.SetDi(jsonObj.getIntValue(PlcMsgConst.JSON_KEY_UP_VAL1));
                plcDataMapper.addPlcDataDi(data);
            }
            else if (msgType == PlcMsgConst.PLC_DATA_TYPE_UP_AI) {
                if (false == jsonObj.containsKey(PlcMsgConst.JSON_KEY_UP_VAL1)
                        || false == jsonObj.containsKey(PlcMsgConst.JSON_KEY_UP_VAL2)) {
                    return false;
                }
                data.SetAi(jsonObj.getIntValue(PlcMsgConst.JSON_KEY_UP_VAL1),
                        jsonObj.getIntValue(PlcMsgConst.JSON_KEY_UP_VAL2));
                plcDataMapper.addPlcDataAi(data);
            }
            else if (msgType == PlcMsgConst.PLC_DATA_TYPE_UP_ACK) {
                //Send back to web server.
            }
            else if (msgType == PlcMsgConst.PLC_DATA_TYPE_UP_MODBUS_TEMP) {
                if (false == jsonObj.containsKey(PlcMsgConst.JSON_KEY_UP_VAL1)
                        || false == jsonObj.containsKey(PlcMsgConst.JSON_KEY_UP_VAL2)) {
                    return false;
                }

                data.SetTempAndHum(jsonObj.getIntValue(PlcMsgConst.JSON_KEY_UP_VAL1),
                        jsonObj.getIntValue(PlcMsgConst.JSON_KEY_UP_VAL2));
                plcDataMapper.addPlcDataTemp(data);
            }
            else if (msgType == PlcMsgConst.PLC_DATA_TYPE_UP_UPS) {
                if (false == jsonObj.containsKey(PlcMsgConst.JSON_KEY_UP_UPS_DATA)) {
                    return false;
                }

                data.SetUps(jsonObj.getString(PlcMsgConst.JSON_KEY_UP_UPS_DATA));
                plcDataMapper.addPlcDataUps(data);
            }
            else{
                System.out.println("Msg type not support!");
                return false;
            }
        }
        return true;
    }
}

 */
