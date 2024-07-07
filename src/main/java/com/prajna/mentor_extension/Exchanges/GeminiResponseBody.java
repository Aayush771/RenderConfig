package com.prajna.mentor_extension.Exchanges;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeminiResponseBody {
    private List<Candidate> candidates;
    private UsageMetadata usageMetadata;

  
}
