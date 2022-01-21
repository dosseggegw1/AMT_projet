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

import java.io.File;
import java.util.List;

public class AwsS3 {
    private AmazonS3 s3client;

    public void connection() {
        AWSCredentials credentials = new BasicAWSCredentials("null", "null");
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
}


