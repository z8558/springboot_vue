package com.cn.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.cn.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";

    //上传接口
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();           //文件名称
        String uuid = UUID.randomUUID().toString();                     //定义唯一标识
        String root = System.getProperty("user.dir")+"/springboot/src/main/resources/files/"+uuid + "_" +originalFilename;//files文件夹路径 + uuid + "_" + 文件名称
        FileUtil.writeBytes(file.getBytes(),root);      //使用字节流,输出到files文件夹
        return Result.success(ip+":"+port+"/api/files/"+uuid+"_"+originalFilename);        //返回文件url
    }

    //富文本文件 上传接口
    @PostMapping("/eaitor/upload")
    public JSON editorUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();           //文件名称
        String uuid = UUID.randomUUID().toString();                     //定义唯一标识
        String root = System.getProperty("user.dir")+"/springboot/src/main/resources/files/"+uuid + "_" +originalFilename;//files文件夹路径 + uuid + "_" + 文件名称
        FileUtil.writeBytes(file.getBytes(),root);      //使用字节流,输出到files文件夹

        String url = ip+":"+port+"/api/files/"+uuid+"_"+originalFilename;
        JSONObject json = new JSONObject();
        json.set("errno",0);
        JSONArray arr = new JSONArray();
        JSONObject data = new JSONObject();
        data.set("url",url);
        arr.add(data);
        json.set("data",arr);
        return json;
    }


    //下载接口
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;        //新建一个输出流对象
        String basePath = System.getProperty("user.dir")+"/springboot/src/main/resources/files/";       //定义文件上传的根路径
        List<String> fileNames = FileUtil.listFileNames(basePath);//获取所有文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");    //找到参数一致的文件
        try{
            if (StrUtil.isNotEmpty(fileName)){
                response.addHeader("Content-Disposition","attachment;filename" + URLEncoder.encode(fileName,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);   //通过文件的路径读取文件字节流
                os = response.getOutputStream();                        //通过输出流返回结果
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e){
            System.out.println("文件下载失败");
        }
    }
}
