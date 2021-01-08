package org.unicome.sample.resource.fileupload;

import org.apache.tomcat.util.http.fileupload.ProgressListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.text.NumberFormat;

public class UploadProgressListener implements ProgressListener {

    private Logger logger = LoggerFactory.getLogger(UploadProgressListener.class);
    private HttpSession session;
    private long startTimeMillis = System.currentTimeMillis();

    public UploadProgressListener(HttpSession session) {
        this.session = session;
    }
    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        logger.info("使用时间: " + (startTimeMillis - System.currentTimeMillis()));
        logger.info("总大小: " + pContentLength);
        logger.info("当前文件: " + pItems);
        if (-1 == pContentLength) {
//            return;
        }
        if (0 == pItems) {
//            return;
        }
        Long kBytesRead = pBytesRead / 1024;
        Long kContentLength = pContentLength / 1024;
        // 进度百分比
        double read = (double)kBytesRead / (double)kContentLength;
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        session.setAttribute("progress", numberFormat.format(read));
    }
}
