package com.liamnbtech.server.file;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.EnumSet;

/**
 * Implementation of LocalFileService that tries to change file/directory permissions in order to obtain the desired
 * input and/or output streams.
 */
@Service
public class LocalFileServiceImpl implements LocalFileService {

    @Override
    public File getFile(String localFilePath) throws IOException {
        File localFile = new File(localFilePath);

        Files.setPosixFilePermissions(localFile.getAbsoluteFile().toPath(),
                EnumSet.of(PosixFilePermission.OWNER_READ,
                        PosixFilePermission.OWNER_WRITE,
                        PosixFilePermission.OWNER_EXECUTE,
                        PosixFilePermission.GROUP_READ,
                        PosixFilePermission.GROUP_WRITE,
                        PosixFilePermission.GROUP_EXECUTE));

        return localFile;
    }

    @Override
    public InputStream getFileInputStream(String localFilePath) throws IOException {
        File localFile = getFile(localFilePath);

        return new BufferedInputStream(new FileInputStream(localFile));
    }

    @Override
    public OutputStream getFileOutputStream(String localFilePath, boolean append) throws IOException {
        File localFile = getFile(localFilePath);

        if (!localFile.exists()) {
            boolean success = localFile.createNewFile();
        } else if (!append){
            boolean deletionSuccess = localFile.delete();
            boolean recreationSuccess = localFile.createNewFile();
        }

        return new BufferedOutputStream(new FileOutputStream(localFile));
    }

    @Override
    public boolean deleteFile(String localFilePath) throws IOException {
        File localFile = getFile(localFilePath);

        return localFile.delete();
    }
}
