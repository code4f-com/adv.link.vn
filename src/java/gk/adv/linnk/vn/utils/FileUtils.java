/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author PLATUAN
 */
public class FileUtils {

    /**
     * Write file not is Images
     *
     * @param arrbyte
     * @param full_path
     * @return
     */
    public static boolean writeNomalFile(byte[] arrbyte, String full_path) {
        boolean flag = false;
        FileOutputStream fsave = null;
        try {
            File f = new File(full_path);
            if (!f.exists()) {
                f.createNewFile();
            }
            fsave = new FileOutputStream(f);
            fsave.write(arrbyte);
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fsave.flush();
                fsave.close();
            } catch (IOException ex) {
                System.out.println("Loi dong Ouput Stream");
            }
        }
        return flag;
    }

    /**
     *
     * @param bis
     * @return
     */
    public static byte[] writeBuffer2Byte(BufferedInputStream bis) {
        byte[] byteReturn = null;
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            while (true) {
                int iBytes = bis.read(buffer);
                ////System.out.println(iBytes);
                // If there was nothing read, get out of loop
                if (iBytes == -1) {
                    break;
                }
                baos.write(buffer, 0, iBytes);
            }
            byteReturn = baos.toByteArray();
        } catch (IOException ie) {
            ie.printStackTrace();
        } finally {
            try {
                baos.flush();
                baos.close();
                bis.close();
            } catch (Exception e) {
            }
        }
        return byteReturn;
    }

    public static boolean writeContent(BufferedInputStream bis, ByteArrayOutputStream baos) {
        boolean flag = true;
        byte[] buffer = new byte[1024];
        try {
            while (true) {
                int iBytes = bis.read(buffer);
                // If there was nothing read, get out of loop
                if (iBytes == -1) {
                    break;
                }
                baos.write(buffer, 0, iBytes);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
            flag = false;
        } finally {
            try {
                baos.flush();
                baos.close();
                bis.close();
            } catch (Exception e) {
            }
        }
        return flag;
    }

    public static String getImgExtendTion(String ctType, String imageURL) {
        String ext = "jpg";
        if (!Tool.checkNull(ctType)) {
            if (ctType.equalsIgnoreCase("image/png")) {
                ext = "png";
            }
            if (ctType.equalsIgnoreCase("image/gif")) {
                ext = "gif";
            }
        } else {
            int index = imageURL.lastIndexOf(".");
            if (index > 0) {
                ext = imageURL.substring(index + 1);
            }
        }
        return ext;
    }

    public static byte[] getBytesFromFile(File file) throws IOException, FileNotFoundException {
        byte[] bytes;
        try (InputStream fin = new FileInputStream(file)) {
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                throw new IOException("File is too large" + file.getName());
                // File is too large
            }
            bytes = new byte[(int) length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = fin.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
        }
        return bytes;
    }

    /**
     * Use thumbnailator-0.4.7.jar
     *
     * @param ipst
     * @param max_width
     * @param realPath
     * @param extention
     */
    public static void resizeMaxWithWriteImg(InputStream ipst, int max_width, String realPath, String extention) {
        try {
            BufferedImage originalImage = ImageIO.read(ipst);
            //----------------
            if (originalImage.getWidth() > max_width) {
                // resize va write
                Thumbnails.of(originalImage)
                        .width(max_width)
                        .outputFormat(extention)
                        .outputQuality(1)
                        .toFile(new File(realPath));
            } else {
                Thumbnails.of(originalImage)
                        .width(originalImage.getWidth())
                        .outputFormat(extention)
                        .outputQuality(1)
                        .toFile(new File(realPath));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resizeMaxWithWriteImg(URL url, int max_width, String realPath, String extention) {
        try {
            BufferedImage originalImage = ImageIO.read(url.openStream());
            //----------------
            if (originalImage.getWidth() > max_width) {
                // resize va write
                Thumbnails.of(url)
                        .width(max_width)
                        .outputQuality(1)
                        // .outputFormat(extention)
                        .toFile(new File(realPath));
            } else {
                Thumbnails.of(url)
                        .width(originalImage.getWidth())
                        .outputFormat(extention)
                        .outputQuality(1)
                        .toFile(new File(realPath));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean writeImg(InputStream ipst, String realPath, String extention) {
        try {
            BufferedImage originalImage = ImageIO.read(ipst);
            Thumbnails.of(originalImage)
                    .width(originalImage.getWidth())
                    .outputFormat(extention)
                    .outputQuality(1)
                    .toFile(new File(realPath));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void writeImg(URL url, String realPath, String extention) {
        try {
            BufferedImage originalImage = ImageIO.read(url.openStream());
            Thumbnails.of(url)
                    .width(originalImage.getWidth())
                    .outputQuality(1)
                    .toFile(new File(realPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resizeWriteImg(InputStream ips, int width, String realPath, String extention) {
        try {
            // resize va write
            Thumbnails.of(ips)
                    .width(width)
                    .outputFormat(extention)
                    .outputQuality(1)
                    .toFile(new File(realPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resizeWriteImg(URL url, int width, String realPath, String extention) {
        try {
            //----------------
            // resize va write
            Thumbnails.of(url)
                    .width(width)
                    // .outputFormat(extention)
                    .outputQuality(1)
                    .toFile(new File(realPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
