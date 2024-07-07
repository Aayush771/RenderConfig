package com.prajna.mentor_extension.Exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public
class SafetyRating {
    private String category;
    private String probability;
}