package com.ImageUpload;



import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private byte[] data;




}
