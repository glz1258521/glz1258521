package com.baizhi;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class AliyunOSSTests {

    //创建存储空间
    @Test
    public void testCreateBucket(){

        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G3a3BJ9AFFvMoKjiSpE";
        String accessKeySecret = "zDqCCfOGgqBRn08pb9QUEnKzWue9HB";

        //创建存储空间
        String bucketName = "yingxs2008";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建存储空间。
        ossClient.createBucket(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    //上传文件  本地文件
    @Test
    public void testUploadFile(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G3a3BJ9AFFvMoKjiSpE";
        String accessKeySecret = "zDqCCfOGgqBRn08pb9QUEnKzWue9HB";

        String bucketName="yingxue123";  //指定上传的存储空间
        String objectName="小龙少.mp4";  //文件名
        String localFile="C:\\Users\\hp\\Desktop\\yingx_zhangcn\\草原.mp4";  //本地文件

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(localFile));

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    //上传文件  文件流
    @Test
    public void testUploadFiles() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G3a3BJ9AFFvMoKjiSpE";
        String accessKeySecret = "zDqCCfOGgqBRn08pb9QUEnKzWue9HB";

        String bucketName="yingxue123";  //指定上传的存储空间
        String objectName="啊龙少.MP4";  //文件名
        String localFile="C:\\Users\\hp\\Desktop\\yingx_zhangcn\\草原.MP4";  //本地文件

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = new FileInputStream(localFile);
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void testUploadNetFile() throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G3a3BJ9AFFvMoKjiSpE";
        String accessKeySecret = "zDqCCfOGgqBRn08pb9QUEnKzWue9HB";

        String bucketName="yingxue123";  //指定上传的存储空间
        String objectName="cover/草原.mp4";  //文件名


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传网络流。
        InputStream inputStream = new URL("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3860576747,4222045237&fm=26&gp=0.jpg").openStream();
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();}

    //下载文件到本地
    @Test
    public void testDowloadFile() throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G3a3BJ9AFFvMoKjiSpE";
        String accessKeySecret = "zDqCCfOGgqBRn08pb9QUEnKzWue9HB";

        String bucketName="yingxue123";  //指定上传的存储空间
        String objectName="龙少.jpg";  //文件名
        String localFile="C:\\Users\\hp\\Desktop\\第三阶段\\4.jpg";  //本地文件

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(localFile));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void showBucket(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G3a3BJ9AFFvMoKjiSpE";
        String accessKeySecret = "zDqCCfOGgqBRn08pb9QUEnKzWue9HB";


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void testDeleteBucket(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。Region：存储地址
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G3a3BJ9AFFvMoKjiSpE";
        String accessKeySecret = "zDqCCfOGgqBRn08pb9QUEnKzWue9HB";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除存储空间。
        ossClient.deleteBucket("hr-6");

        // 关闭OSSClient。
        ossClient.shutdown();
    }



    public static void main(String[] args) {

    }

}
