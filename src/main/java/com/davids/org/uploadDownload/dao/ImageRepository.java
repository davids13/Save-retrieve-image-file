package com.davids.org.uploadDownload.dao;

import com.davids.org.uploadDownload.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Integer> {
}
