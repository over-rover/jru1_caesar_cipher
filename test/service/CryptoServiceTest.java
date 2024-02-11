package service;

import model.CryptoModel;

public class CryptoServiceTest {
    CryptoModel cryptoModel = new CryptoModel();
    CryptoService cryptoService = new CryptoService(new FileService());

    @org.junit.Before
    public void setUp() {


    }

    @org.junit.Test
    public void encryptAssertEquals() {
        cryptoModel.setSourcePath("no matter");
        cryptoModel.setTargetPath("no matter");
        cryptoModel.setKey(0);
        cryptoService.crypt(cryptoModel);

    }
}