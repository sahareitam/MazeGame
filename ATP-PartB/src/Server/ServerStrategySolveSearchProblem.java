package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
//import com.sun.corba.se.impl.io.IIOPInputStream;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze maze = (Maze) fromClient.readObject();
            File f = new File (System.getProperty("java.io.tmpdir"));

            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File f, String name) {
                    return name.startsWith("maze");
                }
            };
            File[] files = f.listFiles(filter);
            int i=0;
            FileInputStream file = null;
            ObjectInputStream intObject = null;
            for ( i = 0; i < files.length; i++) {
                file=new FileInputStream(files[i]);
                intObject=new ObjectInputStream(file);
                Maze m=(Maze)intObject.readObject();
                boolean areMazesEquals = Arrays.equals(m.toByteArray(), maze.toByteArray());
                if (areMazesEquals==true)
                    break;
            }

            if (i==files.length) {
                try(InputStream input = new FileInputStream("C:\\Users\\97252\\IdeaProjects\\ATP-PartB\\resources\\config.properties")) {
                    ISearchingAlgorithm searcher=null;
                    Properties prop = new Properties();
                    prop.load(input);
                    if (prop.getProperty("mazeSearchingAlgorithm").equals("BreadthFirstSearch"))
                        searcher=new BreadthFirstSearch();
                    if (prop.getProperty("mazeSearchingAlgorithm").equals("DepthFirstSearch"))
                        searcher=new DepthFirstSearch();
                    if (prop.getProperty("mazeSearchingAlgorithm").equals("BestFirstSearch"))
                        searcher=new BestFirstSearch();
                    SearchableMaze searchableMaze = new SearchableMaze(maze);
                    Solution s = searcher.solve(searchableMaze);
                    toClient.writeObject(s);
                    File tempFile = File.createTempFile("maze", null);
                    FileOutputStream newFile = new FileOutputStream(tempFile.getAbsoluteFile());
                    ObjectOutputStream outObject = new ObjectOutputStream(newFile);
                    outObject.writeObject(maze);
                    outObject.writeObject(s);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else
            {
                toClient.writeObject((Solution)intObject.readObject());
            }
            toClient.flush();

            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
