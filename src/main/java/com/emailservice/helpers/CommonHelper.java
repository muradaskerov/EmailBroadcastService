package com.emailservice.helpers;

import com.emailservice.controllers.MailController;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by murad on 10/15/16.
 */
public class CommonHelper {

    private static final Logger logger = LoggerFactory.getLogger(CommonHelper.class);

    public static String getCurrentDate(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();

        return dateFormat.format(date);
    }
    public static String getDateInterval(String format,Integer interval){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,interval);
        DateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(calendar.getTime());
    }

    public static boolean uploadFile(MultipartFile file, String uploadPath,String filename){
        boolean result = false;
        if(file != null){
            logger.info("filename: {}",filename);
            try {
                logger.info("uploadpath: {}",uploadPath);
                try {
                    InputStream inputStream = file.getInputStream();

                    File newFile = new File(uploadPath + filename);
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    OutputStream outputStream = new FileOutputStream(newFile);
                    int read = 0;
                    byte[] bytes = new byte[1024];

                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                    result = true;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    logger.error("Error: {}",e.getMessage());
                }

            }catch (Exception ioe){
                logger.error("Error: {}",ioe.getMessage());
            }

        }else{
            logger.info("file not found");
        }

        return result;
    }

    public static List<String> readFromExcel(String filename){
        ArrayList<String> mailList = new ArrayList<String>();
        try
        {
            FileInputStream file = new FileInputStream(new File(filename));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING){
                        mailList.add(cell.getStringCellValue());
                        logger.debug(cell.getStringCellValue() + "t");
                    }
                }
                logger.debug("");
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return mailList;
    }

}
