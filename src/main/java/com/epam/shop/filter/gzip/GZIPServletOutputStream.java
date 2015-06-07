package com.epam.shop.filter.gzip;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 06-Dec-14.
 */
public class GZIPServletOutputStream extends ServletOutputStream {

    private GZIPOutputStream gzipOutputStream = null;
    private ServletOutputStream servletOutputStream = null;
    private ByteArrayOutputStream byteArrayOutputStream;
    private HttpServletResponse response;
    private boolean useGzip;
    private String CONTENT_LENGTH = "Content-Length";
    private String CONTENT_ENCODING = "Content-Encoding";
    private String contentType;

    public GZIPServletOutputStream(HttpServletResponse response, String contentType)
            throws IOException {
        super();
        this.contentType = contentType;
        this.servletOutputStream = response.getOutputStream();
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        this.response = response;
    }

    @Override
    public void close() throws IOException {
        if (useGzip) {
            gzipOutputStream.finish();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            response.addHeader(CONTENT_LENGTH, Integer.toString(bytes.length));
            response.addHeader(CONTENT_ENCODING, "gzip");
            servletOutputStream.write(bytes);
            servletOutputStream.flush();
            servletOutputStream.close();
        } else {
            servletOutputStream.close();
        }
    }

    @Override
    public void flush() throws IOException {
        getOutputStream().flush();
    }

    @Override
    public void write(byte b[]) throws IOException {
        getOutputStream().write(b);
    }

    @Override
    public void write(byte b[], int off, int len) throws IOException {
        getOutputStream().write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        getOutputStream().write(b);
    }

    @Override
    public boolean isReady() {
        return servletOutputStream.isReady();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        throw new UnsupportedOperationException();
    }

    private OutputStream getOutputStream() throws IOException {
        if (response.getContentType() != null && response.getContentType().startsWith(contentType)) {
            useGzip = true;
            return gzipOutputStream;
        }
        return servletOutputStream;
    }
}
