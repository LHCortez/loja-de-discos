package com.luiz.lhcdiscos.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class S3Service {

    private Logger log = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    @Value("${s3.region}")
    private String region;


    public String write(MultipartFile file) {
        try {
            s3client.putObject(new PutObjectRequest(bucketName,
                    file.getOriginalFilename(),
                    file.getInputStream(),
                    null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return "http://" + bucketName + ".s3-" + region + ".amazonaws.com/" + file.getOriginalFilename();

        } catch (IllegalStateException | IOException | AmazonClientException e) {
            log.info(e.getClass() + ": " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(String fileKey) {
        try {
            s3client.deleteObject(new DeleteObjectRequest(bucketName, fileKey));

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }


    }

}
