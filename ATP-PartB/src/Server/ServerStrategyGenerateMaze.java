package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.nio.channels.Channels;
import java.util.Locale;
import java.util.Properties;

public class ServerStrategyGenerateMaze implements IServerStrategy{

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            // receive from the client 2 integers - number of rows and columns
            int[] al = (int[]) fromClient.readObject();
            try(InputStream input = new FileInputStream(("C:/Users/97252/IdeaProjects/ATP-PartB/resources/config.properties"))) {
                Properties prop = new Properties();
                prop.load(input);
                IMazeGenerator mazeGenerator=null;
                if (prop.getProperty("mazeGeneratingAlgorithm").equals("EmptyMazeGenerator"))
                    mazeGenerator=new EmptyMazeGenerator();
                if (prop.getProperty("mazeGeneratingAlgorithm").equals("SimpleMazeGenerator"))
                    mazeGenerator=new SimpleMazeGenerator();
                if (prop.getProperty("mazeGeneratingAlgorithm").equals("MyMazeGenerator"))
                    mazeGenerator=new MyMazeGenerator();
                Maze maze=mazeGenerator.generate(al[0],al[1]);
                byte[] b = maze.toByteArray();
                ByteArrayOutputStream arr=new ByteArrayOutputStream();
                OutputStream out = new MyCompressorOutputStream(arr);
                out.write(b);
                out.flush();
                toClient.writeObject(arr.toByteArray());
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            toClient.flush();

            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
