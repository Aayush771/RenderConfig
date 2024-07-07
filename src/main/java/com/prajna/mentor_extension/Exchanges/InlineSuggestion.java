package com.prajna.mentor_extension.Exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineSuggestion {
    Integer lineNumber;
    String text;// we just suggest an idea, not solution code
}
