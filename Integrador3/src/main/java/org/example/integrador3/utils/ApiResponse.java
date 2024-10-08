package org.example.integrador3.utils;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private T results;

    public ApiResponse(int status,T results) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.results = results;

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T Results) {
        this.results = results;
    }
}
