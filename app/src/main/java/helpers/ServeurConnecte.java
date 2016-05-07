package helpers;

import android.os.Message;

import com.example.ammach.bourse.UserActivity;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ammach on 5/3/2016.
 */
public class ServeurConnecte extends Thread
{ ServerSocket reception;
    static final int port=40000;

    public ServeurConnecte()
    { try
    { reception=new ServerSocket(port);
        System.out.println("Le serveur est en écoute sur le "+port);
    }
    catch(IOException e) { System.exit(1); }
        this.start();
    }

    public void run()
    { Socket sock;
        String text;

        try
        {
            System.out.println("votre adresse est  "+ InetAddress.getLocalHost().getHostName());
            while(true)
            {
                System.out.println("Le serveur est en attente ");
                sock=reception.accept();
                System.out.println("Le serveur a accepté la connexion avec "+sock.getInetAddress());
                DataInputStream dataInputStream = new DataInputStream(sock.getInputStream());
                text = dataInputStream.readUTF();
                Message msg=Message.obtain();
                msg.obj=text;
                UserActivity.handler.sendMessage(msg);
                sock.close();
            }
        }
        catch(IOException e) { }
    }
}
