package fr.alvisevenezia;

import fr.alvisevenezia.Utils.VERSION;
import fr.alvisevenezia.Web.DiscussPacket.DiscussPacket;
import fr.alvisevenezia.Web.DiscussPacket.DiscussPacketHandler;
import fr.alvisevenezia.Web.Utils.DATAType;
import fr.alvisevenezia.encryption.symmetrical.SymmetricalEncryptedMessage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {

    final static int port = 9632;

    public static void main(String[] args) {

        DiscussPacketHandler packetHandler = new DiscussPacketHandler(VERSION.BETA, DATAType.TEXT,128,0);
        packetHandler.setEncryptedMessage("bonjour les zouaves on fait des tests et j'ai besoin d'écrire un long message ziziziziziziziziziziiziziziiziziziiziziiz","caca");

        System.out.println("MSG LENGTH : "+packetHandler.getMessage().length);

        System.out.print("BYTE MSG :");

        for(byte b : packetHandler.getMessage()){

            System.out.print(b);

        }

        System.out.println('\n');

        DiscussPacket[] discussPacket = packetHandler.createPacket();
        int i = discussPacket.length;
        Socket socket;

        try {
            socket = new Socket("127.0.0.1",port);

            packetHandler.sendDiscussPacket(socket,discussPacket);

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.print("BYTE MSG 2 :");

        for(byte b : packetHandler.getMessage()){

            System.out.print(b);

        }

        System.out.println('\n');

        System.out.println("MSG :"+ SymmetricalEncryptedMessage.getDecryptedMessage(packetHandler.getMessage(),"caca"));


    }
}
