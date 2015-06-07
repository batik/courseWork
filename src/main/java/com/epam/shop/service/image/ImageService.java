package com.epam.shop.service.image;

import com.epam.shop.domain.User;
import com.epam.shop.exception.ImageProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Avatar service implementation
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 10-Nov-14.
 */
@Component
public class ImageService implements IImageService {
    public static final String FORMAT = "jpg";
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);
    private static final int IMG_WIDTH = 150;
    private static final int IMG_HEIGHT = 150;
    private final String folderPath;

    @Autowired
    public ImageService(@Value("${image.defaultFolder}") String folderPath) {
        this.folderPath = folderPath + File.separator;
        File fileSaveDir = new File(folderPath);
        if (!fileSaveDir.exists()) {
            if (!fileSaveDir.mkdirs()) {
                LOGGER.warn("Folder wasn't create");
                throw new ImageProcessException("Unexpected result when creating folder");
            }
        }
    }

    private static void resizeImage(String avatar) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(avatar));
        BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, originalImage.getType());
        Graphics2D g = image.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        ImageIO.write(image, FORMAT, new File(avatar));
    }

    @Override
    public void saveImage(User user, MultipartFile avatar) {
        String avatarName = generateAvatarName(user);
        String avatarFolderPath = folderPath + "avatars\\";
        File folder = new File(avatarFolderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String path = avatarFolderPath + avatarName;
        writeAvatarOnDisk(avatar, path);
        LOGGER.debug("Image saved to {} ", path);
    }

    private void writeAvatarOnDisk(MultipartFile avatar, String filePath) {
        try {
            File fileForImage = new File(filePath);
            if (!fileForImage.exists()) {
                fileForImage.createNewFile();
            }
            avatar.transferTo(fileForImage);
            resizeImage(filePath);
        } catch (IOException e) {
            LOGGER.error("Image can't be written on disk", e);
            throw new ImageProcessException("Image can't be save", e);
        }
    }

    private String generateAvatarName(User user) {
        return user.getLogin() + "." + FORMAT;
    }

    @Override
    public File getImage(String subfolder, String name) {
        return new File(folderPath + subfolder + name + "." + FORMAT);
    }
}
