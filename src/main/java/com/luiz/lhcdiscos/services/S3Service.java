package com.luiz.lhcdiscos.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.base.Strings;
import com.luiz.lhcdiscos.models.entities.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class S3Service {

    private Logger log = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    @Value("${s3.region}")
    private String region;


    private String salva(MultipartFile file) {
        try {
            String nomeDoArquivo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss")) + "_" + file.getOriginalFilename();
            s3client.putObject(new PutObjectRequest(bucketName,
                    nomeDoArquivo,
                    file.getInputStream(),
                    null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return "https://" + bucketName + ".s3-" + region + ".amazonaws.com/" + nomeDoArquivo;

        } catch (IllegalStateException | IOException | AmazonClientException e) {
            log.info(e.getClass() + ": " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void deleta(String fileKey) {
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

    public void deletaCapa(String capa) {
        if (capa.contains("amazonaws.com")) {
            String[] url = capa.split("/");
            String fileKey = url[url.length-1];
            deleta(fileKey);
        }
    }

    public void salvaCapa(Produto produto, MultipartFile capa) {
        if (!Strings.isNullOrEmpty(produto.getCapa().trim()) && !Strings.isNullOrEmpty(capa.getOriginalFilename())) {
            deletaCapa(produto.getCapa());
            String path = salva(capa);
            produto.setCapa(path);
        } else if (!Strings.isNullOrEmpty(capa.getOriginalFilename())) {
            String path = salva(capa);
            produto.setCapa(path);
        } else {
            throw new IllegalArgumentException("Não foi possível salvar no S3, argumentos inválidos");
        }
    }

}
