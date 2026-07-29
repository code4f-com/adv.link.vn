/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.sync;

import gk.adv.linnk.vn.utils.Tool;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author centurion
 */
public class FTPImage extends Thread {

    private static final String IP_SERVER = "127.0.0.1";
    private static final String USER = "img.link.vn";
    private static final String PASS = "img.link.vn1217389";
    private static final String SCAN_DIR = "C:\\Users\\TUANPLA\\Downloads";

    public static void main(String[] args) {
//        try {
//            FTPuploadFile("C:/Users/TUANPLA/Downloads/El8WUP21Cytect3uB0D3FyRMXrHAD64y-zsKWx4PTLM.jpg");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(ALIAS_PATH);
        new FTPImage().start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                File scanPath = new File(SCAN_DIR);
                if (scanPath.isDirectory()) {
                    ProcessDir(scanPath);
                } else {
                    System.out.println("Directory is not Exist");
                }
                System.out.println("-----------------------");
                sleep(1 * 60 * 1000);
            } catch (Exception e) {
            }
        }
    }

    private static String getAliasPath(String path) {
        String alias = "/";
        System.out.println("Path:" + path);
        if (!Tool.checkNull(path)) {
            if (path.startsWith(SCAN_DIR)) {
                alias = path.substring(SCAN_DIR.length());
                System.out.println("Sub Alias:" + alias);
                if (!Tool.checkNull(alias)) {
                    int endIndex = alias.lastIndexOf("\\");
                    if (endIndex > 0) {
                        alias = alias.substring(0, endIndex);
//                        System.out.println("Alias: " + alias);
                    } else {
                        alias = "/";
                        // LA ROOT THI TREN SEVER CUNG LA ROOT ROI
                    }
                }
            } else {
                System.out.println("NOT START SCAN_DIR");
            }
        } else {
            System.out.println("getAliasPath NULL PATH");
        }
        return alias;
    }

    private static void ProcessDir(File f) {
        try {
            File[] arrFile = f.listFiles();
            for (File one : arrFile) {
                if (one.isDirectory()) {
                    ProcessDir(one);
                } else {
                    FTPuploadFile(one.getPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void FTPuploadFile(String path) throws IOException {
        FTPClient svFTP = null;
        FileInputStream oneFis = null;
        try {
            String aliasPath = getAliasPath(path);
            //ket noi server
            svFTP = new FTPClient();
            svFTP.connect(IP_SERVER);
            boolean login = svFTP.login(USER, PASS);
            if (login) {
//                System.out.println("Dang nhap Thanh Cong..." + svFTP.printWorkingDirectory());
                if (!svFTP.changeWorkingDirectory(aliasPath)) {
                    svFTP.makeDirectory(aliasPath);
                    svFTP.changeWorkingDirectory(aliasPath);
                }
                // List File Client
                try {
                    File upFile = new File(path);
                    if (upFile.isFile()) {
                        oneFis = new FileInputStream(upFile);
                        svFTP.setFileType(FTPClient.IMAGE_FILE_TYPE);
                        FTPFile[] svFile = svFTP.listFiles(aliasPath);
                        for (FTPFile oneSv : svFile) {
                            String svFname = oneSv.getName();
                            long svFSize = oneSv.getSize();
                            Calendar time = oneSv.getTimestamp();
                            Timestamp aaa = new Timestamp(time.getTimeInMillis());
                            System.out.println("-------------");
                            System.out.println("Name: " + svFname);
                            System.out.println("svFSize: " + svFSize);
                            System.out.println("time: " + aaa);
                            System.out.println("-- Client");
                            System.out.println("Client Name: " + upFile.getName());
                            System.out.println("Client size: " + upFile.length());
                            System.out.println("Client Name: " + new Timestamp(upFile.lastModified()));
                        }
                        if (svFTP.storeFile(upFile.getName(), oneFis)) {
//                            System.out.println("Send File Completed!");
                        } else {
                            System.out.println("Deck Day len duoc");
                        }
                        // Dong FileInputStream
                        oneFis.close();
                    } else {
                        System.out.println("Khong Phai La File dau Check Lai Code de^");
                    }
                } catch (Exception ex) {
                    if (oneFis != null) {
                        oneFis.close();
                    }
                    ex.printStackTrace();
                }
            } else {
                System.out.println("ket noi server ko thanh cong:");
                System.out.println("ip: " + IP_SERVER + " --user: " + USER + " --pass: " + PASS);
            }
            sleep(30*1000);
        } catch (Exception e) {
            System.out.println("==>Loi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (svFTP != null) {
                svFTP.logout();
                svFTP.disconnect();
            }
        }
    }
}
