package com.ImageUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    ImageService imageService;

    @RequestMapping("/upload")
    public String uploadForm() {
        return "upload";
    }


    @PostMapping("/upload")
    public ResponseEntity <String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            ImageData image = new ImageData();
            image.setData(file.getBytes());

            imageService.saveImage(image);

            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading image");
        }
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) {
        // Retrieve image data from the service
        byte[] imageData = imageService.getImageDataById(id);

        if (imageData != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Assuming JPEG format, adjust accordingly

            // Respond with the image data and appropriate headers
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            // If image data is not found, respond with a 404 Not Found status
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
