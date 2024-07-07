
---

# P.R.A.G.N.A. - Proactive Review and Analysis for Guiding Next-generation Assessments

---

PRAGNA (**Sanskrit/Samskrutam for Enhanced Perception**) is a tool designed to enhance the perception of code quality and provide proactive review and analysis for guiding next-generation assessments.

## Features

- **Code Quality Assessment**: PRAGNA assesses the quality of internal code projects by analyzing various aspects such as code structure, naming conventions, cohesion, coupling, and more.
  
- **Inline Suggestions**: Provides actionable inline suggestions to improve code readability, organization, and efficiency.
  
- **CheckStyle Analysis**: Evaluates code against predefined standards and provides feedback on violations including line numbers where improvements can be made.
  
- **Summary of Strengths and Improvements**: Summarizes the strengths found in the code structure and identifies areas for improvement, helping developers focus on enhancing code reusability and commenting practices.
  
- **Dark/Light Mode Support**: Toggle between dark and light modes for comfortable viewing.

## Problem Solved

PRAGNA addresses the challenge of maintaining high code quality standards by automating the review process. It helps developers identify and rectify potential issues early in the development lifecycle, thereby reducing technical debt and improving overall software maintainability.

## Running PRAGNA

To run PRAGNA in your VS Code environment:

1. Clone the repository from [GitHub](https://github.com/Aayush771/RenderConfig).
2. Open the project folder in VS Code.
3. Launch the VS Code debugger (Ctrl+Shift+D).
4. Open the command palette (Ctrl+Shift+P) and run the command "**Pragna Scan**".
5. Follow the on-screen instructions and view the assessment results directly in the PRAGNA dashboard.

For more details, refer to the [Swagger API documentation](https://renderconfig-jjux.onrender.com/swagger-ui/index.html) of the backend hosted on Render.

---

### Request Body

```json
{
  "fileName": "MemeServiceImpl.java",
  "content": "package com.crio.starter.service;\n\nimport java.util.ArrayList;\nimport java.util.Collections;\nimport java.util.List;\nimport com.crio.starter.data.Meme;\nimport com.crio.starter.repository.MemeRepository;\nimport org.springframework.beans.factory.annotation.Autowired;\n// import org.springframework.data.mongodb.core.MongoTemplate;\nimport org.springframework.stereotype.Service;\n@Service\n\npublic class MemeServiceImpl implements MemeService {\n    private Long autoIncrement = (long) 0;\n    @Autowired\n    private MemeRepository memeRepository;\n    // @Autowired\n    // private MongoTemplate mongoTemplate;\n\n    // @Override\n    public Meme saveMeme(Meme meme) {\n       // System.out.println(meme);\n        List<Meme> memes =  memeRepository.findAll();\n        for(Meme i:memes){\n           if(i.getName().equals(meme.getName()) && i.getUrl().equals(meme.getUrl()) && i.getCaption().equals(meme.getCaption())) return null;\n        }\n        if(memes.size() == 0){\n            autoIncrement++;\n        }else {\n            autoIncrement = memes.get(memes.size()-1).getId()+1;\n        }\n         \n        meme.setId(autoIncrement);\n        Meme meme2 = memeRepository.save(meme);\n        System.out.println(meme2);\n        return meme2;\n    }\n\n    @Override\n    public List<Meme> latest100Meme() {\n        List<Meme> memes =  memeRepository.findAll();\n         if(memes.size() > 100){\n            List<Meme> memes2 = new ArrayList<>();\n            for(int i = memes.size()-1; i>memes.size()-100-1; i--){\n                memes2.add(memes.get(i));\n            }\n            \n            return memes2;\n         }\n         Collections.sort(memes, (a, b) -> Long.compare(b.getId(), a.getId()));\n\n         return memes;\n    }\n\n    @Override\n    public Meme getMemeById(Long id) {\n        // TODO Auto-generated method stub\n       Meme meme = memeRepository.getMemeById(id);\n       return meme;\n    }\n    \n}\n",
  "projectName": "BUILDOUT_XMEMEJAVA"
}
```

### Response Body

```json
{
  "status": {
    "noOfQueries": 1,
    "inlineCompletions": 13,
    "overallQuality": 80
  },
  "inlineSuggestion": [
    {
      "lineNumber": 12,
      "text": "The current implementation might lead to issues if the memeRepository.findAll() is slow. Consider using a pagination approach or indexing for improved efficiency."
    },
    {
      "lineNumber": 14,
      "text": "This check for duplicate memes can be made more efficient by using a unique index in your database or by leveraging the findByNameAndUrlAndCaption method in the MemeRepository."
    },
    {
      "lineNumber": 20,
      "text": "This approach of auto incrementing the ID could lead to potential issues in a distributed environment. Consider using a dedicated auto-increment service or the database's built-in auto-increment mechanism."
    },
    {
      "lineNumber": 28,
      "text": "Instead of looping to get the last 100 memes, consider using the Pageable interface provided by Spring Data JPA to fetch a specific page of results."
    },
    {
      "lineNumber": 31,
      "text": "The use of a for loop can be replaced with a more efficient approach using streams or the subList method for better readability."
    },
    {
      "lineNumber": 35,
      "text": "Consider using Spring Data JPA's Pageable interface or sorting functionality to achieve this more efficiently."
    },
    {
      "lineNumber": 41,
      "text": "Instead of using a for loop to fetch the last 100 memes, consider utilizing the Pageable interface provided by Spring Data JPA to retrieve a specific page of data."
    },
    {
      "lineNumber": 44,
      "text": "The current sorting logic using a for loop can be replaced with a more efficient approach using streams or the sort method for better readability."
    },
    {
      "lineNumber": 50,
      "text": "Consider checking for the null value to handle cases where the meme with the specified ID is not found. Return a specific response or handle the situation accordingly."
    }
  ],
  "checkStyle": {
    "checkboxes": [
      false,
      true,
      true,
      true,
      false,
      true,
      false,
      true,
      true,
      false
    ],
    "occuranceLineNumbers": [
      [
        10,
        15
      ],
      [],
      [],
      [],
      [
        20,
        25
      ],
      [],
      [
        30,
        35
      ],
      [],
      [],
      [
        40,
        45
      ]
    ],
    "Score": 6
  },
  "summary": {
    "strength": "The code demonstrates basic understanding of Spring Data JPA and MongoDB integration for meme management. Good use of auto-incrementing IDs and sorting logic.",
    "scopeForImprovement": "Consider implementing a better approach for handling duplicate memes and implementing efficient pagination for retrieving large datasets. Improve the robustness of the code by handling potential null values when retrieving memes by ID."
  }
}
```

### Running on Swagger

Use the above request and response bodies to run the `fetchDashboard` endpoint on Swagger. This will provide an assessment and guidance for the specified Java file in your project.

---
