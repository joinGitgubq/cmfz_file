package com.baizhi.conllection;

import com.baizhi.pojo.Banner;
import com.baizhi.service.BannerService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.UUID;

    @RestController
    public class FileUploadController {

        @Autowired
        BannerService bannerService;

        /**
         * 实现文件上传
         */
        @RequestMapping("fileUpload")
        @ResponseBody
        public String fileUpload(MultipartFile multipartFile, HttpSession session) throws IOException {
            //获取上传文件的原始文件名
            String originalFilename = multipartFile.getOriginalFilename();
            String id = UUID.randomUUID().toString();
            //生成要保存的文件名
            originalFilename = id + "UUID" + originalFilename;
            //文件要上传的位置
            String filePath = session.getServletContext().getRealPath("/upFile");
            //把文件上传到服务器上

            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
            multipartFile.transferTo(new File(filePath + "/" + originalFilename));
            Banner banner = new Banner();
            banner.setTitle(originalFilename);
            banner.setImgPath("/upFile/" + originalFilename);
            int i = bannerService.insertSelective(banner);
            String s = null;
            if (i == 1) {
                s = "上传成功";
            } else {
                s = "上传失败";
            }
            return s;
        }

        /**
         * 实现文件下载
         */
        @RequestMapping("download")
        @ResponseBody
        public void downLoad(String path, HttpServletResponse response, HttpSession session) throws IOException {
            //获取要下载的文件资源的路径
            String realPath = session.getServletContext().getRealPath(path);
            System.out.println(path);
            System.out.println("realPath" + realPath);
            InputStream in = new FileInputStream(realPath);
            ServletOutputStream outputStream = response.getOutputStream();
            String extension = FilenameUtils.getExtension(path);
            //获取下载文件的默认文件名
            String[] uuIds = path.split("UUID");
            for (String uuId : uuIds) {
                System.out.println(uuId);
            }
            path = uuIds[1];
            //设置下载文件的类型
            response.setHeader("content-type", extension);
            //设置下载文件的方式
            response.setHeader("content-disposition", "attachment;filename=" + path);
            IOUtils.copy(in, outputStream);
        }
    }
