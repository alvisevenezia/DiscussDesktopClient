package fr.alvisevenezia;

import fr.alvisevenezia.Utils.VERSION;
import fr.alvisevenezia.Web.DiscussPacket.DiscussPacket;
import fr.alvisevenezia.Web.DiscussPacket.DiscussPacketHandler;
import fr.alvisevenezia.Web.Utils.DATAType;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {

    final static int port = 9632;

    public static void main(String[] args) {

        DiscussPacketHandler packetHandler = new DiscussPacketHandler(VERSION.BETA, DATAType.TEXT,25,0);
        packetHandler.setMessage("bonjour les zouaves");
        DiscussPacket[] discussPacket = packetHandler.createPacket();
        int i = discussPacket.length;
        Socket socket;

        try {
            socket = new Socket("127.0.0.1",port);

            packetHandler.sendDiscussPacket(socket,discussPacket);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
