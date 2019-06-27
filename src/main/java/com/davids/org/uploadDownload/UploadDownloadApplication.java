package com.davids.org.uploadDownload;

import com.davids.org.uploadDownload.dao.ImageRepository;
import com.davids.org.uploadDownload.model.ImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class UploadDownloadApplication implements CommandLineRunner {

	@Autowired
	ImageRepository imageRepository;

	public static void main(String[] args) {
		SpringApplication.run(UploadDownloadApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//first we get the image path
		//then we get the image and put it in a array of bytes
		//then put the array of bytes into a stream collection
		//We create a new instance of ImageModel and pass the last arg as stream
		ClassPathResource backImage = new ClassPathResource("image/pubg.jpeg");
		byte[] arrayPic = new byte[(int) backImage.contentLength()];
		backImage.getInputStream().read(arrayPic);
		ImageModel imgModel = new ImageModel(1, "pubg", "jpeg", arrayPic);

		//save img to mysql via jpa
		imageRepository.save(imgModel);

		//retrieve img from mysql via jpa
		for(ImageModel imageModel : imageRepository.findAll()) {
			Files.write(Paths.get("src/main/retrieve-dir/" + imageModel.getName() + "." + imageModel.getType()), imageModel.getPic());
		}
	}

}