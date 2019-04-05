package com.oliver.vmovier.core.download.inner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import android.support.annotation.NonNull;
import com.oliver.vmovier.core.download.DownloadConfig;
import com.oliver.vmovier.core.download.DownloadSubject;
import com.oliver.vmovier.core.utils.VFiles;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadExecutor {

    private AtomicInteger mSeed;
    private String mCacheDirPath;
    private ThreadPoolExecutor mExecutor;

    public DownloadExecutor(@NonNull DownloadConfig config) {
        mSeed = new AtomicInteger();
        mCacheDirPath = config.getCacheDirPath();
        VFiles.mkdirs(mCacheDirPath);
        mExecutor = new ThreadPoolExecutor(1, config.getMaxThreadSize(),
            60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                String name = String.format(Locale.US, "DownloadExecutor_%d", mSeed.incrementAndGet());
                return new Thread(r, name);
            }
        });
        mExecutor.allowCoreThreadTimeOut(true);
    }

    public void submit(@NonNull final DownloadSubject subject) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                download(subject);
            }
        });
    }

    private void download(DownloadSubject subject) {
        try {
            try (Response response = sendHttpRequest(subject);
                 ResponseBody body = response.body()) {
                if (null != body) {
                    try (InputStream inputStream = body.byteStream()) {
                        writeFile(subject, inputStream, body.contentLength());
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Response sendHttpRequest(@NonNull DownloadSubject subject) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .build();
        Request request = new Request.Builder()
            .url(subject.getTask().getUrl())
            .build();
        return okHttpClient.newCall(request).execute();
    }

    private void writeFile(@NonNull DownloadSubject subject, InputStream inputStream, long totalSize) {
        String filePath = VFiles.combine(subject.getTask().getDirPath(), subject.getTask().getFileName());
        byte[] data = new byte[4096];
        try {
            try (FileOutputStream outputStream = new FileOutputStream(filePath, false)) {
                int written = 0;
                int length;
                while ((length = inputStream.read(data)) > 0) {
                    outputStream.write(data, 0, length);
                    written += length;
                    subject.notifyOnProgress((int)(written/totalSize * 100));
                }
                subject.notifyOnComplete();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
