package com.epam.shop.filter.gzip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Class for Task9
 * Created by Oleh_Osyka on 06-Dec-14.
 */
public class GZIPResponseWrapper extends HttpServletResponseWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(GZIPResponseWrapper.class);
    private GZIPServletOutputStream gzipOutputStream;
    private PrintWriter writer;
    private String contentType;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response
     * @param contentType - to gzip
     * @throws IllegalArgumentException if the response is null
     */
    public GZIPResponseWrapper(HttpServletResponse response, String contentType) {
        super(response);
        this.contentType = contentType;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return getGzipOutputStream();
    }

    private GZIPServletOutputStream getGzipOutputStream() throws IOException {
        if (writer != null)
            throw new IllegalStateException("PrintWriter obtained already");
        if (gzipOutputStream == null) {
            gzipOutputStream = new GZIPServletOutputStream((HttpServletResponse) getResponse(), contentType);
        }
        return gzipOutputStream;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (this.writer != null) {
            this.writer.flush();
        }
        if (this.gzipOutputStream != null) {
            this.gzipOutputStream.flush();
        }
        super.flushBuffer();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (this.writer == null && this.gzipOutputStream != null) {
            throw new IllegalStateException(
                    "OutputStream obtained already - cannot get PrintWriter");
        }
        if (this.writer == null) {
            this.gzipOutputStream = new GZIPServletOutputStream((HttpServletResponse) getResponse(), contentType);
            this.writer = new PrintWriter(new OutputStreamWriter(
                    this.gzipOutputStream, getResponse().getCharacterEncoding()));
        }
        return this.writer;
    }

    public void close() throws IOException {
        if (this.writer != null) {
            this.writer.close();
        }

        if (this.gzipOutputStream != null) {
            this.gzipOutputStream.close();
        }
    }
}
