package de.rieckpil.blog.lazyloadingofjpaattributeswithhibernate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
}
