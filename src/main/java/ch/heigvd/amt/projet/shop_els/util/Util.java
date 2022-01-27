package ch.heigvd.amt.projet.shop_els.util;

import javax.servlet.http.Part;
import java.sql.Timestamp;

public class Util {
    public static final int MAXIMUM_NAME_LENGTH = 50;
    public static final int MAXIMUM_DESCRIPTION_LENGTH = 255;
    public static final int MINIMUM_QUANTITY = 0;

    /**
     * Extracts file name from HTTP header content-disposition
     */
    public static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    /**
     * Method to add a timestamp to an image name
     * @param fileName Name of the image
     * @return New name with timestamp
     */
    public static String addTimestamp(String fileName){
        String[] fileNameArray = fileName.split("\\.");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // Add timestamp to filename in case there are two same images in S3
        return fileNameArray[0] + "-" + timestamp.getTime() + "." + fileNameArray[1];
    }
}
