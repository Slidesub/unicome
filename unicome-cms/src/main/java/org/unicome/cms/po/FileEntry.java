package org.unicome.cms.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;

@Data
@Document(collection = "file_entry")
public class FileEntry extends Base {
    @Id
    private String id;
    private String name;
    private String url;
    private String path;
    private String type;
    private Long size;
    private String version;

    public FileEntry() {

    }

    @PersistenceConstructor
    public FileEntry(String id, String name, String url, String path, String type, Long size, String version) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.path = path;
        this.type = type;
        this.size = size;
        this.version = version;
    }
}
