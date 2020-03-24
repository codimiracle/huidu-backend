package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.ReferenceData;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.service.ReferenceDataService;
import com.codimiracle.application.platform.huidu.util.CodeUtil;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@Slf4j
public class ApiReferenceDataController {
    @Resource
    private ReferenceDataService referenceDataService;

    @Value("${huidu.upload.dir}")
    private String uploadDir;

    @PostMapping(value = "/api/reference-data/upload")
    public ApiResponse uploadFile(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile uploadfile) {
        if (uploadfile.isEmpty()) {
            return RestfulUtil.fail("无法进行空上传！");
        }
        ApiResponse result;
        ReferenceData referenceData = new ReferenceData();
        referenceData.setReferenceId(CodeUtil.getReferenceId());
        referenceData.setFileOriginalName(uploadfile.getOriginalFilename());
        referenceData.setType(uploadfile.getContentType());
        referenceData.setFileSize(uploadfile.getSize());
        referenceData.setUploadedDate(new Timestamp(System.currentTimeMillis()));
        referenceData.setStatus(0);
        if (Objects.isNull(user)) {
            if (referenceData.getFileSize() > 5 * 1024 * 1024) {
                return RestfulUtil.fail("文件过大!");
            }
        } else {
            referenceData.setUserId(user.getId());
        }
        try {
            String originalName = uploadfile.getOriginalFilename();
            Assert.notNull(originalName, "需要给定文件原始名称");
            String filename = referenceData.getReferenceId() + originalName.substring(originalName.indexOf('.'));
            referenceData.setFileName(filename);
            String directory = uploadDir;
            if (Objects.isNull(directory)) {
                log.warn("doesn't specify upload dir yet, it will use default folder [./upload].");
                directory = "./upload";
            }
            File dir = Paths.get(directory).toFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String filepath = Paths.get(directory, filename).toString();
            referenceData.setFilePath(filepath);
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(uploadfile.getBytes());
            stream.close();
            referenceData.setStatus(1);
            result = RestfulUtil.success(referenceData);
        } catch (Exception e) {
            log.error("upload file failed:", e);
            referenceData.setStatus(-1);
            result = RestfulUtil.fail("上传失败，原因：" + e.getMessage());
        }
        referenceDataService.save(referenceData);
        return result;
    }

    private void fileToResponse(String filepath, HttpServletResponse response) throws IOException {
        Path file = new File(filepath).toPath();
        response.setContentType(Files.probeContentType(file));
        Files.copy(file, response.getOutputStream());
    }

    private void referenceDataToResponse(ReferenceData referenceData, HttpServletResponse response) throws IOException {
        Path path = Paths.get(referenceData.getFilePath());
        response.setContentType(referenceData.getType());
        response.addHeader("Content-Disposition", "attachment; filename=" + referenceData.getFileName());
        Files.copy(path, response.getOutputStream());
        response.getOutputStream().flush();
    }

    private void avatarNotFound(HttpServletResponse response) throws IOException {
        fileToResponse("./assets/avatar-not-found.png", response);
    }

    private void coverNotFound(HttpServletResponse response) throws IOException {
        fileToResponse("./assets/empty.png", response);
    }

    private void downloadReferenceDataOrNotFound(HttpServletResponse response, String referenceId, Supplier<? extends Throwable> notFound) throws Throwable {
        ReferenceData referenceData = referenceDataService.findById(referenceId);
        if (Objects.nonNull(referenceData) && Objects.equals(Objects.toString(referenceData.getStatus()), "1")) {
            Path file = Paths.get(referenceData.getFilePath());
            if (Files.exists(file)) {
                referenceDataToResponse(referenceData, response);
                return;
            }
        }
        Throwable throwable = notFound.get();
        if (throwable != null) {
            throw throwable;
        }
    }

    @GetMapping("/api/reference-data/source/{reference_id}")
    public void downloadReferenceData(HttpServletResponse response, @PathVariable("reference_id") String referenceId) throws Throwable {
        downloadReferenceDataOrNotFound(response, referenceId, () -> {
            try {
                response.sendError(404, "Resource not found.");
            } catch (IOException e) {
                return e;
            }
            return null;
        });
    }

    @GetMapping("/api/user/avatar/{reference_id}")
    public void downloadAvatar(HttpServletResponse response, @PathVariable("reference_id") String referenceId) throws Throwable {
        downloadReferenceDataOrNotFound(response, referenceId, () -> {
            try {
                avatarNotFound(response);
            } catch (IOException e) {
                return e;
            }
            return null;
        });
    }

    @GetMapping("/api/books/cover/{reference_id}")
    public void downloadBookCover(HttpServletResponse response, @PathVariable("reference_id") String referenceId) throws Throwable {
        downloadReferenceDataOrNotFound(response, referenceId, () -> {
            try {
                coverNotFound(response);
            } catch (IOException e) {
                return e;
            }
            return null;
        });
    }

    @DeleteMapping("/api/reference-data/{id}")
    public ApiResponse delete(@PathVariable String id) {
        referenceDataService.deleteById(id);
        return RestfulUtil.success();
    }

    @GetMapping("/api/reference-data/{id}")
    public ApiResponse entity(@PathVariable String id) {
        ReferenceData referenceData = referenceDataService.findById(id);
        return RestfulUtil.success(referenceData);
    }

    @GetMapping("/api/reference-data")
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<ReferenceData> slice = referenceDataService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
