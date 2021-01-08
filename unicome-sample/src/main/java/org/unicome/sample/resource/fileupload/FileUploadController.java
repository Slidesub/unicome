package org.unicome.sample.resource.fileupload;


import lombok.Data;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class FileUploadController {

    @Data
    static class B {
        String fileupload1;
    }
    // multipart
    @PostMapping("/upload")
    public String multiUpload(HttpServletRequest request, String fileupload1, B b) throws IOException {
        FileEntry fileEntry = FileEntry.builder()
                .charset("UTF-8")
                .fileSizeMax(1024L)
                .sizeMax(1024L)
                .session(request.getSession())
                .build();
        try {
            fileEntry.upload(request);
        } catch (FileUploadException e) {
            if (e instanceof FileSizeLimitExceededException) {
                return "超出总文件大小限制";
            } else if (e instanceof SizeLimitExceededException) {
                return "单个文件超出大小限制";
            }
        }

        return "success";
    }

    @GetMapping("upload/progress")
    public String progress(HttpSession session) {
        return (String) session.getAttribute("progress");
    }

    @PostMapping("/upload1")
    public String multiUpload2(@RequestParam("fileupload1") String fileupload1,
                               @RequestParam("fileupload2") MultipartFile[] files,
                               HttpServletRequest request) throws IOException {
        FileEntry fileEntry = FileEntry.builder()
                .charset("UTF-8")
                .fileSizeMax(1024L)
                .sizeMax(1024L)
                .session(request.getSession())
                .build();
        try {
            fileEntry.upload(request);
        } catch (FileUploadException e) {
            if (e instanceof FileSizeLimitExceededException) {
                return "超出总文件大小限制";
            } else if (e instanceof SizeLimitExceededException) {
                return "单个文件超出大小限制";
            }
        }

        return "success";
    }

    // json
    @PostMapping("/upload2")
    public String upload2(@RequestBody B b) {
        return b.getFileupload1();
    }

    // x-www-form-urlencoded
    @PostMapping("/upload3")
    public String upload3( B b) {
        return b.getFileupload1();
    }
}
