package org.unicome.sample.resource.fileupload;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

public class FileUploadUtils {

    public static Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);
    public final static String FILE_PATH = ".";

    public String combineFileName(String ori) {
        return "";
    }

    public static void upload(HttpServletRequest request) throws FileUploadException, IOException {
        FileEntry fileEntry = FileEntry.builder().charset("UTF-8").session(request.getSession()).build();
        List<FileItem> fileItemList = fileEntry.getServletFileUpload().parseRequest(new ServletRequestContext(request));

//        DiskFileItemFactory diskFileItemFactory
//                = new DiskFileItemFactory();
//        diskFileItemFactory.setDefaultCharset("UTF-8");

//        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
//        servletFileUpload.setFileSizeMax(100 * 1024 * 1024); // 所有文件的总大小
//        servletFileUpload.setSizeMax(10 * 1024 * 1024); // 单个文件的总大小
//        // 监听进度
//        UploadProgressListener listener = new UploadProgressListener(request.getSession());
//        servletFileUpload.setProgressListener(listener);

//        List<FileItem> fileItemList = servletFileUpload.parseRequest(new ServletRequestContext(request));
        for(FileItem fileItem : fileItemList) {
            if (!fileItem.isFormField()) {
                String fileName = fileItem.getName();
                String fieldName = fileItem.getFieldName();
                logger.info(fileName + "|" + fieldName);
                try(
                        OutputStream outputStream = new FileOutputStream(new File(FILE_PATH, fileName));
                        InputStream inputStream = fileItem.getInputStream();
                ) {
                    int length = 0;
                    byte[] buffer = new byte[1024 * 1024];
                    while((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer);
                    }
                } catch (IOException e) {
                    throw e;
                } finally {
                    // 删除临时文件
                    fileItem.delete();
                }
            } else {
                String name = fileItem.getFieldName();
                String value = fileItem.getString();
                logger.info(name + "|" + value);
            }
        }
    }
}
