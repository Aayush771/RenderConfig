package com.prajna.mentor_extension.Exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Data
public class GeminiRequestBody {
    public SystemInstruction system_instruction;
    public List<Content> contents;
    public List<SafetySetting> safetySettings;
    public GenerationConfig generationConfig;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Part {
        public String text;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SystemInstruction {
        public Part parts;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {
        public List<Part> parts;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SafetySetting {
        public String category;
        public String threshold;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GenerationConfig {
        public List<String> stopSequences;
        public double temperature;
        public int maxOutputTokens;
        public int topK;
        public String responseMimeType;
        public Object responseSchema;
    }


    // Custom constructor to instantiate all objects recursively
    public GeminiRequestBody() {
        this.system_instruction = new SystemInstruction(new Part(""));

        this.contents = new ArrayList<>();
        this.contents.add(new Content(new ArrayList<>()));
        this.contents.get(0).parts = new ArrayList<>();
        this.contents.get(0).parts.add(new Part(""));

        this.safetySettings = new ArrayList<>();
        this.safetySettings.add(new SafetySetting());
        this.safetySettings.get(0).category = "";
        this.safetySettings.get(0).threshold = "";

        this.generationConfig = new GenerationConfig(new ArrayList<>(), 0.0, 0, 0, "",null);


    }

}
