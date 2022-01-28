package ch.heigvd.amt.projet.shop_els.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.*;
import java.util.Objects;

public class AwsS3 {
    private AmazonS3 s3Client;
    final private String bucketName = "shopels.diduno.education";
    final private String AWSaccessKey = "/home/admin/Secret/AWS_accessKey";
    final private String AWSsecretKey = "/home/admin/Secret/AWS_secretKey";

    /**
     * Constructeur qui permet de se connecter au bucket S3
     * @throws IOException
     */
    public AwsS3() throws IOException {
        connection();
    }

    /**
     * Connexion au client S3
     * @throws IOException
     */
    private void connection() throws IOException {
        File fileUrl = new File(AWSaccessKey);
        BufferedReader br = new BufferedReader(new FileReader(fileUrl));
        String accessKey = br.readLine();

        fileUrl = new File(AWSsecretKey);
        br = new BufferedReader(new FileReader(fileUrl));
        String secretKey = br.readLine();


        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_NORTH_1)
                .build();
    }

    /**
     * Récupération du client S3
     * @return
     */
    public AmazonS3 getClient() {
        return s3Client;
    }

    /**
     * Récupération de l'URL de l'image afin d'ajouter dans la base de données
     * @param fileKey Nom du fichier
     * @return
     */
    public String getImageURL(String fileKey){
        return "https://s3." + s3Client.getRegionName() + ".amazonaws.com/" + bucketName + "/" + fileKey;
    }

    /**
     * Méthode qui permet d'upload une image dans le bucket AWS
     * @param file Image à insérer en format InputStream
     * @param key Nom de l'image à upload
     */
    public void uploadImage(InputStream file, String key) {
        if (!s3Client.doesObjectExist(bucketName, key))
            s3Client.putObject(new PutObjectRequest(bucketName, key, file, new ObjectMetadata()));
    }

    /**
     * Permet de remplacer une image qui se trouve dans le bucket. En premier temps, on la supprime puis on en
     * réupload une
     * @param newFile Image à upload en format InputStream
     * @param newKey Nom de l'image à upload
     * @param oldKey Nom de l'image à supprimer
     */
    public void updateImg(InputStream newFile, String newKey, String oldKey) {
        if(!Objects.equals(oldKey, "default.jpg")) {
            deleteImage(oldKey);
        }
        uploadImage(newFile, newKey);
    }

    /**
     * Méthode qui permet de supprimer une image sur le bucket
     * @param fileKey Nom de l'image à supprimer
     * @throws AmazonServiceException
     */
    public void deleteImage(String fileKey) throws AmazonServiceException {
        s3Client.deleteObject(bucketName, fileKey);
    }

    public S3Object getObject(String key) {
        return s3Client.getObject(bucketName, key);
    }
}


