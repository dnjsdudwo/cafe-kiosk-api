package com.onandon.cafe.cafekioskapi.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class FileRepository {
    private final List<String> imageTmpRepo;
    private final List<String> imageRepo;


    public void addTmpImage(String fileName){
        imageTmpRepo.add(fileName);
    }

    public void addImage(String fileName){
        imageRepo.add(fileName);
    }

    public String getTmpImage(){
        return imageTmpRepo.get(imageTmpRepo.size()-1);
    }

    public String getImage(){
        return imageRepo.get(imageRepo.size()-1);
    }
}
