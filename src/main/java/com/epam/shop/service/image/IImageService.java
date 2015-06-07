package com.epam.shop.service.image;


import com.epam.shop.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Interface for image service
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 10-Nov-14.
 */
public interface IImageService {

    /**
     * Saving image to file on disk
     *
     * @param avatar to save from part
     * @param user   to associated with
     */
    public void saveImage(User user, MultipartFile avatar);

    /**
     * Getting image from file system to upload near user
     *
     * @param name to load
     */
    public File getImage(String subfolder, String name);
}
