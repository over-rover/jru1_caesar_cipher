package service;

import consts.Consts;
import exceptions.FileException;
import model.CryptoModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileService {
    public List<String> read(CryptoModel cryptoModel) throws FileException {
        Path source = Path.of(cryptoModel.getSource());
        try {
            return Files.readAllLines(source);
        } catch (IOException e) {
            throw new FileException(Consts.READ_FILE_ERROR, e.getCause());
        }
    }

    public void write(CryptoModel cryptoModel, List<String> data) throws FileException {
        try {
            Files.write(Path.of(cryptoModel.getTarget()), data);
        } catch (IOException e) {
            throw new FileException(Consts.WRITE_FILE_ERROR, e.getCause());
        }
    }

    public void deleteFile(CryptoModel cryptoModel) {
        try {
            Files.delete(Path.of(cryptoModel.getSource()));
        } catch (IOException e) {
            throw new FileException(Consts.DELETE_FILE_ERROR, e.getCause());
        }
    }
}