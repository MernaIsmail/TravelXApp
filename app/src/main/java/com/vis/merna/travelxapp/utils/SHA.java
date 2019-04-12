package com.vis.merna.travelxapp.utils;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {
    public static int getIntegerID(String idToHash) {

        int generatedID = 0;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            md.update(idToHash.getBytes());

            //Get the hash's bytes
            byte[] bytes = md.digest();

            //Get complete hashed password in hex format
            generatedID = ByteBuffer.wrap(bytes).getInt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedID;
    }
}
