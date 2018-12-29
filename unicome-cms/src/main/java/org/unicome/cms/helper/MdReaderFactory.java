package org.unicome.cms.helper;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

interface ReaderFactory {

}

@Slf4j
public class MdReaderFactory implements ReaderFactory {
    public String read() {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(""), StandardCharsets.UTF_8)) {
            stream.forEach(line -> builder.append(line));
        } catch (IOException e) {
            log.error("Read markdown file failed!", e);
        }
        return builder.toString();
    }
}
