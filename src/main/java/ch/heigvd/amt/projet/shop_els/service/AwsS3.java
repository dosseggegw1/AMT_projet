package ch.heigvd.amt.projet.shop_els.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

public class AwsS3 {
    private AmazonS3 s3client;
    private String bucketName = "shopels.diduno.education";
    private String AWSaccessKey = "/home/admin/Secret/AWS_accessKey";
    private String AWSsecretKey = "/home/admin/Secret/AWS_secretKey";

    public void connection() throws IOException {
        //read the url file
        File fileUrl = new File(AWSaccessKey);
        BufferedReader br = new BufferedReader(new FileReader(fileUrl));
        String accessKey = br.readLine();

        fileUrl = new File(AWSsecretKey);
        br = new BufferedReader(new FileReader(fileUrl));
        String secretKey = br.readLine();


        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_NORTH_1)
                .build();
    }

    public AmazonS3 getClient() {
        return s3client;
    }

    public void uploadImage(String key, File file) {
        s3client.putObject("shopels.diduno.education",
                key,
               file
        );
        System.out.println("hi");
    }


    //J'ai try qqch mais j'avais pas fini haha
    public byte[] downloadImage(String name) throws IOException {
        byte[] image = null;

        S3Object s3object = s3client.getObject(bucketName, "agnes-1642802939281.jpeg"); //TODO add img repertory
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        image = IOUtils.toByteArray(inputStream);
        s3object.close();

        return image;
    }
}


