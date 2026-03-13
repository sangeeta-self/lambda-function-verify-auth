package com.org.lambda.auth;

public class LambdaResponse {

    private String version;
    private ChallengeResponse response;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ChallengeResponse getResponse() {
        return response;
    }

    public void setResponse(ChallengeResponse response) {
        this.response = response;
    }

    public static class ChallengeResponse {
        private Boolean answerCorrect;

        public Boolean getAnswerCorrect() {
            return answerCorrect;
        }

        public void setAnswerCorrect(Boolean answerCorrect) {
            this.answerCorrect = answerCorrect;
        }
    }
}
