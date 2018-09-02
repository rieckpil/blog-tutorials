package de.rieckpil.blog.springbootuploadinganddownloadingfileswithreact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {
}
