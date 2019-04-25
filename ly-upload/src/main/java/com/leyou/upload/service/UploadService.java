package com.leyou.upload.service;

import com.leyou.common.exception.LyException;
import com.leyou.upload.controller.UploadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author qin
 * @create 2019-04-24 10:03
 */
@Service
public class UploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    //支持的文件类型
    private static final List<String> suffixes = Arrays.asList("image/png","image/jpeg");

    public String upload(MultipartFile file) {

        try {


            String type = file.getContentType();
            //1、图片信息校验
            //1)校验文件类型
            if (!suffixes.contains(type)) {
                LOGGER.info("上传失败文件类型不匹配{}", type);
                throw new LyException(400, "文件类型不匹配");
            }
            //2)校验图片内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image == null){
                LOGGER.info("上传失败，文件内容不符合要求");
                throw new LyException(400,"文件已损坏");
            }
            //2、保存图片
            //2.1、生成保存目录,保存到nginx所在目录的html下，这样可以直接通过nginx来访问到
            File dir = new File("E:\\soft\\nginx-1.12.2-leyou\\html\\");
            if(!dir.exists()){
                dir.mkdirs();
            }
            //2.2、保存图片
            file.transferTo(new File(dir,file.getOriginalFilename()));
            //2.3、拼接图片地址
            String url = "http://image.leyou.com/"+file.getOriginalFilename();
            return url;
        }catch (Exception e){
            throw new LyException(500,"文件上传异常");
        }
    }
}
