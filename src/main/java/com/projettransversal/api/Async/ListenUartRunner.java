package com.projettransversal.api.Async;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class ListenUartRunner implements CommandLineRunner {

    @Override
    @Async
    public void run(String... args) throws Exception {
        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.openPort();
        try {
            while (true)
            {
                System.out.println("Listening on UART");
                while (comPort.bytesAvailable() == 0) {
                    Thread.sleep(20);
                }

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                comPort.readBytes(readBuffer, readBuffer.length);
                String data = new String(readBuffer, StandardCharsets.UTF_8);
                System.out.println(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comPort.closePort();
    }
}