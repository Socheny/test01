package ReadFile;

import DB.DBUtil;
import model.Picture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        try {
             readfile("F:\\资产维护部\\车牌号\\车辆档案电子档");
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        //System.out.println("ok");
    }


    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {

        try {
            List<Picture> pictureList = new ArrayList();
            Picture picture = new Picture();
            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println(file.getName());
               // System.out.println("path=" + file.getPath());
               // System.out.println("absolutepath=" + file.getAbsolutePath());
               // System.out.println("name=" + file.getName());
                picture = new Picture();
                picture.setName(file.getName());
                picture.setPath(file.getPath());
                pictureList.add(picture);

            } else if (file.isDirectory()) {
                //System.out.println(file.getName());
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        //System.out.println("path=" + readfile.getPath());
                        //System.out.println("absolutepath=" + readfile.getAbsolutePath());
                        System.out.println("name=" + readfile.getName());
                            picture = new Picture();
                            picture.setName(readfile.getName());
                            picture.setPath(readfile.getPath());
                            pictureList.add(picture);

                    } else if (readfile.isDirectory()) {
                         readfile(filepath + "\\" + filelist[i]);
                    }
                }
            }
            //System.out.println(pictureList.get(x).getName()+" 第"+x+"张");
                 //DBUtil.Insert(pictureList);



        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return  true;
    }
}
