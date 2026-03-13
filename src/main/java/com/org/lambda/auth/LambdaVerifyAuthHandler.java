package com.org.lambda.auth;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.HashMap;
import java.util.Map;

public class LambdaVerifyAuthHandler implements RequestHandler<Map<String, Object>, Map<String, Object>> {

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> handleRequest(Map<String, Object> event, Context context) {
         Map<String, Object> request  = (Map<String, Object>) event.get("request");
        Map<String, Object> response = (Map<String, Object>) event.getOrDefault("response", new HashMap<>());

        Map<String, String> privateParams = (Map<String, String>) request.get("privateChallengeParameters");
        String expectedOtp = privateParams != null ? privateParams.get("answer") : null;
        Object rawAnswer = request.get("challengeAnswer");
        String userAnswer = rawAnswer != null ? rawAnswer.toString() : null;
        boolean isCorrect = expectedOtp != null && expectedOtp.equals(userAnswer);
        response.put("answerCorrect", isCorrect);
        event.put("response", response);
        context.getLogger().log(String.format("Verify-Auth-Challenge | expected=%s, got=%s, ok=%s%n",
                mask(expectedOtp), mask(userAnswer), isCorrect));

        return event;
    }

    private String mask(String s) {
        if (s == null || s.length() < 2) return String.valueOf(s);
        return s.charAt(0) + "---" + s.charAt(s.length() - 1);
    }
}