curl 'https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=' \
  -H 'Content-Type: application/json' \
  -X POST \
  -d '{ 
    "system_instruction": {
      "parts":
        { "text": "You are Neko the cat respond like one"}},
    "contents": [{
      "parts": [
        {"text": "Write a story about a magic backpack."}
      ]
    }],
    "safetySettings": [
      {
        "category": "HARM_CATEGORY_DANGEROUS_CONTENT",
        "threshold": "BLOCK_ONLY_HIGH"
      }
    ],
    "generationConfig": {
      "stopSequences": [
        "Title"
      ],
      "temperature": 1.0,
      "maxOutputTokens": 800,
      "topP": 0.8,
      "topK": 10,
      "responseMimeType": "application/json",
      "responseSchema": {
    "$schema": "http://json-schema.org/draft-07/schema#",
    "type": "object",
    "properties": {
      "status": {
        "type": "object",
        "properties": {
          "noOfQueries": {
            "type": "integer"
          },
          "inlineCompletions": {
            "type": "integer"
          },
          "overallQuality": {
            "type": "integer"
          },
          "required": ["noOfQueries", "inlineCompletions", "overallQuality"]
        }
      },
      "inlineSuggestion": {
        "type": "array",
        "items": {
          "type": "object",
          "properties": {
            "lineNumber": {
              "type": "integer"
            },
            "text": {
              "type": "string"
            }
          },
          "required": ["lineNumber", "text"]
        }
      },
      "checkStyle": {
        "type": "object",
        "properties": {
          "checkboxes": {
            "type": "array",
            "items": {
              "type": "boolean"
            }
          },
          "occuranceLineNumbers": {
            "type": "array",
            "items": {
              "type": "array",
              "items": {
                "type": "integer"
              }
            }
          },
          "Score": {
            "type": "integer"
          }
        },
        "required": ["checkboxes", "occuranceLineNumbers", "Score"]
      },
      "summary": {
        "type": "object",
        "properties": {
          "strength": {
            "type": "string"
          },
          "scopeForImprovement": {
            "type": "string"
          }
        },
        "required": ["strength", "scopeForImprovement"]
      }
    },
    "required": ["status", "inlineSuggestion", "checkStyle", "summary"]
  }
  
    }
  }' 2> /dev/null | grep "text"
