package helpers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import classes.Client;

/**
 * Created by ammach on 5/2/2016.
 */
public class ClientConnecte {

    Socket sock=null;
    public void connexion(String host, int port)
    { try
    { 	System.out.println("Le client cherche à se connecter au serveur " + host + "@"+port);
        sock=new Socket(host, port);
        System.out.println("Le client s'est connecté sur serveur " + host + "@"+port);

    }
    catch(IOException e) { }
    }
    public void envoi(String data){
        try {
            DataOutputStream dataInputStream=new DataOutputStream(sock.getOutputStream());
            dataInputStream.writeUTF(data);
            dataInputStream.flush();
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void envoiObject(Object data){
        try {
            ObjectOutputStream objectOutputStream =new ObjectOutputStream(sock.getOutputStream());
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void recevoir(){
        try{
            System.out.println("Le client cherche à récupérer le canal de communication ");
            BufferedReader entree=new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String text=entree.readLine();
            System.out.println(text);
        }
        catch(IOException e){
        }
    }

    public void recevoirObject(){
        try{
            System.out.println("Le client cherche à récupérer le canal de communication ");
            ObjectInputStream objectInputStream =new ObjectInputStream(sock.getInputStream());
            Client data= (Client) objectInputStream.readObject();
            System.out.println(data);
        }
        catch(IOException e){
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fermer(){
        try{
            System.out.println("Le client ferme la connexion au serveur ");
            sock.close();
        }
        catch(IOException e){
        }
    }

    public Socket getSock() {
        return sock;
    }
}
