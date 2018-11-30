package org.unicome.cms.singleton;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.unicome.cms.constant.Constant;
import sun.misc.Unsafe;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Base64;
import java.util.Hashtable;

/**
 * @author Gerald.Wang
 * @date 2018-11-29
 */

public class QrCodeSingleton implements Serializable {
    public static final int[] QR_CODE_IMAGE_DIMENS = {300, 300};
    public static final String QR_CODE_IMAGE_TYPE = "png";
    public static final int COLOR_BLACK = 0xFF000000;
    public static final int COLOR_WHITE = 0xFFFFFFFF;
    public static final String BASE64_PREFIX = "data:image/%s;base64";

//    线程同步锁的方式 - 延迟初始化
//    private static volatile QrCodeSingleton instance = null;
//    public static QrCodeSingleton getInstance() {
//        if (instance == null) {
//            synchronized(QrCodeSingleton.class) {
//                if (instance == null) {
//                    instance = new QrCodeSingleton();
//                }
//            }
//
//        }
//        return instance;
//    }

//    内部静态类的方式 - 延迟初始化
//    private static class QrCodeSingletonHolder {
//        private static QrCodeSingleton instance = new QrCodeSingleton();
//    }
//    public static QrCodeSingleton getInstance() {
//        return QrCodeSingletonHolder.instance;
//    }

//  类装载时即进行初始化
    private static QrCodeSingleton instance = new QrCodeSingleton();

    public static QrCodeSingleton getInstance() {
        return instance;
    }

    private QrCodeSingleton() {
//        处理反射方式对单例模式的破坏
        if (instance != null) {
            throw new RuntimeException();
        }
        System.out.println("QrCodeSingleton 初始化");
    }

//    解决序列化对单例模式的破坏
    private Object readResolve() {
        return instance;
    }

    private BufferedImage encode(String content, int... dimens) throws Exception {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, Constant.CHARSET_UTF8);
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, dimens[0], dimens[1], hints);
        BufferedImage bufferedImage = new BufferedImage(dimens[0], dimens[1], BufferedImage.TYPE_INT_BGR);
        for (int i = 0; i < dimens[0]; i++) {
            for (int j = 0; j < dimens[1]; j++) {
                bufferedImage.setRGB(i, j, bitMatrix.get(i, j) ? COLOR_BLACK : COLOR_WHITE);
            }
        }
        return bufferedImage;
    }

    private Result decode(BufferedImage bufferedImage) throws Exception {
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        HybridBinarizer hybridBinarizer = new HybridBinarizer(luminanceSource);
        BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, Constant.CHARSET_UTF8);
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        Result result = multiFormatReader.decode(binaryBitmap, hints);
        return result;
    }

    private int[] getDimens(int... array) {
        if (null != array && array.length > 0) {
            int[] dimens = new int[2];
            if (array.length == 1) {
                dimens[0] = array[0] > 0 ? array[0] : QR_CODE_IMAGE_DIMENS[0];
                dimens[1] = QR_CODE_IMAGE_DIMENS[1];
            } else if (array.length > 1) {
                dimens[0] = array[0] > 0 ? array[0] : QR_CODE_IMAGE_DIMENS[0];
                dimens[1] = array[1] > 0 ? array[1] : QR_CODE_IMAGE_DIMENS[1];
            }
            return dimens;
        }
        return QR_CODE_IMAGE_DIMENS;
    }

    public String encode2Base64(String content, @Nullable int... dimens) throws Exception {
        BufferedImage bufferedImage = encode(content, getDimens(dimens));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, QR_CODE_IMAGE_TYPE, byteArrayOutputStream);
        String base64Image = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        StringBuilder stringBuilder = new StringBuilder(String.format(BASE64_PREFIX, QR_CODE_IMAGE_TYPE))
                .append(",")
                .append(base64Image);
        return stringBuilder.toString();
    }

    public Result decodeFromBase64(String base64) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(String.format(BASE64_PREFIX, QR_CODE_IMAGE_TYPE)).append(",");
        byte[] bytes = Base64.getDecoder().decode(base64.replace(stringBuilder.toString(), "").trim());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
        Result result = decode(bufferedImage);
        return result;
    }

    public void encode2File(String content, @NonNull String path, @Nullable int... dimens) throws Exception {
        File file = new File(path);
        BufferedImage bufferedImage = encode(content, getDimens(dimens));
        ImageIO.write(bufferedImage, QR_CODE_IMAGE_TYPE, file);
    }

    public Result decodeFromFile(String path) throws Exception {
        File file = new File(path);
        BufferedImage bufferedImage = ImageIO.read(file);
        Result result = decode(bufferedImage);
        return result;
    }

    public static void main(String[] args) {
//        try {
//            String base64 = QrCodeSingleton.getInstance().encode2Base64("http://www.unicome.com", 300, 300);
//            Result result = QrCodeSingleton.getInstance().decodeFromBase64(base64);
//            System.out.print(result.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//         反射获取
//        try {
//            Class s = Class.forName("org.unicome.cms.singleton.QrCodeSingleton");
//            Constructor ct = s.getDeclaredConstructor();
//            ct.setAccessible(true);
//            QrCodeSingleton instance = QrCodeSingleton.getInstance();
//            System.out.println(ct.newInstance());
//            System.out.println(instance);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        序列化方式获取
//        try {
//            QrCodeSingleton instance = QrCodeSingleton.getInstance();
//
//            FileOutputStream fos = new FileOutputStream("FileInputStream.obj");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(instance);
//            oos.flush();
//            fos.close();
//            oos.close();
//
//            FileInputStream fis = new FileInputStream("FileInputStream.obj");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//
//            QrCodeSingleton instance2 = (QrCodeSingleton)ois.readObject();
//            System.out.println(instance);
//            System.out.println(instance2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        Unsafe获取
//        try {
//            QrCodeSingleton instance = QrCodeSingleton.getInstance();
//
//            Class c = Unsafe.class;
//            Constructor constructor = c.getDeclaredConstructor();
//            constructor.setAccessible(true);
//            Unsafe unsafe = (Unsafe) constructor.newInstance();
//            QrCodeSingleton instance2 = (QrCodeSingleton) unsafe.allocateInstance(QrCodeSingleton.class);
//            System.out.println(instance);
//            System.out.println(instance2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
