package service;

import consts.Consts;
import exceptions.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FileService {
    protected final LoggerService loggerService;

    public FileService(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public List<String> read(String source) throws FileException {
        try {
            return Files.readAllLines(Path.of(source));
        } catch (IOException e) {
            loggerService.logException(Consts.READ_FILE_ERROR, e);
            throw new FileException(Consts.READ_FILE_ERROR, e);
        }
    }

    public void write(String target, List<String> data) throws FileException {
        try {
            Files.write(Path.of(target), data);
        } catch (IOException e) {
            loggerService.logException(Consts.WRITE_FILE_ERROR, e);
            throw new FileException(Consts.WRITE_FILE_ERROR, e);
        }
    }

    public void write(String target, CharSequence data) throws FileException {
        try {
            Files.writeString(Path.of(target), data);
        } catch (IOException e) {
            loggerService.logException(Consts.WRITE_FILE_ERROR, e);
            throw new FileException(Consts.WRITE_FILE_ERROR, e);
        }
    }

    public void deleteFile(String file) throws FileException {
        try {
            Files.delete(Path.of(file));
        } catch (IOException e) {
            loggerService.logException(Consts.DELETE_FILE_ERROR, e);
            throw new FileException(Consts.DELETE_FILE_ERROR, e);
        }
    }

    public void copyFile(String from, String to) throws FileException {
        try {
            Files.copy(Path.of(from), Path.of(to), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            loggerService.logException(Consts.COPY_FILE_ERROR, e);
            throw new FileException(Consts.COPY_FILE_ERROR, e);
        }
    }
}