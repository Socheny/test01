package ReadFile;

import DB.DBUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
public class test01 {


    public static void main(String[] args) {

        String path = "F:\\资产维护部\\车架号\\合格证（车架号后6位命名）";

        List<File> files = traverseFolder1(path);

       // DBUtil.Insert(files);

    }


    public static List<File> traverseFolder1(String path) {
        List<File> fileList = new ArrayList<>();
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                   // System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                    folderNum++;
                } else {
                    fileList.add(file2);
                    System.out.println("文件:" + file2.getAbsolutePath());
                    fileNum++;
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                       // System.out.println("文件夹:" + file2.getAbsolutePath());
                        list.add(file2);
                        folderNum++;
                    } else {
                        fileList.add(file2);
                        System.out.println("文件:" + file2.getAbsolutePath());
                       // System.out.println("名称"+file2.getName().substring(file2.getName().lastIndexOf(".")+1));
                        //System.out.println(file2.getName().substring(file2.getName().indexOf("."), file2.getName().length()));
                        fileNum++;
                    }
                    if (fileList.size()>5000){
                        System.out.println("xxxxxxxxxx");
                        DBUtil.Insert(fileList);
                        fileList = new ArrayList<>();

                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }

        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);
        return fileList;
    }
}
