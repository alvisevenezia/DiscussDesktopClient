package fr.alvisevenezia;

import fr.alvisevenezia.encryption.asymmetrical.AsymmetricalEncryptionMessage;
import fr.alvisevenezia.utils.VERSION;
import fr.alvisevenezia.web.discusspacket.DiscussPacket;
import fr.alvisevenezia.web.discusspacket.DiscussPacketHandler;
import fr.alvisevenezia.web.utils.DATAType;
import fr.alvisevenezia.encryption.symmetrical.SymmetricalEncryptedMessage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {

    final static int port = 9632;

    public static void main(String[] args) {


        DiscussPacketHandler packetHandler = new DiscussPacketHandler(VERSION.BETA, DATAType.ID,1024,0);

        String msg = "1,"+new String(AsymmetricalEncryptionMessage.getKeyPair().getPublic().getEncoded(), StandardCharsets.UTF_8);

        packetHandler.setMessage(msg);

        DiscussPacket[] discussPacket = packetHandler.createPacket();

        Socket socket;
        
        try {
            socket = new Socket("127.0.0.1",port);
            packetHandler.sendDiscussPacket(socket,discussPacket);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        packetHandler.setDataType(DATAType.TEXT);
        packetHandler.setEncryptedMessage("bonjour les zouaves on fait des tests et j'ai besoin d'écrire un long message ziziziziziziziziziziiziziziiziziziiziziiz","caca");

        System.out.println("MSG LENGTH : "+packetHandler.getMessage().length);

        System.out.print("BYTE MSG :");

        for(byte b : packetHandler.getMessage()){

            System.out.print(b);

        }

        System.out.println('\n');

        discussPacket = packetHandler.createPacket();
        int i = discussPacket.length;

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
