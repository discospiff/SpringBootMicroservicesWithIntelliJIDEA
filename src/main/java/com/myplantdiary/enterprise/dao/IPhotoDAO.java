package com.myplantdiary.enterprise.dao;

import com.myplantdiary.enterprise.dto.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IPhotoDAO {
    void save(Photo photo);

    void saveImage(MultipartFile imageFile, Photo photo) throws IOException;
}
