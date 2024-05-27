package com.ImageUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    ImageDataRepository imageDataRepository;

    public void saveImage(ImageData image)
    {
        imageDataRepository.save(image);
    }

    public byte[] getImageDataById(int id) {
        // Check if image with the given ID exists in the repository
        if (imageDataRepository.existsById(id)) {
            // Retrieve image data from the repository
            ImageData image = imageDataRepository.findById(id).get();
            return image.getData();
        } else {
            return null; // Return null if image with the given ID doesn't exist
        }
    }
}
