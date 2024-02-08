import model.CryptoModel;
import service.CryptoService;
import service.FileService;

public class MainCrypt {
    public static void main(String[] args) {
        CryptoModel cryptoModel = new CryptoModel();
        CryptoService cryptoService = new CryptoService(new FileService());

        int key = 1;

        //Encrypt
        cryptoModel.setSourcePath("resources/encrypt/source.txt");
        cryptoModel.setTargetPath("resources/decrypt/encrypted.txt");
        cryptoModel.setKey(key);
        cryptoService.crypt(cryptoModel);

        //Decrypt
        cryptoModel.setSourcePath("resources/decrypt/encrypted.txt");
        cryptoModel.setTargetPath("resources/decrypt/decrypted.txt");
        cryptoModel.setKey(-key);
        cryptoService.crypt(cryptoModel);
    }
}
