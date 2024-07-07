package com.prajna.mentor_extension.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prajna.mentor_extension.DTO.FileFormat;
import com.prajna.mentor_extension.Exchanges.Dashboard;
import com.prajna.mentor_extension.Exchanges.GeminiRequestBody;
import com.prajna.mentor_extension.Exchanges.GeminiResponseBody;

@Service
public class GeminiService {

    private static String SYSTEM_INSTRUCTION = "{\n" +
    "  \"status\": {\n" +
    "    \"noOfQueries\": 1,\n" +
    "    \"inlineCompletions\": 18,\n" +
    "    \"overallQuality\": 75\n" +
    "  },\n" +
    "  \"inlineSuggestion\": [\n" +
    "    {\n" +
    "      \"lineNumber\": 15,\n" +
    "      \"text\": \"Consider using a loop for better code readability and efficiency.\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"lineNumber\": 28,\n" +
    "      \"text\": \"Maybe refactor this section for better organization.\"\n" +
    "    }\n" +
    "  ],\n" +
    "  \"checkStyle\": {\n" +
    "    \"checkboxes\": [\n" +
    "      false,\n" +
    "      true,\n" +
    "      true,\n" +
    "      true,\n" +
    "      false,\n" +
    "      true,\n" +
    "      false,\n" +
    "      true,\n" +
    "      true,\n" +
    "      false\n" +
    "    ],\n" +
    "    \"occuranceLineNumbers\": [\n" +
    "      [10, 15],\n" +
    "      [],\n" +
    "      [],\n" +
    "      [],\n" +
    "      [20, 25],\n" +
    "      [],\n" +
    "      [30, 35],\n" +
    "      [],\n" +
    "      [],\n" +
    "      [40, 45]\n" +
    "    ],\n" +
    "    \"Score\": 6\n" +
    "  },\n" +
    "  \"summary\": {\n" +
    "    \"strength\": \"Good code structure and cohesion.\",\n" +
    "    \"scopeForImprovement\": \"Improve code reusability and consider better commenting practices.\"\n" +
    "  }\n" +
    "}";

    private static String PRE_FORMAT_PROMPT = "user code:\n %s \n solution code:\n %s";
    private static String RESPONSE_MIMETYPE = "application/json";
    private static String RESPONSE_SCHEMA_STRING = "{  \"type\": \"object\",  \"properties\": {    \"status\": {      \"type\": \"object\",      \"properties\": {        \"noOfQueries\": {          \"type\": \"integer\"        },        \"inlineCompletions\": {          \"type\": \"integer\"        },        \"overallQuality\": {          \"type\": \"integer\"        }      },      \"required\": [        \"noOfQueries\",        \"inlineCompletions\",        \"overallQuality\"      ]    },    \"inlineSuggestion\": {      \"type\": \"array\",      \"items\": {        \"type\": \"object\",        \"properties\": {          \"lineNumber\": {            \"type\": \"integer\"          },          \"text\": {            \"type\": \"string\"          }        },        \"required\": [          \"lineNumber\",          \"text\"        ]      }    },    \"checkStyle\": {      \"type\": \"object\",      \"properties\": {        \"checkboxes\": {          \"type\": \"array\",          \"items\": {            \"type\": \"boolean\"          }        },        \"occuranceLineNumbers\": {          \"type\": \"array\",          \"items\": {            \"type\": \"array\",            \"items\": {              \"type\": \"integer\"            }          }        },        \"score\": {          \"type\": \"integer\"        }      },      \"required\": [        \"checkboxes\",        \"occuranceLineNumbers\",        \"score\"      ]    },    \"summary\": {      \"type\": \"object\",      \"properties\": {        \"strength\": {          \"type\": \"string\"        },        \"scopeForImprovement\": {          \"type\": \"string\"        }      },      \"required\": [        \"strength\",        \"scopeForImprovement\"      ]    }  },  \"required\": [    \"status\",    \"inlineSuggestion\",    \"checkStyle\",    \"summary\"  ]}";

    private static Double TEMPERATURE = 1.0;
    private static Integer TOP_K = 2;
    private static Integer MAX_OUTPUT_TOKENS = 100000;

    private RestTemplate restTemplate = new RestTemplate();
    private String URL_TEMPLATE = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=%s";

    private ResponseEntity<String> callGemini(String input, String geminiApiKey) throws JsonProcessingException {

        String url = String.format(URL_TEMPLATE, geminiApiKey);
        String prompt = input;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        GeminiRequestBody requestBody = new GeminiRequestBody();
        requestBody.system_instruction.parts.text = SYSTEM_INSTRUCTION;
        requestBody.contents.get(0).parts.get(0).text = prompt;
        requestBody.safetySettings.get(0).category = "HARM_CATEGORY_DANGEROUS_CONTENT";
        requestBody.safetySettings.get(0).threshold = "BLOCK_ONLY_HIGH";
        requestBody.generationConfig.responseMimeType = RESPONSE_MIMETYPE;

        ObjectMapper om = new ObjectMapper();
        Object response_schema_object = om.readValue(RESPONSE_SCHEMA_STRING, Object.class);
        requestBody.generationConfig.responseSchema = response_schema_object;

        requestBody.generationConfig.temperature = TEMPERATURE;
        requestBody.generationConfig.topK = TOP_K;
        requestBody.generationConfig.maxOutputTokens = MAX_OUTPUT_TOKENS;

        String requestJson = om.writeValueAsString(requestBody);

        System.out.println(requestJson);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return response;
    }

    public ResponseEntity<Dashboard> getDashboard(FileFormat fileFormat, String geminiApiKey) throws IOException {
        List<File> matchingFiles = FileSearcher.search(fileFormat.getFileName());
        String SolutionData = "";
        if (matchingFiles.size() > 0) {
            if (matchingFiles.size() == 1) {
                SolutionData = Files.readString(Paths.get(matchingFiles.get(0).getPath()));
            } else {
                for (File file : matchingFiles) {
                    if (file.toString().contains(fileFormat.getProjectName().toString())) {
                        SolutionData = Files.readString(Paths.get(file.getPath()));
                        break;

                    }
                }
            }
        }


        String inputForCall=String.format(PRE_FORMAT_PROMPT,fileFormat.getContent(),SolutionData);
        ResponseEntity<String> response = callGemini(inputForCall, geminiApiKey);
        if(response.getStatusCode().is2xxSuccessful()){
            ObjectMapper om = new ObjectMapper();

            GeminiResponseBody responseBody = om.readValue(response.getBody(), GeminiResponseBody.class);
            System.out.println(responseBody.toString());
            
            String responseText = responseBody.getCandidates().get(0).getContent().getParts().get(0).getText();

            Dashboard dashboard = om.readValue(responseText, Dashboard.class);

            // Dashboard dashboard = om.readValue(response.getBody(), Dashboard.class);
            return new ResponseEntity<Dashboard>(dashboard, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * public static void main(String[] args) throws JsonProcessingException {
     * 
     * GeminiService obj = new GeminiService();
     * var response = obj.callGemini("");
     * System.out.println(response);
     * }
     * //
     */
}
