package org.izy1sky.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.izy1sky.springboot.pojo.Result;
import org.izy1sky.springboot.utils.OSSUtil;

import java.io.IOException;
import java.util.UUID;

@RequestMapping("/upload")
@RestController
public class FileUploadController {
    @PostMapping
    public Result<String> fileUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String imgUrl = OSSUtil.uploadFile(filename, file.getInputStream());
        return Result.success(imgUrl);
    }
}
