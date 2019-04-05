package com.oliver.vmovier.core.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

public final class VFiles {

    public static String getAppFileDir(@NonNull Context context) {
        return context.getExternalFilesDir(null).getAbsolutePath();
    }

    public static String getAppCacheDir(@NonNull Context context) {
        return context.getExternalCacheDir().getAbsolutePath();
    }

    public static boolean exists(@NonNull String filePath) {
        return !TextUtils.isEmpty(filePath) && new File(filePath).exists();
    }

    public static boolean mkdirs(@NonNull String dirPath) {
        if (!TextUtils.isEmpty(dirPath)) {
            File dir = new File(dirPath);
            if (dir.exists()) {
                return dir.isDirectory();
            } else {
                return dir.mkdirs();
            }
        }
        return false;
    }

    public static String combine(String... parts) {
        if (null != parts && parts.length > 0) {
            StringBuilder sb = new StringBuilder(parts[0]);
            for (int i = 1; i < parts.length; ++i) {
                sb.append(File.separatorChar).append(parts[i]);
            }
            return sb.toString();
        }
        return "";
    }

    public static void writeContent(@NonNull String filePath, @NonNull String content) {
        try {
            try (FileWriter writer = new FileWriter(filePath, false)) {
                writer.write(content);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeBytes(@NonNull String filePath, @NonNull byte[] data) {
        try {
            try (FileOutputStream stream = new FileOutputStream(filePath, false)) {
                stream.write(data);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String readFile(@NonNull String filePath) {
        StringBuilder builder = new StringBuilder();
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                while (true) {
                    String line = reader.readLine();
                    if (!TextUtils.isEmpty(line)) {
                        builder.append(line);
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
