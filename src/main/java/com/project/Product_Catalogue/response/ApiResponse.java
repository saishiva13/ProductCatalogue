package com.project.Product_Catalogue.response;

import org.openapitools.model.ErrorDTO;

public class ApiResponse<T> {
    private T data;
    private ErrorDTO error;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(ErrorDTO error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public ErrorDTO getError() {
        return error;
    }

    public boolean isSuccess() {
        return error == null;
    }
}