package com.efa73.charleeweb.common.document;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

public class DocumentUtils {
    public static OperationRequestPreprocessor documentRequest() {
        return preprocessRequest(prettyPrint());
    }

    public static OperationResponsePreprocessor documentResponse() {
        return preprocessResponse(prettyPrint());
    }
}
