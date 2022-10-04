import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Main {
    public static void main(String[] args) throws IOException {

        StringBuilder builder = new StringBuilder();

        File src = new File("C://Games/src");
        if(src.mkdir())
            builder.append("Каталог src создан\n");

        File res = new File("C://Games/res");
        if(res.mkdir())
            builder.append("Каталог res создан\n");

        File savegames = new File("C://Games/savegames");
        if(savegames.mkdir())
            builder.append("Каталог savegames создан\n");

        File temp = new File("C://Games/temp");
        if(temp.mkdir())
            builder.append("Каталог temp создан\n");

        File main = new File("C://Games/src/main");
        if(main.mkdir())
            builder.append("Каталог main создан\n");

        File test = new File("C://Games/src/test");
        if(test.mkdir())
            builder.append("Каталог test создан\n");

        File drawables = new File("C://Games/res/drawables");
        if(drawables.mkdir())
            builder.append("Каталог drawables создан\n");

        File vectors = new File("C://Games/res/vectors");
        if(vectors.mkdir())
            builder.append("Каталог vectors создан\n");

        File icons = new File("C://Games/res/icons");
        if(icons.mkdir())
            builder.append("Каталог icons создан\n");

        File Main = new File("C://Games/src/main/Main.java");
        try {
            if(Main.createNewFile())
                builder.append("Файл Main.java был создан\n");
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        File Utils = new File("C://Games/src/main/Utils.java");
        try {
            if(Utils.createNewFile())
                builder.append("Файл Utils.java был создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (FileWriter writer = new FileWriter("C://Games/temp/temp.txt")){
            writer.write(String.valueOf(builder));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        GameProgress save1 = new GameProgress(85, 5, 3, 130.54);
        GameProgress save2 = new GameProgress(75, 7, 2, 96.54);
        GameProgress save3 = new GameProgress(98, 4, 1, 37.54);

        saveGame("C://Games/savegames/save1.dat", save1);
        saveGame("C://Games/savegames/save2.dat", save2);
        saveGame("C://Games/savegames/save3.dat", save3);
        zipFiles("C://Games/savegames/zip.zip", savegames);

        File[] array = savegames.listFiles();

        array[0].delete();
        array[1].delete();
        array[2].delete();  
    }

    public static void saveGame(String path, GameProgress save) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String archivePath, File listOfFiles) throws IOException{
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archivePath));
             FileInputStream fis = new FileInputStream(listOfFiles)) {
            File path = null;
            if(listOfFiles.isDirectory()) {
                for(File item : listOfFiles.listFiles()) {
                    path = item;
                }
            }
            ZipEntry entry = new ZipEntry(String.valueOf(path));
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
