package a面试.io.zip;

import json.IOUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {

    public static void main(String[] args) {
        try {

//            extract(new File("/Users/fuzhipeng/Downloads/dist200914170127.zip"),
//                    new File("/Users/fuzhipeng/Downloads/abba"));
            extract(new File("/Users/fuzhipeng/Downloads/dist200914170127.zip"),
                    new File("/Users/fuzhipeng/Downloads/abba","abba2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void extract(File source, File dest,String renameUnZipRootName) throws Exception {
        try {
            extract(new ZipInputStream(new FileInputStream(source)), dest,renameUnZipRootName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ERROR_UNZIP_FILE_NOT_FOUND", e);
        }
    }
    public static void extract(File source, File dest) throws Exception {
        extract(source,dest,null);
    }

    public static void extract(ZipInputStream zis, File root,String renameUnZipRootName) throws Exception {
        OutputStream os = null;
        try {
            boolean isSuccess = false;
            byte[] data = new byte[32768];
            ZipEntry e;
            String UnZipRootName=null;
            String renameUnZipRootNameWithSeparator = renameUnZipRootName + File.separator;
            while ((e = zis.getNextEntry()) != null) {
                String name = e.getName();
                if (UnZipRootName == null) { //第一次是解压的包名
                    UnZipRootName = name;
                }

                if (name == null || name.contains("../") || name.contains("__MACOSX")) {
                    continue;
                }

                String nameFinal = name;
                if (renameUnZipRootName != null && !"".equals(renameUnZipRootName)) {
                    if (name.contains(File.separator)) {
                        nameFinal = name.replaceFirst(UnZipRootName, renameUnZipRootNameWithSeparator);
                    } else {
                        nameFinal = name.replaceFirst(UnZipRootName, renameUnZipRootName);
                    }
                }

                if (e.isDirectory()) {
                    File test = new File(root, nameFinal);
                    if (!test.isDirectory()) {
                        test.delete();
                        if (!test.mkdirs()) {
                            isSuccess = false;
                            break;
                        }
                    }
                } else {
                    File target = new File(root, nameFinal);
                    File test = target.getParentFile();
                    if (!test.isDirectory()) {
                        test.delete();
                        if (!test.mkdirs()) {
                            isSuccess = false;
                            break;
                        }
                    }
                    os = new FileOutputStream(target);
                    int n = 0;
                    while ((n = zis.read(data, 0, data.length)) != -1) {
                        os.write(data, 0, n);
                    }
                    os.close();
                    zis.closeEntry();
                }
                if (!isSuccess) {
                    isSuccess = true;
                }
            }
            if (!isSuccess) {
                throw new Exception("ERROR_UNZIP_EMPTY:" + root.getPath());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception("ERROR_UNZIP_FILE_NOT_FOUND:" + root.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("ERROR_UNZIP_IO:" + root.getPath());
        } finally {
           closeQuietly(zis);
           closeQuietly(os);
        }
    }
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
