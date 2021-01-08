package org.unicome.sample.resource.fileupload;

import lombok.Data;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.Map;

@Data
public class FileEntry {
    public static Logger logger = LoggerFactory.getLogger(FileEntry.class);

    private ServletFileUpload servletFileUpload;
    private Map<String, Object> fields;

    private String filePath; // 文件路径
    private String fileName; // 文件明
    private List<String> contentTypes; // 上传文件类型
    private Long fileSizeMax; // 所有文件的总大小, 默认-1不设置
    private Long sizeMax; // 单个文件的大小, 默认-1不设置

    public static Builder builder() {
        return new Builder();
    }

    public void upload(HttpServletRequest request) throws FileUploadException, IOException {
        List<FileItem> fileItemList = this.servletFileUpload.parseRequest(new ServletRequestContext(request));
        for(FileItem fileItem : fileItemList) {
            if (!fileItem.isFormField()) {
                String name = fileItem.getName();
                String fieldName = fileItem.getFieldName();
                String value = fileItem.getString();
                logger.info("!field: " + fieldName + ":" + fieldName + ":" + value);
                try(
                        OutputStream outputStream = new FileOutputStream(new File(this.filePath, this.fileName));
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
                String fieldName = fileItem.getFieldName();
                String value = fileItem.getString();
                logger.info("field: " + fieldName + ":" + value);
            }
        }
    }

    static class Builder {
        private String charset = "UTF-8"; // 文件编码
        private Integer sizeThreshold; // 默认10k, 超过10k，使用磁盘缓存
        private HttpSession session; // 存储上传过程信息

        private String filePath;
        private String fileName;
        private String contentType;
        private Long fileSizeMax;
        private Long sizeMax;

        private ServletFileUpload servletFileUpload;

        public FileEntry build() {
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            diskFileItemFactory.setDefaultCharset(this.charset);
            if (null != sizeThreshold) {
                diskFileItemFactory.setSizeThreshold(sizeThreshold);
            }

            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            if (null != this.fileSizeMax) {
                servletFileUpload.setFileSizeMax(this.fileSizeMax);
            }
            if (null != this.sizeMax) {
                servletFileUpload.setSizeMax(this.sizeMax);
            }

            if (null != session) {
                UploadProgressListener uploadProgressListener = new UploadProgressListener(session);
                servletFileUpload.setProgressListener(uploadProgressListener);
            }

            FileEntry fileEntry = new FileEntry();
            fileEntry.setServletFileUpload(servletFileUpload);
            fileEntry.setFileSizeMax(this.fileSizeMax);
            fileEntry.setSizeMax(this.sizeMax);
            fileEntry.setFilePath(this.filePath);
            fileEntry.setFileName(this.fileName);
            return fileEntry;
        }
        public Builder charset(String charset) {
            this.charset = charset;
            return this;
        }
        public Builder fileSizeMax(long fileSizeMax) {
            this.fileSizeMax = fileSizeMax;
            return this;
        }
        public Builder sizeMax(long sizeMax) {
            this.sizeMax = sizeMax;
            return this;
        }
        public Builder session(HttpSession session) {
            this.session = session;
            return this;
        }
        public Builder sizeThreshold(Integer sizeThreshold) {
            this.sizeThreshold = sizeThreshold;
            return this;
        }
        public Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }
        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }
        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }
    }
}
